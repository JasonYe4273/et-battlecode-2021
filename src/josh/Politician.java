package josh;

import battlecode.common.GameActionException;
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
	public Politician(RobotController r) {
		super(r);
	}
	public void turn() throws Exception {
		RobotInfo[] nearby = rc.senseNearbyRobots();
		findRakerFlags(nearby);
		movement(nearby);
		setRakerFlags();
	}
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
		for(RobotInfo r:nearby) {
			if(r.team != rc.getTeam()) {
				if(r.type == RobotType.MUCKRAKER) {
					int d = rc.getLocation().distanceSquaredTo(r.location);
					if(d < rc.getType().actionRadiusSquared && rc.canEmpower(d)) {
						rc.empower(d);
						return;
					} else {
						this.moveToward(r.location);
						return;
					}
				} else if(r.type == RobotType.POLITICIAN) {
					if(rc.getLocation().distanceSquaredTo(home) < 100) {
						moveToward(new MapLocation((r.location.x + home.x)/2,(r.location.y + home.y)/2));
						return;
					}
				}
			}
		}
		if(raker != null) {
			moveToward(raker);
		}
	}
	public void findRakerFlags(RobotInfo[] nearby) throws GameActionException {
		raker = null;
		rakerRound = 99999;
		for(RobotInfo r:nearby) {
			if(r.team==rc.getTeam()) {
				if(r.type == RobotType.POLITICIAN) {
					if(rc.canGetFlag(r.ID)) {
						int f = rc.getFlag(r.ID);
						if(f > 0) {
							int rr = Robot.flagToRound(rc.getRoundNum(), f);
							if(rr < rakerRound) {
								rakerRound = rr;
								raker = Robot.flagToLoc(r.location, f);
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
		if(rakerRound < 48) {
			rc.setIndicatorLine(rc.getLocation(), raker, 0, 255, 0);
			return;
		}
	}
	public void setRakerFlags() throws GameActionException {
		if(rakerRound > 48) {
			if(rc.getFlag(rc.getID())>0)
				rc.setFlag(0);
			return;
		}
		rc.setFlag(Robot.roundToFlag(rc.getRoundNum() - rakerRound) | Robot.locToFlag(rc.getLocation(), raker));
	}
}
