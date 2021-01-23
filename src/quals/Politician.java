package quals;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

import java.util.Arrays;

public class Politician extends Robot {

    //TODO: occasionally form attack squads that charge the enemy in a big ball of rushing.
    /*
     * politicians want to stay slightly away from other politicians and your base, but near slanderers
     * 
     */
    public Politician(RobotController r) throws GameActionException {
        super(r);
        rc.setFlag(politicianMask);
    }

    int turnsSinceEnemy = 0;
    public void turn() throws Exception {
        politicianMask = 0x080000; // reset to default value (in case this got changed during former life as a slanderer)
        RobotInfo[] nearby = rc.senseNearbyRobots();
        checkEdges();
        if (homeID == -1) {
            for (RobotInfo r : nearby) {
                if (r.team == rc.getTeam() && r.type == RobotType.ENLIGHTENMENT_CENTER) {
                    homeID = r.ID;
                    home = r.location;
                    break;
                }
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
                    rc.setIndicatorDot(nonfriendlyHQs[i], 255, 0, 0);
                }
            }
        }

        if (rc.senseNearbyRobots(RobotType.POLITICIAN.sensorRadiusSquared, rc.getTeam().opponent()).length == 0)
            turnsSinceEnemy++;
        else turnsSinceEnemy = 0;

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
        if (rc.getConviction() >= 200) huntBeefyMuckrakers(nearby);

