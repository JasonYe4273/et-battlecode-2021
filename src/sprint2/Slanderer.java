package sprint2;

import java.util.Arrays;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class Slanderer extends Politician {
	int homeAdjLimit=0;
	public Slanderer(RobotController r) throws GameActionException {
		super(r);
		for(Direction d:RobotPlayer.directions) {
			if(rc.onTheMap(home.add(d)))
				homeAdjLimit++;
		}
		politicianMask = 0;
		patrolRadius = 1;
	}

	public void turn() throws Exception {
		if(rc.getType() == RobotType.POLITICIAN) {
			super.turn();
			return;
		}

		RobotInfo[] nearby = rc.senseNearbyRobots();
		findRakerFlags(nearby);
		movementS(nearby);
		setRakerFlags();
	}
	public void movementS(RobotInfo[] nearby) throws GameActionException {
		if(raker != null && raker.distanceSquaredTo(rc.getLocation()) < 400) {
			moveInDirection(raker.directionTo(rc.getLocation()));
			return;
		}
		/*
		double scale = Math.sqrt(15/rc.getLocation().distanceSquaredTo(home));
		MapLocation l = new MapLocation((int)(scale*(rc.getLocation().x-home.x)+rc.getLocation().x),
				(int)(scale*(rc.getLocation().y-home.y)+rc.getLocation().y));
		for(RobotInfo r:nearby) {
			if(r.team==rc.getTeam()) {
				if(r.type == RobotType.POLITICIAN) {
					l.add(rc.getLocation().directionTo(r.location));
				}
			}
		}
		moveToward(l);
		*/
		int nearp = 0, farp = 0;
		int myDist = rc.getLocation().distanceSquaredTo(home);
		int farthest = 0;
		int homeAdj = 0;
		MapLocation nearestEnemy = null;
		for(RobotInfo r:nearby) {
			int d = home.distanceSquaredTo(r.location);
			if(r.team==rc.getTeam()) {
				if(d < 3) homeAdj++;
				if(r.type == RobotType.POLITICIAN) {
					if(d < myDist)
						nearp++;
					else
						farp++;
					if(d > farthest)
						farthest = d;
				}
			} else {
				if(nearestEnemy == null || rc.getLocation().distanceSquaredTo(nearestEnemy) < rc.getLocation().distanceSquaredTo(r.location))
					nearestEnemy = r.location;
			}
		}
		if(nearestEnemy != null) {
			moveInDirection(nearestEnemy.directionTo(rc.getLocation()));
			return;
		}
		if(patrolRadius > 2 && farp<2)
			patrolRadius--;
		int d = rc.getLocation().distanceSquaredTo(home);
		if(nearp > 10 && farp>5 && d > patrolRadius*patrolRadius || homeAdj >= homeAdjLimit)
			patrolRadius++;
		patrol(home,patrolRadius*patrolRadius,(patrolRadius+2)*(patrolRadius+2));
		//System.out.println("PatrolRadius="+patrolRadius);
	}
	
	
		/*
		MapLocation[] adj = new MapLocation[9];
		double[] h = new double[9];
		for(int i=0;i<8;i++) {
			if(rc.canMove(directions[i])) {
				adj[i] = rc.getLocation().add(directions[i]);
				h[i] = Math.random() * 10;
			}
		}
		adj[8] = rc.getLocation();
		
		for(RobotInfo r:nearby) {
			if(r.team != rc.getTeam()) {
				continue;
			} else {
				for(int i=0;i<9;i++) {
					if(adj[i]==null)
						continue;
					switch (r.type) {
					case POLITICIAN: 
						h[i] -= 100/r.location.distanceSquaredTo(adj[i]);
						break;
					case SLANDERER: 
						h[i] -= 200/r.location.distanceSquaredTo(adj[i]);
						break;
					case ENLIGHTENMENT_CENTER:
						h[i] += 500/r.location.distanceSquaredTo(adj[i]);
						break;
					default: break;
					}
				}
			}
		} 
		//System.out.println(Arrays.toString(adj));
		//System.out.println(Arrays.toString(h));
		double min = 10000;
		int mini = 8;
		for(int i=0;i<9;i++) {
			if(h[i] < min  && adj[i] != null) {
				min = h[i];
				mini = i;
			}
		}
		if(mini!=8 && rc.canMove(directions[mini]))
			rc.move(directions[mini]);
			
		double scale = Math.sqrt(15/rc.getLocation().distanceSquaredTo(home));
		MapLocation l = new MapLocation((int)(scale*(rc.getLocation().x-home.x)+rc.getLocation().x),
				(int)(scale*(rc.getLocation().y-home.y)+rc.getLocation().y));
		MapLocation raker = null;
		int rakerRound = 99999;
		for(RobotInfo r:nearby) {
			if(r.team==rc.getTeam()) {
				if(r.type == RobotType.POLITICIAN) {
					l.add(rc.getLocation().directionTo(r.location));
					if(rc.canGetFlag(r.ID)) {
						int f = rc.getFlag(r.ID);
						//System.out.println("flag "+f);
						if(f > 0) {
							rakerRound = Robot.flagToRound(rc.getRoundNum(), f);
							raker = Robot.flagToLoc(r.location, f);
							System.out.println("Raker at "+raker+" "+rakerRound+" rounds ago");
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
		if(rakerRound < 48) {
			rc.setIndicatorLine(rc.getLocation(), raker, 0, 255, 0);
			super.moveToward(rc.getLocation().add(raker.directionTo(rc.getLocation())));
			rc.setFlag(Robot.roundToFlag(rc.getRoundNum() - rakerRound) | Robot.locToFlag(rc.getLocation(), raker));
			return;
		} else {
			rc.setFlag(0);
		}
		super.moveToward(l);
	}
	*/


}
