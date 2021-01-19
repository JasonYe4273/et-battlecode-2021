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
}