        setRakerFlags();
        if(rc.getConviction() <= 10) {
            if (rc.senseNearbyRobots(16, rc.getTeam()).length > 20 && rc.canEmpower(1)) rc.empower(1);
            else walling(nearby);
        } else if(shouldAttackHQ(nearby)) {
            attackHQ();
        } else {
            checkEmpower(nearby);
            movement(nearby);
        }
        if(rc.canGetFlag(homeID)) {
            super.receiveNonfriendlyHQ(rc.getFlag(homeID));
            receiveEdges(rc.getFlag(homeID));
        }
        if(rc.getRoundNum() % 4 == 0) {
            readNearbyFlags(nearby);
            System.out.println(nonfriendlyHQ);
            sendNonfriendlyHQ();
            if ((rc.getFlag(rc.getID()) & 0xF00000) != Robot.NONFRIENDLY_HQ) sendEdges();
        }
        if(nonfriendlyHQ != null && rc.canSenseLocation(nonfriendlyHQ)) {
            RobotInfo r = rc.senseRobotAtLocation(nonfriendlyHQ);
            if(r == null || r.team == rc.getTeam()) {
                super.unsendNonfriendlyHQ(nonfriendlyHQ);
                nonfriendlyHQ = null;
            }
        }
    }
    public boolean shouldAttackHQ(RobotInfo[] nearby) throws GameActionException {
        nonfriendlyHQ = null;
        if(rc.getID() == 10082)
            System.out.println("should attack HQ?");
        boolean nonfriendlyHQIsEnemy = false;
        for(int i=0;i<nonfriendlyHQs.length;i++) {
            if(nonfriendlyHQs[i] != null) {
                if(rc.getRoundNum() > nonfriendlyHQrounds[i] + 10) {
                    nonfriendlyHQs[i] = null;
                    continue;
                }
                int d = rc.getLocation().distanceSquaredTo(nonfriendlyHQs[i]);
                if(nonfriendlyHQ==null || nonfriendlyHQ.distanceSquaredTo(rc.getLocation()) > d) {
                    nonfriendlyHQ = nonfriendlyHQs[i];
                    nonfriendlyHQStrength = nonfriendlyHQstrengths[i];
                    nonfriendlyHQIsEnemy = enemyHQs[i];
                    nonfriendlyHQround = nonfriendlyHQrounds[i];
                }
            }
        }
        // see if you can sense something closer than reported
        for (RobotInfo r : nearby) {
            if (r.type == RobotType.ENLIGHTENMENT_CENTER && r.team != rc.getTeam()) {
                if((nonfriendlyHQ == null || r.location.distanceSquaredTo(rc.getLocation()) < nonfriendlyHQ.distanceSquaredTo(rc.getLocation()))) {
                    nonfriendlyHQ = r.location;
                    nonfriendlyHQStrength = r.influence;
                    nonfriendlyHQround = rc.getRoundNum();
                    nonfriendlyHQIsEnemy = (r.team == rc.getTeam().opponent());
                    super.isEnemyHQ = nonfriendlyHQIsEnemy;
                    System.out.println("notifying base of new nonfriendly");
                    if(homeID != -1)
                        super.sendNonfriendlyHQ();
                }
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
            if( e != null && e.team != rc.getTeam() ) {
                if (e.influence > pow) {
                    // We don't yet have strength to attack HQ but we should at least move toward it
                    moveToward(e.location);
                    return false;
                }
                else return true;
            } else return false;
        }
        return true;
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
    // this should be conviction. If a raker has like 10hp left, it's not a beefy raker, no matter how much initial hp it had.
    public void huntBeefyMuckrakers(RobotInfo [] nearby) throws GameActionException {
        MapLocation nearbyBigRaker = null;
        for (RobotInfo r : nearby) {
            if (r.type == RobotType.MUCKRAKER && r.team != rc.getTeam() && r.conviction > 70) {
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

        double buff = rc.getEmpowerFactor(rc.getTeam(), 0);
        int damage = rc.getConviction() - GameConstants.EMPOWER_TAX;
        if(damage <= 0) return;
        int[] unitsAtDist = new int[RobotType.POLITICIAN.actionRadiusSquared+1];
        int[] killsAtDist = new int[RobotType.POLITICIAN.actionRadiusSquared+1];
        int[] infDrainAtDist = new int[RobotType.POLITICIAN.actionRadiusSquared+1];
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
        boolean adjToEnemyCenter = false;
        int numFriendlyP = 0;
        int numFriendlyS = 0;
        for(RobotInfo r:nearby) {
            int d = r.location.distanceSquaredTo(rc.getLocation());
            if(r.team==rc.getTeam()) {
                if(r.type == RobotType.POLITICIAN) {
                    if(rc.canGetFlag(r.ID) && ((rc.getFlag(r.ID) & politicianMask) != politicianMask)) {
                        if (DEBUG) rc.setIndicatorDot(r.location, 0, 0, 255);
                        numFriendlyS++;
                    } else if (d <= RobotType.POLITICIAN.actionRadiusSquared) numFriendlyP++;
                }

                // Don't care about empowering own non-EC units
                if (r.type != RobotType.ENLIGHTENMENT_CENTER) continue;
            }
            int k = (r.type == RobotType.ENLIGHTENMENT_CENTER)?100:1;
            if(r.team != rc.getTeam() && r.type == RobotType.ENLIGHTENMENT_CENTER && d==1)
                adjToEnemyCenter = true;
            switch(d) {
                case 1:
                    if (r.team != rc.getTeam() && ((r.conviction / buff) < (damage / unitsAtDist[1]))) {
                        // Killed enemy units
                        killsAtDist[1] += k;
                        infDrainAtDist[1] += (int) (r.conviction / r.type.convictionRatio);
                    } else if (r.team == rc.getTeam()) {
                        // Friendly ECs don't get affected by buff
                        infDrainAtDist[1] += damage / unitsAtDist[1];
                    } else {
                        // Enemy non-killed units
                        infDrainAtDist[1] += (int) ((int) (buff * (damage / unitsAtDist[1])) / r.type.convictionRatio);
                    }
                case 2:
                    if (r.team != rc.getTeam() && ((r.conviction / buff) < (damage / unitsAtDist[2]))) {
                        // Killed enemy units
                        killsAtDist[2] += k;
                        infDrainAtDist[2] += (int) (r.conviction / r.type.convictionRatio);
                    } else if (r.team == rc.getTeam()) {
                        // Friendly ECs don't get affected by buff
                        infDrainAtDist[2] += damage / unitsAtDist[2];
                    } else {
                        // Enemy non-killed units
                        infDrainAtDist[2] += (int) ((int) (buff * (damage / unitsAtDist[2])) / r.type.convictionRatio);
                    }
                case 4:
                    if (r.team != rc.getTeam() && ((r.conviction / buff) < (damage / unitsAtDist[4]))) {
                        // Killed enemy units
                        killsAtDist[4] += k;
                        infDrainAtDist[4] += (int) (r.conviction / r.type.convictionRatio);
                    } else if (r.team == rc.getTeam()) {
                        // Friendly ECs don't get affected by buff
                        infDrainAtDist[4] += damage / unitsAtDist[4];
                    } else {
                        // Enemy non-killed units
                        infDrainAtDist[4] += (int) ((int) (buff * (damage / unitsAtDist[4])) / r.type.convictionRatio);
                    }
                case 5:
                    if (r.team != rc.getTeam() && ((r.conviction / buff) < (damage / unitsAtDist[5]))) {
                        // Killed enemy units
                        killsAtDist[5] += k;
                        infDrainAtDist[5] += (int) (r.conviction / r.type.convictionRatio);
                    } else if (r.team == rc.getTeam()) {
                        // Friendly ECs don't get affected by buff
                        infDrainAtDist[5] += damage / unitsAtDist[5];
                    } else {
                        // Enemy non-killed units
                        infDrainAtDist[5] += (int) ((int) (buff * (damage / unitsAtDist[5])) / r.type.convictionRatio);
                    }
                case 8:
                    if (r.team != rc.getTeam() && ((r.conviction / buff) < (damage / unitsAtDist[8]))) {
                        // Killed enemy units
                        killsAtDist[8] += k;
                        infDrainAtDist[8] += (int) (r.conviction / r.type.convictionRatio);
                    } else if (r.team == rc.getTeam()) {
                        // Friendly ECs don't get affected by buff
                        infDrainAtDist[8] += damage / unitsAtDist[8];
                    } else {
                        // Enemy non-killed units
                        infDrainAtDist[8] += (int) ((int) (buff * (damage / unitsAtDist[8])) / r.type.convictionRatio);
                    }
                case 9:
                    if (r.team != rc.getTeam() && ((r.conviction / buff) < (damage / unitsAtDist[9]))) {
                        // Killed enemy units
                        killsAtDist[9] += k;
                        infDrainAtDist[9] += (int) (r.conviction / r.type.convictionRatio);
                    } else if (r.team == rc.getTeam()) {
                        // Friendly ECs don't get affected by buff
                        infDrainAtDist[9] += damage / unitsAtDist[9];
                    } else {
                        // Enemy non-killed units
                        infDrainAtDist[9] += (int) ((int) (buff * (damage / unitsAtDist[9])) / r.type.convictionRatio);
                    }
                default:
            }
        }

        int best = 0;
        int bestD = 0;
        int maxKills = 0;
        int maxKillInfDrain = 0;
        int maxKillD = 0;
        for (int i = 1; i < 10; i++) {
            int metric = (killsAtDist[i] - 1) * 100 + infDrainAtDist[i] - rc.getConviction();
            if (metric > best) {
                best = metric;
                bestD = i;
            }

            if (killsAtDist[i] > maxKills || (killsAtDist[i] == maxKills && infDrainAtDist[i] > maxKillInfDrain)) {
                maxKills = killsAtDist[i];
                maxKillInfDrain = infDrainAtDist[i];
                maxKillD = i;
            }
        }

        if (best > 0 && rc.canEmpower(bestD)) rc.empower(bestD);
        else if ((numFriendlyS >= 1 || numFriendlyP > 8) && maxKills >= 1 && rc.canEmpower(maxKillD))
            rc.empower(maxKillD);
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
            if(!(home.distanceSquaredTo(me) > 25 && nearby.length < 20)) {
                x -= 200 * (home.x - me.x)/ Math.sqrt(home.distanceSquaredTo(me));
                y -= 200 * (home.y - me.y)/ Math.sqrt(home.distanceSquaredTo(me));  
            }
        }

        if(nearby.length > 30)
            nearby = rc.senseNearbyRobots(9);
        for(RobotInfo r: nearby) {
            if(r.type == RobotType.POLITICIAN) {
                if(rc.canGetFlag(r.ID) && (rc.getFlag(r.ID) & politicianMask)==politicianMask) {
                    //politician
                    x -= 1000 * (r.location.x - me.x)/ r.location.distanceSquaredTo(me);
                    y -= 1000 * (r.location.y - me.y)/ r.location.distanceSquaredTo(me);
                }
            }
        }
        if(mapXmin != -1)
            x += 1000 / (me.x - mapXmin);
        if(mapYmin != -1)
            y += 1000 / (me.y - mapYmin);
        if(mapXmax != 999999)
            x += 1000 / (me.x - mapXmax);
        if(mapYmax != 999999)
            y += 1000 / (me.y - mapYmax);

        this.moveToward(me.translate(x, y));
    }

    public void movement(RobotInfo[] nearby) throws GameActionException {
        int nearestPoliticanToRaker = 99999;
        for(RobotInfo r:nearby) {
            if(r.team == rc.getTeam()) {
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

        if(raker != null && rc.getLocation().distanceSquaredTo(raker) < nearestPoliticanToRaker) {
            moveToward(raker);
        } else if (turnsSinceEnemy < 10 || rc.getRoundNum() < 100) defend(nearby);
        else if (rc.getConviction() >= 300 && nonfriendlyHQ != null) moveToward(nonfriendlyHQ);
        else if (raker != null) moveToward(raker);
        else moveInDirection(RobotPlayer.randomDirection());
    }
}
