package rush;
import battlecode.common.*;
import java.util.ArrayList;
import java.util.Iterator;

public strictfp class RobotPlayer {
    static RobotController rc;

    static final RobotType[] spawnableRobot = {
        RobotType.POLITICIAN,
        RobotType.SLANDERER,
        RobotType.MUCKRAKER,
    };

    static final Direction[] directions = {
        Direction.NORTH,
        Direction.NORTHEAST,
        Direction.EAST,
        Direction.SOUTHEAST,
        Direction.SOUTH,
        Direction.SOUTHWEST,
        Direction.WEST,
        Direction.NORTHWEST,
    };

    static int turnCount;

    static MapLocation hqLoc = null; // enlightenment center where this robot was spawned
    static int hqID = -1;
    static MapLocation enemyHqLoc = null; // there could be multiple of these; for now, just stick with one
    static boolean enemyHqCaptured = false; // switch to true when enemy HQ falls

    static int flag = 0;
    /**
     * HQ Flag codes:
     * 0: unset
     * 1-65535: location of enemy HQ
     * 65536-131071: location of captured HQ (that was formerly enemy HQ)
     **/
    
    // for HQ, list of IDs of spawned units
    static ArrayList<Integer> spawnedIDs = new ArrayList<Integer>();

    /**
     * run() is the method that is called when a robot is instantiated in the Battlecode world.
     * If this method returns, the robot dies!
     **/
    @SuppressWarnings("unused")
    public static void run(RobotController rc) throws GameActionException {

        // This is the RobotController object. You use it to perform actions from this robot,
        // and to get information on its current status.
        RobotPlayer.rc = rc;

        turnCount = 0;

        System.out.println("I'm a " + rc.getType() + " and I just got created!");
        /*
        if (rc.getType() == RobotType.ENLIGHTENMENT_CENTER && rc.getRoundNum() > 1)
            rc.setFlag(256*256); // this was a captured enlightnment center
        */
        while (true) {
            turnCount += 1;
            // Try/catch blocks stop unhandled exceptions, which cause your robot to freeze
            try {
                // Here, we've separated the controls into a different method for each RobotType.
                // You may rewrite this into your own control structure if you wish.
                System.out.println("I'm a " + rc.getType() + "! Location " + rc.getLocation());
                switch (rc.getType()) {
                    case ENLIGHTENMENT_CENTER: runEnlightenmentCenter(); break;
                    case POLITICIAN:           runPolitician();          break;
                    case SLANDERER:            runSlanderer();           break;
                    case MUCKRAKER:            runMuckraker();           break;
                }

                // Clock.yield() makes the robot wait until the next turn, then it will perform this loop again
                Clock.yield();

            } catch (Exception e) {
                System.out.println(rc.getType() + " Exception");
                e.printStackTrace();
            }
        }
    }

    static void runEnlightenmentCenter() throws GameActionException {
        RobotType toBuild = RobotType.POLITICIAN;
        if (rc.getInfluence() < 100)
            toBuild = RobotType.MUCKRAKER;
        int influence = Math.max(50, rc.getInfluence() - 100);
        if (toBuild == RobotType.SLANDERER) influence = (influence/20) * 20;
        else if (toBuild == RobotType.MUCKRAKER) influence = 1;
        Direction dir1 = randomDirection();
        if (rc.canBuildRobot(toBuild, dir1, influence)) {
            rc.buildRobot(toBuild, dir1, influence);
            spawnedIDs.add(rc.senseRobotAtLocation(rc.getLocation().add(dir1)).ID);
        }
        for (Direction dir : directions) {
            if (rc.canBuildRobot(toBuild, dir, influence)) {
                rc.buildRobot(toBuild, dir, influence);
                spawnedIDs.add(rc.senseRobotAtLocation(rc.getLocation().add(dir)).ID);
            } else {
                break;
            }
        }
        // check to see if any spawned robots have found the enemy HQ
        if (flag == 0 || flag > 65536) {
            System.out.println("Flag is 0, looking for other flags");
            Iterator<Integer> it = spawnedIDs.iterator();
            while (it.hasNext()) {
                int id = (Integer) it.next();
                System.out.println("Checking ID " + id);
                if (rc.canGetFlag(id)) {
                    int readFlag = rc.getFlag(id);
                    System.out.println(readFlag);
                    if (readFlag > 0 && readFlag < 65536) {
                        flag = readFlag;
                        rc.setFlag(flag);
                        System.out.println("Flag set to " + flag);
                    }
                } else {
                    it.remove();
                }
            }
        } else {
            System.out.println("Flag is set, looking for capture flags");
            Iterator<Integer> it = spawnedIDs.iterator();
            while (it.hasNext()) {
                int id = (Integer) it.next();
                System.out.println("Checking ID " + id);
                if (rc.canGetFlag(id)) {
                    int readFlag = rc.getFlag(id);
                    System.out.println(readFlag);
                    if (readFlag > 65536) {
                        flag = readFlag;
                        rc.setFlag(flag);
                        System.out.println("Flag set to " + flag);
                    }
                } else {
                    it.remove();
                }
            }
        }
    }

    static void runPolitician() throws GameActionException {
        // set hqLoc if not set already
        if (hqLoc == null) {
            RobotInfo [] possibleHQ = rc.senseNearbyRobots(2, rc.getTeam());
            for (RobotInfo r : possibleHQ)
                if (r.type == RobotType.ENLIGHTENMENT_CENTER) {
                    hqLoc = r.getLocation();
                    hqID = r.getID();
                    System.out.println("Found the HQ!");
                }
        }
        Team enemy = rc.getTeam().opponent();
        // set enemyHqLoc if not set already
        if (enemyHqLoc == null) {
            // try to see enemy HQ if it's visible
            RobotInfo [] possibleEnemyHQ = rc.senseNearbyRobots(-1, enemy);
            for (RobotInfo r : possibleEnemyHQ)
                if (r.type == RobotType.ENLIGHTENMENT_CENTER) {
                    enemyHqLoc = r.getLocation();
                    // set flag to signal this
                    if (hqLoc != null) {
                        int dx = enemyHqLoc.x - hqLoc.x;
                        int dy = enemyHqLoc.y - hqLoc.y;
                        flag = (dx + 128) * 256 + (dy + 128);
                        rc.setFlag(flag);
                    }
                }
        } else {
            // check if enemy HQ has already been captured
            if (rc.canSenseLocation(enemyHqLoc) && rc.senseRobotAtLocation(enemyHqLoc).team == rc.getTeam()) {
                enemyHqCaptured = true;
                int dx = enemyHqLoc.x - hqLoc.x;
                int dy = enemyHqLoc.y - hqLoc.y;
                enemyHqLoc = null;
                flag = (dx + 128) * 256 + (dy + 128) + 65536;
                rc.setFlag(flag);
            }
        }
        // try to read from home HQ flag
        if (hqLoc != null) {
            if (rc.canGetFlag(hqID)) {
                int hqFlag = rc.getFlag(hqID);
                if (enemyHqLoc == null && hqFlag != 0 && hqFlag < 256*256) {
                    enemyHqLoc = hqLoc.translate(hqFlag / 256 - 128, hqFlag % 256 - 128);
                    System.out.println("Read enemy HQ loc from home HQ!");
                } else if (enemyHqLoc != null && hqFlag > 65536) {
                    enemyHqLoc = null;
                    System.out.println("Home HQ says that enemy HQ is captured, resetting enemyHqLoc to null!");
                }
            }
        }
        int actionRadius = rc.getType().actionRadiusSquared;
        RobotInfo[] attackable = rc.senseNearbyRobots(actionRadius, enemy);
        boolean enemyHQInRange = false;
        for (RobotInfo r : attackable)
            enemyHQInRange |= (r.type == RobotType.ENLIGHTENMENT_CENTER);
        // only attack enemy HQ (don't waste time with other robots) unless empower factor > 1 or no enemy HQ found
        // zeroth order navigation strategy: move away from HQ
        if (enemyHqLoc != null && rc.getLocation().distanceSquaredTo(enemyHqLoc) > 2 && fuzzyTryMove(rc.getLocation().directionTo(enemyHqLoc)))
            System.out.println("Moved towards enemy HQ!");
        else if ((enemyHQInRange || attackable.length > 0 && (rc.getEmpowerFactor(rc.getTeam(), 0) > 1 || enemyHqLoc == null))
            && rc.canEmpower(actionRadius) && rc.getConviction() > 10) {
            // attack either enemy HQ or farthest enemy in range
            int attackLength = 1;
            for (RobotInfo r : attackable) {
                if (r.getLocation().distanceSquaredTo(rc.getLocation()) > attackLength)
                    attackLength = r.getLocation().distanceSquaredTo(rc.getLocation());
            }
            if (enemyHQInRange) attackLength = rc.getLocation().distanceSquaredTo(enemyHqLoc);
            System.out.println("empowering...");
            rc.empower(attackLength);
            System.out.println("empowered");
            return;
        }
        else if (hqLoc != null && fuzzyTryMove(hqLoc.directionTo(rc.getLocation())))
            System.out.println("Moved away from HQ!");
        else if (fuzzyTryMove(randomDirection()))
            System.out.println("I moved!");
    }

    static void runSlanderer() throws GameActionException {
        if (tryMove(randomDirection()))
            ;
            //System.out.println("I moved!");
    }

    static void runMuckraker() throws GameActionException {
        // set hqLoc if not set already
        if (hqLoc == null) {
            RobotInfo [] possibleHQ = rc.senseNearbyRobots(2, rc.getTeam());
            for (RobotInfo r : possibleHQ)
                if (r.type == RobotType.ENLIGHTENMENT_CENTER) {
                    hqLoc = r.getLocation();
                    hqID = r.getID();
                    System.out.println("Found the HQ!");
                }
        }
        Team enemy = rc.getTeam().opponent();
        // set enemyHqLoc if not set already
        if (enemyHqLoc == null) {
            // try to see enemy HQ if it's visible
            RobotInfo [] possibleEnemyHQ = rc.senseNearbyRobots(-1, enemy);
            for (RobotInfo r : possibleEnemyHQ)
                if (r.type == RobotType.ENLIGHTENMENT_CENTER) {
                    enemyHqLoc = r.getLocation();
                    // set flag to signal this
                    if (hqLoc != null) {
                        int dx = enemyHqLoc.x - hqLoc.x;
                        int dy = enemyHqLoc.y - hqLoc.y;
                        flag = (dx + 128) * 256 + (dy + 128);
                        rc.setFlag(flag);
                    }
                }
        } else {
            // check if enemy HQ has already been captured
            if (rc.canSenseLocation(enemyHqLoc) && rc.senseRobotAtLocation(enemyHqLoc).team == rc.getTeam()) {
                enemyHqCaptured = true;
                int dx = enemyHqLoc.x - hqLoc.x;
                int dy = enemyHqLoc.y - hqLoc.y;
                enemyHqLoc = null;
                flag = (dx + 128) * 256 + (dy + 128) + 65536;
                rc.setFlag(flag);
            }
        }
        // try to read from home HQ flag
        if (hqLoc != null) { 
            if (rc.canGetFlag(hqID)) { 
                int hqFlag = rc.getFlag(hqID);
                if (enemyHqLoc == null && hqFlag != 0 && hqFlag < 256*256) {
                    enemyHqLoc = hqLoc.translate(hqFlag / 256 - 128, hqFlag % 256 - 128);
                    System.out.println("Read enemy HQ loc from home HQ!");
                } else if (enemyHqLoc != null && hqFlag > 65536) {
                    enemyHqLoc = null;
                    System.out.println("Home HQ says that enemy HQ is captured, resetting enemyHqLoc to null!");
                } 
            } 
        } 
        int actionRadius = rc.getType().actionRadiusSquared;
        for (RobotInfo robot : rc.senseNearbyRobots(actionRadius, enemy)) {
            if (robot.type.canBeExposed()) {
                // It's a slanderer... go get them!
                if (rc.canExpose(robot.location)) {
                    System.out.println("e x p o s e d");
                    rc.expose(robot.location);
                    return;
                }
            }
        }
        // If you see an enemy slanderer, move towards them
        int sensorRadius = rc.getType().sensorRadiusSquared;
        for (RobotInfo robot : rc.senseNearbyRobots(sensorRadius, enemy)) {
            if (robot.type.canBeExposed()) {
                fuzzyTryMove(rc.getLocation().directionTo(robot.location));
            }
        }
        // zeroth order navigation strategy: move away from HQ
        if (enemyHqLoc != null && fuzzyTryMove(rc.getLocation().directionTo(enemyHqLoc)))
            System.out.println("Moved towards enemy HQ!");
        else if (hqLoc != null && fuzzyTryMove(hqLoc.directionTo(rc.getLocation())))
            System.out.println("Moved away from HQ!");
        else if (fuzzyTryMove(randomDirection()))
            System.out.println("I moved!");
    }

    /**
     * Returns a random Direction.
     *
     * @return a random Direction
     */
    static Direction randomDirection() {
        return directions[(int) (Math.random() * directions.length)];
    }

    /**
     * Returns a random spawnable RobotType
     *
     * @return a random RobotType
     */
    static RobotType randomSpawnableRobotType() {
        return spawnableRobot[(int) (Math.random() * spawnableRobot.length)];
    }

    /**
     * Attempts to move in a given direction.
     *
     * @param dir The intended direction of movement
     * @return true if a move was performed
     * @throws GameActionException
     */
    static boolean tryMove(Direction dir) throws GameActionException {
        //System.out.println("I am trying to move " + dir + "; " + rc.isReady() + " " + rc.getCooldownTurns() + " " + rc.canMove(dir));
        if (rc.canMove(dir)) {
            rc.move(dir);
            return true;
        } else return false;
    }

    // try to move in or near a given direction
    static boolean fuzzyTryMove(Direction dir) throws GameActionException {
        //System.out.println("I am trying to move " + dir + "; " + rc.isReady() + " " + rc.getCooldownTurns() + " " + rc.canMove(dir));
        if (rc.canMove(dir)) {
            rc.move(dir);
            return true;
        } else if (rc.canMove(dir.rotateLeft())) {
            rc.move(dir.rotateLeft());
            return true;
        } else if (rc.canMove(dir.rotateRight())) {
            rc.move(dir.rotateRight());
            return true;
        } else return false;
    }
}