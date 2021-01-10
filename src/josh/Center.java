package josh;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class Center extends Robot {
	public Center(RobotController r) {
		super(r);
	}
	int lastInf = 1;
	int lastRakerRound = 0;
	public void turn() throws Exception {
		if(rc.getRoundNum() > 400 && rc.getInfluence() > 0) rc.bid(rc.getInfluence()/100+1);
		if(rc.getCooldownTurns() >= 1) return;
		if(rc.getInfluence() < 20) {
			build(RobotType.POLITICIAN, 1);
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
				else if(r.type == RobotType.MUCKRAKER) {
					enemyRStrength+=r.conviction;
					lastRakerRound = rc.getRoundNum();
				}	
			}
		}
		if(rc.getEmpowerFactor(rc.getTeam().opponent(), 20) > 1)
			lastRakerRound = rc.getRoundNum();
		System.out.println("p = "+politicians+" s="+slanderers);
		int inf = rc.getInfluence() - enemyPStrength;
		if(inf < 11) {
			build(RobotType.POLITICIAN, 1);
			return;
		}
		if(enemyPStrength > myPStrength) {
			build(RobotType.POLITICIAN, Math.min(rc.getInfluence(), enemyPStrength - myPStrength));
		} else
		if(enemyRStrength == 0 && (politicians*(rc.getRoundNum() - lastRakerRound) > slanderers || politicians > 20 || (rc.getInfluence()-lastInf)*100/(lastInf+1) < 5)&& inf<0x00ffffff) {
			build(RobotType.SLANDERER, Threshold.slandererThreshold(inf));
		} else {
			build(RobotType.POLITICIAN, Math.min(inf, 17 + inf/8));
		}
		lastInf = rc.getInfluence();
	}
	private void build(RobotType t, int influence) throws GameActionException {
		System.out.println("building "+t+" with inf "+influence);
		int offset = (int)(Math.random()*8);
		for (int i=0;i<8;i++) {
			Direction dir = RobotPlayer.directions[(i+offset)%8];
			if (rc.canBuildRobot(t, dir, influence)) {
				rc.buildRobot(t, dir, influence);
			}
		}
        
	}

}
