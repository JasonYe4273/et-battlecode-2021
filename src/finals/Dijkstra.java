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
    LocationInfo [] nearbyLocs = new LocationInfo[DIAMETER * DIAMETER];
    for (int i = -RADIUS; i <= RADIUS; i ++) {
      for (int j = -RADIUS; j <= RADIUS; j ++) {
        MapLocation newLoc = currentLoc.translate(i, j);
        double passability, distToTarget;
        boolean unoccupied;
        if (!rc.onTheMap(newLoc)) {
          passability = Double.MAX_VALUE;
          distToTarget = Double.MAX_VALUE;
          unoccupied = false;
        } else {
          passability = 1.0 / rc.sensePassability(newLoc);
          distToTarget = Math.max(Math.abs(newLoc.x - l.x), Math.abs(newLoc.y - l.y)) * 100 + passability;
          unoccupied = true;
        }
        LocationInfo newLocationInfo = new LocationInfo(newLoc, passability, distToTarget, unoccupied);
        nearbyLocs[(i+RADIUS)*DIAMETER+(j+RADIUS)] = newLocationInfo;
      }
    }
    System.out.println("Bytecodes used in checking location validity: " + (Clock.getBytecodeNum() - initialBytecodeCount));
    MinHeap<LocationInfo> pq = new MinHeap<LocationInfo> (nearbyLocs);
    System.out.println("Bytecodes used in checking location validity + initializing MinHeap: " + (Clock.getBytecodeNum() - initialBytecodeCount));
   
    // now implement Dijkstra on the priority queue
    LocationInfo newLoc = null;
    System.out.println(l);
    while (newLoc == null || newLoc.loc != currentLoc) {
      newLoc = pq.extractMin();
      System.out.println(newLoc.loc + " " + newLoc.distToTarget);
      if (newLoc == null) System.out.println("FATAL ERROR: newLoc should not be null!");
      double newLocCost = newLoc.distToTarget; // cost of moving from newLoc to target
      if (!newLoc.unoccupied) continue;
      // update neighbors in nearbyLocs
      int i = newLoc.loc.x - currentLoc.x;
      int j = newLoc.loc.y - currentLoc.y;
      if (i <= 1 && i >= -1 && j <= 1 && j >= -1) break;
      if (i-1 >= -RADIUS && nearbyLocs[(i-1+RADIUS)*DIAMETER + (j+RADIUS)].distToTarget > newLocCost + nearbyLocs[(i-1+RADIUS)*DIAMETER + (j+RADIUS)].passability) {
        nearbyLocs[(i-1+RADIUS)*DIAMETER + (j+RADIUS)].distToTarget = newLocCost + nearbyLocs[(i-1+RADIUS)*DIAMETER + (j+RADIUS)].passability;
        pq.updateKey((i-1+RADIUS)*DIAMETER + (j+RADIUS));
      }
      if (i+1 <= RADIUS && nearbyLocs[(i+1+RADIUS)*DIAMETER + (j+RADIUS)].distToTarget > newLocCost + nearbyLocs[(i+1+RADIUS)*DIAMETER + (j+RADIUS)].passability) {
        nearbyLocs[(i+1+RADIUS)*DIAMETER + (j+RADIUS)].distToTarget = newLocCost + nearbyLocs[(i+1+RADIUS)*DIAMETER + (j+RADIUS)].passability;
        pq.updateKey((i+1+RADIUS)*DIAMETER + (j+RADIUS));
      }
      if (j-1 >= -RADIUS && nearbyLocs[(i+RADIUS)*DIAMETER + (j-1+RADIUS)].distToTarget > newLocCost + nearbyLocs[(i+RADIUS)*DIAMETER + (j-1+RADIUS)].passability) {
        nearbyLocs[(i+RADIUS)*DIAMETER + (j-1+RADIUS)].distToTarget = newLocCost + nearbyLocs[(i+RADIUS)*DIAMETER + (j-1+RADIUS)].passability;
        pq.updateKey((i+RADIUS)*DIAMETER + (j-1+RADIUS));
      }
      if (j+1 <= RADIUS && nearbyLocs[(i+RADIUS)*DIAMETER + (j+1+RADIUS)].distToTarget > newLocCost + nearbyLocs[(i+RADIUS)*DIAMETER + (j+1+RADIUS)].passability) {
        nearbyLocs[(i+RADIUS)*DIAMETER + (j+1+RADIUS)].distToTarget = newLocCost + nearbyLocs[(i+RADIUS)*DIAMETER + (j+1+RADIUS)].passability;
        pq.updateKey((i+RADIUS)*DIAMETER + (j+1+RADIUS));
      }
      if (i-1 >= -RADIUS && j-1 >= -RADIUS && nearbyLocs[(i-1+RADIUS)*DIAMETER + (j-1+RADIUS)].distToTarget > newLocCost + nearbyLocs[(i-1+RADIUS)*DIAMETER + (j-1+RADIUS)].passability) {
        nearbyLocs[(i-1+RADIUS)*DIAMETER + (j-1+RADIUS)].distToTarget = newLocCost + nearbyLocs[(i-1+RADIUS)*DIAMETER + (j-1+RADIUS)].passability;
        pq.updateKey((i-1+RADIUS)*DIAMETER + (j-1+RADIUS));
      }
      if (i+1 <= RADIUS && j-1 >= -RADIUS && nearbyLocs[(i+1+RADIUS)*DIAMETER + (j-1+RADIUS)].distToTarget > newLocCost + nearbyLocs[(i+1+RADIUS)*DIAMETER + (j-1+RADIUS)].passability) {
        nearbyLocs[(i+1+RADIUS)*DIAMETER + (j-1+RADIUS)].distToTarget = newLocCost + nearbyLocs[(i+1+RADIUS)*DIAMETER + (j-1+RADIUS)].passability;
        pq.updateKey((i+1+RADIUS)*DIAMETER + (j-1+RADIUS));
      }
      if (i-1 >= -RADIUS && j+1 <= RADIUS && nearbyLocs[(i-1+RADIUS)*DIAMETER + (j+1+RADIUS)].distToTarget > newLocCost + nearbyLocs[(i-1+RADIUS)*DIAMETER + (j+1+RADIUS)].passability) {
        nearbyLocs[(i-1+RADIUS)*DIAMETER + (j+1+RADIUS)].distToTarget = newLocCost + nearbyLocs[(i-1+RADIUS)*DIAMETER + (j+1+RADIUS)].passability;
        pq.updateKey((i-1+RADIUS)*DIAMETER + (j+1+RADIUS));
      }
      if (i+1 <= RADIUS && j+1 <= RADIUS && nearbyLocs[(i+1+RADIUS)*DIAMETER + (j+1+RADIUS)].distToTarget > newLocCost + nearbyLocs[(i+1+RADIUS)*DIAMETER + (j+1+RADIUS)].passability) {
        nearbyLocs[(i+1+RADIUS)*DIAMETER + (j+1+RADIUS)].distToTarget = newLocCost + nearbyLocs[(i+1+RADIUS)*DIAMETER + (j+1+RADIUS)].passability;
        pq.updateKey((i+1+RADIUS)*DIAMETER + (j+1+RADIUS));
      }
    }
    if (newLoc != null) {
      Direction dir = currentLoc.directionTo(newLoc.loc);
      if (rc.canMove(dir)) rc.move(dir);
    }
    System.out.println("Total bytecodes used: " + (Clock.getBytecodeNum() - initialBytecodeCount));
  }
}
