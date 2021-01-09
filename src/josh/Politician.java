package josh;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;
import static josh.RobotPlayer.directions;

import java.util.Arrays;

public class Politician extends Robot {
	/*
	 * politicans want to stay slightly away from other politicans and your base, but near slanderers
	 * 
	 */
	MapLocation raker;
	int rakerRound;
	public static final int RAKER_ROUNDS = 24;
	public int politicanMask = 0x400000;
	public Politician(RobotController r) {
		super(r);
	}
	public void turn() throws Exception {
		RobotInfo[] nearby = rc.senseNearbyRobots();
		findRakerFlags(nearby);
		if(rc.getConviction() <= 10)
			walling(nearby);
		else {
			checkEmpower();
			movement(nearby);
		}
		setRakerFlags();
	}
	public void walling(RobotInfo[] nearby) throws GameActionException {
		int wallRadius = 25;
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
		if(nearestRakerD> 0 && nearestRaker != null) {
			moveToward(nearestRaker.add(nearestRaker.directionTo(home)));
		} else {
			int distFromHome = rc.getLocation().distanceSquaredTo(home);
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
	public void checkEmpower() throws GameActionException {
		if(rc.getCooldownTurns() >= 1) return;
		if(rc.getConviction() <= 10) return;
		int[] unitsAtDist = new int[RobotType.POLITICIAN.actionRadiusSquared+1];
		int[] killsAtDist = new int[RobotType.POLITICIAN.actionRadiusSquared+1];
		RobotInfo[] nearerby = rc.senseNearbyRobots(RobotType.POLITICIAN.actionRadiusSquared);
		for(RobotInfo r:nearerby) {
			int d = r.location.distanceSquaredTo(rc.getLocation());
			if(d <= RobotType.POLITICIAN.actionRadiusSquared) {
				unitsAtDist[d]++;
			}
		}
		unitsAtDist[2] += unitsAtDist[1];
		unitsAtDist[4] += unitsAtDist[2];
		unitsAtDist[5] += unitsAtDist[4];
		unitsAtDist[8] += unitsAtDist[5];
		unitsAtDist[9] += unitsAtDist[8];
		int damage = (int)(rc.getEmpowerFactor(rc.getTeam(), 0) * rc.getConviction()) - GameConstants.EMPOWER_TAX;
		for(RobotInfo r:nearerby) {
			if(r.team==rc.getTeam()) continue;
			switch(r.location.distanceSquaredTo(rc.getLocation())) {
			case 1:
			if(r.conviction <= damage/unitsAtDist[1]) 
				killsAtDist[1]++;
			case 2:
			if(r.conviction <= damage/unitsAtDist[2]) 
				killsAtDist[2]++;
			case 4:
			if(r.conviction <= damage/unitsAtDist[4]) 
				killsAtDist[4]++;
			case 5:
			if(r.conviction <= damage/unitsAtDist[5]) 
				killsAtDist[5]++;
			case 8:
			if(r.conviction <= damage/unitsAtDist[8]) 
				killsAtDist[8]++;
			case 9:
			if(r.conviction <= damage/unitsAtDist[9]) 
				killsAtDist[9]++;
			default:
			}
		}
		int maxKills = 0;
		int maxKillD = 0;
		for(int i=1;i<10;i++) {
			if(killsAtDist[i] > maxKills) {
				maxKillD = i;
				maxKills = killsAtDist[i];
			}
		}
		if(maxKills > 2)
			rc.empower(maxKillD);
		if(killsAtDist[1] == 1)
			rc.empower(1);
		if(killsAtDist[2] == 1)
			rc.empower(2);
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
		int nearestPoliticanToRaker = 999;
		int nearp = 0, farp = 0;
		int politicians = 0;
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
					politicians++;
					if(r.location.distanceSquaredTo(home) < myDist)
						nearp++;
					else
						farp++;
				}
				if(raker == null) continue;
				if(r.type == RobotType.POLITICIAN && rc.canGetFlag(r.ID) && (rc.getFlag(r.ID)&0x400000)>0) {
					int d2 = r.location.distanceSquaredTo(raker);
					if(d2 < nearestPoliticanToRaker) {
						nearestPoliticanToRaker = d2;
					}
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
		}
		//System.out.println("PatrolRadius="+patrolRadius+" nearp="+nearp+" farp="+farp);
	}
	
	public void findRakerFlags(RobotInfo[] nearby) throws GameActionException {
		raker = null;
		rakerRound = 99999;
		for(RobotInfo r:nearby) {
			if(r.team==rc.getTeam()) {
				if(r.type == RobotType.POLITICIAN) {
					if(rc.canGetFlag(r.ID)) {
						int f = rc.getFlag(r.ID);
						if((f&0xffff) > 0) {
							int rr = Robot.flagToRound(rc.getRoundNum(), f);
							MapLocation l = Robot.flagToLoc(r.location, f);
							if(rr < rakerRound && l.distanceSquaredTo(rc.getLocation()) > 20) {
								rakerRound = rr;
								raker = l;
							}
						}
					}
				}
			} else {
				if(r.type == RobotType.MUCKRAKER) {
					raker = r.location;
					rakerRound = 0;
				}
			}
		}
		if(rakerRound < RAKER_ROUNDS) {
			rc.setIndicatorLine(rc.getLocation(), raker, 0, 255, 0);
			return;
		}
	}
	public void setRakerFlags() throws GameActionException {
		if(rakerRound > RAKER_ROUNDS) {
			if((rc.getFlag(rc.getID())&0xffff)>0)
				rc.setFlag(0 | politicanMask);
			return;
		}
		rc.setFlag(politicanMask | Robot.roundToFlag(rc.getRoundNum() - rakerRound) | Robot.locToFlag(rc.getLocation(), raker));
	}
}
