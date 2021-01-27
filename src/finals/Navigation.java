package finals;
import battlecode.common.*;

public class Navigation {
    RobotController rc;
    int lastMoveTurn = 0;

    public Navigation(RobotController r) {
        rc = r;
    }  

    public void moveInDirection(Direction d) throws GameActionException {
        Direction[] dd = {d, d.rotateLeft(), d.rotateRight(), d.rotateLeft().rotateLeft(), d.rotateRight().rotateRight()};
        double[] suitability = {1,.5,.5,.1,.1};
        for(int i=0;i<5;i++) {
            MapLocation l = rc.getLocation().add(dd[i]);
            if(rc.onTheMap(l))
                suitability[i] *= rc.sensePassability(l);
        }
        double best = 0;
        Direction bestD = null;
        for(int i=0;i<5;i++) {
            if(suitability[i]>best && rc.canMove(dd[i])) {
                best = suitability[i];
                bestD = dd[i];
            }
        }
        if(bestD != null) {
            rc.move(bestD);
            lastMoveTurn = rc.getRoundNum();
        }
    }
    public boolean moveInDirections(Direction[] dirs, int minR, int maxR, MapLocation l) throws GameActionException {
        for(Direction d : dirs) {
            int dist = rc.getLocation().add(d).distanceSquaredTo(l);
            if(dist < minR || dist > maxR) continue;
            if(rc.canMove(d)) {
                rc.move(d);
                lastMoveTurn = rc.getRoundNum();
                return true;
            }
        }
        return false;
    }
    public void moveToward(MapLocation l) throws GameActionException {
        System.out.println("Navigating toward " + l);
        if(rc.getCooldownTurns()>=1) return;
        if(rc.getLocation().equals(l)) return;
        if(rc.getLocation().isAdjacentTo(l)) {
            Direction d = rc.getLocation().directionTo(l);
            if(rc.canMove(d))
                rc.move(d);
            return;
        }
        if (Clock.getBytecodesLeft() < 12000) {
            System.out.println("Not using bytecode-intensive navigation: " + Clock.getBytecodesLeft());
            Direction d = rc.getLocation().directionTo(l);
            moveInDirection(d);
        } else {
            navTo(l);
        }
    }

