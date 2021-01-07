package josh;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

import static josh.RobotPlayer.directions;

public class Robot {
	RobotController rc;
	MapLocation home;
	public Robot(RobotController robot) {
		rc = robot;
		for(RobotInfo r:rc.senseNearbyRobots(2, rc.getTeam())) {
			if(r.type==RobotType.ENLIGHTENMENT_CENTER)
				home = r.location;
		}
		if(home == null)
			home = rc.getLocation();
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
	public void moveToward(MapLocation l) throws GameActionException {
		if(rc.getCooldownTurns()>1) return;
		rc.setIndicatorLine(rc.getLocation(), l, 255, 255, 0);
		Direction d = rc.getLocation().directionTo(l);
		Direction[] dd = {d, d.rotateLeft(), d.rotateRight(), d.rotateLeft().rotateLeft(), d.rotateRight().rotateRight()};
		for(Direction dir:dd) {
			if(rc.canMove(dir)) {
				rc.move(dir);
				return;
			}
		}
	}
	public static int locToFlag(MapLocation from, MapLocation to) {
		return (to.x-from.x+64)<< 7 | (to.y-from.y+64);
	}
	public static MapLocation flagToLoc(MapLocation from, int flag) {
		//System.out.println("reading "+(((flag&0x3f80)>>7)-64)+","+((flag&0x007f)-64));
		return new MapLocation(from.x+(((flag&0x3f80)>>7)-64),from.y+((flag&0x007f)-64));
	}
	public static int roundToFlag(int round) {
		return (round&0x7f)<<14;
	}
	public static int flagToRound(int round, int flag) {
		return (round - ((flag>>14)&0x7f))&0x7f;
	}
	public void moveInDirections(Direction[] dirs) throws GameActionException {
		for(Direction d : dirs) {
			if(rc.canMove(d)) {
				rc.move(d);
				return;
			}
		}
	}
	public void patrol(MapLocation l, int minR, int maxR) throws GameActionException {
		int dist = rc.getLocation().distanceSquaredTo(l);
		if(dist < minR) {
			Direction d = l.directionTo(rc.getLocation());
			Direction[] dirs = {d, d.rotateLeft(), d.rotateRight(), d.rotateLeft().rotateLeft(), d.rotateRight().rotateRight()};
			moveInDirections(dirs);
		} else if(dist > maxR) {
			moveToward(l);
		} else {
			Direction d = l.directionTo(rc.getLocation());
			if(Math.random() < .5) {
				Direction[] dirs = {d.rotateLeft().rotateLeft(), d.rotateRight().rotateRight(), d.rotateLeft(), d.rotateRight()};
				moveInDirections(dirs);
			} else {
				Direction[] dirs = {d.rotateRight().rotateRight(), d.rotateLeft().rotateLeft(), d.rotateRight(), d.rotateLeft()};
				moveInDirections(dirs);
			}
		}
	}
}
