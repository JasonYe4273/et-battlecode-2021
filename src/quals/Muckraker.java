package quals;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class Muckraker extends Robot {

    public Muckraker(RobotController r) {
        super(r);
    }
    MapLocation target = null;
    public void turn() throws GameActionException {
        checkEdges(5);
        //System.out.println("Knowledge of map: " + mapXmin + " " + mapXmax + " " + mapYmin + " " + mapYmax);
        //System.out.println(target);
        RobotInfo[] nearby = rc.senseNearbyRobots();
        findRakerFlags(nearby);
        if(rc.canGetFlag(homeID)) {
            super.receiveNonfriendlyHQ(rc.getFlag(homeID));
            receiveEdges(rc.getFlag(homeID));
        }
        setRakerFlags();
        if(rc.getRoundNum() % 4 == 0) {
            sendNonfriendlyHQ();
            if ((rc.getFlag(rc.getID()) & 0xF00000) != Robot.NONFRIENDLY_HQ) sendEdges();
        }

        //move away from nearby friendly rakers
        //charge enemy slanderers
        MapLocation nearestRaker = null;
        boolean nearHome = false;
        int enemyPStrength = 0;
        MapLocation slandererLoc = null; // closest one to move toward
        MapLocation bestSlanderer = null; // best one to expose
        int highestInfluence = 0; // influence of highest slanderer
        // see if any elements of nonfriendlyHQs are null
        for(int i=0;i<nonfriendlyHQs.length;i++) {
            if(nonfriendlyHQs[i] != null && rc.canSenseLocation(nonfriendlyHQs[i]) && rc.senseRobotAtLocation(nonfriendlyHQs[i]) == null) {
                super.unsendNonfriendlyHQ(nonfriendlyHQs[i]);
                nonfriendlyHQs[i] = null;
                enemyHQs[i] = false;
            }
        }

        for(RobotInfo r:nearby) {
            if(r.type == RobotType.ENLIGHTENMENT_CENTER) {
                if(r.team == rc.getTeam()) {
                    for(int i=0;i<nonfriendlyHQs.length;i++) {
                        if(r.location.equals(nonfriendlyHQs[i])) {
                            super.unsendNonfriendlyHQ(r.location);
                            break;
                        }
                    }

                    nearHome = true;
                } else {
                    nonfriendlyHQ = r.location;
                    nonfriendlyHQround = rc.getRoundNum();
                    isEnemyHQ = (r.team == rc.getTeam().opponent());
                    nonfriendlyHQStrength = r.influence;
                }
            }
            if(r.team != rc.getTeam() && r.type == RobotType.SLANDERER) {
                if(rc.canExpose(r.location) && r.influence > highestInfluence ) {
                    highestInfluence = r.influence;
                    bestSlanderer = r.location;
                }
                else slandererLoc = r.location;
            } else if (r.team == rc.getTeam() && r.type == RobotType.MUCKRAKER) {
                if(nearestRaker == null || rc.getLocation().distanceSquaredTo(nearestRaker) > rc.getLocation().distanceSquaredTo(r.location))
                    nearestRaker = r.location;
            }
        }
        if (bestSlanderer != null) {
            rc.expose(bestSlanderer);
            return;
        }
        if (slandererLoc != null) {
            moveToward(slandererLoc);
            return;
        }
   

        if (nearHome && rc.getConviction() < 50) {
            int polMinD = 9999;
            MapLocation closeP = null;
            for (RobotInfo r : nearby) {
                if (r.team != rc.getTeam() && r.type == RobotType.POLITICIAN) {
                    int d = rc.getLocation().distanceSquaredTo(r.location);
                    if (d < polMinD) {
                        polMinD = d;
                        closeP = r.location;
                    }
                }
            }
            if (closeP != null && polMinD > 2) {
                moveToward(closeP);
                return;
            }
        }

        int minD = 9999;
        for(int i=0;i<nonfriendlyHQs.length;i++) {
            if(enemyHQs[i]) {
                int d = rc.getLocation().distanceSquaredTo(nonfriendlyHQs[i]);
                if (d < minD) {
                    minD = d;
                    target = nonfriendlyHQs[i];
                }
            }
        }

        if(nearestRaker != null && rc.getConviction() < 50) {
            //moveInDirection(nearestRaker.directionTo(rc.getLocation()));
            if(target == null) target = rc.getLocation();
            target = target.translate(rc.getLocation().x==nearestRaker.x?0:25/(rc.getLocation().x - nearestRaker.x),
                    rc.getLocation().y==nearestRaker.y?0:25/(rc.getLocation().y - nearestRaker.y));
            //target = null;
            //return;
        }
        int distToTarget = 64;
        while(target == null || rc.getLocation().distanceSquaredTo(target) < 25 || !onTheMap(target)) {
            // if target is an enemy HQ, orbit it
            if (target != null && rc.canSenseLocation(target) && rc.senseRobotAtLocation(target) != null
                    && rc.senseRobotAtLocation(target).type == RobotType.ENLIGHTENMENT_CENTER && rc.senseRobotAtLocation(target).team == rc.getTeam().opponent()) {
                if (rc.getLocation().distanceSquaredTo(target) >= 9) moveInDirection(rc.getLocation().directionTo(target));
                else moveInDirection(rc.getLocation().directionTo(target).rotateRight().rotateRight());
                break;
            } else {
                //otherwise, if we are too close to the target, find a new one at random
                double angle = Math.random() * Math.PI * 2;
                target = rc.getLocation().translate((int)(Math.cos(angle) * distToTarget),(int)( Math.sin(angle) * distToTarget));
                distToTarget --;
            }
        }
        moveToward(target);
    }
    public boolean onTheMap(MapLocation l) {
        return l.x > mapXmin && l.x < mapXmax && l.y > mapYmin && l.y < mapYmax;
    }


}
