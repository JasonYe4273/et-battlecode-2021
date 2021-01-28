package finals;
import battlecode.common.*;
public class Dijkstra extends Navigation {
  public static final int RADIUS = 2;
  public static final int DIAMETER = 2*RADIUS + 1;
  public Dijkstra(RobotController rc) {super(rc);}
  public void navTo (MapLocation l) throws GameActionException {
    int initialBytecodeCount = Clock.getBytecodeNum();
    System.out.println("Intelligent navigation to " + l);
    // initialize nearby locations
    MapLocation currentLoc = rc.getLocation();
    MapLocation [] nearbyLocs = new MapLocation[DIAMETER * DIAMETER];
    double passabilities [] = new double[DIAMETER * DIAMETER];
    double distances [] = new double[DIAMETER * DIAMETER];
    boolean [] unoccupied = new boolean[DIAMETER * DIAMETER];
    for (int i = -RADIUS; i <= RADIUS; i ++) {
      for (int j = -RADIUS; j <= RADIUS; j ++) {
        MapLocation newLoc = currentLoc.translate(i, j);
        double passability, distToTarget;
        boolean unoccupiedLoc;
        if (!rc.onTheMap(newLoc)) {
          passability = Double.MAX_VALUE;
          distToTarget = Double.MAX_VALUE;
          unoccupiedLoc = false;
        } else {
          passability = 1.0 / rc.sensePassability(newLoc);
          distToTarget = Math.max(Math.abs(newLoc.x - l.x), Math.abs(newLoc.y - l.y)) * 100 + Math.sqrt(l.distanceSquaredTo(newLoc)) * 0.01 + passability;
          unoccupiedLoc = !(rc.isLocationOccupied(newLoc));
        }
        nearbyLocs[(i+RADIUS)*DIAMETER+(j+RADIUS)] = newLoc;
        passabilities[(i+RADIUS)*DIAMETER+(j+RADIUS)] = passability;
        distances[(i+RADIUS)*DIAMETER+(j+RADIUS)] = distToTarget;
        unoccupied[(i+RADIUS)*DIAMETER+(j+RADIUS)] = unoccupiedLoc;
      }
    }
    System.out.println("Bytecodes used in checking location validity: " + (Clock.getBytecodeNum() - initialBytecodeCount));
    MinHeap pq = new MinHeap (distances);
    System.out.println("Bytecodes used in checking location validity + initializing MinHeap: " + (Clock.getBytecodeNum() - initialBytecodeCount));
   
    // now implement Dijkstra on the priority queue
    int newLocIndex = -2;
    MapLocation newLoc = null;
    System.out.println(l);
    while (newLocIndex == -2 || nearbyLocs[newLocIndex] != currentLoc) {
      newLocIndex = pq.extractMin();
      newLoc = nearbyLocs[newLocIndex];
      System.out.println(newLoc + " " + distances[newLocIndex]);
      //if (newLoc == null) System.out.println("FATAL ERROR: newLoc should not be null!");
      double newLocCost = distances[newLocIndex]; // cost of moving from newLoc to target
      if (!unoccupied[newLocIndex]) continue;
      // update neighbors in nearbyLocs
      int i = newLoc.x - currentLoc.x;
      int j = newLoc.y - currentLoc.y;
      if (i <= 1 && i >= -1 && j <= 1 && j >= -1) break;
      if (i-1 >= -RADIUS && distances[(i-1+RADIUS)*DIAMETER + (j+RADIUS)] > newLocCost + passabilities[(i-1+RADIUS)*DIAMETER + (j+RADIUS)]) {
        distances[(i-1+RADIUS)*DIAMETER + (j+RADIUS)] = newLocCost + passabilities[(i-1+RADIUS)*DIAMETER + (j+RADIUS)];
        pq.updateKey((i-1+RADIUS)*DIAMETER + (j+RADIUS), distances[(i-1+RADIUS)*DIAMETER + (j+RADIUS)]);
      }
      if (i+1 <= RADIUS && distances[(i+1+RADIUS)*DIAMETER + (j+RADIUS)] > newLocCost + passabilities[(i+1+RADIUS)*DIAMETER + (j+RADIUS)]) {
        distances[(i+1+RADIUS)*DIAMETER + (j+RADIUS)] = newLocCost + passabilities[(i+1+RADIUS)*DIAMETER + (j+RADIUS)];
        pq.updateKey((i+1+RADIUS)*DIAMETER + (j+RADIUS), distances[(i+1+RADIUS)*DIAMETER + (j+RADIUS)]);
      }
      if (j-1 >= -RADIUS && distances[(i+RADIUS)*DIAMETER + (j-1+RADIUS)] > newLocCost + passabilities[(i+RADIUS)*DIAMETER + (j-1+RADIUS)]) {
        distances[(i+RADIUS)*DIAMETER + (j-1+RADIUS)] = newLocCost + passabilities[(i+RADIUS)*DIAMETER + (j-1+RADIUS)];
        pq.updateKey((i+RADIUS)*DIAMETER + (j-1+RADIUS), distances[(i+RADIUS)*DIAMETER + (j-1+RADIUS)]);
      }
      if (j+1 <= RADIUS && distances[(i+RADIUS)*DIAMETER + (j+1+RADIUS)] > newLocCost + passabilities[(i+RADIUS)*DIAMETER + (j+1+RADIUS)]) {
        distances[(i+RADIUS)*DIAMETER + (j+1+RADIUS)] = newLocCost + passabilities[(i+RADIUS)*DIAMETER + (j+1+RADIUS)];
        pq.updateKey((i+RADIUS)*DIAMETER + (j+1+RADIUS), distances[(i+RADIUS)*DIAMETER + (j+1+RADIUS)]);
      }
      if (i-1 >= -RADIUS && j-1 >= -RADIUS && distances[(i-1+RADIUS)*DIAMETER + (j-1+RADIUS)] > newLocCost + passabilities[(i-1+RADIUS)*DIAMETER + (j-1+RADIUS)]) {
        distances[(i-1+RADIUS)*DIAMETER + (j-1+RADIUS)] = newLocCost + passabilities[(i-1+RADIUS)*DIAMETER + (j-1+RADIUS)];
        pq.updateKey((i-1+RADIUS)*DIAMETER + (j-1+RADIUS), distances[(i-1+RADIUS)*DIAMETER + (j-1+RADIUS)]);
      }
      if (i+1 <= RADIUS && j-1 >= -RADIUS && distances[(i+1+RADIUS)*DIAMETER + (j-1+RADIUS)] > newLocCost + passabilities[(i+1+RADIUS)*DIAMETER + (j-1+RADIUS)]) {
        distances[(i+1+RADIUS)*DIAMETER + (j-1+RADIUS)] = newLocCost + passabilities[(i+1+RADIUS)*DIAMETER + (j-1+RADIUS)];
        pq.updateKey((i+1+RADIUS)*DIAMETER + (j-1+RADIUS), distances[(i+1+RADIUS)*DIAMETER + (j-1+RADIUS)]);
      }
      if (i-1 >= -RADIUS && j+1 <= RADIUS && distances[(i-1+RADIUS)*DIAMETER + (j+1+RADIUS)] > newLocCost + passabilities[(i-1+RADIUS)*DIAMETER + (j+1+RADIUS)]) {
        distances[(i-1+RADIUS)*DIAMETER + (j+1+RADIUS)] = newLocCost + passabilities[(i-1+RADIUS)*DIAMETER + (j+1+RADIUS)];
        pq.updateKey((i-1+RADIUS)*DIAMETER + (j+1+RADIUS), distances[(i-1+RADIUS)*DIAMETER + (j+1+RADIUS)]);
      }
      if (i+1 <= RADIUS && j+1 <= RADIUS && distances[(i+1+RADIUS)*DIAMETER + (j+1+RADIUS)] > newLocCost + passabilities[(i+1+RADIUS)*DIAMETER + (j+1+RADIUS)]) {
        distances[(i+1+RADIUS)*DIAMETER + (j+1+RADIUS)] = newLocCost + passabilities[(i+1+RADIUS)*DIAMETER + (j+1+RADIUS)];
        pq.updateKey((i+1+RADIUS)*DIAMETER + (j+1+RADIUS), distances[(i+1+RADIUS)*DIAMETER + (j+1+RADIUS)]);
      }
    }
    if (newLoc != null) {
      Direction dir = currentLoc.directionTo(newLoc);
      if (rc.canMove(dir)) rc.move(dir);
    }
    System.out.println("Total bytecodes used: " + (Clock.getBytecodeNum() - initialBytecodeCount));
  }
}
