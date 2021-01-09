package josh2;

import battlecode.common.*;

public class Muckraker extends Robot {

	public Muckraker(RobotController r) {
		super(r);
	}
	MapLocation target = null;
	public void turn() throws GameActionException {
		checkEdges();
		RobotInfo[] nearby = rc.senseNearbyRobots();
		//move away from nearby friendly rakers
		//charge enemy slanderers
		MapLocation nearestRaker = null;
		for(RobotInfo r:nearby) {
			if(r.team != rc.getTeam() && r.type == RobotType.SLANDERER) {
				if(rc.canExpose(r.location))
					rc.expose(r.location);
				else
					moveToward(r.location);
				return;
			} else if(r.team == rc.getTeam() && r.type == RobotType.MUCKRAKER) {
				if(nearestRaker == null || rc.getLocation().distanceSquaredTo(nearestRaker) > rc.getLocation().distanceSquaredTo(r.location))
					nearestRaker = r.location;
			}
		}
		if(nearestRaker != null) {
			moveToward(rc.getLocation().add(nearestRaker.directionTo(rc.getLocation())));
		}
		while(target == null || rc.getLocation().distanceSquaredTo(target) < 25 || !onTheMap(target)) {
			double angle = Math.random() * Math.PI * 2;
			target = rc.getLocation().translate((int)(Math.cos(angle) * 30),(int)( Math.sin(angle) * 30));
		}
		moveToward(target);
	}
	int mapXmin = -1;
	int mapXmax = 999999;
	int mapYmin = -1;
	int mapYmax = 999999;
	public void checkEdges() throws GameActionException {
		if(mapXmin == -1) {
			for(int i=5;i>0 && !rc.onTheMap(rc.getLocation().translate(-i, 0));i--)
				mapXmin = rc.getLocation().x - i;
		}
		if(mapXmax == 999999) {
			for(int i=5;i>0 && !rc.onTheMap(rc.getLocation().translate(i, 0));i--)
				mapXmax = rc.getLocation().x + i;
		}
		if(mapYmin == -1) {
			for(int i=5;i>0 && !rc.onTheMap(rc.getLocation().translate(0, -i));i--)
				mapYmin = rc.getLocation().y - i;
		}
		if(mapYmax == 999999) {
			for(int i=5;i>0 && !rc.onTheMap(rc.getLocation().translate(0, i));i--)
				mapYmax = rc.getLocation().y + i;
		}
	}
	public boolean onTheMap(MapLocation l) {
		return l.x > mapXmin && l.x < mapXmax && l.y > mapYmin && l.y < mapYmax;
	}

}
