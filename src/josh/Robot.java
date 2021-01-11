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
	 * 0x100000: raker
	 * 	1-14 location
	 *  15-18 round mod 16
	 *  20 am i a politician or a slanderer
	 * 0x200000: enemy/neutral HQ
	 *  1-14 location
	 *  sent by rakers and by HQ
	 */
	RobotController rc;
	MapLocation home;
	int lastMoveTurn = 0;

	MapLocation raker;
	int rakerRound;
	public static final int RAKER_ROUNDS = 12;
	public static final int NONFRIENDLY_HQ = 0x200000;
	public int politicanMask = 0x080000;
	int homeID;
	public Robot(RobotController robot) {
		rc = robot;
		for(RobotInfo r:rc.senseNearbyRobots(3, rc.getTeam())) {
			if(r.type==RobotType.ENLIGHTENMENT_CENTER) {
				home = r.location;
				homeID = r.ID;
			}
		}
		if(home == null) {
			home = rc.getLocation();
			homeID = -1;
		}
	}
	public void turn() throws Exception {
		
	}
	public void run() {
		while(true) {
			try {
			rc.setIndicatorDot(home, 255, 255, 255);
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
	public void moveInDirection(Direction d) throws GameActionException {
		Direction[] dd = {d, d.rotateLeft(), d.rotateRight(), d.rotateLeft().rotateLeft(), d.rotateRight().rotateRight()};
		for(Direction dir:dd) {
			if(rc.canMove(dir)) {
				rc.move(dir);
				lastMoveTurn = rc.getRoundNum();
				break;
			}
		}
	}
	public void moveToward(MapLocation l) throws GameActionException {
		if(rc.getCooldownTurns()>1) return;
		rc.setIndicatorLine(rc.getLocation(), l, 255, 255, 0);
		if(rc.getLocation().equals(l)) return;
		if(rc.getLocation().isAdjacentTo(l)) {
			Direction d = rc.getLocation().directionTo(l);
			if(rc.canMove(d))
				rc.move(d);
			return;
		}
		Direction d = rc.getLocation().directionTo(l);
		moveInDirection(d);
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
				if(r.type == RobotType.MUCKRAKER && (rakerRound > 0 || raker.distanceSquaredTo(rc.getLocation()) > r.location.distanceSquaredTo(rc.getLocation()))) {
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
