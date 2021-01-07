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
		if(rc.getRoundNum() > 400) rc.bid(rc.getInfluence()/100+1);
		if(rc.getInfluence() < 20) {
			return;
		}
		RobotInfo[] nearby = rc.senseNearbyRobots();
		int politicians = 0;
		int slanderers = 0;
		int myPStrength = 0;
		int enemyPStrength = 0;
		int enemyRStrength = 0;
		for(RobotInfo r:nearby) {
			if(r.team == rc.getTeam()) {
				if(r.type == RobotType.POLITICIAN) {
					politicians++;
					myPStrength += r.conviction;
				} else if(r.type == RobotType.SLANDERER) {
					slanderers++;
				}
			} else {
				if(r.type == RobotType.POLITICIAN)
					enemyPStrength+=r.conviction;
				else if(r.type == RobotType.MUCKRAKER)
					enemyRStrength+=r.conviction;
					
			}
		}
		//System.out.println("p = "+politicians+" s="+slanderers);
		int inf = rc.getInfluence() - enemyPStrength;
		if(enemyPStrength > myPStrength) {
			build(RobotType.POLITICIAN, Math.min(inf, enemyPStrength - myPStrength));
		} else
		if(enemyRStrength == 0 && (politicians*2 > slanderers || politicians > 20 )&& inf<0x00ffffff) {
			build(RobotType.SLANDERER, 20*(inf/20));
		} else {
			build(RobotType.POLITICIAN, Math.min(Math.min(inf, 30 + inf/2),123456));
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
