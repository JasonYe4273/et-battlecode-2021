package fakenews;
import battlecode.common.*;
import java.util.ArrayList;
import java.util.Arrays;

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

    static final int SLANDER_FACTOR = 20;
    static final int EMPOWER_MAX_RADIUS = 9;
    static final int MIN_POL_INFLUENCE = 0;
    static final int DEFAULT = 0;
    static final int HEAL = 1;

    static Team me;
    static Team op;

    static int turnCount;
    static int currConviction;
    static int currInfluence;
    static MapLocation currLocation;
    static RobotInfo[] nearbyRobots;

    static MapLocation myHQ;
    static ArrayList<MapLocation> hqLocs;
    static ArrayList<Team> hqTeams;
    static ArrayList<Integer> slandererIDs;

    static int flag;
    static boolean flagChanged;

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
        op = me.opponent();
        myHQ = rc.getLocation();
        hqLocs = new ArrayList<MapLocation>();
        hqTeams = new ArrayList<Team>();
        flag = 0;
        flagChanged = false;

        for (RobotInfo ri : rc.senseNearbyRobots(2)) {
            if (ri.type == RobotType.ENLIGHTENMENT_CENTER && ri.team == me) {
                myHQ = ri.location;
                break;
            }
        }

        while (true) {
            // Try/catch blocks stop unhandled exceptions, which cause your robot to freeze
            try {
                turnCount += 1;
                currConviction = rc.getConviction();
                currInfluence = rc.getInfluence();
                currLocation = rc.getLocation();
                nearbyRobots = rc.senseNearbyRobots();
                // Here, we've separated the controls into a different method for each RobotType.
                // You may rewrite this into your own control structure if you wish.
                switch (rc.getType()) {
                    case ENLIGHTENMENT_CENTER: runEnlightenmentCenter(); break;
                    case POLITICIAN:           runPolitician();          break;
                    case SLANDERER:            runSlanderer();           break;
                    case MUCKRAKER:            runMuckraker();           break;
                }

                if (flagChanged) {
                    rc.setFlag(flag);
                    flagChanged = false;
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
        if (flag != f) {
            flag = f;
            flagChanged = true;
        }
    }

    /*static void checkForHQs() throws GameActionException {
        outer:
        for (RobotInfo ri : nearbyRobots) {
            if (ri.type == RobotType.ENLIGHTENMENT_CENTER) {
                for (int i = 0; i < hqLocs.size(); i++) {
                    if (hqLocs.get(i).equals(ri.location)) {
                        if (hqTeams.get(i).equals(ri.team)) continue outer;

                        hqTeams.set(i, ri.team);
                        // TODO: bitFlag about team change
                        continue outer;
                    }
                }

                hqLocs.add(ri.location);
                hqTeams.add(ri.team);
                // TODO: bitFlag about new hq found
            }
        }
    }*/

    static void runEnlightenmentCenter() throws GameActionException {
        build();
        vote();
    }

    static void build() throws GameActionException {
        for (RobotInfo ri : nearbyRobots) {
            if (ri.type == RobotType.MUCKRAKER && ri.team == op) {
                if (buildPolitician()) return;
            }
        }

        /*int enemyPolConviction = 0;
        for (RobotInfo ri : nearbyRobots) {
            if (ri.type == RobotType.POLITICIAN && ri.team == op) {
                enemyPolConviction += ri.conviction;
            }
        }
        if (enemyPolConviction > currConviction) {
            setFlag(HEAL);
            if (buildPolitician()) return;
        }
        else if (flag == HEAL) setFlag(DEFAULT);*/

        int income = 0;
        for (RobotInfo ri : nearbyRobots) {
            if (ri.type == RobotType.SLANDERER && ri.team == me && rc.canGetFlag(ri.ID)) {
                income += rc.getFlag(ri.ID);
            }
        }
        if (income < 5 && buildSlanderer()) return;

        int politicians = 0;
        for (RobotInfo ri : nearbyRobots) {
            if (ri.type == RobotType.POLITICIAN && ri.team == me) {
                politicians++;
            }
        }
        
        if ((((int) (Math.random() * 10)) < politicians) && buildPolitician()) return;
    }

    static boolean buildMuckracker() throws GameActionException {
        // Always build 1 influence muckrackers
        for (Direction dir : directions) {
            if (rc.canBuildRobot(RobotType.MUCKRAKER, dir, 1)) {
                rc.buildRobot(RobotType.MUCKRAKER, dir, 1);
                return true;
            }
        }
        return false;
    }

    static boolean buildPolitician(int influence) throws GameActionException {
        if (currInfluence < influence || influence <= MIN_POL_INFLUENCE) return false;
        for (Direction dir : directions) {
            if (rc.canBuildRobot(RobotType.POLITICIAN, dir, influence)) {
                rc.buildRobot(RobotType.POLITICIAN, dir, influence);
                return true;
            }
        }
        return false;
    }

    static boolean buildPolitician() throws GameActionException {
        return buildPolitician(currInfluence);
    }

    static boolean buildSlanderer(int factor) throws GameActionException {
        // always make slanderers in multiples of slander factor
        if (currInfluence < factor * SLANDER_FACTOR) return false;
        for (Direction dir : directions) {
            if (rc.canBuildRobot(RobotType.SLANDERER, dir, factor * SLANDER_FACTOR)) {
                rc.buildRobot(RobotType.SLANDERER, dir, factor * SLANDER_FACTOR);
                return true;
            }
        }
        return false;
    }

    static boolean buildSlanderer() throws GameActionException {
        if (currInfluence < SLANDER_FACTOR) return false;
        return buildSlanderer(currInfluence / SLANDER_FACTOR);
    }

    static void vote() throws GameActionException {
        if (rc.canBid(currInfluence / 100)) {
            //rc.bid(currInfluence / 100);
        }
    }

    static void runPolitician() throws GameActionException {
        for (RobotInfo ri : nearbyRobots) {
            if (ri.type == RobotType.ENLIGHTENMENT_CENTER && ri.team == me && rc.canGetFlag(ri.ID)) {
                if (rc.getFlag(ri.ID) == HEAL) {
                    if (target(ri.location)) return;
                }
            }
        }

        int r = Integer.MAX_VALUE;
        MapLocation t = currLocation;
        for (RobotInfo ri : nearbyRobots) {
            if (ri.type == RobotType.MUCKRAKER && ri.team == op && currLocation.distanceSquaredTo(ri.location) < r) {
                r = currLocation.distanceSquaredTo(ri.location);
                t = ri.location;
            }
        }
        if (r != Integer.MAX_VALUE && target(t)) return;

        hoverAroundLoc(myHQ, 16);
    }

    static boolean target(MapLocation loc) throws GameActionException {
        int r = currLocation.distanceSquaredTo(loc);
        if (r <= 2 && rc.canEmpower(r)) {
            rc.empower(r);
            return true;
        }

        Direction dir = currLocation.directionTo(loc);
        for (RobotInfo ri : nearbyRobots) {
            if (currLocation.distanceSquaredTo(ri.location) <= r) {
                if (tryMove(dir)) return true;
                if (tryMove(dir.rotateRight())) return true;
                if (tryMove(dir.rotateLeft())) return true;
                return false;
            }
        }

        if (rc.canEmpower(r)) {
            rc.empower(r);
            return true;
        }
        return false;
    }

    static void runSlanderer() throws GameActionException {
        if (turnCount < 50) setFlag(currConviction / 20);
        else if (turnCount == 50) setFlag(0);
        else hoverAroundLoc(myHQ, 9);
    }

    static void runMuckraker() throws GameActionException {
        for (RobotInfo ri : nearbyRobots) {
            if (ri.team == op && ri.type.canBeExposed() && rc.canExpose(ri.location)) {
                rc.expose(ri.location);
                return;
            }
        }

        moveRandomly();
    }

    static void moveRandomly() throws GameActionException {
        ArrayList<Direction> dir = new ArrayList<Direction>();
        for (Direction d : directions) dir.add(d);
        while (dir.size() > 0) {
            int rand = (int) (Math.random() * dir.size());
            if (tryMove(dir.get(rand))) return;
            else dir.remove(rand);
        }
    }

    static void hoverAroundLoc(MapLocation loc, int radius) throws GameActionException {
        int dist = loc.distanceSquaredTo(currLocation);
        Direction dir = loc.directionTo(currLocation);
        if (dist > radius) {
            dir = dir.opposite();
        } else if (dist == radius) {
            dir = randomDirection();
        }

        if (tryMove(dir)) return;
        if (tryMove(dir.rotateLeft())) return;
        if (tryMove(dir.rotateRight())) return;
        if (tryMove(dir.rotateLeft().rotateLeft())) return;
        if (tryMove(dir.rotateRight().rotateRight())) return;
        if (tryMove(dir.opposite().rotateLeft())) return;
        if (tryMove(dir.opposite().rotateRight())) return;
    }

    static Direction randomDirection() {
        return directions[(int) (Math.random() * directions.length)];
    }

    static boolean tryMove(Direction dir) throws GameActionException {
        if (rc.canMove(dir)) {
            rc.move(dir);
            return true;
        } else return false;
    }
}
