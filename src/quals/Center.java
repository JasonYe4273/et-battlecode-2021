package quals;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import battlecode.common.*;

public class Center extends Robot {
    //TODO: allow for reading poly flags
    //TODO: build fewer polys when we aren't getting raker rushed.
    public Center(RobotController r) {
        super(r);
    }
    int lastInf = rc.getInfluence();
    int lastRakerRound = 0;
    Set<Integer> rakers = new HashSet<Integer>();
    Set<Integer> others = new HashSet<Integer>(); // units that aren't rakers (pols and slanderers)
    // only build one politician to kill each neutral, this keeps track of this
    boolean [] builtPoliticianToKillNeutral = {false, false, false, false, false, false, false, false, false, false};
    public void turn() throws GameActionException {
        mainturn();
        lastInf = rc.getInfluence();
    }
    public void mainturn() throws GameActionException {
        //System.out.println("Knowledge of map: " + mapXmin + " " + mapXmax + " " + mapYmin + " " + mapYmax);
        //readNonfriendlyHQFlag();
        readAllFlags();
        RobotInfo[] nearby = rc.senseNearbyRobots();
        int politicians = 0;
        int slanderers = 0;
        int myPStrength = 0;
        int enemyPStrength = 0;
        int enemyRStrength = 0;
        for(RobotInfo r:nearby) {
            if(r.team == rc.getTeam()) {
                if(r.type == RobotType.POLITICIAN) {
                    politicians++;
                    myPStrength += r.conviction;
                } else if(r.type == RobotType.SLANDERER) {
                    slanderers++;
                }
            } else {
                if(r.type == RobotType.POLITICIAN)
                    enemyPStrength+=r.conviction - GameConstants.EMPOWER_TAX;
                else if(r.type == RobotType.MUCKRAKER) {
                    enemyRStrength+=r.conviction;
                    lastRakerRound = rc.getRoundNum();
                }   
            }
        }
        int inf = rc.getInfluence() - enemyPStrength;

        //if(rc.getRoundNum() > 1000) rc.resign();
        if(rc.getRoundNum() > 400 && inf > 0) {
            if (rc.getRoundNum() < 1000) bid(inf / 100 + 1);
            else if (rc.getRoundNum() < 1200) bid(inf / 50 + 1);
            else if (rc.getRoundNum() < 1350) bid(inf / 30 + 1);
            else bid(inf / 20 + 1);
        }
        if(rc.getCooldownTurns() >= 1) {
            return;
        }
        boolean neutralHQ = false;
        boolean enemyHQ = false;
        for(int i=0;i<10;i++) {
            if(nonfriendlyHQs[i]!=null) {
                if (enemyHQs[i]) {enemyHQ = true; neutralHQ = false;}
                else neutralHQ = !enemyHQ;
            }
        }
        System.out.println(enemyHQ + " " + neutralHQ);

        // if we are attacking a neutral HQ, find the influence of the nearest neutral
        if (neutralHQ) {
            int neutralStrength = -1;
            int distanceToClosestNeutral = Integer.MAX_VALUE;
            int neutralIndex = -1;
            for (int i = 0; i < 10; i ++) {
                if (nonfriendlyHQs[i] != null && rc.getLocation().distanceSquaredTo(nonfriendlyHQs[i]) < distanceToClosestNeutral && !builtPoliticianToKillNeutral[i]) {
                    distanceToClosestNeutral = rc.getLocation().distanceSquaredTo(nonfriendlyHQs[i]);
                    neutralStrength = nonfriendlyHQstrengths[i];
                    neutralIndex = i;
                }
            }
            // spawn a politician of sufficient strength to take over this neutral HQ
            if (neutralIndex != -1 && inf > Math.min(neutralStrength + 64, 500) + 100 + 11) {
                build(RobotType.POLITICIAN, Math.min(neutralStrength + 64, 500) + 11);
                builtPoliticianToKillNeutral[neutralIndex] = true;
                return;
            }
        }

        if(inf > 500 && (enemyHQ | neutralHQ)) {
            if (enemyHQ && Math.random() < 0.2) build(RobotType.MUCKRAKER, Math.max(400, inf / 100));
            else build(RobotType.POLITICIAN, Math.max(400, inf / 100));
            return;
        }
        if(rc.getEmpowerFactor(rc.getTeam().opponent(), 20) > 1)
            lastRakerRound = rc.getRoundNum();
        //System.out.println("p = "+politicians+" s="+slanderers);
        
        if(inf < 11 || rc.getInfluence()<40) {
            build(RobotType.MUCKRAKER, 1);
            return;
        }
        if(slanderers > 0 && Math.random() < 1.0/(1.0+rakerCount /*rakers.size()*/)) {
            build(RobotType.MUCKRAKER, 1);
        }
        int income =  rc.getInfluence() - lastInf;
        if(enemyRStrength > 0 && enemyRStrength > myPStrength - GameConstants.EMPOWER_TAX) {
            build(RobotType.POLITICIAN, Math.min(inf, GameConstants.EMPOWER_TAX + enemyRStrength - myPStrength));
        } else if(enemyRStrength == 0 && (rc.getRoundNum()<3 || politicians*(rc.getRoundNum() - lastRakerRound) > slanderers || politicians > 20) && (inf<1000 || income<500) && (income < 60 || polyCount > 10)) {
            if(rc.getRoundNum() < 50 && inf < 949 && income * 6 > Threshold.slandererThreshold(inf)) {
                if (rakerCount > 100) build(RobotType.POLITICIAN, Math.min(inf, 22 + inf/40));
                else build(RobotType.MUCKRAKER,1);
            } else build(RobotType.SLANDERER, Threshold.slandererThreshold(inf));
        } else {
            build(RobotType.POLITICIAN, Math.min(inf, 22 + inf/40));
        }
    }
    private void build(RobotType t, int influence) throws GameActionException {
        //System.out.println("building "+t+" with inf "+influence);
        int offset = (int)(Math.random()*8);
        for (int i=0;i<8;i++) {
            Direction dir = RobotPlayer.directions[(i+offset)%8];
            if (rc.canBuildRobot(t, dir, influence)) {
                rc.buildRobot(t, dir, influence);
                RobotInfo r = rc.senseRobotAtLocation(rc.getLocation().add(dir));
                if(t == RobotType.MUCKRAKER) {
                    //rakers.add(r.ID);
                    rakerCount++;
                } else if(t == RobotType.POLITICIAN) {
                    polyCount++;
                    //others.add(r.ID);
                }
                allRobots[flagsIndex++] = r;
                allBuilds[rc.getRoundNum()] = r;
            }
        }
    }
    int rakerCount = 0;
    int polyCount = 0;
    int flagsIndex = 0;
    RobotInfo[] allRobots = new RobotInfo[750]; //all currently living robots. Length is flagsIndex
    RobotInfo[] allBuilds = new RobotInfo[1500]; //all robots ever built, indexed by round.
    int lastSlandererRoundChecked = 0;
    public void readAllFlags() throws GameActionException {
        int t0 = Clock.getBytecodeNum();
        int empty = 0;
        for(int i=0; i<flagsIndex;i++) {
            int id = allRobots[i].ID;
            if(rc.canGetFlag(id)) {
                int f = rc.getFlag(id);
                receiveNonfriendlyHQ(f);
                receiveEdges(f);
                allRobots[empty++] = allRobots[i];
            } else {
                if(allRobots[i].type == RobotType.MUCKRAKER)
                    rakerCount--;
                else if(allRobots[i].type == RobotType.POLITICIAN || allRobots[i].type == RobotType.SLANDERER)
                    polyCount--;
            }
        }
        flagsIndex = empty;
        sendNonfriendlyHQ();
        while(lastSlandererRoundChecked + 300 < rc.getRoundNum()) {
            RobotInfo r = allBuilds[lastSlandererRoundChecked];
            if(r != null && r.type == RobotType.SLANDERER)// && rc.canGetFlag(r.ID))
                polyCount++;
            lastSlandererRoundChecked++;
        }

        //System.out.println("robots length "+flagsIndex);
        //System.out.println(" r "+rakerCount+" p "+polyCount+" s "+(flagsIndex-rakerCount-polyCount));
    }
    
