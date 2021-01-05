package josh;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;
import static josh.RobotPlayer.*;

public class Center extends Robot {
	public Center(RobotController r) {
		super(r);
	}

	public void turn() throws Exception {
		rc.bid(rc.getInfluence()/100+1);
		if(rc.getInfluence() < 20) {
			return;
		}
		RobotInfo[] nearby = rc.senseNearbyRobots();
		int politicians = 0;
		int slanderers = 0;
		for(RobotInfo r:nearby) {
			if(r.team == rc.getTeam()) {
				if(r.type == RobotType.POLITICIAN) {
					politicians++;
				} else if(r.type == RobotType.SLANDERER) {
					slanderers++;
				}
			}
		}
		System.out.println("p = "+politicians+" s="+slanderers);
		if(politicians > slanderers) {
			build(RobotType.SLANDERER, rc.getInfluence()/20);
		} else {
			build(RobotType.POLITICIAN, 30);
		}
	}
	private void build(RobotType t, int influence) throws GameActionException {
		int offset = (int)(Math.random()*8);
		for (int i=0;i<8;i++) {
			Direction dir = directions[(i+offset)%8];
			if (rc.canBuildRobot(t, dir, influence)) {
				rc.buildRobot(t, dir, influence);
			}
		}
        
	}

}
