package josh;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import battlecode.common.*;

public class Center extends Robot {
	public Center(RobotController r) {
		super(r);
	}
	int lastInf = 1;
	int lastRakerRound = 0;
	int rakersBuilt= 0;
	Set<Integer> rakers = new HashSet<Integer>();
	public void turn() throws Exception {
		readNonfriendlyHQFlag();
		//if(rc.getRoundNum() > 1000) rc.resign();
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
		if(rc.getInfluence() > 1000 && nonfriendlyHQ!=null) {
			build(RobotType.POLITICIAN, 400);
		}
		if(rc.getEmpowerFactor(rc.getTeam().opponent(), 20) > 1)
			lastRakerRound = rc.getRoundNum();
		//System.out.println("p = "+politicians+" s="+slanderers);
		int inf = rc.getInfluence() - enemyPStrength;
		if(inf < 11) {
			build(RobotType.MUCKRAKER, 1);
			return;
		}
		if(slanderers > 0 && Math.random() < 1.0/(1.0+rakersBuilt)) {
			build(RobotType.MUCKRAKER, 1);
			rakersBuilt++;
		}
		int income =  rc.getInfluence() - lastInf;
		if(enemyPStrength > myPStrength) {
			build(RobotType.POLITICIAN, Math.min(rc.getInfluence(), enemyPStrength - myPStrength));
		} else if(enemyRStrength == 0 && (politicians*(rc.getRoundNum() - lastRakerRound) > slanderers || politicians > 20) && (inf<1000 || income<500) && (income < 60 || politicians > 11)) {
			build(RobotType.SLANDERER, Threshold.slandererThreshold(inf));
		} else {
			build(RobotType.POLITICIAN, Math.min(inf, 22 + inf/40));
		}
		lastInf = rc.getInfluence();
	}
	private void build(RobotType t, int influence) throws GameActionException {
		//System.out.println("building "+t+" with inf "+influence);
		int offset = (int)(Math.random()*8);
		for (int i=0;i<8;i++) {
			Direction dir = RobotPlayer.directions[(i+offset)%8];
			if (rc.canBuildRobot(t, dir, influence)) {
				rc.buildRobot(t, dir, influence);
				if(t == RobotType.MUCKRAKER)
					rakers.add(rc.senseRobotAtLocation(rc.getLocation().add(dir)).ID);
			}
		}
        
	}
	public void readNonfriendlyHQFlag() throws GameActionException {
		Iterator<Integer> i = rakers.iterator();
		//System.out.println("num rakers " +rakers.size());
		while(i.hasNext()) {
			int id = i.next();
			if(rc.canGetFlag(id)) {
				int f = rc.getFlag(id);
				if((f&0xf00000) == Robot.NONFRIENDLY_HQ) {
					MapLocation l = Robot.flagToLoc(rc.getLocation(), f);
					if((f&0x4000) == 0x4000) {
						if(nonfriendlyHQ.equals(l))
						nonfriendlyHQ = null;
					} else {
						nonfriendlyHQ = l;
						nonfriendlyHQround = rc.getRoundNum();
					}
				}
			} else {
				i.remove();
			}
		}
		sendNonfriendlyHQ();
	}

}
