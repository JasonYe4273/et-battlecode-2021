package quals;

import java.util.Arrays;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;

public class Slanderer extends Politician {
    int homeAdjLimit=0;
    public Slanderer(RobotController r) throws GameActionException {
        super(r);
        for(Direction d:RobotPlayer.directions) {
            if(rc.onTheMap(home.add(d)))
                homeAdjLimit++;
        }
        politicianMask = 0;
        patrolRadius = 1;
    }

    public void turn() throws Exception {
        if(rc.getType() == RobotType.POLITICIAN) {
            super.turn();
            return;
        }

        RobotInfo[] nearby = rc.senseNearbyRobots();
        checkEdges(4);
        if(rc.canGetFlag(homeID)) receiveEdges(rc.getFlag(homeID));
        if(rc.getRoundNum() % 4 == 0) sendEdges();
        findRakerFlags(nearby);
        movementS(nearby);
        setRakerFlags();
    }

    int patrolRadius = 4;
    public void movementS(RobotInfo[] nearby) throws GameActionException {
        int nearp = 0, farp = 0;
        int myDist = rc.getLocation().distanceSquaredTo(home);
        int farthest = 0;
        int homeAdj = 0;
        MapLocation nearestEnemy = null;
        for(RobotInfo r:nearby) {
            int d = home.distanceSquaredTo(r.location);
            if(r.team==rc.getTeam()) {
                if(d < 3) homeAdj++;
                if(r.type == RobotType.POLITICIAN) {
                    if(d < myDist)
                        nearp++;
                    else
                        farp++;
                    if(d > farthest)
                        farthest = d;
                }
            } else {
                if(nearestEnemy == null || rc.getLocation().distanceSquaredTo(nearestEnemy) < rc.getLocation().distanceSquaredTo(r.location))
                    nearestEnemy = r.location;
            }
        }
        if(homeAdj >= homeAdjLimit) {
            moveInDirection(home.directionTo(rc.getLocation()));
        }
        if(raker != null && raker.distanceSquaredTo(rc.getLocation()) < 400) {
            moveInDirection(raker.directionTo(rc.getLocation()));
            return;
        }
        /*if(nearestEnemy != null) {
            moveInDirection(nearestEnemy.directionTo(rc.getLocation()));
            return;
        }*/

        MapLocation patrolCenter = home;
        MapLocation me = rc.getLocation();
        /*int xDir = 0;
        int yDir = 0;
        if (mapXmin != -1 && me.x - mapXmin < 6) {
            xDir = -1;
        } else if (mapXmax != 999999 && mapXmax - me.x < 6) {
            xDir = 1;
        }
        if (mapYmin != -1 && me.y - mapYmin < 6) {
            yDir = -1;
        } else if (mapYmax != 999999 && mapYmax - me.y < 6) {
            yDir = 1;
        }

        if (xDir == -1) {
            if (yDir == -1) patrolCenter = new MapLocation(mapXmin+1, mapYmin+1);
            else if (yDir == 1) patrolCenter = new MapLocation(mapXmin+1, mapYmax-1);
        } else if (xDir == 1) {
            if (yDir == -1) patrolCenter = new MapLocation(mapXmax-1, mapYmin+1);
            else if (yDir == 1) patrolCenter = new MapLocation(mapXmax-1, mapYmax-1);
        }*/

        if(patrolRadius > 2 && farp<2)
            patrolRadius--;
        int d = rc.getLocation().distanceSquaredTo(patrolCenter);
        if((nearp > 10 && farp>5 && d > patrolRadius*patrolRadius) || homeAdj >= homeAdjLimit)
            patrolRadius++;
        patrol(patrolCenter,patrolRadius*patrolRadius,(patrolRadius+2)*(patrolRadius+2));
        //System.out.println("PatrolRadius="+patrolRadius);
    }
}
