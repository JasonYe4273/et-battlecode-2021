package josh;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

import java.util.Arrays;

public class Politician extends Robot {
	/*
	 * politicans want to stay slightly away from other politicans and your base, but near slanderers
	 * 
	 */
	public Politician(RobotController r) {
		super(r);
	}
	public void turn() throws Exception {
		RobotInfo[] nearby = rc.senseNearbyRobots();
		findRakerFlags(nearby);
		if(shouldAttackHQ(nearby))
			attackHQ();
		else if(rc.getConviction() <= 10)
			walling(nearby);
		else {
			checkEmpower(nearby);
			movement(nearby);
		}
		setRakerFlags();
		if(nonfriendlyHQ != null && rc.canSenseLocation(nonfriendlyHQ)) {
			RobotInfo r = rc.senseRobotAtLocation(nonfriendlyHQ);
			if(r.team == rc.getTeam())
				super.unsendNonfriendlyHQ();
		}
	}
	public boolean shouldAttackHQ(RobotInfo[] nearby) throws GameActionException {
		if(rc.getConviction() < 300)
			return false;
		//System.out.println("homeID = "+homeID);
		if(!rc.canGetFlag(homeID))
			return false;
		int f = rc.getFlag(homeID);
		if((f&0xf00000) != Robot.NONFRIENDLY_HQ)
			return false;
		nonfriendlyHQ = Robot.flagToLoc(rc.getLocation(), f);
		rc.setIndicatorLine(rc.getLocation(), nonfriendlyHQ, 128, 0, 255);
		boolean yes = true;
		for(RobotInfo r:nearby) {
			if(r.type == RobotType.POLITICIAN && (rc.getFlag(r.ID)&politicanMask)>0 && r.conviction > 300 && r.location.distanceSquaredTo(nonfriendlyHQ) < rc.getLocation().distanceSquaredTo(nonfriendlyHQ)) {
				//System.out.println(r.location);
				rc.setIndicatorDot(r.location, 255, 0, 255);
				yes = false;
			}
		}
		return yes;
	}
	public void attackHQ() throws GameActionException {
		int d = rc.getLocation().distanceSquaredTo(nonfriendlyHQ);
		if(d < 3 && rc.canEmpower(d))
			rc.empower(d);
		this.moveToward(nonfriendlyHQ);
	}
	public void walling(RobotInfo[] nearby) throws GameActionException {
		int wallRadius = 5;
		MapLocation nearestRaker = null;
		int nearestRakerD = 999;
		for(RobotInfo r:nearby) {
			if(r.team != rc.getTeam()) {
				if(r.type == RobotType.MUCKRAKER) {
					int d = rc.getLocation().distanceSquaredTo(r.location);
					if(d < nearestRakerD) {
						nearestRaker = r.location;
						nearestRakerD = d;
					}
				}
			}
		}
		if(nearestRakerD> 3 && nearestRaker != null) {
			moveToward(nearestRaker.add(nearestRaker.directionTo(home)));
		} else if(nearestRaker == null){
			int distFromHome = Robot.taxiDistance(home, rc.getLocation());
			if(distFromHome < wallRadius) {
				Direction d = home.directionTo(rc.getLocation());
				for(int i=0;i<4;i++) {
					if(rc.canMove(d)) {
						rc.move(d);
						break;
					}
					d = d.rotateLeft();
				}
			}
		}
	}
	/*
	 * empower conditions:
	 * a double kill
	 * or kill a center
	 * or kill a raker and there's a nearby slanderer
	 * or adjacent to enemy center, and there's an adjacent friendly politician
	 */
	public void checkEmpower(RobotInfo[] nearby) throws GameActionException {
		if(rc.getCooldownTurns() >= 1) return;
		if(rc.getConviction() <= 10) return;
		int[] unitsAtDist = new int[RobotType.POLITICIAN.actionRadiusSquared+1];
		int[] killsAtDist = new int[RobotType.POLITICIAN.actionRadiusSquared+1];
		RobotInfo[] nearerby = rc.senseNearbyRobots(RobotType.POLITICIAN.actionRadiusSquared);
		for(RobotInfo r:nearerby) {
			int d = r.location.distanceSquaredTo(rc.getLocation());
			unitsAtDist[d]++;
		}
		unitsAtDist[2] += unitsAtDist[1];
		unitsAtDist[4] += unitsAtDist[2];
		unitsAtDist[5] += unitsAtDist[4];
		unitsAtDist[8] += unitsAtDist[5];
		unitsAtDist[9] += unitsAtDist[8];
		int damage = (int)(rc.getEmpowerFactor(rc.getTeam(), 0) * rc.getConviction()) - GameConstants.EMPOWER_TAX;
		boolean adjToEnemyCenter = false;
		int numberFriendlyP = 0;
		for(RobotInfo r:nearerby) {
			if(r.team==rc.getTeam()) {
				if(r.type == RobotType.POLITICIAN)
					numberFriendlyP++;
				continue;
			}
			int a = (r.type == RobotType.ENLIGHTENMENT_CENTER)?10:1;
			int d = r.location.distanceSquaredTo(rc.getLocation());
			if(r.type == RobotType.ENLIGHTENMENT_CENTER && d==1)
				adjToEnemyCenter = true;
			switch(d) {
			case 1:
			if(r.conviction < damage/unitsAtDist[1]) 
				killsAtDist[1]+=a;
			case 2:
			if(r.conviction < damage/unitsAtDist[2]) 
				killsAtDist[2]+=a;
			case 4:
			if(r.conviction < damage/unitsAtDist[4]) 
				killsAtDist[4]+=a;
			case 5:
			if(r.conviction < damage/unitsAtDist[5]) 
				killsAtDist[5]+=a;
			case 8:
			if(r.conviction < damage/unitsAtDist[8]) 
				killsAtDist[8]+=a;
			case 9:
			if(r.conviction < damage/unitsAtDist[9]) 
				killsAtDist[9]+=a;
			default:
			}
		}
		if(adjToEnemyCenter && numberFriendlyP > 5)
			rc.empower(1);
		int maxKills = 0;
		int maxKillD = 0;
		for(int i=1;i<10;i++) {
			if(killsAtDist[i] > maxKills) {
				maxKillD = i;
				maxKills = killsAtDist[i];
			}
		}
		//System.out.println(Arrays.toString(killsAtDist));
		if(maxKills >= 2) {
			rc.empower(maxKillD);
			return;
		}
		int slanderers = 0;
		for(RobotInfo r:nearby) {
			if(r.team!=rc.getTeam() || r.type != RobotType.POLITICIAN) continue;
			if((rc.getFlag(r.ID) & politicanMask) != politicanMask) {
				rc.setIndicatorDot(r.location, 0, 0, 255);
				slanderers++;
			}
		}
		//System.out.println("slanderers = "+slanderers);
		if(slanderers > 1 || numberFriendlyP > 8) {
			if(killsAtDist[1] == 1)
				rc.empower(1);
			if(killsAtDist[2] == 1)
				rc.empower(2);
		}
	}
	int patrolRadius = 4;
	public void movement(RobotInfo[] nearby) throws GameActionException {

		/*
		MapLocation[] adj = new MapLocation[9];
		double[] h = new double[9];
		for(int i=0;i<8;i++) {
			if(rc.canMove(directions[i])) {
				adj[i] = rc.getLocation().add(directions[i]);
				h[i] = Math.random() * 10 + 4*home.distanceSquaredTo(rc.getLocation());
			}
		}
		adj[8] = rc.getLocation();
		*/
		int nearestPoliticanToRaker = 99999;
		int nearp = 0, farp = 0;
		int myDist = rc.getLocation().distanceSquaredTo(home);
		for(RobotInfo r:nearby) {
			if(r.team != rc.getTeam()) {
				if(r.type == RobotType.MUCKRAKER) {
					/*
					int d = r.location.distanceSquaredTo(rc.getLocation());
					if(d < rc.getType().actionRadiusSquared && rc.canEmpower(d)) {
						rc.empower(d);
						return;
					} else {*/
						this.moveToward(r.location);
						return;
					//}
				}
					/*
					if(rc.getLocation().distanceSquaredTo(home) < 100) {
						moveToward(new MapLocation((r.location.x + home.x)/2,(r.location.y + home.y)/2));
						return;
					}*/
			} else {
				if(r.type == RobotType.POLITICIAN) {
					if(r.location.distanceSquaredTo(home) < myDist)
						nearp++;
					else
						farp++;
				}
				if(raker == null) continue;
				if(r.type == RobotType.POLITICIAN && rc.canGetFlag(r.ID) && (rc.getFlag(r.ID)&politicanMask)>0) {
					int d2 = r.location.distanceSquaredTo(raker);
					if(d2 < nearestPoliticanToRaker) {
						nearestPoliticanToRaker = d2;
					}
					//if(d2 < rc.getLocation().distanceSquaredTo(raker))
						rc.setIndicatorDot(r.location, 255, 0, 0);
				}
			}
		}
		if(raker != null && rc.getLocation().distanceSquaredTo(raker) < nearestPoliticanToRaker) {
			moveToward(raker);
		} else {
			int d = rc.getLocation().distanceSquaredTo(home);
			if(nearp > 12 && d > patrolRadius*patrolRadius)
				patrolRadius++;
			if(nearp < 5 && patrolRadius > 5)
				patrolRadius--;
			patrol(home,patrolRadius*patrolRadius,(patrolRadius+2)*(patrolRadius+2));
			
			//if(lastMoveTurn + 10 < rc.getRoundNum())
			//	System.out.println("patrolR = "+patrolRadius);
		}
		//System.out.println("PatrolRadius="+patrolRadius+" nearp="+nearp+" farp="+farp);
	}
	
}
