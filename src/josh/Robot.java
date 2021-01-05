package josh;

import battlecode.common.Clock;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import static josh.RobotPlayer.directions;

public class Robot {
	RobotController rc;
	public Robot(RobotController r) {
		rc = r;
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