    public void navTo(MapLocation l) throws GameActionException {
        System.out.println("Starting navigation: " + Clock.getBytecodesLeft());
        // try to do more intelligent navigation within a 7x7 square centered at the current unit
        // NOTE: Right now, we only use the center 5x5 square; I don't think array initialization is a huge cost, though, and we do hope to accelerate this
        // TODO: Make this better and more bytecode efficient (probably just replace with Dijkstra)
        double [][] passabilities = new double [7][7]; // actually inverse passabilities (cost of moving to the square)
        double [][] distancesToTarget = new double [7][7];
        boolean [][] unoccupiedLocs = new boolean[7][7];
        // initialize arrays
        MapLocation currentLoc = rc.getLocation();
        for (int i = -2; i <= 2; i ++) {
            for (int j = -2; j <= 2; j ++) {
                MapLocation newLoc = currentLoc.translate(i, j);
                if (!rc.onTheMap(newLoc)) {
                    passabilities[i+3][j+3] = Double.MAX_VALUE;
                    distancesToTarget[i+3][j+3] = Double.MAX_VALUE;
                    unoccupiedLocs[i+3][j+3] = false;
                } else {
                    passabilities[i+3][j+3] = 1.0 / rc.sensePassability(newLoc);
                    distancesToTarget[i+3][j+3] = Math.max(Math.abs(newLoc.x - l.x), Math.abs(newLoc.y - l.y)) * 100
                        + l.distanceSquaredTo(newLoc) * 0.01; // taxicab distance with ties broken by map distance
                    unoccupiedLocs[i+3][j+3] = true;
                }
            }
        }
        // add in all nearby robots to their locations
        for (RobotInfo r : rc.senseNearbyRobots()) {
            MapLocation robotLoc = r.location;
            if (-3 <= robotLoc.x - currentLoc.x && robotLoc.x - currentLoc.x <= 3 
                && -3 <= robotLoc.y - currentLoc.y && robotLoc.y - currentLoc.y <= 3)
                unoccupiedLocs[robotLoc.x - currentLoc.x + 3][robotLoc.y - currentLoc.y + 3] = false;
        }
        System.out.println("Finished initialization: " + Clock.getBytecodesLeft());
        int maxIterations = 2;

        // store some values to avoid repeated array lookups
        double difference; // difference in distances to target
        boolean unoccupied; // if location [i][j] is unoccupied
        double passability; // passability of [i][j]
        for (int counter = 0; counter < maxIterations; counter ++) {
            for (int i = 1; i < 5; i ++) {
                for (int j = 1; j < 5; j ++) {
                    // frequently used expressions to avoid excessive array lookups
                    unoccupied = unoccupiedLocs[i][j];
                    passability = passabilities[i][j];
                    // check all the edges to see if we can relax in either direction
                    // horizontal edge (-)
                    difference = distancesToTarget[i][j] - distancesToTarget[i+1][j];
                    if (unoccupiedLocs[i+1][j] && difference > passabilities[i+1][j]) distancesToTarget[i][j] = distancesToTarget[i+1][j] + passabilities[i+1][j];
                    if (unoccupied && -difference > passability) distancesToTarget[i+1][j] = distancesToTarget[i][j] + passability;
                    // vertical edge (|)
                    difference = distancesToTarget[i][j] - distancesToTarget[i][j+1];
                    if (unoccupiedLocs[i][j+1] && difference > passabilities[i][j+1]) distancesToTarget[i][j] = distancesToTarget[i][j+1] + passabilities[i][j+1];
                    if (unoccupied && -difference > passability) distancesToTarget[i][j+1] = distancesToTarget[i][j] + passability;
                    // diagonal edge (/)
                    difference = distancesToTarget[i][j] - distancesToTarget[i+1][j+1];
                    if (unoccupiedLocs[i+1][j+1] && difference > passabilities[i+1][j+1]) distancesToTarget[i][j] = distancesToTarget[i+1][j+1] + passabilities[i+1][j+1];
                    if (unoccupied && -difference > passability) distancesToTarget[i+1][j+1] = distancesToTarget[i][j] + passability;
                    // diagonal edge (\)
                    difference = distancesToTarget[i][j+1] - distancesToTarget[i+1][j];
                    if (unoccupiedLocs[i+1][j] && difference > passabilities[i+1][j]) distancesToTarget[i][j+1] = distancesToTarget[i+1][j] + passabilities[i+1][j];
                    if (unoccupiedLocs[i][j+1] && -difference > passabilities[i][j+1]) distancesToTarget[i+1][j] = distancesToTarget[i][j+1] + passabilities[i][j+1];
                }
            }
        }
        // move to adjacent location with the nearest cost
        // Note: This cost should include the cost of moving to the square, so we add that in now
        distancesToTarget[2][4] += passabilities[2][4];
        distancesToTarget[2][3] += passabilities[2][3];
        distancesToTarget[2][2] += passabilities[2][2];
        distancesToTarget[3][2] += passabilities[3][2];
        distancesToTarget[4][2] += passabilities[4][2];
        distancesToTarget[4][3] += passabilities[4][3];
        distancesToTarget[4][4] += passabilities[4][4];
        distancesToTarget[3][4] += passabilities[3][4];
        double closestNeighbor = Double.MAX_VALUE;
        Direction bestDir = null;
        // default to the direct path if there is a tie
        Direction directPath = currentLoc.directionTo(l);
        if (rc.canMove(directPath)) {
            bestDir = directPath;
            if(directPath == Direction.NORTHWEST) closestNeighbor = distancesToTarget[2][4];
            if(directPath == Direction.WEST) closestNeighbor = distancesToTarget[2][3];
            if(directPath == Direction.SOUTHWEST) closestNeighbor = distancesToTarget[2][2];
            if(directPath == Direction.SOUTH) closestNeighbor = distancesToTarget[3][2];
            if(directPath == Direction.SOUTHEAST) closestNeighbor = distancesToTarget[4][2];
            if(directPath == Direction.EAST) closestNeighbor = distancesToTarget[4][3];
            if(directPath == Direction.NORTHEAST) closestNeighbor = distancesToTarget[4][4];
            if(directPath == Direction.NORTH) closestNeighbor = distancesToTarget[3][4];
        }
        if (unoccupiedLocs[2][4] && distancesToTarget[2][4] < closestNeighbor) { closestNeighbor = distancesToTarget[2][4]; bestDir = Direction.NORTHWEST; }
        if (unoccupiedLocs[2][3] && distancesToTarget[2][3] < closestNeighbor) { closestNeighbor = distancesToTarget[2][3]; bestDir = Direction.WEST; }
        if (unoccupiedLocs[2][2] && distancesToTarget[2][2] < closestNeighbor) { closestNeighbor = distancesToTarget[2][2]; bestDir = Direction.SOUTHWEST; }
        if (unoccupiedLocs[3][2] && distancesToTarget[3][2] < closestNeighbor) { closestNeighbor = distancesToTarget[3][2]; bestDir = Direction.SOUTH; }
        if (unoccupiedLocs[4][2] && distancesToTarget[4][2] < closestNeighbor) { closestNeighbor = distancesToTarget[4][2]; bestDir = Direction.SOUTHEAST; }
        if (unoccupiedLocs[4][3] && distancesToTarget[4][3] < closestNeighbor) { closestNeighbor = distancesToTarget[4][3]; bestDir = Direction.EAST; }
        if (unoccupiedLocs[4][4] && distancesToTarget[4][4] < closestNeighbor) { closestNeighbor = distancesToTarget[4][4]; bestDir = Direction.NORTHEAST; }
        if (unoccupiedLocs[3][4] && distancesToTarget[3][4] < closestNeighbor) { closestNeighbor = distancesToTarget[3][4]; bestDir = Direction.NORTH; }
        if (bestDir != null && rc.canMove(bestDir)) rc.move(bestDir);
        System.out.println("Finished navigation: " + Clock.getBytecodesLeft());
    }
}
