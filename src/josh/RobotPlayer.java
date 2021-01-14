package josh;
import battlecode.common.*;

/*
 * Politician + slanderer turtle
 *  
 */




public strictfp class RobotPlayer {
    static RobotController rc;

    static final RobotType[] spawnableRobot = {
        RobotType.POLITICIAN,
        RobotType.SLANDERER,
        RobotType.MUCKRAKER,
    };

    static final Direction[] directions = {
        Direction.NORTH,
        Direction.NORTHEAST,
        Direction.EAST,
        Direction.SOUTHEAST,
        Direction.SOUTH,
        Direction.SOUTHWEST,
        Direction.WEST,
        Direction.NORTHWEST,
    };

    static int turnCount;

    /**
     * run() is the method that is called when a robot is instantiated in the Battlecode world.
     * If this method returns, the robot dies!
     **/
    
    
    @SuppressWarnings("unused")
    public static void run(RobotController rc) throws GameActionException {

        // This is the RobotController object. You use it to perform actions from this robot,
        // and to get information on its current status.
        RobotPlayer.rc = rc;
		rc.setFlag(0);
        Robot r = null;

        switch (rc.getType()) {
            case ENLIGHTENMENT_CENTER: r = new Center(rc); 			  break;
            case POLITICIAN:           r = new Politician(rc);          break;
            case SLANDERER:            r = new Slanderer(rc);           break;
            case MUCKRAKER:            r = new Muckraker(rc);           break;
        }
        r.run();
    }

    /**
     * Returns a random Direction.
     *
     * @return a random Direction
     */
    static Direction randomDirection() {
        return directions[(int) (Math.random() * directions.length)];
    }
}
