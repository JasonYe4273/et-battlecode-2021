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

    static Team me;
    static Team enemy;

    static int turnCount;
    static int currConviction;
    static int currInfluence;
    static MapLocation currLocation;
    static RobotType currType;
    static RobotInfo[] nearbyRobots;

    static MapLocation hqLoc = null; // enlightenment center where this robot was spawned
    static int hqID = -1;
    static MapLocation enemyHqLoc = null; // there could be multiple of these; for now, just stick with one
    static boolean enemyHqCaptured = false; // switch to true when enemy HQ falls

    static int flag = 0;
    static int flagSetTurn = 0;

    static final int CAPTURED_HQ_FLAG = 65536;
    /**
     * HQ Flag codes:
     * 0: unset
     * 1-65535: location of enemy HQ
     * 65536-131071: location of captured HQ (that was formerly enemy HQ)
     **/
    
    // for HQ, list of IDs of spawned units
    // for non-HQ, ID of HQ
    static ArrayList<Integer> flagsToRead = new ArrayList<Integer>();

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
        currInfluence = 0;
        me = rc.getTeam();
        enemy = me.opponent();
        if (rc.getType() == ENLIGHTENMENT_CENTER) {
            hqID = rc.getID();
        }

        while (true) {
            turnCount += 1;
            currConviction = rc.getConviction();
            currInfluence = rc.getInfluence();
            currLocation = rc.getLocation();
            currType = rc.getType();
            nearbyRobots = rc.senseNearbyRobots();
            attackable = rc.senseNearbyRobots(currType.actionRadiusSquared, enemy);

            // Try/catch blocks stop unhandled exceptions, which cause your robot to freeze
            try {
                // Here, we've separated the controls into a different method for each RobotType.
                // You may rewrite this into your own control structure if you wish.
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

    static void setFlag(int f) {
        if (flag != f && rc.canSetFlag(f)) {
            flag = f;
            flagSetTurn = turnCount;
            rc.setFlag(f);
        }
    }

    static int locToFlag(MapLocation loc) {
        if (hqLoc == null) return 0;
        return (loc.x - hqLoc.x + 128) * 256 + (loc.y - hqLoc.y + 128);
    }

    static MapLocation flagToLoc(int f) {
        if (hqLoc == null) return null;
        return hqLoc.translate(f / 256 - 128, f % 256 - 128);
    }

    static void findMyHQ() throws GameActionException {
        RobotInfo [] possibleHQ = rc.senseNearbyRobots(2, me);
        for (RobotInfo r : possibleHQ) {
            if (r.type == RobotType.ENLIGHTENMENT_CENTER) {
                hqLoc = r.getLocation();
                hqID = r.getID();
                flagsToRead.add(hqID);
                System.out.println("Found the HQ!");
            }
        }
    }

    static void findEnemyHQ() throws GameActionException {
        // try to see enemy HQ if it's visible
        if (enemyHqLoc == null) {
            for (RobotInfo r : nearbyRobots) {
                if (r.type == RobotType.ENLIGHTENMENT_CENTER && r.team == enemy) {
                    enemyHqLoc = r.getLocation();
                    setFlag(locToFlag(enemyHqLoc));
                    return;
                }
            }
        } else if (rc.canSenseLocation(enemyHqLoc) && rc.senseRobotAtLocation(enemyHqLoc).team == me) {
            enemyHqCaptured = true;
            enemyHqLoc = null;
            setFlag(locToFlag(enemyHqLoc) + CAPTURED_HQ_FLAG);
            return;
        }

        if (enemyHqLoc == null) {
            System.out.println("No enemy HQ, looking for other flags");
            Iterator<Integer> it = flagsToRead.iterator();
            while (it.hasNext()) {
                int id = (Integer) it.next();
                System.out.println("Checking ID " + id);
                if (rc.canGetFlag(id)) {
                    int readFlag = rc.getFlag(id);
                    System.out.println(readFlag);
                    if (readFlag > 0 && readFlag < CAPTURED_HQ_FLAG) {
                        if (currType == RobotType.ENLIGHTENMENT_CENTER) setFlag(flag);
                        enemyHqLoc = flagToLoc(flag);
                    }
                } else {
                    it.remove();
                }
            }
        } else {
            Iterator<Integer> it = flagsToRead.iterator();
            while (it.hasNext()) {
                int id = (Integer) it.next();
                System.out.println("Checking ID " + id);
                if (rc.canGetFlag(id)) {
                    int readFlag = rc.getFlag(id);
                    System.out.println(readFlag);
                    if (readFlag == flag + CAPTURED_HQ_FLAG) {
                        if (currType == RobotType.ENLIGHTENMENT_CENTER) setFlag(flag);
                        enemyHqLoc = null;
                        return;
                    }
                } else {
                    it.remove();
                }
            }
        }
    }

    static void runEnlightenmentCenter() throws GameActionException {
        findEnemyHQ();

        RobotType toBuild = RobotType.POLITICIAN;
        if (currInfluence < 100)
            toBuild = RobotType.MUCKRAKER;
        int influence = Math.max(50, currInfluence - 100);
        if (toBuild == RobotType.SLANDERER) influence = (influence/20) * 20;
        else if (toBuild == RobotType.MUCKRAKER) influence = 1;

        Direction dir1 = randomDirection();
        if (enemyHqLoc != null) {
            dir1 = hqLoc.directionTo(enemyHqLoc);
        }
        if (rc.canBuildRobot(toBuild, dir1, influence)) {
            rc.buildRobot(toBuild, dir1, influence);
            flagsToRead.add(rc.senseRobotAtLocation(rc.getLocation().add(dir1)).ID);
        }

        for (Direction dir : directions) {
            if (rc.canBuildRobot(toBuild, dir, influence)) {
                rc.buildRobot(toBuild, dir, influence);
                flagsToRead.add(rc.senseRobotAtLocation(rc.getLocation().add(dir)).ID);
            } else {
                break;
            }
        }
    }

    static void runPolitician() throws GameActionException {
        if (hqLoc == null) findMyHQ();

        findEnemyHQ();

        int actionRadius = rc.getType().actionRadiusSquared;
        boolean enemyHQInRange = false;
        for (RobotInfo r : attackable)
            enemyHQInRange |= (r.type == RobotType.ENLIGHTENMENT_CENTER);
        // only attack enemy HQ (don't waste time with other robots) unless empower factor > 1 or no enemy HQ found
        // zeroth order navigation strategy: move away from HQ
        if (enemyHqLoc != null && rc.getLocation().distanceSquaredTo(enemyHqLoc) > 2 && fuzzyTryMove(rc.getLocation().directionTo(enemyHqLoc)))
            System.out.println("Moved towards enemy HQ!");
        else if ((enemyHQInRange || attackable.length > 0 && (rc.getEmpowerFactor(me, 0) > 1 || enemyHqLoc == null))
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
        if (hqLoc == null) findMyHQ();

        findEnemyHQ();
        
        tryMove(randomDirection());
    }

    static void runMuckraker() throws GameActionException {
        if (hqLoc == null) findMyHQ();

        findEnemyHQ();

        int actionRadius = rc.getType().actionRadiusSquared;
        for (RobotInfo r : attackable) {
            if (r.type.canBeExposed()) {
                // It's a slanderer... go get them!
                if (rc.canExpose(r.location)) {
                    System.out.println("e x p o s e d");
                    rc.expose(r.location);
                    return;
                }
            }
        }
        // If you see an enemy slanderer, move towards them
        int sensorRadius = rc.getType().sensorRadiusSquared;
        for (RobotInfo r : nearbyRobots) {
            if (r.team == enemy && r.type.canBeExposed()) {
                fuzzyTryMove(rc.getLocation().directionTo(r.location));
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
        if (rc.canMove(dir)) {
            rc.move(dir);
            return true;
        } else return false;
    }

    // try to move in or near a given direction
    static boolean fuzzyTryMove(Direction dir) throws GameActionException {
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
