package josh;

import static josh.RobotPlayer.directions;

import java.util.Arrays;

import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class Slanderer extends Politician {

	public Slanderer(RobotController r) {
		super(r);
	}

	public void turn() throws Exception {
		if(rc.getType() == RobotType.POLITICIAN) {
			super.turn();
			return;
		}
		if(rc.getCooldownTurns() > 1) {
			return;
		}

		RobotInfo[] nearby = rc.senseNearbyRobots();
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
	}


}
