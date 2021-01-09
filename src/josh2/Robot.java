package josh2;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

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
			result[i] = l.add(RobotPlayer.directions[i]);
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
	private boolean moveInDirections(Direction[] dirs, int minR, int maxR, MapLocation l) throws GameActionException {
		for(Direction d : dirs) {
			int dist = rc.getLocation().add(d).distanceSquaredTo(l);
			if(dist < minR || dist > maxR) continue;
			if(rc.canMove(d)) {
				rc.move(d);
				return true;
			}
		}
		return false;
	}
	private static final int LEFT = 0, RIGHT = 1;
	private int patrolDirection = Math.random()>.5?RIGHT:LEFT;
	public void patrol(MapLocation l, int minR, int maxR) throws GameActionException {
		int dist = rc.getLocation().distanceSquaredTo(l);
		if(dist < minR) {
			Direction d = l.directionTo(rc.getLocation());
			Direction[] dirs = {d, d.rotateLeft(), d.rotateRight(), d.rotateLeft().rotateLeft(), d.rotateRight().rotateRight()};
			moveInDirections(dirs, 0, 999, l);
		} else if(dist > maxR) {
			moveToward(l);
		} else {
			Direction d = l.directionTo(rc.getLocation());
			if(patrolDirection == LEFT) {
				d = d.rotateLeft().rotateLeft();
			} else {
				d = d.rotateRight().rotateRight();
			}
			Direction[] dirs = {d, d.rotateLeft(), d.rotateRight()};
			if(!moveInDirections(dirs, minR, maxR, l)) {
				patrolDirection = patrolDirection==LEFT?RIGHT:LEFT;
				d = d.opposite();
				Direction[] dirs2 = {d, d.rotateLeft(), d.rotateRight()};
				moveInDirections(dirs2, minR, maxR, l);
			}
		}
	}
	public static int taxiDistance(MapLocation l1, MapLocation l2) {
		return Math.max(Math.abs(l1.x - l2.x), Math.abs(l1.y - l2.y));
	}
}