    // this method now receives edges as well as non-friendly HQs
    public void readNonfriendlyHQFlag() throws GameActionException {
        System.out.println("Starting reading: " + Clock.getBytecodesLeft());
        Iterator<Integer> it = rakers.iterator();
        System.out.println("num rakers " +rakers.size());
        while(it.hasNext()) {
            int id = it.next();
            if(rc.canGetFlag(id)) {
                int f = rc.getFlag(id);
                receiveNonfriendlyHQ(f);
                receiveEdges(f);
            } else {
                it.remove();
            }
        }
        System.out.println("Read rakers: " + Clock.getBytecodesLeft());
        it = others.iterator();
        while(it.hasNext()) {
            int id = it.next();
            if(rc.canGetFlag(id)) {
                int f = rc.getFlag(id);
                receiveNonfriendlyHQ(f);
                receiveEdges(f);
            } else {
                it.remove();
            }
        }
        System.out.println("Read others: " + Clock.getBytecodesLeft());
        sendNonfriendlyHQ();
    }
    int hqIndex=0;
    public void sendNonfriendlyHQ() throws GameActionException {
        if(true) {
            for(int i=0;i<nonfriendlyHQs.length;i++) {
                if(nonfriendlyHQs[i]!=null) {
                    if (DEBUG) rc.setIndicatorLine(rc.getLocation(), nonfriendlyHQs[i], 255, 0, 0);
                    if(rc.getRoundNum() > nonfriendlyHQrounds[i] + 5000)
                        nonfriendlyHQs[i] = null;
                }
            }
        }
        hqIndex = (hqIndex + 1)%nonfriendlyHQs.length;
        for(int i=0;i<nonfriendlyHQs.length;i++) {
            if(nonfriendlyHQs[hqIndex]==null)
                hqIndex = (hqIndex + 1)%nonfriendlyHQs.length;
            else
                break;
        }
        MapLocation l = nonfriendlyHQs[hqIndex];
        //System.out.println(l);
        if(l!=null) {
            int a = enemyHQs[hqIndex]? Robot.ENEMY_HQ : Robot.NEUTRAL_HQ;
            setFlag(NONFRIENDLY_HQ | Robot.locToFlag(l) | a); 
        } else {
            // see if we can guess an enemy HQ location from map edges
            if (mapXmin == -1 || mapXmax == 999999 || mapYmin == -1 || mapYmax == 999999) {
                setFlag(0);
                // communicate this partial knowledge
                sendEdges();
            }
            else if(true) {
                //System.out.println("No enemy HQ found; computing one from map edges");
                MapLocation myLoc = rc.getLocation();
                int oppX, oppY;
                oppX = mapXmin + mapXmax - myLoc.x;
                oppY = mapYmin + mapYmax - myLoc.y;
                nonfriendlyHQs[0] = new MapLocation(oppX, oppY);
                nonfriendlyHQs[1] = new MapLocation(myLoc.x, oppY);
                nonfriendlyHQs[2] = new MapLocation(oppX, myLoc.y);
                // putatively claim these are enemies rather than neutrals
                enemyHQs[0] = enemyHQs[1] = enemyHQs[2] = true;
                nonfriendlyHQrounds[0] = nonfriendlyHQrounds[1] = nonfriendlyHQrounds[2] = rc.getRoundNum();
                setFlag(NONFRIENDLY_HQ | Robot.locToFlag(nonfriendlyHQs[0]) | Robot.ENEMY_HQ);
            }
        }
    }

    int myVotes = 0;
    int opponentVotes = 0;
    boolean attemptedVote = false;
    public void bid(int b) throws GameActionException {
        boolean lostVote = attemptedVote && rc.getTeamVotes() == myVotes;
        if (lostVote) opponentVotes++;
        myVotes = rc.getTeamVotes();

        if (myVotes > 750 || opponentVotes - myVotes > 1501 - rc.getRoundNum()) {
            attemptedVote = false;
            return;
        }

        if (rc.canBid(b)) {
            rc.bid(b);
            attemptedVote = true;
        } else attemptedVote = false;

    }

}
