package finals;

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
    int[] polIDToKillNeutral = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[] lastTurnSensedPolToKillNeutral = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public void turn() throws GameActionException {
        mainturn();
        lastInf = rc.getInfluence();
        /*
        while(slandererBuildsIndex < rc.getRoundNum()-50) {
            expectedTotalIncome -= expectedCurrentIncome;
            expectedCurrentIncome -= slandererBuilds[slandererBuildsIndex];
            slandererBuildsIndex++;
        }
        */
        expectedTotalIncome = 0;
        expectedCurrentIncome = 0;
        for(int i=Math.max(0, 50-rc.getRoundNum());i<50;i++) {
            int j = rc.getRoundNum() - 50 + i;
            expectedCurrentIncome += slandererBuilds[j];
            expectedTotalIncome += i * slandererBuilds[j];
        }
        System.out.println("income = "+expectedCurrentIncome+" total = "+expectedTotalIncome +" p "+polyCount+" sp "+smallPolyCount);
    }
    int smallPolyCount = 0;
    int slandererBuildsIndex = 0;
    int[] slandererBuilds = new int[1500]; //This is how much $/turn the slanderer we built on round i made
    int expectedTotalIncome = 0; //expected income from all current slanderers over all future rounds
    int expectedCurrentIncome = 0; //expected income for next round
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
        inf -= vote(inf);

        if (rc.getRoundNum() > 1490) return;

        if(rc.getCooldownTurns() >= 1) {
            return;
        }
        boolean neutralHQ = false;
        boolean enemyHQ = false;
        for(int i=0;i<10;i++) {
            if(nonfriendlyHQs[i]!=null) {
                if (enemyHQs[i]) {enemyHQ = true;}
                else {
                    //System.out.println("neutral at "+nonfriendlyHQs[i]+" hp "+nonfriendlyHQstrengths[i] + " id "+polIDToKillNeutral[i]);
                    neutralHQ = true;
                }
            }
        }
        //System.out.println(enemyHQ + " " + neutralHQ);

        // if we are attacking a neutral HQ, find the influence of the nearest neutral
        if (neutralHQ) {
            int neutralStrength = -1;
            int distanceToClosestNeutral = Integer.MAX_VALUE;
            int neutralIndex = -1;
            for (int i = 0; i < 10; i ++) {
                //we have a poly for this neutral already, and it was alive in the last 10 turns
                if (polIDToKillNeutral[i] != -1) {
                    //if it's still alive note that
                    if (rc.canGetFlag(polIDToKillNeutral[i])) lastTurnSensedPolToKillNeutral[i] = rc.getRoundNum();
                    else if (rc.getRoundNum() - lastTurnSensedPolToKillNeutral[i] > 10) {
                        polIDToKillNeutral[i] = -1;
                    }
                }
                if(polIDToKillNeutral[i] != -1)
                    continue;
                if (nonfriendlyHQs[i] != null && rc.getLocation().distanceSquaredTo(nonfriendlyHQs[i]) < distanceToClosestNeutral) {
                    distanceToClosestNeutral = rc.getLocation().distanceSquaredTo(nonfriendlyHQs[i]);
                    neutralStrength = nonfriendlyHQstrengths[i];
                    neutralIndex = i;
                }
            }
            //System.out.println("trying to attack a base with hp "+neutralStrength);
            // spawn a politician of sufficient strength to take over this neutral HQ
            int cost = Math.min(neutralStrength + 64, 500) + 11 + (int)Math.max(0, Math.pow(distanceToClosestNeutral,.75)-50)/2;
            if (neutralIndex != -1) {
                if(inf > cost) {
                    polIDToKillNeutral[neutralIndex] = build(RobotType.POLITICIAN, cost);
                    //System.out.println("building poly with inf "+ (Math.min(neutralStrength + 64, 500) + 110)+" to attack neutral");
                    return;
                } else if(expectedTotalIncome > cost * 3) {
                    //save up for this neutral attack
                    build(RobotType.MUCKRAKER, 1);
                }
            }
        }

        /*
        if(inf > 500 && (enemyHQ | neutralHQ)) {
            if (enemyHQ && Math.random() < 0.2) build(RobotType.MUCKRAKER, Math.max(400, inf / 100));
            else build(RobotType.POLITICIAN, Math.max(400, inf / 100));
            return;
        }
        */
        if(inf < 11 || rc.getInfluence()<40) {
            build(RobotType.MUCKRAKER, 1);
            return;
        }
        //open with a slanderer always
        if(rc.getRoundNum() < 3) {
            build(RobotType.SLANDERER, Threshold.slandererThreshold(inf));
            return;
        }
        //build a small number of cheap rakers, but only build if we are poor
        if(slanderers > 0 && Math.random() < 1.0/(1.0+rakerCount /*rakers.size()*/) && (inf+expectedTotalIncome) < 2000) {
            build(RobotType.MUCKRAKER, 1);
        }
        int income =  rc.getInfluence() - lastInf;
        //if i am under attack by rakers, build a poly to defend
        if(enemyRStrength > 0 && enemyRStrength > myPStrength - GameConstants.EMPOWER_TAX) {
            build(RobotType.POLITICIAN, Math.min(inf, GameConstants.EMPOWER_TAX + enemyRStrength - myPStrength));
            return;
        }
        //a small poly is for blowing up cheap rakers. build them in a variety of values
        int smallPoly = Math.min(inf, 16 + inf/400 + (int)Math.min(Math.random() * 50, (inf+expectedTotalIncome)/100));
        final double INCOME_TARGET = 187 * (polyCount-3)*3/2; //at 187 * x, this corresponds to building slanderers for x% of builds

        //if rakers are around, don't build slanderers
        if(enemyRStrength==0) {
            //number of polys needed before proceeding beyond income thresholds:
            //500 total income : 4 polys
            //1500 total income : 8 polys
            //2500 total income : 12 polys
            if(expectedTotalIncome < 1500) {
                if(expectedTotalIncome > 500 && smallPolyCount < 4)
                    build(RobotType.POLITICIAN, smallPoly);
                //if we could wait for a bigger slanderer, then build a 1hp raker
                if(inf < 100 && income * 6 > Threshold.slandererThreshold(inf)) {
                    //except actually build a smallPoly if we have waaay to many rakers
                    if (rakerCount > 1200) build(RobotType.POLITICIAN, smallPoly);
                    else build(RobotType.MUCKRAKER,1);
                    return;
                }
                build(RobotType.SLANDERER, Threshold.slandererThreshold(inf));
                return;
            } else if((expectedTotalIncome > 2500 && polyCount < 12) || polyCount < 8 ) {
                if(inf > 1500)
                    build(RobotType.POLITICIAN, Math.min(inf-(int)INCOME_TARGET/10, (inf + expectedTotalIncome)/20));
                else
                    build(RobotType.POLITICIAN, smallPoly);
                return;
            }
            System.out.println("INCOME_TARGET "+INCOME_TARGET);
            //this indicates that we are falling behind on income
            if(expectedTotalIncome < expectedCurrentIncome * 30 && (expectedTotalIncome + Math.max(0, inf-10000)< INCOME_TARGET || expectedTotalIncome < expectedCurrentIncome * 21)) {
                build(RobotType.SLANDERER, Threshold.slandererThreshold(inf));
                return;
            }
            //if we have sufficient income, build a mix of small polys, big polys, and buffrakers
            //currently: 25% buffrakers, 50% big polys, 12.5% small polys, 12.5% small rakers
            if(Math.random() < .25) {
                build(RobotType.POLITICIAN, Math.min(inf-(int)INCOME_TARGET/10, (inf + expectedTotalIncome)/20));
            } else if(Math.random() < .75) {
                if(Math.random() < .25)
                    build(RobotType.MUCKRAKER, smallPoly);
                else
                    build(RobotType.POLITICIAN, smallPoly);
            } else {
                build(RobotType.MUCKRAKER, Math.min(inf-(int)INCOME_TARGET/10, (inf + expectedTotalIncome)/20));
            }
        } else {
            //if rakers are around, build a small Poly
            build(RobotType.POLITICIAN, smallPoly);
        }
        /*
        if(enemyRStrength == 0 && (rc.getRoundNum()<3 || politicians*(rc.getRoundNum() - lastRakerRound) > slanderers || politicians > 20) && (inf<1000 || income<500) && (income < 40 || polyCount > 10)) {
            if(rc.getRoundNum() < 50 && inf < 949 && income * 6 > Threshold.slandererThreshold(inf)) { //more early rakers
                if (rakerCount > 100) build(RobotType.POLITICIAN, Math.min(inf, 22 + inf/40));
                else build(RobotType.MUCKRAKER,1);
            } else build(RobotType.SLANDERER, Threshold.slandererThreshold(inf));
        } else {
            build(RobotType.POLITICIAN, Math.min(inf, 22 + inf/40));
        }
        */
    }
    private int build(RobotType t, int influence) throws GameActionException {
        //System.out.println("building "+t+" with inf "+influence);
        int offset = (int)(Math.random()*8);
        for (int i=0;i<8;i++) {
            Direction dir = RobotPlayer.directions[(i+offset)%8];
            if (rc.canBuildRobot(t, dir, influence)) {
                rc.buildRobot(t, dir, influence);
                RobotInfo r = rc.senseRobotAtLocation(rc.getLocation().add(dir));
                if(r==null) return -1;
                if(t == RobotType.MUCKRAKER) {
                    //rakers.add(r.ID);
                    rakerCount++;
                } else if(t == RobotType.POLITICIAN) {
                    polyCount++;
                    if(r.conviction < 200)
                        smallPolyCount++;
                    //others.add(r.ID);
                } else if(t == RobotType.SLANDERER) {
                    slandererBuilds[rc.getRoundNum()] = Threshold.slandererIncome(influence);
                    expectedTotalIncome += Threshold.slandererIncome(influence) * 50;
                    expectedCurrentIncome += Threshold.slandererIncome(influence);
                }
                allRobots[flagsIndex++] = r;
                allBuilds[rc.getRoundNum()] = r;
                return r.ID;
            }
        }
        return -1;
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
                else if(allRobots[i].type == RobotType.POLITICIAN || allRobots[i].type == RobotType.SLANDERER) {
                    polyCount--;
                    if(allRobots[i].conviction < 200 && allRobots[i].type==RobotType.POLITICIAN)
                        smallPolyCount--;
                }
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
        //System.out.println("Starting reading: " + Clock.getBytecodesLeft());
        Iterator<Integer> it = rakers.iterator();
        //System.out.println("num rakers " +rakers.size());
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
        //System.out.println("Read rakers: " + Clock.getBytecodesLeft());
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
        //System.out.println("Read others: " + Clock.getBytecodesLeft());
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
            setFlag(NONFRIENDLY_HQ | Robot.locToFlag(l) | a | ((Math.min(nonfriendlyHQstrengths[hqIndex], 448) >> 6) << 16)); 
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
    double voteProp = 0;
    double maxVoteProp = 0;
    double minVoteProp = 0;
    public int vote(int inf) throws GameActionException {
        if (inf <= 0) {
            attemptedVote = false;
            return 0;
        }

        boolean lostVote = attemptedVote && rc.getTeamVotes() == myVotes;
        if (lostVote) opponentVotes++;
        myVotes = rc.getTeamVotes();

        if (myVotes > 750 || opponentVotes - myVotes > 1501 - rc.getRoundNum()) {
            attemptedVote = false;
            return 0;
        }

        int round = rc.getRoundNum();
        if (round == 1450) {
            minVoteProp = 0.1;
            if (voteProp < minVoteProp) voteProp = minVoteProp;
            maxVoteProp = 0.25;
        } else if (round == 1350) {
            minVoteProp = 0.05;
            if (voteProp < minVoteProp) voteProp = minVoteProp;
            maxVoteProp = 0.1;
        } else if (round == 1200) {
            minVoteProp = 0.03;
            if (voteProp < minVoteProp) voteProp = minVoteProp;
            maxVoteProp = 0.05;
        } else if (round == 1000) {
            minVoteProp = 0.02;
            if (voteProp < minVoteProp) voteProp = minVoteProp;
            maxVoteProp = 0.03;
        } else if (round == 400) {
            minVoteProp = 0.01;
            if (voteProp < minVoteProp) voteProp = minVoteProp;
            maxVoteProp = 0.02;
        } else if (round % 25 == 0) {
            if (voteProp < minVoteProp) voteProp = minVoteProp;
        }

        if (voteProp == 0) {
            attemptedVote = false;
            return 0;
        }

        if (lostVote) {
            if (voteProp < maxVoteProp) voteProp += maxVoteProp / 4;
            else {
                voteProp = 0;
                attemptedVote = false;
                return 0;
            }
        } else if (voteProp > minVoteProp) voteProp -= maxVoteProp / 16;

        return bid((int) (inf * voteProp) + 1);
    }

    public int bid(int b) throws GameActionException {
        if (rc.canBid(b)) {
            rc.bid(b);
            attemptedVote = true;
            return b;
        } else {
            attemptedVote = false;
            return 0;
        }
    }
}
