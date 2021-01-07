package rush2;
import battlecode.common.*;
import java.util.ArrayList;
import java.util.Iterator;

public strictfp class RobotPlayer {
    static RobotController rc;

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
    static RobotInfo[] attackable;

    static MapLocation hqLoc = null; // enlightenment center where this robot was spawned
    static int hqID = -1;
    static MapLocation enemyHqLoc = null; // there could be multiple of these; for now, just stick with one

    static int flag = 0;
    static int newFlag = 0;
    static int flagSetTurn = 0;

    // min and max coordinates of map
    static int mapMinX = 0;
    static int mapMaxX = 0;
    static int mapMinY = 0;
    static int mapMaxY = 0;
    static boolean reportedMinX = false;
    static boolean reportedMaxX = false;
    static boolean reportedMinY = false;
    static boolean reportedMaxY = false;
    static boolean triedRotate = false;
    static boolean triedReflectX = false;
    static boolean triedReflectY = false;

    static final int CAPTURED_HQ_FLAG_OFFSET = 65536;
    static final int MIN_COORD = 10000;
    static final int MIN_X_COORD_FLAG_OFFSET = 140000;
    static final int MAX_X_COORD_FLAG_OFFSET = 160000;
    static final int MIN_Y_COORD_FLAG_OFFSET = 180000;
    static final int MAX_Y_COORD_FLAG_OFFSET = 200000;

    /**
     * Flag codes:
     * 0: unset
     * 1-65535: location of enemy HQ
     * 65536-131071: location of captured HQ (that was formerly enemy HQ) + 65535
     * 140000-160000: min x coordinate + 130000
     * 160000-180064: max x coordinate + 150000
     * 190000-210000: min y coordinate + 180000
     * 210000-230064: max y coordinate + 200000
     * 240001-305535: possible location of 
     **/
    
    // for HQ, list of IDs of spawned units
    // for non-HQ, ID of HQ
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
        currInfluence = 0;
        me = rc.getTeam();
        enemy = me.opponent();
        currType = rc.getType();
        if (currType == RobotType.ENLIGHTENMENT_CENTER) {
            hqLoc = rc.getLocation();
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
                checkEdges();
                if (currType == RobotType.ENLIGHTENMENT_CENTER) {
                    getFlagsFromUnits();
                    lookForEnemyHQ();
                    if (enemyHqLoc == null && mapMinX > 0 && mapMinY > 0 && mapMaxX > 0 && mapMaxY > 0)
                        calculateEnemyHQ();
                    runEnlightenmentCenter();
                    if (enemyHqLoc != null) newFlag = locToFlag(enemyHqLoc);
                    if (newFlag != flag && rc.canSetFlag(newFlag)) {
                        rc.setFlag(newFlag);
                        flag = newFlag;
                    }
                } else {
                    findMyHQ();
                    getFlagFromHQ();
                    lookForEnemyHQ();

                    // Here, we've separated the controls into a different method for each RobotType.
                    // You may rewrite this into your own control structure if you wish.
                    switch (currType) {
                        case POLITICIAN:           runPolitician();          break;
                        case SLANDERER:            runSlanderer();           break;
                        case MUCKRAKER:            runMuckraker();           break;
                    }

                    if (newFlag != flag) {
                        if (rc.canSetFlag(newFlag)) {
                            rc.setFlag(newFlag);
                            flag = newFlag;
                            flagSetTurn = turnCount;
                            if (flag >= MIN_X_COORD_FLAG_OFFSET) {
                                if (flag < MAX_X_COORD_FLAG_OFFSET) reportedMinX = true;
                                else if (flag < MIN_Y_COORD_FLAG_OFFSET) reportedMaxX = true;
                                else if (flag < MAX_Y_COORD_FLAG_OFFSET) reportedMinY = true;
                                else reportedMaxY = true;
                            }
                        } else newFlag = flag;
                    }
                    clearFlag();
                }

                Clock.yield();

            } catch (Exception e) {
                System.out.println(rc.getType() + " Exception");
                e.printStackTrace();
            }
        }
    }

    ////////////////////////////////
    // MOVEMENT
    ////////////////////////////////

    static Direction randomDirection() {
        return directions[(int) (Math.random() * directions.length)];
    }

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

    ////////////////////////////////
    // FLAGS
    ////////////////////////////////

    static void clearFlag() throws GameActionException {
        if (flag != 0 && turnCount > flagSetTurn + 10 && rc.canSetFlag(0)) {
            flag = 0;
            rc.setFlag(0);
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

    static void getFlagsFromUnits() throws GameActionException {
        Iterator<Integer> it = spawnedIDs.iterator();
        while (it.hasNext()) {
            int id = (Integer) it.next();
            //System.out.println("Checking ID " + id);
            if (rc.canGetFlag(id)) {
                int readFlag = rc.getFlag(id);
                //System.out.println(readFlag);

                if (readFlag == 0) continue;
                else if (readFlag < CAPTURED_HQ_FLAG_OFFSET) {
                    // unit found enemy HQ
                    enemyHqLoc = flagToLoc(readFlag);
                    System.out.println("Found enemy HQ!");
                } else if (readFlag < MAX_X_COORD_FLAG_OFFSET) {
                    // unit found min x coordinate
                    mapMinX = readFlag - MIN_X_COORD_FLAG_OFFSET + MIN_COORD;
                } else if (readFlag < MIN_Y_COORD_FLAG_OFFSET) {
                    // unit found max x coordinate
                    mapMaxX = readFlag - MAX_X_COORD_FLAG_OFFSET + MIN_COORD;
                } else if (readFlag < MAX_Y_COORD_FLAG_OFFSET) {
                    // unit found min y coordinate
                    mapMinY = readFlag - MIN_Y_COORD_FLAG_OFFSET + MIN_COORD;
                } else {
                    // unit found max y coordinate
                    mapMaxY = readFlag - MAX_Y_COORD_FLAG_OFFSET + MIN_COORD;
                }
            } else {
                it.remove();
            }
        }

        it = spawnedIDs.iterator();
        while (enemyHqLoc != null && it.hasNext()) {
            int id = (Integer) it.next();
            //System.out.println("Checking ID " + id);
            if (rc.canGetFlag(id)) {
                int readFlag = rc.getFlag(id);
                //System.out.println(readFlag);
                if (locToFlag(enemyHqLoc) == readFlag - CAPTURED_HQ_FLAG_OFFSET) {
                    enemyHqLoc = null;
                    newFlag = readFlag;
                }
            } else {
                it.remove();
            }
        }
    }

    static void getFlagFromHQ() throws GameActionException {
        if (hqID != -1 && rc.canGetFlag(hqID)) {
            int hqFlag = rc.getFlag(hqID);
            if (hqFlag > 0 && hqFlag < CAPTURED_HQ_FLAG_OFFSET) {
                enemyHqLoc = flagToLoc(hqFlag);
            } else if (enemyHqLoc != null && hqFlag == (locToFlag(enemyHqLoc)) + CAPTURED_HQ_FLAG_OFFSET) {
                enemyHqLoc = null;
            }
        }
    }

    ////////////////////////////////
    // COMMON
    ////////////////////////////////

    static void findMyHQ() throws GameActionException {
        if (hqLoc != null) return;
        RobotInfo [] possibleHQ = rc.senseNearbyRobots(2, me);
        for (RobotInfo r : possibleHQ) {
            if (r.type == RobotType.ENLIGHTENMENT_CENTER) {
                hqLoc = r.getLocation();
                hqID = r.getID();
                spawnedIDs.add(hqID);
                //System.out.println("Found the HQ!");
            }
        }
    }

    static void lookForEnemyHQ() throws GameActionException {
        // try to see enemy HQ if it's visible
        if (enemyHqLoc == null) {
            for (RobotInfo r : nearbyRobots) {
                if (r.type == RobotType.ENLIGHTENMENT_CENTER && r.team == enemy) {
                    enemyHqLoc = r.location;
                    newFlag = locToFlag(r.location);
                    return;
                }
            }
        } else if (rc.canSenseLocation(enemyHqLoc) && (rc.senseRobotAtLocation(enemyHqLoc) == null || rc.senseRobotAtLocation(enemyHqLoc).team == me)) {
            newFlag = locToFlag(enemyHqLoc) + CAPTURED_HQ_FLAG_OFFSET;
            enemyHqLoc = null;
        }
    }

    static void calculateEnemyHQ() throws GameActionException {
        int mapX = mapMaxX - mapMinX + 1;
        int mapY = mapMaxY - mapMinY + 1;

        // assuming rotational symmetry, find enemy X and Y coordinates
        int enemyX = mapMinX + (mapMaxX - hqLoc.x);
        int enemyY = mapMinY + (mapMaxY - hqLoc.y);

        if (!triedRotate) {
            enemyHqLoc = new MapLocation(enemyX, enemyY);
            triedRotate = true;
        } else if (!triedReflectX) {
            enemyHqLoc = new MapLocation(enemyX, hqLoc.y);
            triedReflectX = true;
        } else if (!triedReflectY) {
            enemyHqLoc = new MapLocation(hqLoc.x, enemyY);
            triedReflectY = true;
        }
    }

    // map edge checker
    static void checkEdges() throws GameActionException {
        int r = 1;
        boolean checkMinX = mapMinX == 0;
        boolean checkMaxX = mapMaxX == 0;
        boolean checkMinY = mapMinY == 0;
        boolean checkMaxY = mapMaxY == 0;
        MapLocation left = currLocation.translate(-1, 0);
        MapLocation right = currLocation.translate(1, 0);
        MapLocation down = currLocation.translate(0, -1);
        MapLocation up = currLocation.translate(0, 1);
        while (r * r <= currType.sensorRadiusSquared && (checkMinX || checkMaxX || checkMinY || checkMaxY)) {
            if (checkMinX) {
                if (!rc.onTheMap(left)) {
                    mapMinX = left.x + 1;
                    checkMinX = false;
                } else left = left.translate(-1, 0);
            }

            if (checkMaxX) {
                if (!rc.onTheMap(right)) {
                    mapMaxX = left.x - 1;
                    checkMaxX = false;
                } else right = right.translate(1, 0);
            }

            if (checkMinY) {
                if (!rc.onTheMap(down)) {
                    mapMinY = left.y + 1;
                    checkMinY = false;
                } else down = down.translate(0, -1);
            }

            if (checkMaxY) {
                if (!rc.onTheMap(up)) {
                    mapMaxY = left.y - 1;
                    checkMaxY = false;
                } else up = up.translate(0, 1);
            }

            r++;
        }

        if (flag == 0 && newFlag == 0) {
            if (mapMinX != 0 && !reportedMinX) newFlag = mapMinX + MIN_X_COORD_FLAG_OFFSET - MIN_COORD;
            else if (mapMaxX != 0 && !reportedMaxX) newFlag = mapMaxX + MAX_X_COORD_FLAG_OFFSET - MIN_COORD;
            else if (mapMinY != 0 && !reportedMinY) newFlag = mapMinY + MIN_Y_COORD_FLAG_OFFSET - MIN_COORD;
            else if (mapMaxY != 0 && !reportedMaxY) newFlag = mapMaxY + MAX_Y_COORD_FLAG_OFFSET - MIN_COORD;
        }
    }

    ////////////////////////////////
    // INDIVIDUAL
    ////////////////////////////////


    static void runEnlightenmentCenter() throws GameActionException {
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
    }

    static void runPolitician() throws GameActionException {
        int actionRadius = rc.getType().actionRadiusSquared;
        boolean enemyHQInRange = false;
        for (RobotInfo r : attackable)
            enemyHQInRange |= (r.type == RobotType.ENLIGHTENMENT_CENTER);
        // only attack enemy HQ (don't waste time with other robots) unless empower factor > 1 or no enemy HQ found
        // zeroth order navigation strategy: move away from HQ
        if (enemyHqLoc != null && rc.getLocation().distanceSquaredTo(enemyHqLoc) > 2 && fuzzyTryMove(rc.getLocation().directionTo(enemyHqLoc)))
            ;//System.out.println("Moved towards enemy HQ!");
        else if ((enemyHQInRange || attackable.length > 0 && (rc.getEmpowerFactor(me, 0) > 1 || enemyHqLoc == null))
            && rc.canEmpower(actionRadius) && rc.getConviction() > 10) {
            // attack either enemy HQ or farthest enemy in range
            int attackLength = 1;
            for (RobotInfo r : attackable) {
                if (r.getLocation().distanceSquaredTo(rc.getLocation()) > attackLength)
                    attackLength = r.getLocation().distanceSquaredTo(rc.getLocation());
            }
            if (enemyHQInRange) attackLength = rc.getLocation().distanceSquaredTo(enemyHqLoc);
            //System.out.println("empowering...");
            rc.empower(attackLength);
            //System.out.println("empowered");
            return;
        }
        else if (hqLoc != null && fuzzyTryMove(hqLoc.directionTo(rc.getLocation())))
            ;//System.out.println("Moved away from HQ!");
        else if (fuzzyTryMove(randomDirection()))
            ;//System.out.println("I moved!");
    }

    static void runSlanderer() throws GameActionException {
        tryMove(randomDirection());
    }

    static void runMuckraker() throws GameActionException {
        int actionRadius = rc.getType().actionRadiusSquared;
        for (RobotInfo r : attackable) {
            if (r.type.canBeExposed()) {
                // It's a slanderer... go get them!
                if (rc.canExpose(r.location)) {
                    //System.out.println("e x p o s e d");
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
            ;//System.out.println("Moved towards enemy HQ!");
        else if (hqLoc != null && fuzzyTryMove(hqLoc.directionTo(rc.getLocation())))
            ;//System.out.println("Moved away from HQ!");
        else if (fuzzyTryMove(randomDirection()))
            ;//System.out.println("I moved!");
    }
}
