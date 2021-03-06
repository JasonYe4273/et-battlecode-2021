package sprint2;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

import java.util.Arrays;

public class Politician extends Robot {
	/*
	 * politicians want to stay slightly away from other politicians and your base, but near slanderers
	 * 
	 */
	public Politician(RobotController r) {
		super(r);
	}
	public void turn() throws Exception {
		RobotInfo[] nearby = rc.senseNearbyRobots();

		if (homeID == -1) {
			for (RobotInfo r : nearby) {
				if (r.team == rc.getTeam() && r.type == RobotType.ENLIGHTENMENT_CENTER) {
					homeID = r.ID;
					home = r.location;
					break;
				}
			}

			if (homeID == -1 && (rc.getConviction() * rc.getEmpowerFactor(rc.getTeam(), 0)) < 50) {
				walling(nearby);
				setRakerFlags();
				return;
			}
		}

		findRakerFlags(nearby);
		if(homeID != -1 && rc.canGetFlag(homeID))
			super.receiveNonfriendlyHQ(rc.getFlag(homeID));
		//remove old enemyHQs
		if (DEBUG) {
			for(int i=0;i<nonfriendlyHQs.length;i++) {
				if(nonfriendlyHQs[i]!=null) {
					rc.setIndicatorLine(rc.getLocation(), nonfriendlyHQs[i], 255, 0, 0);
				}
			}
		}
    // if empower factor > 5, politicians should consider empowering their own base (or an enemy base)
    if (rc.getEmpowerFactor(rc.getTeam(), 0) > 5 && (rc.getEmpowerFactor(rc.getTeam(), 0) * rc.getConviction()) > 1000) {
      int distToHQ = -1;
      for (RobotInfo r : nearby) 
        if (r.type == RobotType.ENLIGHTENMENT_CENTER && r.influence < GameConstants.ROBOT_INFLUENCE_LIMIT/2) 
          distToHQ = r.location.distanceSquaredTo(rc.getLocation());
      if (distToHQ != -1) {
        int numUnits = rc.senseNearbyRobots(distToHQ).length;
        if (rc.getEmpowerFactor(rc.getTeam(), 0) > (numUnits * 5) && rc.canEmpower(distToHQ)) rc.empower(distToHQ);
      }
    }
    // if a beefy enemy is attacking a nearby weak base, then run home and defend it
    if (home != null && rc.canSenseLocation(home)) {
      RobotInfo homeRobot = rc.senseRobotAtLocation(home);
      if (homeRobot != null) {
        int homeInfluence = homeRobot.influence;
        for (RobotInfo r : nearby) {
          if (r.type == RobotType.POLITICIAN && r.team != rc.getTeam() && r.conviction > homeInfluence)
            moveToward(home);
        }
      }
    }
    if (rc.getConviction() >= 200) {
      huntBeefyMuckrakers(nearby);
    }
    if(rc.getConviction() <= 10) {
			if (rc.senseNearbyRobots(16, rc.getTeam()).length > 20 && rc.canEmpower(1)) rc.empower(1);
			else walling(nearby);
		}
		else if(shouldAttackHQ(nearby)) {
			attackHQ();
    }
		else {
			checkEmpower(nearby);
			movement(nearby);
		}
		setRakerFlags();
		/*
		if(nonfriendlyHQ != null && rc.canSenseLocation(nonfriendlyHQ)) {
			RobotInfo r = rc.senseRobotAtLocation(nonfriendlyHQ);
			if(r.team == rc.getTeam())
				super.unsendNonfriendlyHQ();
		}
		*/
	}
	public boolean shouldAttackHQ(RobotInfo[] nearby) throws GameActionException {
		//System.out.println("homeID = "+homeID);
		nonfriendlyHQ = null;
    int nonfriendlyHQStrength = -1;
    boolean nonfriendlyHQIsEnemy = false;
		for(int i=0;i<nonfriendlyHQs.length;i++) {
			if(nonfriendlyHQs[i] != null) {
				if(rc.getRoundNum() > nonfriendlyHQrounds[i] + 20) {
					nonfriendlyHQs[i] = null;
					continue;
				}
				int d = rc.getLocation().distanceSquaredTo(nonfriendlyHQs[i]);
				if(nonfriendlyHQ==null || nonfriendlyHQ.distanceSquaredTo(rc.getLocation()) > d)
        {
					nonfriendlyHQ = nonfriendlyHQs[i];
          nonfriendlyHQStrength = nonfriendlyHQstrengths[i];
          nonfriendlyHQIsEnemy = enemyHQs[i];
        }
			}
		}
    // see if you can sense something closer than reported
    for (RobotInfo r : nearby) {
      if (r.type == RobotType.ENLIGHTENMENT_CENTER && r.team != rc.getTeam()
          && (nonfriendlyHQ == null || r.location.distanceSquaredTo(rc.getLocation()) < nonfriendlyHQ.distanceSquaredTo(rc.getLocation()))) {
        nonfriendlyHQ = r.location;
        nonfriendlyHQStrength = r.influence;
        nonfriendlyHQIsEnemy = (r.team == rc.getTeam().opponent());
      }
    }
		if(nonfriendlyHQ == null)
			return false;
		if(rc.getConviction() < 300 && (nonfriendlyHQIsEnemy || nonfriendlyHQStrength == -1 || rc.getConviction() < (nonfriendlyHQStrength + 64)))
			return false;
		if(rc.canSenseLocation(nonfriendlyHQ)) {
			int pow = (int)(rc.getConviction() * rc.getEmpowerFactor(rc.getTeam(), 0) - 10);
			RobotInfo e = rc.senseRobotAtLocation(nonfriendlyHQ);
			for(RobotInfo r:nearby) {
				if(r.team == rc.getTeam() && r.type == RobotType.POLITICIAN && r.conviction > 300) {
					pow += r.conviction * rc.getEmpowerFactor(rc.getTeam(), 0) - 10;
				}
			}
			return e != null && e.team != rc.getTeam() && e.influence < pow;
		}
		return true;
		/*if (DEBUG) rc.setIndicatorLine(rc.getLocation(), nonfriendlyHQ, 128, 0, 255);
		boolean yes = true;
		for(RobotInfo r:nearby) {
			if(r.team == rc.getTeam() && r.type == RobotType.POLITICIAN && (rc.getFlag(r.ID)&politicianMask)>0 && r.conviction > 300 && r.location.distanceSquaredTo(nonfriendlyHQ) < rc.getLocation().distanceSquaredTo(nonfriendlyHQ)) {
				//System.out.println(r.location);
				if (DEBUG) rc.setIndicatorDot(r.location, 255, 0, 255);
				yes = false;
				break;
			}
		}
		return yes;*/
	}
	public void attackHQ() throws GameActionException {
		int d = rc.getLocation().distanceSquaredTo(nonfriendlyHQ);
		if(d < 2 && rc.canEmpower(d))
			rc.empower(d);
		if(d < 3 && rc.canEmpower(d)) {
      // First priority: empower if no other units nearby
      if (rc.senseNearbyRobots(2).length == 1)
  			rc.empower(d);
      // Second priority: Move to one of the squares in a cardinal direction from HQ
      Direction dirToHQ = rc.getLocation().directionTo(nonfriendlyHQ);
      if (rc.canMove(dirToHQ.rotateRight())) rc.move(dirToHQ.rotateRight());
      else if (rc.canMove(dirToHQ.rotateLeft())) rc.move(dirToHQ.rotateLeft());
      // otherwise just empower anyway
      else rc.empower(d);
    }
		this.moveToward(nonfriendlyHQ);
	}
	public void walling(RobotInfo[] nearby) throws GameActionException {
		int wallRadius = 5;
		MapLocation nearestRaker = null;
		int nearestRakerD = 999;
		for(RobotInfo r:nearby) {
			if(r.team != rc.getTeam()) {
				if(r.type == RobotType.MUCKRAKER) {
					int d = rc.getLocation().distanceSquaredTo(r.location);
					if(d < nearestRakerD) {
						nearestRaker = r.location;
						nearestRakerD = d;
					}
				}
			}
		}
		if(nearestRakerD> 3 && nearestRaker != null) {
			if (homeID == -1) moveToward(nearestRaker.add(RobotPlayer.randomDirection()));
			else moveToward(nearestRaker.add(nearestRaker.directionTo(home)));
		} else if(nearestRaker == null){
			int distFromHome = Robot.taxiDistance(home, rc.getLocation());
			if (distFromHome == 0) {
				// this can only happen if homeless
				moveInDirection(RobotPlayer.randomDirection());
				return;
			}
			if(distFromHome < wallRadius || homeID == -1) {
				Direction d = home.directionTo(rc.getLocation());
				for(int i=0;i<4;i++) {
					if(rc.canMove(d)) {
						rc.move(d);
						break;
					}
					d = d.rotateLeft();
				}
			}
		}
	}

