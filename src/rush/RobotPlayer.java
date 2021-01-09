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
     * 132000-132007: moved in directions[i] from HQ and hit edge of map without finding enemy HQ
     * 140000-160000: minimum of x-coordinate of map
     * 160000-180000: maximum of x-coordinate of map
     * 180001-200001: minimum of y-coordinate of map
     * 200000-220000: maximum of y-coordinate of map
     **/

    // directions already explored
    static boolean [] dirsExplored = {false, false, false, false, false, false, false, false};

    // min and max coordinates of map
    static int mapMinX = 0;
    static int mapMaxX = 0;
    static int mapMinY = 0;
    static int mapMaxY = 0;
    
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
                // System.out.println("I'm a " + rc.getType() + "! Location " + rc.getLocation());
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
        System.out.println("Knowledge of map: " + mapMinX + " " + mapMaxX + " " + mapMinY + " " + mapMaxY);
        // check to see if any spawned robots have found the enemy HQ
        if (flag == 0 || flag > 65536) {
            System.out.println("Flag is 0, looking for other flags");
            Iterator<Integer> it = spawnedIDs.iterator();
            while (it.hasNext()) {
                int id = (Integer) it.next();
                // System.out.println("Checking ID " + id);
                if (rc.canGetFlag(id)) {
                    int readFlag = rc.getFlag(id);
                    // System.out.println(readFlag);
                    if (readFlag > 0 && readFlag < 65536) {
                        flag = readFlag;
                        rc.setFlag(flag);
                        System.out.println("Flag set to " + flag);
                    } else if (readFlag > 131072) {
                        // try to get min and max coordinates of map
                        if (readFlag >= 132000 && readFlag <= 132007) dirsExplored[readFlag - 132000] = true;
                        else if (readFlag >= 140000 && readFlag < 160000) mapMinX = readFlag - 130000;
                        else if (readFlag > 160000 && readFlag <= 180000) mapMaxX = readFlag - 150000;
                        else if (readFlag >= 180001 && readFlag < 200001) mapMinY = readFlag - 170001;
                        else if (readFlag > 200000 && readFlag <= 220000) mapMaxY = readFlag - 190000;
                        // if we know all the map coordinates, maybe we can figure out where the enemy HQ is
                        if (mapMinX > 0 && mapMinY > 0 && mapMaxX > 0 && mapMaxY > 0) {
                            int mapX = mapMaxX - mapMinX + 1;
                            int mapY = mapMaxY - mapMinY + 1;
                            // assuming rotational symmetry, find enemy X and Y coordinates
                            int enemyX, enemyY;
                            hqLoc = rc.getLocation();
                            enemyX = mapMinX + (mapMaxX - hqLoc.x);
                            enemyY = mapMinY + (mapMaxY - hqLoc.y);
                            System.out.println("Enemy HQ location: " + enemyX + " " + enemyY);
                            // if mapX != mapY, the map is probably rotationally symmetric
                            if (flag == 0 && mapX != mapY) {
                                enemyHqLoc = new MapLocation(enemyX, enemyY);
                                int dx = enemyHqLoc.x - hqLoc.x;
                                int dy = enemyHqLoc.y - hqLoc.y;
                                flag = (dx + 128) * 256 + (dy + 128);
                                rc.setFlag(flag);
                            } else if (mapX == mapY) {
                                // map is probably reflectionally symmetric (although this is not guaranteed)
                                // TODO: Rule out rotational symmetry explicitly
                                // if N and S are explored, then map has symmetry in x direction
                                if (dirsExplored[0] && dirsExplored[4]) enemyHqLoc = new MapLocation(enemyX, hqLoc.y);
                                else if (dirsExplored[2] && dirsExplored[6]) enemyHqLoc = new MapLocation(hqLoc.x, enemyY);
                                if (enemyHqLoc != null) {
                                    int dx = enemyHqLoc.x - hqLoc.x;
                                    int dy = enemyHqLoc.y - hqLoc.y;
                                    flag = (dx + 128) * 256 + (dy + 128);
                                    rc.setFlag(flag);
                                }
                            }
                        }
                    }
                } else {
                    it.remove();
                }
            }
        } else {
            // System.out.println("Flag is set, looking for capture flags");
            Iterator<Integer> it = spawnedIDs.iterator();
            while (it.hasNext()) {
                int id = (Integer) it.next();
                // System.out.println("Checking ID " + id);
                if (rc.canGetFlag(id)) {
                    int readFlag = rc.getFlag(id);
                    // System.out.println(readFlag);
                    if (readFlag > 65536 && readFlag < 131072) {
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
            if (rc.canSenseLocation(enemyHqLoc)
                && (rc.senseRobotAtLocation(enemyHqLoc) == null || rc.senseRobotAtLocation(enemyHqLoc).team == rc.getTeam())) {
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
        if (flag == 0 || flag >= 132000 && flag <= 132007) checkEdges();
        int actionRadius = rc.getType().actionRadiusSquared;
        RobotInfo[] attackable = rc.senseNearbyRobots(actionRadius, enemy);
        boolean enemyHQInRange = false;
        for (RobotInfo r : attackable)
            enemyHQInRange |= (r.type == RobotType.ENLIGHTENMENT_CENTER);
        // only attack enemy HQ (don't waste time with other robots) unless empower factor > 1 or no enemy HQ found
        // zeroth order navigation strategy: move away from HQ
        if (enemyHqLoc != null && rc.getLocation().distanceSquaredTo(enemyHqLoc) > 2)
            if (rc.getConviction() > 10) 
                if (fuzzyTryMove(rc.getLocation().directionTo(enemyHqLoc))) return;
            else if (fuzzyTryMove(enemyHqLoc.directionTo(rc.getLocation()))) return; // move away from enemy HQ if conviction <= 10 (worthless)
        if ((enemyHQInRange || attackable.length > 0 && (rc.getEmpowerFactor(rc.getTeam(), 0) > 1 || enemyHqLoc == null))
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
            ;
            //System.out.println("Moved away from HQ!");
        else if (fuzzyTryMove(randomDirection()))
            ;
            //System.out.println("I moved!");
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
            // TODO: Figure out what to do if enemyHqLoc is empty (probably requires a flag)
            if (rc.canSenseLocation(enemyHqLoc)
                && (rc.senseRobotAtLocation(enemyHqLoc) == null || rc.senseRobotAtLocation(enemyHqLoc).team == rc.getTeam())) {
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
        if (flag == 0 || flag >= 132000 && flag <= 132007) checkEdges();
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
        if (enemyHqLoc != null) {
            // if enemy HQ is already swarmed, don't get any closer
            if (rc.senseNearbyRobots(-1, rc.getTeam()).length > 40) // && rc.getLocation().distanceSquaredTo(enemyHqLoc) <= 5)
                fuzzyTryMove(enemyHqLoc.directionTo(rc.getLocation()));
            else
                fuzzyTryMove(rc.getLocation().directionTo(enemyHqLoc));
            //System.out.println("Moved towards enemy HQ!");
        // zeroth order navigation strategy: move away from HQ
        }
        else if (hqLoc != null && fuzzyTryMove(hqLoc.directionTo(rc.getLocation())))
            ;
            //System.out.println("Moved away from HQ!");
        else if (fuzzyTryMove(randomDirection()))
            ;
            //System.out.println("I moved!");
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
        } else if (rc.canMove(dir.rotateLeft().rotateLeft())) {
            rc.move(dir.rotateLeft().rotateLeft());
            return true;
        } else if (rc.canMove(dir.rotateRight().rotateRight())) {
            rc.move(dir.rotateRight().rotateRight());
            return true;
        } else return false;
    }

    // map edge checker
    static boolean checkEdges() throws GameActionException {
        // first iteration: set flag based on direction from HQ
        if (flag == 0) {
            if (hqLoc == null) return false;
            Direction dirFromHQ = hqLoc.directionTo(rc.getLocation());
            if (!rc.onTheMap(rc.getLocation().add(dirFromHQ))) {
                flag = 132000;
                for (int i = 0; i < 8; i ++) if (dirFromHQ == directions[i]) flag += i;
            }
        } else if (flag >= 132000 && flag <= 132007) {
            if (!rc.onTheMap(rc.getLocation().translate(-1,0))) {
                flag = rc.getLocation().x + 130000;
            } else if (!rc.onTheMap(rc.getLocation().translate(1,0))) {
                flag = rc.getLocation().x + 150000;
            } else if (!rc.onTheMap(rc.getLocation().translate(0,-1))) {
                flag = rc.getLocation().y + 170001;
            } else if (!rc.onTheMap(rc.getLocation().translate(0,1))) {
                flag = rc.getLocation().y + 190000;
            } else return false;
        }
        rc.setFlag(flag);
        return true;
    }
}
