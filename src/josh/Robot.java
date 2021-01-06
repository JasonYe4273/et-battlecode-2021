package josh;

import battlecode.common.Clock;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

import static josh.RobotPlayer.directions;

public class Robot {
	RobotController rc;
	MapLocation home;
	public Robot(RobotController robot) {
		rc = robot;
		for(RobotInfo r:rc.senseNearbyRobots(2, rc.getTeam())) {
			if(r.type==RobotType.ENLIGHTENMENT_CENTER)
				home = r.location;
		}
	}
	public void turn() throws Exception {
		
	}
	public void run() {
		while(true) {
			try {
			turn();
			} catch(Exception e) {
				e.printStackTrace();
			}
			Clock.yield();
		}
	}
	public static MapLocation[] getAdjacentLocations(MapLocation l) {
		MapLocation[] result = new MapLocation[9];
		for(int i=0;i<8;i++) {
			result[i] = l.add(directions[i]);
		}
		result[8]=l;
		return result;
	}
}
