package sprint2;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

import java.util.PriorityQueue;

// This class is designed to implement robot navigation

public class Navigation {
  RobotController rc;
  public static final int RADIUS = 2; // radius of square to search in

  public Navigation (RobotController r) {
    rc = r;
  }

  // navigate efficiently from current location to a new location l
  public void moveToward(MapLocation l) throws GameActionException {
    System.out.println(Clock.getBytecodesLeft());
    MapLocation currentLoc = rc.getLocation();
    // initialize nearby locations
    LocationInfo [][] nearbyLocs = new LocationInfo[2*RADIUS+1][2*RADIUS+1];
    PriorityQueue<LocationInfo> pq = new PriorityQueue<LocationInfo>((2*RADIUS+1)*(2*RADIUS+1));
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
          distToTarget = Math.max(Math.abs(newLoc.x - l.x), Math.abs(newLoc.y - l.y)) * 100;
          unoccupied = true;
        }
        LocationInfo newLocationInfo = new LocationInfo(newLoc, passability, distToTarget, unoccupied);
        nearbyLocs[i+RADIUS][j+RADIUS] = newLocationInfo;
        pq.add(newLocationInfo);
      }
    }
    System.out.println(Clock.getBytecodesLeft());

    // now implement Dijkstra on the priority queue
    LocationInfo lastLoc = null, newLoc = null;
    System.out.println(l);
    while (newLoc == null || newLoc.loc != currentLoc) {
      newLoc = pq.poll();
      System.out.println(newLoc.loc + " " + newLoc.distToTarget);
      if (newLoc == null) System.out.println("FATAL ERROR: newLoc should not be null!");
      double newLocCost = newLoc.distToTarget + newLoc.passability; // cost of moving to newLoc and then to target
      if (!newLoc.unoccupied) continue;
      // update neighbors in nearbyLocs
      int i = newLoc.loc.x - currentLoc.x;
      int j = newLoc.loc.y - currentLoc.y;
      if (i == 0 && j == 0) break;
      if (i-1 >= -RADIUS && nearbyLocs[i-1+RADIUS][j+RADIUS].distToTarget > newLocCost) {
        nearbyLocs[i-1+RADIUS][j+RADIUS].distToTarget = newLocCost;
        pq.add(nearbyLocs[i-1+RADIUS][j+RADIUS]);
      }
      if (i+1 <= RADIUS && nearbyLocs[i+1+RADIUS][j+RADIUS].distToTarget > newLocCost) {
        nearbyLocs[i+1+RADIUS][j+RADIUS].distToTarget = newLocCost;
        pq.add(nearbyLocs[i+1+RADIUS][j+RADIUS]);
      }
      if (j-1 >= -RADIUS && nearbyLocs[i+RADIUS][j-1+RADIUS].distToTarget > newLocCost) {
        nearbyLocs[i+RADIUS][j-1+RADIUS].distToTarget = newLocCost;
        pq.add(nearbyLocs[i+RADIUS][j-1+RADIUS]);
      }
      if (j+1 <= RADIUS && nearbyLocs[i+RADIUS][j+1+RADIUS].distToTarget > newLocCost) {
        nearbyLocs[i+RADIUS][j+1+RADIUS].distToTarget = newLocCost;
        pq.add(nearbyLocs[i+RADIUS][j+1+RADIUS]);
      }
      if (i-1 >= -RADIUS && j-1 >= -RADIUS && nearbyLocs[i-1+RADIUS][j-1+RADIUS].distToTarget > newLocCost) {
        nearbyLocs[i-1+RADIUS][j-1+RADIUS].distToTarget = newLocCost;
        pq.add(nearbyLocs[i-1+RADIUS][j-1+RADIUS]);
      }
      if (i+1 <= RADIUS && j-1 >= -RADIUS && nearbyLocs[i+1+RADIUS][j-1+RADIUS].distToTarget > newLocCost) {
        nearbyLocs[i+1+RADIUS][j-1+RADIUS].distToTarget = newLocCost;
        pq.add(nearbyLocs[i+1+RADIUS][j-1+RADIUS]);
      }
      if (i-1 >= -RADIUS && j+1 <= RADIUS && nearbyLocs[i-1+RADIUS][j+1+RADIUS].distToTarget > newLocCost) {
        nearbyLocs[i-1+RADIUS][j+1+RADIUS].distToTarget = newLocCost;
        pq.add(nearbyLocs[i-1+RADIUS][j+1+RADIUS]);
      }
      if (i+1 <= RADIUS && j+1 <= RADIUS && nearbyLocs[i+1+RADIUS][j+1+RADIUS].distToTarget > newLocCost) {
        nearbyLocs[i+1+RADIUS][j+1+RADIUS].distToTarget = newLocCost;
        pq.add(nearbyLocs[i+1+RADIUS][j+1+RADIUS]);
      }
      lastLoc = newLoc;
    }
    if (lastLoc != null) {
      Direction dir = currentLoc.directionTo(lastLoc.loc);
      if (rc.canMove(dir)) rc.move(dir);
    }
    System.out.println(Clock.getBytecodesLeft());
  }
}
