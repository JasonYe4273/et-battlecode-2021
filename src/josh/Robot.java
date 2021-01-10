package josh;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class Robot {
	/*
	 * first 4 bits of flag determine type
	 */
	RobotController rc;
	MapLocation home;
	int lastMoveTurn = 0;

	MapLocation raker;
	int rakerRound;
	public static final int RAKER_ROUNDS = 12;
	public int politicanMask = 0x080000;
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
				lastMoveTurn = rc.getRoundNum();
				return;
			}
		}
	}
	public static int locToFlag(MapLocation to) {
		return (to.x&0x7f)<< 7 | (to.y&0x7f);
	}
	public static MapLocation flagToLoc(MapLocation from, int flag) {
		//System.out.println("reading "+(((flag&0x3f80)>>7)-64)+","+((flag&0x007f)-64));\
		int x = (flag&0x3f80)>>7;
		x = (from.x&0xffff80)|x;
		if(from.x - x > 64)
			x += 128;
		else if(from.x - x < -64)
			x -= 128;
		int y = flag&0x007f;
		y = (from.y&0xffff80)|y;
		if(from.y - y > 64)
			y += 128;
		else if(from.y - y < -64)
			y -= 128;
		return new MapLocation(x,y);
	}
	public static int roundToFlag(int round) {
		return (round&0xf)<<14;
	}
	public static int flagToRound(int round, int flag) {
		return (round - ((flag>>14)&0xf))&0xf;
	}
	private boolean moveInDirections(Direction[] dirs, int minR, int maxR, MapLocation l) throws GameActionException {
		for(Direction d : dirs) {
			int dist = rc.getLocation().add(d).distanceSquaredTo(l);
			if(dist < minR || dist > maxR) continue;
			if(rc.canMove(d)) {
				rc.move(d);
				lastMoveTurn = rc.getRoundNum();
				return true;
			}
		}
		return false;
	}
	private static final int LEFT = 0, RIGHT = 1;
	private int patrolDirection = Math.random()>.5?RIGHT:LEFT;
	public void patrol(MapLocation l, int minR, int maxR) throws GameActionException {
		if(rc.getCooldownTurns() >= 1) return;
		int dist = rc.getLocation().distanceSquaredTo(l);
		if(dist < minR) {
			Direction d = l.directionTo(rc.getLocation());
			Direction[] dirs = {d, d.rotateLeft(), d.rotateRight(), d.rotateLeft().rotateLeft(), d.rotateRight().rotateRight()};
			moveInDirections(dirs, 0, 99999, l);
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
	

	public void findRakerFlags(RobotInfo[] nearby) throws GameActionException {
		raker = null;
		rakerRound = 99999;
		for(RobotInfo r:nearby) {
			if(r.team==rc.getTeam()) {
				if(true || r.type == RobotType.POLITICIAN) {
					if(rc.canGetFlag(r.ID)) {
						int f = rc.getFlag(r.ID);
						if((f&0xf00000) == 0x100000) {
							int rr = Robot.flagToRound(rc.getRoundNum()>>0, f);
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
		//if(raker != null)
			//System.out.println("rakerRound = "+rakerRound);
		if(rakerRound > RAKER_ROUNDS) {
			if((rc.getFlag(rc.getID())&0xf00000)==0x100000)
				rc.setFlag(0 | politicanMask);
			return;
		}
		rc.setFlag(0x100000 | politicanMask | Robot.roundToFlag((rc.getRoundNum()>>0) - rakerRound) | Robot.locToFlag(raker));
	}
}
