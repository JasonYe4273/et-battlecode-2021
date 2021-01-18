package sprint2;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class LocationInfo implements Comparable<LocationInfo> {
  public MapLocation loc;
  public double passability;
  public double distToTarget;
  public boolean unoccupied;

  public LocationInfo (MapLocation loc, double passability, double distToTarget, boolean unoccupied) {
    this.loc = loc;
    this.passability = passability;
    this.distToTarget = distToTarget;
    this.unoccupied = unoccupied;
  }

  public int compareTo(LocationInfo other) {
    return (int) Math.signum(distToTarget - other.distToTarget);
  }
}