  // special method to hunt down and attack rakers with > 100 influence
  // (Should this be conviction or influence?)
  public void huntBeefyMuckrakers(RobotInfo [] nearby) throws GameActionException {
    MapLocation nearbyBigRaker = null;
    for (RobotInfo r : nearby) {
      if (r.type == RobotType.MUCKRAKER && r.team != rc.getTeam() && r.influence > 100) {
        nearbyBigRaker = r.location;
        break;
      }
    }
    if (nearbyBigRaker == null) return;
    // move next to it
    if (rc.getLocation().distanceSquaredTo(nearbyBigRaker) > 2) {
      this.moveToward(nearbyBigRaker);
    } else {
      // maybe make sure there aren't other things that would absorb damage?
      int empowerRadius = rc.getLocation().distanceSquaredTo(nearbyBigRaker);
      if (rc.canEmpower(empowerRadius)) rc.empower(empowerRadius);
    }
  }

	/*
	 * empower conditions:
	 * a double kill
	 * or kill a center
	 * or kill a raker and there's a nearby slanderer
	 * or adjacent to enemy center, and there's an adjacent friendly politician
	 */
	public void checkEmpower(RobotInfo[] nearby) throws GameActionException {
		if(rc.getCooldownTurns() >= 1) return;
		if(rc.getConviction() * rc.getEmpowerFactor(rc.getTeam(), 0) <= 10) return;
		int[] unitsAtDist = new int[RobotType.POLITICIAN.actionRadiusSquared+1];
		int[] killsAtDist = new int[RobotType.POLITICIAN.actionRadiusSquared+1];
		RobotInfo[] nearerby = rc.senseNearbyRobots(RobotType.POLITICIAN.actionRadiusSquared);
		for(RobotInfo r:nearerby) {
			int d = r.location.distanceSquaredTo(rc.getLocation());
			unitsAtDist[d]++;
		}
		unitsAtDist[2] += unitsAtDist[1];
		unitsAtDist[4] += unitsAtDist[2];
		unitsAtDist[5] += unitsAtDist[4];
		unitsAtDist[8] += unitsAtDist[5];
		unitsAtDist[9] += unitsAtDist[8];
		int damage = (int)(rc.getEmpowerFactor(rc.getTeam(), 0) * rc.getConviction()) - GameConstants.EMPOWER_TAX;
		boolean adjToEnemyCenter = false;
		int numberFriendlyP = 0;
		for(RobotInfo r:nearerby) {
			if(r.team==rc.getTeam()) {
				if(r.type == RobotType.POLITICIAN)
					numberFriendlyP++;
				continue;
			}
			int a = (r.type == RobotType.ENLIGHTENMENT_CENTER)?10:1;
			int d = r.location.distanceSquaredTo(rc.getLocation());
			if(r.type == RobotType.ENLIGHTENMENT_CENTER && d==1)
				adjToEnemyCenter = true;
			switch(d) {
			case 1:
			if(r.conviction < damage/unitsAtDist[1]) 
				killsAtDist[1]+=a;
			case 2:
			if(r.conviction < damage/unitsAtDist[2]) 
				killsAtDist[2]+=a;
			case 4:
			if(r.conviction < damage/unitsAtDist[4]) 
				killsAtDist[4]+=a;
			case 5:
			if(r.conviction < damage/unitsAtDist[5]) 
				killsAtDist[5]+=a;
			case 8:
			if(r.conviction < damage/unitsAtDist[8]) 
				killsAtDist[8]+=a;
			case 9:
			if(r.conviction < damage/unitsAtDist[9]) 
				killsAtDist[9]+=a;
			default:
			}
		}
		if(adjToEnemyCenter && numberFriendlyP > 5)
			rc.empower(1);
		int maxKills = 0;
		int maxKillD = 0;
		for(int i=1;i<10;i++) {
			if(killsAtDist[i] > maxKills) {
				maxKillD = i;
				maxKills = killsAtDist[i];
			}
		}
		if(rc.getID()==12180)
			System.out.println(Arrays.toString(killsAtDist));
		if(maxKills >= 2) {
			rc.empower(maxKillD);
			return;
		}
		int slanderers = 0;
		for(RobotInfo r:nearby) {
			if(r.team!=rc.getTeam() || r.type != RobotType.POLITICIAN) continue;
			if((rc.getFlag(r.ID) & politicianMask) != politicianMask) {
				if (DEBUG) rc.setIndicatorDot(r.location, 0, 0, 255);
				slanderers++;
			}
		}
		//System.out.println("slanderers = "+slanderers);
		if(slanderers >= 1 || numberFriendlyP > 8) {
			if(maxKills == 1 && maxKillD < 9)
				rc.empower(maxKillD);
		}
	}
	public void defend(RobotInfo[] nearby) throws GameActionException {
		//System.out.println("nonfriendlyHQ "+nonfriendlyHQ);
		nonfriendlyHQ = null;
		for(int i=0;i<nonfriendlyHQs.length;i++) {
			if(nonfriendlyHQs[i] != null && enemyHQs[i]) {
				int d = rc.getLocation().distanceSquaredTo(nonfriendlyHQs[i]);
				if(nonfriendlyHQ==null || nonfriendlyHQ.distanceSquaredTo(rc.getLocation()) > d)
					nonfriendlyHQ = nonfriendlyHQs[i];
			}
		}
		if(nonfriendlyHQ!=null && rc.getID()%2 == 0 && rc.getLocation().distanceSquaredTo(nonfriendlyHQ) > 9) {
			moveToward(nonfriendlyHQ);
			return;
		}
		MapLocation me = rc.getLocation();
		int x = 0;
		int y = 0;
		if(!home.equals(me)) {
			if(home.distanceSquaredTo(me) > 25 && nearby.length < 20) {
				x += 200 * (home.x - me.x)/ Math.sqrt(home.distanceSquaredTo(me));
				y += 200 * (home.y - me.y)/ Math.sqrt(home.distanceSquaredTo(me));
			} else {
				x -= 200 * (home.x - me.x)/ Math.sqrt(home.distanceSquaredTo(me));
				y -= 200 * (home.y - me.y)/ Math.sqrt(home.distanceSquaredTo(me));	
			}
		}

		if(nearby.length > 30)
			nearby = rc.senseNearbyRobots(9);
		for(RobotInfo r: nearby) {
			if(r.type == RobotType.POLITICIAN) {
				if((rc.getFlag(r.ID) & politicianMask)==politicianMask) {
					//politician
					x -= 1000 * (r.location.x - me.x)/ r.location.distanceSquaredTo(me);
					y -= 1000 * (r.location.y - me.y)/ r.location.distanceSquaredTo(me);
				} else {
					//slanderer
					//x += 000 * (r.location.x - me.x)/ r.location.distanceSquaredTo(me);
					//y += 000 * (r.location.y - me.y)/ r.location.distanceSquaredTo(me);
				}
			}
		}		
		//rc.setIndicatorLine(me, me.translate(x, y), 255, 255, 0);
		//System.out.println("moving toward "+me.translate(x, y));
		//this.moveInDirection(rc.getLocation().directionTo(me.translate(x, y)));
		this.moveToward(me.translate(x, y));
	}
	int patrolRadius = 4;
	public void movement(RobotInfo[] nearby) throws GameActionException {
		int nearestPoliticanToRaker = 99999;
		int nearp = 0, farp = 0;
		int myDist = rc.getLocation().distanceSquaredTo(home);
		for(RobotInfo r:nearby) {
			if(r.team != rc.getTeam()) {
				if(r.type == RobotType.MUCKRAKER) {
					/*
					int d = r.location.distanceSquaredTo(rc.getLocation());
					if(d < rc.getType().actionRadiusSquared && rc.canEmpower(d)) {
						rc.empower(d);
						return;
					} else {*/
						//this.moveToward(r.location);
						//return;
					//}
				}
					/*
					if(rc.getLocation().distanceSquaredTo(home) < 100) {
						moveToward(new MapLocation((r.location.x + home.x)/2,(r.location.y + home.y)/2));
						return;
					}*/
			} else {
				if(r.type == RobotType.POLITICIAN) {
					if(r.location.distanceSquaredTo(home) < myDist)
						nearp++;
					else
						farp++;
				}
				if(raker == null) continue;
				if(r.type == RobotType.POLITICIAN && rc.canGetFlag(r.ID) && (rc.getFlag(r.ID)&politicianMask)>0) {
					int d2 = r.location.distanceSquaredTo(raker);
					if(d2 < nearestPoliticanToRaker) {
						nearestPoliticanToRaker = d2;
					}
					if(d2 < rc.getLocation().distanceSquaredTo(raker))
						if (DEBUG) rc.setIndicatorDot(r.location, 255, 0, 0);
				}
			}
		}
		//if(raker != null) System.out.println("nearestPtoRaker "+nearestPoliticanToRaker);
		if(raker != null && rc.getLocation().distanceSquaredTo(raker) < nearestPoliticanToRaker) {
			moveToward(raker);
		} else {
			if(true)
				defend(nearby);
			else {
			int d = rc.getLocation().distanceSquaredTo(home);
			if(nearp > 12 && d > patrolRadius*patrolRadius)
				patrolRadius++;
			if(nearp < 5 && patrolRadius > 5)
				patrolRadius--;
			patrol(home,patrolRadius*patrolRadius,(patrolRadius+2)*(patrolRadius+2));
			}
			//if(lastMoveTurn + 10 < rc.getRoundNum())
			//	System.out.println("patrolR = "+patrolRadius);
		}
		//System.out.println("PatrolRadius="+patrolRadius+" nearp="+nearp+" farp="+farp);
	}
	
}
