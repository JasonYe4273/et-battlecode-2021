package sprint2;

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
	 *  15 1 if mine
	 *  16 1 if enemy, 0 if neutral
   *  17-20 strength of HQ / 64
	 *  sent by rakers and by HQ
	 */
	
	/*
	 * HQ location info:
	 * centers will cycle through all known nonfriendly locations, indicating whether they beieve it to be neutral or enemy
	 * any raker can report an HQ location.
	 */
	public static final boolean DEBUG = true;

	RobotController rc;
	MapLocation home;
	int lastMoveTurn = 0;

	MapLocation nonfriendlyHQ = null; //this robot's locally detected nonfriendly HQ
	int nonfriendlyHQround = 0; //when such a nonfriendly HQ was last seen
	MapLocation[] nonfriendlyHQs = new MapLocation [10]; //list of all nonfriendlyHQs broadcast by our HQ
	int[] nonfriendlyHQstrengths = new int [10]; //list of all nonfriendlyHQs strengths (divided by 64) broadcast by our HQ
	boolean[] enemyHQs = new boolean[10]; //whether each HQ in the above list is neutral or enemy
	int[] nonfriendlyHQrounds = new int[10]; //when we last heard about each HQ in the above list
	MapLocation raker;
	int rakerRound;
	public static final int RAKER_ROUNDS = 12;
	public static final int NONFRIENDLY_HQ = 0x200000;
	public static final int FRIENDLY_HQ = 0x4000;
	public static final int ENEMY_HQ = 0x8000;
	public static final int NEUTRAL_HQ = 0;
	public int politicianMask = 0x080000;
	int homeID;
	public Robot(RobotController robot) {
		rc = robot;
		if (rc.getType() == RobotType.ENLIGHTENMENT_CENTER) {
			home = rc.getLocation();
			homeID = rc.getID();
			return;
		}
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
			if (DEBUG) rc.setIndicatorDot(home, 255, 255, 255);
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
		double[] suitability = {1,.5,.5,.1,.1};
		for(int i=0;i<5;i++) {
			MapLocation l = rc.getLocation().add(dd[i]);
			if(rc.onTheMap(l))
				suitability[i] *= rc.sensePassability(l);
		}
		double best = 0;
		Direction bestD = null;
		for(int i=0;i<5;i++) {
			if(suitability[i]>best && rc.canMove(dd[i])) {
				best = suitability[i];
				bestD = dd[i];
			}
		}
		if(bestD != null) {
			rc.move(bestD);
			lastMoveTurn = rc.getRoundNum();
		}
	}
	public void moveToward(MapLocation l) throws GameActionException {
		if(rc.getCooldownTurns()>1) return;
		if (DEBUG) rc.setIndicatorLine(rc.getLocation(), l, 255, 255, 0);
		if(rc.getLocation().equals(l)) return;
		if(rc.getLocation().isAdjacentTo(l)) {
			Direction d = rc.getLocation().directionTo(l);
			if(rc.canMove(d))
				rc.move(d);
			return;
		}
    if (Clock.getBytecodesLeft() < 12000) {
      Direction d = rc.getLocation().directionTo(l);
      moveInDirection(d);
    } else {
      System.out.println(l);
      System.out.println(Clock.getBytecodesLeft());
      // try to do more intelligent navigation within a 7x7 square centered at the current unit
      // TODO: Make this better and more bytecode efficient (probably just replace with Dijkstra)
      double [][] passabilities = new double [7][7]; // actually inverse passabilities (cost of moving to the square)
      double [][] distancesToTarget = new double [7][7];
      boolean [][] unoccupiedLocs = new boolean[7][7];
      // initialize arrays
      MapLocation currentLoc = rc.getLocation();
      for (int i = -3; i <= 3; i ++) {
        for (int j = -3; j <= 3; j ++) {
          MapLocation newLoc = currentLoc.translate(i, j);
          if (!rc.onTheMap(newLoc)) {
            passabilities[i+3][j+3] = Double.MAX_VALUE;
            distancesToTarget[i+3][j+3] = Double.MAX_VALUE;
            unoccupiedLocs[i+3][j+3] = false;
          } else {
            passabilities[i+3][j+3] = 1.0 / rc.sensePassability(newLoc);
            distancesToTarget[i+3][j+3] = Math.max(Math.abs(newLoc.x - l.x), Math.abs(newLoc.y - l.y)) * 100;
            unoccupiedLocs[i+3][j+3] = true;
          }
        }
      }
      // add in all nearby robots to their locations
      for (RobotInfo r : rc.senseNearbyRobots()) {
        MapLocation robotLoc = r.location;
        if (-3 <= robotLoc.x - currentLoc.x && robotLoc.x - currentLoc.x <= 3 
            && -3 <= robotLoc.y - currentLoc.y && robotLoc.y - currentLoc.y <= 3)
          unoccupiedLocs[robotLoc.x - currentLoc.x + 3][robotLoc.y - currentLoc.y + 3] = false;
      }
      System.out.println(Clock.getBytecodesLeft());
      int maxIterations = 2;
      for (int counter = 0; counter < maxIterations; counter ++) {
        for (int i = 1; i < 5; i ++) {
          for (int j = 1; j < 5; j ++) {
            // check all the edges to see if we can relax in either direction
            // horizontal edge (-)
            if (unoccupiedLocs[i+1][j] && distancesToTarget[i][j] - distancesToTarget[i+1][j] > passabilities[i+1][j]) distancesToTarget[i][j] = distancesToTarget[i+1][j] + passabilities[i+1][j];
            if (unoccupiedLocs[i][j] && distancesToTarget[i+1][j] - distancesToTarget[i][j] > passabilities[i][j]) distancesToTarget[i+1][j] = distancesToTarget[i][j] + passabilities[i][j];
            // vertical edge (|)
            if (unoccupiedLocs[i][j+1] && distancesToTarget[i][j] - distancesToTarget[i][j+1] > passabilities[i][j+1]) distancesToTarget[i][j] = distancesToTarget[i][j+1] + passabilities[i][j+1];
            if (unoccupiedLocs[i][j] && distancesToTarget[i][j+1] - distancesToTarget[i][j] > passabilities[i][j]) distancesToTarget[i][j+1] = distancesToTarget[i][j] + passabilities[i][j];
            // diagonal edge (/)
            if (unoccupiedLocs[i+1][j+1] && distancesToTarget[i][j] - distancesToTarget[i+1][j+1] > passabilities[i+1][j+1]) distancesToTarget[i][j] = distancesToTarget[i+1][j+1] + passabilities[i+1][j+1];
            if (unoccupiedLocs[i][j] && distancesToTarget[i+1][j+1] - distancesToTarget[i][j] > passabilities[i][j]) distancesToTarget[i+1][j+1] = distancesToTarget[i][j] + passabilities[i][j];
            // diagonal edge (\)
            if (unoccupiedLocs[i+1][j] && distancesToTarget[i][j+1] - distancesToTarget[i+1][j] > passabilities[i+1][j]) distancesToTarget[i][j+1] = distancesToTarget[i+1][j] + passabilities[i+1][j];
            if (unoccupiedLocs[i][j+1] && distancesToTarget[i+1][j] - distancesToTarget[i][j+1] > passabilities[i][j+1]) distancesToTarget[i+1][j] = distancesToTarget[i][j+1] + passabilities[i][j+1];
          }
        }
      }
      // move to adjacent location with the nearest cost
      double closestNeighbor = Double.MAX_VALUE;
      Direction bestDir = null;
      if (unoccupiedLocs[2][4] && distancesToTarget[2][4] < closestNeighbor) { closestNeighbor = distancesToTarget[2][4]; bestDir = Direction.NORTHWEST; }
      if (unoccupiedLocs[2][3] && distancesToTarget[2][3] < closestNeighbor) { closestNeighbor = distancesToTarget[2][3]; bestDir = Direction.WEST; }
      if (unoccupiedLocs[2][2] && distancesToTarget[2][2] < closestNeighbor) { closestNeighbor = distancesToTarget[2][2]; bestDir = Direction.SOUTHWEST; }
      if (unoccupiedLocs[3][2] && distancesToTarget[3][2] < closestNeighbor) { closestNeighbor = distancesToTarget[3][2]; bestDir = Direction.SOUTH; }
      if (unoccupiedLocs[4][2] && distancesToTarget[4][2] < closestNeighbor) { closestNeighbor = distancesToTarget[4][2]; bestDir = Direction.SOUTHEAST; }
      if (unoccupiedLocs[4][3] && distancesToTarget[4][3] < closestNeighbor) { closestNeighbor = distancesToTarget[4][3]; bestDir = Direction.EAST; }
      if (unoccupiedLocs[4][4] && distancesToTarget[4][4] < closestNeighbor) { closestNeighbor = distancesToTarget[4][4]; bestDir = Direction.NORTHEAST; }
      if (unoccupiedLocs[3][4] && distancesToTarget[3][4] < closestNeighbor) { closestNeighbor = distancesToTarget[3][4]; bestDir = Direction.NORTH; }
      if (bestDir != null && rc.canMove(bestDir)) rc.move(bestDir);
      System.out.println(closestNeighbor);
      System.out.println(Clock.getBytecodesLeft());
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
			if(d == Direction.CENTER)
				d = RobotPlayer.randomDirection();
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
				if(true) {
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
			if (DEBUG) rc.setIndicatorLine(rc.getLocation(), raker, 0, 255, 0);
			return;
		}
	}
	public void setRakerFlags() throws GameActionException {
		//if(raker != null)
			//System.out.println("rakerRound = "+rakerRound);
		if(rakerRound > RAKER_ROUNDS) {
			if((rc.getFlag(rc.getID())&0xf00000)==0x100000)
				rc.setFlag(0 | politicianMask);
			return;
		}
    if (raker == null) return;
		rc.setFlag(0x100000 | politicianMask | Robot.roundToFlag((rc.getRoundNum()>>0) - rakerRound) | Robot.locToFlag(raker));
	}
	boolean isEnemyHQ;
  int nonfriendlyHQStrength;
	public void sendNonfriendlyHQ() throws GameActionException {
		if(rc.getRoundNum() > nonfriendlyHQround + 50) {
			nonfriendlyHQ = null;
			if((rc.getFlag(rc.getID())&0xf00000)==NONFRIENDLY_HQ)
				rc.setFlag(0);
		}
		if(nonfriendlyHQ == null) return;
		rc.setFlag(Robot.locToFlag(nonfriendlyHQ) | NONFRIENDLY_HQ | (isEnemyHQ?Robot.ENEMY_HQ : Robot.NEUTRAL_HQ)
        | ((Math.min(nonfriendlyHQStrength, 960) >> 6) << 16));
		if(nonfriendlyHQ != null)
			if (DEBUG) rc.setIndicatorLine(rc.getLocation(), nonfriendlyHQ, 255, 0, 0);
	}
	public void receiveNonfriendlyHQ(int f) throws GameActionException {
		if((f&0xf00000)!=Robot.NONFRIENDLY_HQ) return;
		MapLocation l = Robot.flagToLoc(rc.getLocation(), f);
		int empty = -1;
		for(int i=0;i<nonfriendlyHQs.length;i++) {
			if(l.equals(nonfriendlyHQs[i])) {
				if((f&Robot.FRIENDLY_HQ) == Robot.FRIENDLY_HQ)
					nonfriendlyHQs[i] = null;
				else {
					enemyHQs[i] = (f&Robot.ENEMY_HQ)==Robot.ENEMY_HQ;
					nonfriendlyHQrounds[i] = rc.getRoundNum();
				}
				return;
			} else if(nonfriendlyHQs[i] == null) {
				empty = i;
			}
		}
		//this location is not present in the nonfriendlyHQs array
		if((f&Robot.FRIENDLY_HQ) == Robot.FRIENDLY_HQ) return; //its a friendly one, we don't need it
		//now we need to store it in a new empty location
		nonfriendlyHQs[empty] = l;
		enemyHQs[empty] = (f&Robot.ENEMY_HQ)==Robot.ENEMY_HQ;
		nonfriendlyHQrounds[empty] = rc.getRoundNum();
    nonfriendlyHQstrengths[empty] = (f&0x0f0000) >> 10;
	}
	public void unsendNonfriendlyHQ(MapLocation nonfriendlyHQ) throws GameActionException {
		rc.setFlag(Robot.locToFlag(nonfriendlyHQ) | NONFRIENDLY_HQ | Robot.FRIENDLY_HQ);
	}
}
