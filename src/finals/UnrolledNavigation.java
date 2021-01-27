package finals;
import battlecode.common.*;
public class UnrolledNavigation extends Navigation {
public UnrolledNavigation(RobotController r) {super(r);}
public void navTo (MapLocation destination) throws GameActionException {
int initialBytecodeCount = Clock.getBytecodeNum();
//System.out.println("Intelligent navigation to " + destination);
MapLocation myLoc = rc.getLocation();
int elevations00;
MapLocation loc00 = new MapLocation(myLoc.x + -2, myLoc.y + -2);
boolean validLocs00 = rc.canSenseLocation(loc00) && !rc.isLocationOccupied(loc00);
double passabilities00= Double.MAX_VALUE; if (validLocs00) passabilities00 = 1.0/rc.sensePassability(loc00);
int elevations01;
MapLocation loc01 = new MapLocation(myLoc.x + -2, myLoc.y + -1);
boolean validLocs01 = rc.canSenseLocation(loc01) && !rc.isLocationOccupied(loc01);
double passabilities01= Double.MAX_VALUE; if (validLocs01) passabilities01 = 1.0/rc.sensePassability(loc01);
int elevations02;
MapLocation loc02 = new MapLocation(myLoc.x + -2, myLoc.y + 0);
boolean validLocs02 = rc.canSenseLocation(loc02) && !rc.isLocationOccupied(loc02);
double passabilities02= Double.MAX_VALUE; if (validLocs02) passabilities02 = 1.0/rc.sensePassability(loc02);
int elevations03;
MapLocation loc03 = new MapLocation(myLoc.x + -2, myLoc.y + 1);
boolean validLocs03 = rc.canSenseLocation(loc03) && !rc.isLocationOccupied(loc03);
double passabilities03= Double.MAX_VALUE; if (validLocs03) passabilities03 = 1.0/rc.sensePassability(loc03);
int elevations04;
MapLocation loc04 = new MapLocation(myLoc.x + -2, myLoc.y + 2);
boolean validLocs04 = rc.canSenseLocation(loc04) && !rc.isLocationOccupied(loc04);
double passabilities04= Double.MAX_VALUE; if (validLocs04) passabilities04 = 1.0/rc.sensePassability(loc04);
int elevations10;
MapLocation loc10 = new MapLocation(myLoc.x + -1, myLoc.y + -2);
boolean validLocs10 = rc.canSenseLocation(loc10) && !rc.isLocationOccupied(loc10);
double passabilities10= Double.MAX_VALUE; if (validLocs10) passabilities10 = 1.0/rc.sensePassability(loc10);
int elevations11;
MapLocation loc11 = new MapLocation(myLoc.x + -1, myLoc.y + -1);
boolean validLocs11 = rc.canSenseLocation(loc11) && !rc.isLocationOccupied(loc11);
double passabilities11= Double.MAX_VALUE; if (validLocs11) passabilities11 = 1.0/rc.sensePassability(loc11);
int elevations12;
MapLocation loc12 = new MapLocation(myLoc.x + -1, myLoc.y + 0);
boolean validLocs12 = rc.canSenseLocation(loc12) && !rc.isLocationOccupied(loc12);
double passabilities12= Double.MAX_VALUE; if (validLocs12) passabilities12 = 1.0/rc.sensePassability(loc12);
int elevations13;
MapLocation loc13 = new MapLocation(myLoc.x + -1, myLoc.y + 1);
boolean validLocs13 = rc.canSenseLocation(loc13) && !rc.isLocationOccupied(loc13);
double passabilities13= Double.MAX_VALUE; if (validLocs13) passabilities13 = 1.0/rc.sensePassability(loc13);
int elevations14;
MapLocation loc14 = new MapLocation(myLoc.x + -1, myLoc.y + 2);
boolean validLocs14 = rc.canSenseLocation(loc14) && !rc.isLocationOccupied(loc14);
double passabilities14= Double.MAX_VALUE; if (validLocs14) passabilities14 = 1.0/rc.sensePassability(loc14);
int elevations20;
MapLocation loc20 = new MapLocation(myLoc.x + 0, myLoc.y + -2);
boolean validLocs20 = rc.canSenseLocation(loc20) && !rc.isLocationOccupied(loc20);
double passabilities20= Double.MAX_VALUE; if (validLocs20) passabilities20 = 1.0/rc.sensePassability(loc20);
int elevations21;
MapLocation loc21 = new MapLocation(myLoc.x + 0, myLoc.y + -1);
boolean validLocs21 = rc.canSenseLocation(loc21) && !rc.isLocationOccupied(loc21);
double passabilities21= Double.MAX_VALUE; if (validLocs21) passabilities21 = 1.0/rc.sensePassability(loc21);
int elevations22;
MapLocation loc22 = new MapLocation(myLoc.x + 0, myLoc.y + 0);
boolean validLocs22 = rc.canSenseLocation(loc22) && !rc.isLocationOccupied(loc22);
double passabilities22= Double.MAX_VALUE; if (validLocs22) passabilities22 = 1.0/rc.sensePassability(loc22);
int elevations23;
MapLocation loc23 = new MapLocation(myLoc.x + 0, myLoc.y + 1);
boolean validLocs23 = rc.canSenseLocation(loc23) && !rc.isLocationOccupied(loc23);
double passabilities23= Double.MAX_VALUE; if (validLocs23) passabilities23 = 1.0/rc.sensePassability(loc23);
int elevations24;
MapLocation loc24 = new MapLocation(myLoc.x + 0, myLoc.y + 2);
boolean validLocs24 = rc.canSenseLocation(loc24) && !rc.isLocationOccupied(loc24);
double passabilities24= Double.MAX_VALUE; if (validLocs24) passabilities24 = 1.0/rc.sensePassability(loc24);
int elevations30;
MapLocation loc30 = new MapLocation(myLoc.x + 1, myLoc.y + -2);
boolean validLocs30 = rc.canSenseLocation(loc30) && !rc.isLocationOccupied(loc30);
double passabilities30= Double.MAX_VALUE; if (validLocs30) passabilities30 = 1.0/rc.sensePassability(loc30);
int elevations31;
MapLocation loc31 = new MapLocation(myLoc.x + 1, myLoc.y + -1);
boolean validLocs31 = rc.canSenseLocation(loc31) && !rc.isLocationOccupied(loc31);
double passabilities31= Double.MAX_VALUE; if (validLocs31) passabilities31 = 1.0/rc.sensePassability(loc31);
int elevations32;
MapLocation loc32 = new MapLocation(myLoc.x + 1, myLoc.y + 0);
boolean validLocs32 = rc.canSenseLocation(loc32) && !rc.isLocationOccupied(loc32);
double passabilities32= Double.MAX_VALUE; if (validLocs32) passabilities32 = 1.0/rc.sensePassability(loc32);
int elevations33;
MapLocation loc33 = new MapLocation(myLoc.x + 1, myLoc.y + 1);
boolean validLocs33 = rc.canSenseLocation(loc33) && !rc.isLocationOccupied(loc33);
double passabilities33= Double.MAX_VALUE; if (validLocs33) passabilities33 = 1.0/rc.sensePassability(loc33);
int elevations34;
MapLocation loc34 = new MapLocation(myLoc.x + 1, myLoc.y + 2);
boolean validLocs34 = rc.canSenseLocation(loc34) && !rc.isLocationOccupied(loc34);
double passabilities34= Double.MAX_VALUE; if (validLocs34) passabilities34 = 1.0/rc.sensePassability(loc34);
int elevations40;
MapLocation loc40 = new MapLocation(myLoc.x + 2, myLoc.y + -2);
boolean validLocs40 = rc.canSenseLocation(loc40) && !rc.isLocationOccupied(loc40);
double passabilities40= Double.MAX_VALUE; if (validLocs40) passabilities40 = 1.0/rc.sensePassability(loc40);
int elevations41;
MapLocation loc41 = new MapLocation(myLoc.x + 2, myLoc.y + -1);
boolean validLocs41 = rc.canSenseLocation(loc41) && !rc.isLocationOccupied(loc41);
double passabilities41= Double.MAX_VALUE; if (validLocs41) passabilities41 = 1.0/rc.sensePassability(loc41);
int elevations42;
MapLocation loc42 = new MapLocation(myLoc.x + 2, myLoc.y + 0);
boolean validLocs42 = rc.canSenseLocation(loc42) && !rc.isLocationOccupied(loc42);
double passabilities42= Double.MAX_VALUE; if (validLocs42) passabilities42 = 1.0/rc.sensePassability(loc42);
int elevations43;
MapLocation loc43 = new MapLocation(myLoc.x + 2, myLoc.y + 1);
boolean validLocs43 = rc.canSenseLocation(loc43) && !rc.isLocationOccupied(loc43);
double passabilities43= Double.MAX_VALUE; if (validLocs43) passabilities43 = 1.0/rc.sensePassability(loc43);
int elevations44;
MapLocation loc44 = new MapLocation(myLoc.x + 2, myLoc.y + 2);
boolean validLocs44 = rc.canSenseLocation(loc44) && !rc.isLocationOccupied(loc44);
double passabilities44= Double.MAX_VALUE; if (validLocs44) passabilities44 = 1.0/rc.sensePassability(loc44);
validLocs22 = true;
passabilities22 = 1.0/rc.sensePassability(loc22);
//System.out.println("Bytecodes used in checking location validity: " + (Clock.getBytecodeNum() - initialBytecodeCount));
boolean validEdges000 = (validLocs01 && validLocs10);
boolean validEdges100 = (validLocs00 && validLocs10);
boolean validEdges200 = (validLocs00 && validLocs11);
boolean validEdges300 = (validLocs00 && validLocs01);
boolean validEdges001 = (validLocs02 && validLocs11);
boolean validEdges101 = (validLocs01 && validLocs11);
boolean validEdges201 = (validLocs01 && validLocs12);
boolean validEdges301 = (validLocs01 && validLocs02);
boolean validEdges002 = (validLocs03 && validLocs12);
boolean validEdges102 = (validLocs02 && validLocs12);
boolean validEdges202 = (validLocs02 && validLocs13);
boolean validEdges302 = (validLocs02 && validLocs03);
boolean validEdges003 = (validLocs04 && validLocs13);
boolean validEdges103 = (validLocs03 && validLocs13);
boolean validEdges203 = (validLocs03 && validLocs14);
boolean validEdges303 = (validLocs03 && validLocs04);
boolean validEdges010 = (validLocs11 && validLocs20);
boolean validEdges110 = (validLocs10 && validLocs20);
boolean validEdges210 = (validLocs10 && validLocs21);
boolean validEdges310 = (validLocs10 && validLocs11);
boolean validEdges011 = (validLocs12 && validLocs21);
boolean validEdges111 = (validLocs11 && validLocs21);
boolean validEdges211 = (validLocs11 && validLocs22);
boolean validEdges311 = (validLocs11 && validLocs12);
boolean validEdges012 = (validLocs13 && validLocs22);
boolean validEdges112 = (validLocs12 && validLocs22);
boolean validEdges212 = (validLocs12 && validLocs23);
boolean validEdges312 = (validLocs12 && validLocs13);
boolean validEdges013 = (validLocs14 && validLocs23);
boolean validEdges113 = (validLocs13 && validLocs23);
boolean validEdges213 = (validLocs13 && validLocs24);
boolean validEdges313 = (validLocs13 && validLocs14);
boolean validEdges020 = (validLocs21 && validLocs30);
boolean validEdges120 = (validLocs20 && validLocs30);
boolean validEdges220 = (validLocs20 && validLocs31);
boolean validEdges320 = (validLocs20 && validLocs21);
boolean validEdges021 = (validLocs22 && validLocs31);
boolean validEdges121 = (validLocs21 && validLocs31);
boolean validEdges221 = (validLocs21 && validLocs32);
boolean validEdges321 = (validLocs21 && validLocs22);
boolean validEdges022 = (validLocs23 && validLocs32);
boolean validEdges122 = (validLocs22 && validLocs32);
boolean validEdges222 = (validLocs22 && validLocs33);
boolean validEdges322 = (validLocs22 && validLocs23);
boolean validEdges023 = (validLocs24 && validLocs33);
boolean validEdges123 = (validLocs23 && validLocs33);
boolean validEdges223 = (validLocs23 && validLocs34);
boolean validEdges323 = (validLocs23 && validLocs24);
boolean validEdges030 = (validLocs31 && validLocs40);
boolean validEdges130 = (validLocs30 && validLocs40);
boolean validEdges230 = (validLocs30 && validLocs41);
boolean validEdges330 = (validLocs30 && validLocs31);
boolean validEdges031 = (validLocs32 && validLocs41);
boolean validEdges131 = (validLocs31 && validLocs41);
boolean validEdges231 = (validLocs31 && validLocs42);
boolean validEdges331 = (validLocs31 && validLocs32);
boolean validEdges032 = (validLocs33 && validLocs42);
boolean validEdges132 = (validLocs32 && validLocs42);
boolean validEdges232 = (validLocs32 && validLocs43);
boolean validEdges332 = (validLocs32 && validLocs33);
boolean validEdges033 = (validLocs34 && validLocs43);
boolean validEdges133 = (validLocs33 && validLocs43);
boolean validEdges233 = (validLocs33 && validLocs44);
boolean validEdges333 = (validLocs33 && validLocs34);
boolean validEdges104 = (validLocs04 && validLocs14);
boolean validEdges114 = (validLocs14 && validLocs24);
boolean validEdges124 = (validLocs24 && validLocs34);
boolean validEdges134 = (validLocs34 && validLocs44);
boolean validEdges340 = (validLocs40 && validLocs41);
boolean validEdges341 = (validLocs41 && validLocs42);
boolean validEdges342 = (validLocs42 && validLocs43);
boolean validEdges343 = (validLocs43 && validLocs44);
//System.out.println("Bytecodes used in setup: " + (Clock.getBytecodeNum() - initialBytecodeCount));
int shiftedX = myLoc.x - destination.x - 2;
int shiftedY = myLoc.y - destination.y - 2;
double distances00 = Math.max(Math.abs(shiftedX + 0), Math.abs(shiftedY + 0)) * 100 + (Math.sqrt(Math.pow(shiftedX + 0, 2) + Math.pow(shiftedY + 0, 2)) * 0.01);
double distances01 = Math.max(Math.abs(shiftedX + 0), Math.abs(shiftedY + 1)) * 100 + (Math.sqrt(Math.pow(shiftedX + 0, 2) + Math.pow(shiftedY + 1, 2)) * 0.01);
double distances02 = Math.max(Math.abs(shiftedX + 0), Math.abs(shiftedY + 2)) * 100 + (Math.sqrt(Math.pow(shiftedX + 0, 2) + Math.pow(shiftedY + 2, 2)) * 0.01);
double distances03 = Math.max(Math.abs(shiftedX + 0), Math.abs(shiftedY + 3)) * 100 + (Math.sqrt(Math.pow(shiftedX + 0, 2) + Math.pow(shiftedY + 3, 2)) * 0.01);
double distances04 = Math.max(Math.abs(shiftedX + 0), Math.abs(shiftedY + 4)) * 100 + (Math.sqrt(Math.pow(shiftedX + 0, 2) + Math.pow(shiftedY + 4, 2)) * 0.01);
double distances10 = Math.max(Math.abs(shiftedX + 1), Math.abs(shiftedY + 0)) * 100 + (Math.sqrt(Math.pow(shiftedX + 1, 2) + Math.pow(shiftedY + 0, 2)) * 0.01);
double distances11 = Math.max(Math.abs(shiftedX + 1), Math.abs(shiftedY + 1)) * 100 + (Math.sqrt(Math.pow(shiftedX + 1, 2) + Math.pow(shiftedY + 1, 2)) * 0.01);
double distances12 = Math.max(Math.abs(shiftedX + 1), Math.abs(shiftedY + 2)) * 100 + (Math.sqrt(Math.pow(shiftedX + 1, 2) + Math.pow(shiftedY + 2, 2)) * 0.01);
double distances13 = Math.max(Math.abs(shiftedX + 1), Math.abs(shiftedY + 3)) * 100 + (Math.sqrt(Math.pow(shiftedX + 1, 2) + Math.pow(shiftedY + 3, 2)) * 0.01);
double distances14 = Math.max(Math.abs(shiftedX + 1), Math.abs(shiftedY + 4)) * 100 + (Math.sqrt(Math.pow(shiftedX + 1, 2) + Math.pow(shiftedY + 4, 2)) * 0.01);
double distances20 = Math.max(Math.abs(shiftedX + 2), Math.abs(shiftedY + 0)) * 100 + (Math.sqrt(Math.pow(shiftedX + 2, 2) + Math.pow(shiftedY + 0, 2)) * 0.01);
double distances21 = Math.max(Math.abs(shiftedX + 2), Math.abs(shiftedY + 1)) * 100 + (Math.sqrt(Math.pow(shiftedX + 2, 2) + Math.pow(shiftedY + 1, 2)) * 0.01);
double distances22 = Math.max(Math.abs(shiftedX + 2), Math.abs(shiftedY + 2)) * 100 + (Math.sqrt(Math.pow(shiftedX + 2, 2) + Math.pow(shiftedY + 2, 2)) * 0.01);
double distances23 = Math.max(Math.abs(shiftedX + 2), Math.abs(shiftedY + 3)) * 100 + (Math.sqrt(Math.pow(shiftedX + 2, 2) + Math.pow(shiftedY + 3, 2)) * 0.01);
double distances24 = Math.max(Math.abs(shiftedX + 2), Math.abs(shiftedY + 4)) * 100 + (Math.sqrt(Math.pow(shiftedX + 2, 2) + Math.pow(shiftedY + 4, 2)) * 0.01);
double distances30 = Math.max(Math.abs(shiftedX + 3), Math.abs(shiftedY + 0)) * 100 + (Math.sqrt(Math.pow(shiftedX + 3, 2) + Math.pow(shiftedY + 0, 2)) * 0.01);
double distances31 = Math.max(Math.abs(shiftedX + 3), Math.abs(shiftedY + 1)) * 100 + (Math.sqrt(Math.pow(shiftedX + 3, 2) + Math.pow(shiftedY + 1, 2)) * 0.01);
double distances32 = Math.max(Math.abs(shiftedX + 3), Math.abs(shiftedY + 2)) * 100 + (Math.sqrt(Math.pow(shiftedX + 3, 2) + Math.pow(shiftedY + 2, 2)) * 0.01);
double distances33 = Math.max(Math.abs(shiftedX + 3), Math.abs(shiftedY + 3)) * 100 + (Math.sqrt(Math.pow(shiftedX + 3, 2) + Math.pow(shiftedY + 3, 2)) * 0.01);
double distances34 = Math.max(Math.abs(shiftedX + 3), Math.abs(shiftedY + 4)) * 100 + (Math.sqrt(Math.pow(shiftedX + 3, 2) + Math.pow(shiftedY + 4, 2)) * 0.01);
double distances40 = Math.max(Math.abs(shiftedX + 4), Math.abs(shiftedY + 0)) * 100 + (Math.sqrt(Math.pow(shiftedX + 4, 2) + Math.pow(shiftedY + 0, 2)) * 0.01);
double distances41 = Math.max(Math.abs(shiftedX + 4), Math.abs(shiftedY + 1)) * 100 + (Math.sqrt(Math.pow(shiftedX + 4, 2) + Math.pow(shiftedY + 1, 2)) * 0.01);
double distances42 = Math.max(Math.abs(shiftedX + 4), Math.abs(shiftedY + 2)) * 100 + (Math.sqrt(Math.pow(shiftedX + 4, 2) + Math.pow(shiftedY + 2, 2)) * 0.01);
double distances43 = Math.max(Math.abs(shiftedX + 4), Math.abs(shiftedY + 3)) * 100 + (Math.sqrt(Math.pow(shiftedX + 4, 2) + Math.pow(shiftedY + 3, 2)) * 0.01);
double distances44 = Math.max(Math.abs(shiftedX + 4), Math.abs(shiftedY + 4)) * 100 + (Math.sqrt(Math.pow(shiftedX + 4, 2) + Math.pow(shiftedY + 4, 2)) * 0.01);
//System.out.println("Bytecodes used in setup and distance initialization: " + (Clock.getBytecodeNum() - initialBytecodeCount));
if (validEdges000){
distances01=Math.min(distances01, distances10+passabilities10);
distances10=Math.min(distances01+passabilities01, distances10);
}
if (validEdges100){
distances00=Math.min(distances00, distances10+passabilities10);
distances10=Math.min(distances00+passabilities00, distances10);
}
if (validEdges200){
distances00=Math.min(distances00, distances11+passabilities11);
distances11=Math.min(distances00+passabilities00, distances11);
}
if (validEdges300){
distances00=Math.min(distances00, distances01+passabilities01);
distances01=Math.min(distances00+passabilities00, distances01);
}
if (validEdges001){
distances02=Math.min(distances02, distances11+passabilities11);
distances11=Math.min(distances02+passabilities02, distances11);
}
if (validEdges101){
distances01=Math.min(distances01, distances11+passabilities11);
distances11=Math.min(distances01+passabilities01, distances11);
}
if (validEdges201){
distances01=Math.min(distances01, distances12+passabilities12);
distances12=Math.min(distances01+passabilities01, distances12);
}
if (validEdges301){
distances01=Math.min(distances01, distances02+passabilities02);
distances02=Math.min(distances01+passabilities01, distances02);
}
if (validEdges002){
distances03=Math.min(distances03, distances12+passabilities12);
distances12=Math.min(distances03+passabilities03, distances12);
}
if (validEdges102){
distances02=Math.min(distances02, distances12+passabilities12);
distances12=Math.min(distances02+passabilities02, distances12);
}
if (validEdges202){
distances02=Math.min(distances02, distances13+passabilities13);
distances13=Math.min(distances02+passabilities02, distances13);
}
if (validEdges302){
distances02=Math.min(distances02, distances03+passabilities03);
distances03=Math.min(distances02+passabilities02, distances03);
}
if (validEdges003){
distances04=Math.min(distances04, distances13+passabilities13);
distances13=Math.min(distances04+passabilities04, distances13);
}
if (validEdges103){
distances03=Math.min(distances03, distances13+passabilities13);
distances13=Math.min(distances03+passabilities03, distances13);
}
if (validEdges203){
distances03=Math.min(distances03, distances14+passabilities14);
distances14=Math.min(distances03+passabilities03, distances14);
}
if (validEdges303){
distances03=Math.min(distances03, distances04+passabilities04);
distances04=Math.min(distances03+passabilities03, distances04);
}
if (validEdges010){
distances11=Math.min(distances11, distances20+passabilities20);
distances20=Math.min(distances11+passabilities11, distances20);
}
if (validEdges110){
distances10=Math.min(distances10, distances20+passabilities20);
distances20=Math.min(distances10+passabilities10, distances20);
}
if (validEdges210){
distances10=Math.min(distances10, distances21+passabilities21);
distances21=Math.min(distances10+passabilities10, distances21);
}
if (validEdges310){
distances10=Math.min(distances10, distances11+passabilities11);
distances11=Math.min(distances10+passabilities10, distances11);
}
if (validEdges011){
distances12=Math.min(distances12, distances21+passabilities21);
distances21=Math.min(distances12+passabilities12, distances21);
}
if (validEdges111){
distances11=Math.min(distances11, distances21+passabilities21);
distances21=Math.min(distances11+passabilities11, distances21);
}
if (validEdges211){
distances11=Math.min(distances11, distances22+passabilities22);
distances22=Math.min(distances11+passabilities11, distances22);
}
if (validEdges311){
distances11=Math.min(distances11, distances12+passabilities12);
distances12=Math.min(distances11+passabilities11, distances12);
}
if (validEdges012){
distances13=Math.min(distances13, distances22+passabilities22);
distances22=Math.min(distances13+passabilities13, distances22);
}
if (validEdges112){
distances12=Math.min(distances12, distances22+passabilities22);
distances22=Math.min(distances12+passabilities12, distances22);
}
if (validEdges212){
distances12=Math.min(distances12, distances23+passabilities23);
distances23=Math.min(distances12+passabilities12, distances23);
}
if (validEdges312){
distances12=Math.min(distances12, distances13+passabilities13);
distances13=Math.min(distances12+passabilities12, distances13);
}
if (validEdges013){
distances14=Math.min(distances14, distances23+passabilities23);
distances23=Math.min(distances14+passabilities14, distances23);
}
if (validEdges113){
distances13=Math.min(distances13, distances23+passabilities23);
distances23=Math.min(distances13+passabilities13, distances23);
}
if (validEdges213){
distances13=Math.min(distances13, distances24+passabilities24);
distances24=Math.min(distances13+passabilities13, distances24);
}
if (validEdges313){
distances13=Math.min(distances13, distances14+passabilities14);
distances14=Math.min(distances13+passabilities13, distances14);
}
if (validEdges020){
distances21=Math.min(distances21, distances30+passabilities30);
distances30=Math.min(distances21+passabilities21, distances30);
}
if (validEdges120){
distances20=Math.min(distances20, distances30+passabilities30);
distances30=Math.min(distances20+passabilities20, distances30);
}
if (validEdges220){
distances20=Math.min(distances20, distances31+passabilities31);
distances31=Math.min(distances20+passabilities20, distances31);
}
if (validEdges320){
distances20=Math.min(distances20, distances21+passabilities21);
distances21=Math.min(distances20+passabilities20, distances21);
}
if (validEdges021){
distances22=Math.min(distances22, distances31+passabilities31);
distances31=Math.min(distances22+passabilities22, distances31);
}
if (validEdges121){
distances21=Math.min(distances21, distances31+passabilities31);
distances31=Math.min(distances21+passabilities21, distances31);
}
if (validEdges221){
distances21=Math.min(distances21, distances32+passabilities32);
distances32=Math.min(distances21+passabilities21, distances32);
}
if (validEdges321){
distances21=Math.min(distances21, distances22+passabilities22);
distances22=Math.min(distances21+passabilities21, distances22);
}
if (validEdges022){
distances23=Math.min(distances23, distances32+passabilities32);
distances32=Math.min(distances23+passabilities23, distances32);
}
if (validEdges122){
distances22=Math.min(distances22, distances32+passabilities32);
distances32=Math.min(distances22+passabilities22, distances32);
}
if (validEdges222){
distances22=Math.min(distances22, distances33+passabilities33);
distances33=Math.min(distances22+passabilities22, distances33);
}
if (validEdges322){
distances22=Math.min(distances22, distances23+passabilities23);
distances23=Math.min(distances22+passabilities22, distances23);
}
if (validEdges023){
distances24=Math.min(distances24, distances33+passabilities33);
distances33=Math.min(distances24+passabilities24, distances33);
}
if (validEdges123){
distances23=Math.min(distances23, distances33+passabilities33);
distances33=Math.min(distances23+passabilities23, distances33);
}
if (validEdges223){
distances23=Math.min(distances23, distances34+passabilities34);
distances34=Math.min(distances23+passabilities23, distances34);
}
if (validEdges323){
distances23=Math.min(distances23, distances24+passabilities24);
distances24=Math.min(distances23+passabilities23, distances24);
}
if (validEdges030){
distances31=Math.min(distances31, distances40+passabilities40);
distances40=Math.min(distances31+passabilities31, distances40);
}
if (validEdges130){
distances30=Math.min(distances30, distances40+passabilities40);
distances40=Math.min(distances30+passabilities30, distances40);
}
if (validEdges230){
distances30=Math.min(distances30, distances41+passabilities41);
distances41=Math.min(distances30+passabilities30, distances41);
}
if (validEdges330){
distances30=Math.min(distances30, distances31+passabilities31);
distances31=Math.min(distances30+passabilities30, distances31);
}
if (validEdges031){
distances32=Math.min(distances32, distances41+passabilities41);
distances41=Math.min(distances32+passabilities32, distances41);
}
if (validEdges131){
distances31=Math.min(distances31, distances41+passabilities41);
distances41=Math.min(distances31+passabilities31, distances41);
}
if (validEdges231){
distances31=Math.min(distances31, distances42+passabilities42);
distances42=Math.min(distances31+passabilities31, distances42);
}
if (validEdges331){
distances31=Math.min(distances31, distances32+passabilities32);
distances32=Math.min(distances31+passabilities31, distances32);
}
if (validEdges032){
distances33=Math.min(distances33, distances42+passabilities42);
distances42=Math.min(distances33+passabilities33, distances42);
}
if (validEdges132){
distances32=Math.min(distances32, distances42+passabilities42);
distances42=Math.min(distances32+passabilities32, distances42);
}
if (validEdges232){
distances32=Math.min(distances32, distances43+passabilities43);
distances43=Math.min(distances32+passabilities32, distances43);
}
if (validEdges332){
distances32=Math.min(distances32, distances33+passabilities33);
distances33=Math.min(distances32+passabilities32, distances33);
}
if (validEdges033){
distances34=Math.min(distances34, distances43+passabilities43);
distances43=Math.min(distances34+passabilities34, distances43);
}
if (validEdges133){
distances33=Math.min(distances33, distances43+passabilities43);
distances43=Math.min(distances33+passabilities33, distances43);
}
if (validEdges233){
distances33=Math.min(distances33, distances44+passabilities44);
distances44=Math.min(distances33+passabilities33, distances44);
}
if (validEdges333){
distances33=Math.min(distances33, distances34+passabilities34);
distances34=Math.min(distances33+passabilities33, distances34);
}
if (validEdges104){
distances04=Math.min(distances04, distances14+passabilities14);
distances14=Math.min(distances04+passabilities04, distances14);
}
if (validEdges114){
distances14=Math.min(distances14, distances24+passabilities24);
distances24=Math.min(distances14+passabilities14, distances24);
}
if (validEdges124){
distances24=Math.min(distances24, distances34+passabilities34);
distances34=Math.min(distances24+passabilities24, distances34);
}
if (validEdges134){
distances34=Math.min(distances34, distances44+passabilities44);
distances44=Math.min(distances34+passabilities34, distances44);
}
if (validEdges340){
distances40=Math.min(distances40, distances41+passabilities41);
distances41=Math.min(distances40+passabilities40, distances41);
}
if (validEdges341){
distances41=Math.min(distances41, distances42+passabilities42);
distances42=Math.min(distances41+passabilities41, distances42);
}
if (validEdges342){
distances42=Math.min(distances42, distances43+passabilities43);
distances43=Math.min(distances42+passabilities42, distances43);
}
if (validEdges343){
distances43=Math.min(distances43, distances44+passabilities44);
distances44=Math.min(distances43+passabilities43, distances44);
}
if (validEdges000){
distances01=Math.min(distances01, distances10+passabilities10);
distances10=Math.min(distances01+passabilities01, distances10);
}
if (validEdges100){
distances00=Math.min(distances00, distances10+passabilities10);
distances10=Math.min(distances00+passabilities00, distances10);
}
if (validEdges200){
distances00=Math.min(distances00, distances11+passabilities11);
distances11=Math.min(distances00+passabilities00, distances11);
}
if (validEdges300){
distances00=Math.min(distances00, distances01+passabilities01);
distances01=Math.min(distances00+passabilities00, distances01);
}
if (validEdges001){
distances02=Math.min(distances02, distances11+passabilities11);
distances11=Math.min(distances02+passabilities02, distances11);
}
if (validEdges101){
distances01=Math.min(distances01, distances11+passabilities11);
distances11=Math.min(distances01+passabilities01, distances11);
}
if (validEdges201){
distances01=Math.min(distances01, distances12+passabilities12);
distances12=Math.min(distances01+passabilities01, distances12);
}
if (validEdges301){
distances01=Math.min(distances01, distances02+passabilities02);
distances02=Math.min(distances01+passabilities01, distances02);
}
if (validEdges002){
distances03=Math.min(distances03, distances12+passabilities12);
distances12=Math.min(distances03+passabilities03, distances12);
}
if (validEdges102){
distances02=Math.min(distances02, distances12+passabilities12);
distances12=Math.min(distances02+passabilities02, distances12);
}
if (validEdges202){
distances02=Math.min(distances02, distances13+passabilities13);
distances13=Math.min(distances02+passabilities02, distances13);
}
if (validEdges302){
distances02=Math.min(distances02, distances03+passabilities03);
distances03=Math.min(distances02+passabilities02, distances03);
}
if (validEdges003){
distances04=Math.min(distances04, distances13+passabilities13);
distances13=Math.min(distances04+passabilities04, distances13);
}
if (validEdges103){
distances03=Math.min(distances03, distances13+passabilities13);
distances13=Math.min(distances03+passabilities03, distances13);
}
if (validEdges203){
distances03=Math.min(distances03, distances14+passabilities14);
distances14=Math.min(distances03+passabilities03, distances14);
}
if (validEdges303){
distances03=Math.min(distances03, distances04+passabilities04);
distances04=Math.min(distances03+passabilities03, distances04);
}
if (validEdges010){
distances11=Math.min(distances11, distances20+passabilities20);
distances20=Math.min(distances11+passabilities11, distances20);
}
if (validEdges110){
distances10=Math.min(distances10, distances20+passabilities20);
distances20=Math.min(distances10+passabilities10, distances20);
}
if (validEdges210){
distances10=Math.min(distances10, distances21+passabilities21);
distances21=Math.min(distances10+passabilities10, distances21);
}
if (validEdges310){
distances10=Math.min(distances10, distances11+passabilities11);
distances11=Math.min(distances10+passabilities10, distances11);
}
if (validEdges011){
distances12=Math.min(distances12, distances21+passabilities21);
distances21=Math.min(distances12+passabilities12, distances21);
}
if (validEdges111){
distances11=Math.min(distances11, distances21+passabilities21);
distances21=Math.min(distances11+passabilities11, distances21);
}
if (validEdges211){
distances11=Math.min(distances11, distances22+passabilities22);
distances22=Math.min(distances11+passabilities11, distances22);
}
if (validEdges311){
distances11=Math.min(distances11, distances12+passabilities12);
distances12=Math.min(distances11+passabilities11, distances12);
}
if (validEdges012){
distances13=Math.min(distances13, distances22+passabilities22);
distances22=Math.min(distances13+passabilities13, distances22);
}
if (validEdges112){
distances12=Math.min(distances12, distances22+passabilities22);
distances22=Math.min(distances12+passabilities12, distances22);
}
if (validEdges212){
distances12=Math.min(distances12, distances23+passabilities23);
distances23=Math.min(distances12+passabilities12, distances23);
}
if (validEdges312){
distances12=Math.min(distances12, distances13+passabilities13);
distances13=Math.min(distances12+passabilities12, distances13);
}
if (validEdges013){
distances14=Math.min(distances14, distances23+passabilities23);
distances23=Math.min(distances14+passabilities14, distances23);
}
if (validEdges113){
distances13=Math.min(distances13, distances23+passabilities23);
distances23=Math.min(distances13+passabilities13, distances23);
}
if (validEdges213){
distances13=Math.min(distances13, distances24+passabilities24);
distances24=Math.min(distances13+passabilities13, distances24);
}
if (validEdges313){
distances13=Math.min(distances13, distances14+passabilities14);
distances14=Math.min(distances13+passabilities13, distances14);
}
if (validEdges020){
distances21=Math.min(distances21, distances30+passabilities30);
distances30=Math.min(distances21+passabilities21, distances30);
}
if (validEdges120){
distances20=Math.min(distances20, distances30+passabilities30);
distances30=Math.min(distances20+passabilities20, distances30);
}
if (validEdges220){
distances20=Math.min(distances20, distances31+passabilities31);
distances31=Math.min(distances20+passabilities20, distances31);
}
if (validEdges320){
distances20=Math.min(distances20, distances21+passabilities21);
distances21=Math.min(distances20+passabilities20, distances21);
}
if (validEdges021){
distances22=Math.min(distances22, distances31+passabilities31);
distances31=Math.min(distances22+passabilities22, distances31);
}
if (validEdges121){
distances21=Math.min(distances21, distances31+passabilities31);
distances31=Math.min(distances21+passabilities21, distances31);
}
if (validEdges221){
distances21=Math.min(distances21, distances32+passabilities32);
distances32=Math.min(distances21+passabilities21, distances32);
}
if (validEdges321){
distances21=Math.min(distances21, distances22+passabilities22);
distances22=Math.min(distances21+passabilities21, distances22);
}
if (validEdges022){
distances23=Math.min(distances23, distances32+passabilities32);
distances32=Math.min(distances23+passabilities23, distances32);
}
if (validEdges122){
distances22=Math.min(distances22, distances32+passabilities32);
distances32=Math.min(distances22+passabilities22, distances32);
}
if (validEdges222){
distances22=Math.min(distances22, distances33+passabilities33);
distances33=Math.min(distances22+passabilities22, distances33);
}
if (validEdges322){
distances22=Math.min(distances22, distances23+passabilities23);
distances23=Math.min(distances22+passabilities22, distances23);
}
if (validEdges023){
distances24=Math.min(distances24, distances33+passabilities33);
distances33=Math.min(distances24+passabilities24, distances33);
}
if (validEdges123){
distances23=Math.min(distances23, distances33+passabilities33);
distances33=Math.min(distances23+passabilities23, distances33);
}
if (validEdges223){
distances23=Math.min(distances23, distances34+passabilities34);
distances34=Math.min(distances23+passabilities23, distances34);
}
if (validEdges323){
distances23=Math.min(distances23, distances24+passabilities24);
distances24=Math.min(distances23+passabilities23, distances24);
}
if (validEdges030){
distances31=Math.min(distances31, distances40+passabilities40);
distances40=Math.min(distances31+passabilities31, distances40);
}
if (validEdges130){
distances30=Math.min(distances30, distances40+passabilities40);
distances40=Math.min(distances30+passabilities30, distances40);
}
if (validEdges230){
distances30=Math.min(distances30, distances41+passabilities41);
distances41=Math.min(distances30+passabilities30, distances41);
}
if (validEdges330){
distances30=Math.min(distances30, distances31+passabilities31);
distances31=Math.min(distances30+passabilities30, distances31);
}
if (validEdges031){
distances32=Math.min(distances32, distances41+passabilities41);
distances41=Math.min(distances32+passabilities32, distances41);
}
if (validEdges131){
distances31=Math.min(distances31, distances41+passabilities41);
distances41=Math.min(distances31+passabilities31, distances41);
}
if (validEdges231){
distances31=Math.min(distances31, distances42+passabilities42);
distances42=Math.min(distances31+passabilities31, distances42);
}
if (validEdges331){
distances31=Math.min(distances31, distances32+passabilities32);
distances32=Math.min(distances31+passabilities31, distances32);
}
if (validEdges032){
distances33=Math.min(distances33, distances42+passabilities42);
distances42=Math.min(distances33+passabilities33, distances42);
}
if (validEdges132){
distances32=Math.min(distances32, distances42+passabilities42);
distances42=Math.min(distances32+passabilities32, distances42);
}
if (validEdges232){
distances32=Math.min(distances32, distances43+passabilities43);
distances43=Math.min(distances32+passabilities32, distances43);
}
if (validEdges332){
distances32=Math.min(distances32, distances33+passabilities33);
distances33=Math.min(distances32+passabilities32, distances33);
}
if (validEdges033){
distances34=Math.min(distances34, distances43+passabilities43);
distances43=Math.min(distances34+passabilities34, distances43);
}
if (validEdges133){
distances33=Math.min(distances33, distances43+passabilities43);
distances43=Math.min(distances33+passabilities33, distances43);
}
if (validEdges233){
distances33=Math.min(distances33, distances44+passabilities44);
distances44=Math.min(distances33+passabilities33, distances44);
}
if (validEdges333){
distances33=Math.min(distances33, distances34+passabilities34);
distances34=Math.min(distances33+passabilities33, distances34);
}
if (validEdges104){
distances04=Math.min(distances04, distances14+passabilities14);
distances14=Math.min(distances04+passabilities04, distances14);
}
if (validEdges114){
distances14=Math.min(distances14, distances24+passabilities24);
distances24=Math.min(distances14+passabilities14, distances24);
}
if (validEdges124){
distances24=Math.min(distances24, distances34+passabilities34);
distances34=Math.min(distances24+passabilities24, distances34);
}
if (validEdges134){
distances34=Math.min(distances34, distances44+passabilities44);
distances44=Math.min(distances34+passabilities34, distances44);
}
if (validEdges340){
distances40=Math.min(distances40, distances41+passabilities41);
distances41=Math.min(distances40+passabilities40, distances41);
}
if (validEdges341){
distances41=Math.min(distances41, distances42+passabilities42);
distances42=Math.min(distances41+passabilities41, distances42);
}
if (validEdges342){
distances42=Math.min(distances42, distances43+passabilities43);
distances43=Math.min(distances42+passabilities42, distances43);
}
if (validEdges343){
distances43=Math.min(distances43, distances44+passabilities44);
distances44=Math.min(distances43+passabilities43, distances44);
}
if (validEdges000){
distances01=Math.min(distances01, distances10+passabilities10);
distances10=Math.min(distances01+passabilities01, distances10);
}
if (validEdges100){
distances00=Math.min(distances00, distances10+passabilities10);
distances10=Math.min(distances00+passabilities00, distances10);
}
if (validEdges200){
distances00=Math.min(distances00, distances11+passabilities11);
distances11=Math.min(distances00+passabilities00, distances11);
}
if (validEdges300){
distances00=Math.min(distances00, distances01+passabilities01);
distances01=Math.min(distances00+passabilities00, distances01);
}
if (validEdges001){
distances02=Math.min(distances02, distances11+passabilities11);
distances11=Math.min(distances02+passabilities02, distances11);
}
if (validEdges101){
distances01=Math.min(distances01, distances11+passabilities11);
distances11=Math.min(distances01+passabilities01, distances11);
}
if (validEdges201){
distances01=Math.min(distances01, distances12+passabilities12);
distances12=Math.min(distances01+passabilities01, distances12);
}
if (validEdges301){
distances01=Math.min(distances01, distances02+passabilities02);
distances02=Math.min(distances01+passabilities01, distances02);
}
if (validEdges002){
distances03=Math.min(distances03, distances12+passabilities12);
distances12=Math.min(distances03+passabilities03, distances12);
}
if (validEdges102){
distances02=Math.min(distances02, distances12+passabilities12);
distances12=Math.min(distances02+passabilities02, distances12);
}
if (validEdges202){
distances02=Math.min(distances02, distances13+passabilities13);
distances13=Math.min(distances02+passabilities02, distances13);
}
if (validEdges302){
distances02=Math.min(distances02, distances03+passabilities03);
distances03=Math.min(distances02+passabilities02, distances03);
}
if (validEdges003){
distances04=Math.min(distances04, distances13+passabilities13);
distances13=Math.min(distances04+passabilities04, distances13);
}
if (validEdges103){
distances03=Math.min(distances03, distances13+passabilities13);
distances13=Math.min(distances03+passabilities03, distances13);
}
if (validEdges203){
distances03=Math.min(distances03, distances14+passabilities14);
distances14=Math.min(distances03+passabilities03, distances14);
}
if (validEdges303){
distances03=Math.min(distances03, distances04+passabilities04);
distances04=Math.min(distances03+passabilities03, distances04);
}
if (validEdges010){
distances11=Math.min(distances11, distances20+passabilities20);
distances20=Math.min(distances11+passabilities11, distances20);
}
if (validEdges110){
distances10=Math.min(distances10, distances20+passabilities20);
distances20=Math.min(distances10+passabilities10, distances20);
}
if (validEdges210){
distances10=Math.min(distances10, distances21+passabilities21);
distances21=Math.min(distances10+passabilities10, distances21);
}
if (validEdges310){
distances10=Math.min(distances10, distances11+passabilities11);
distances11=Math.min(distances10+passabilities10, distances11);
}
if (validEdges011){
distances12=Math.min(distances12, distances21+passabilities21);
distances21=Math.min(distances12+passabilities12, distances21);
}
if (validEdges111){
distances11=Math.min(distances11, distances21+passabilities21);
distances21=Math.min(distances11+passabilities11, distances21);
}
if (validEdges211){
distances11=Math.min(distances11, distances22+passabilities22);
distances22=Math.min(distances11+passabilities11, distances22);
}
if (validEdges311){
distances11=Math.min(distances11, distances12+passabilities12);
distances12=Math.min(distances11+passabilities11, distances12);
}
if (validEdges012){
distances13=Math.min(distances13, distances22+passabilities22);
distances22=Math.min(distances13+passabilities13, distances22);
}
if (validEdges112){
distances12=Math.min(distances12, distances22+passabilities22);
distances22=Math.min(distances12+passabilities12, distances22);
}
if (validEdges212){
distances12=Math.min(distances12, distances23+passabilities23);
distances23=Math.min(distances12+passabilities12, distances23);
}
if (validEdges312){
distances12=Math.min(distances12, distances13+passabilities13);
distances13=Math.min(distances12+passabilities12, distances13);
}
if (validEdges013){
distances14=Math.min(distances14, distances23+passabilities23);
distances23=Math.min(distances14+passabilities14, distances23);
}
if (validEdges113){
distances13=Math.min(distances13, distances23+passabilities23);
distances23=Math.min(distances13+passabilities13, distances23);
}
if (validEdges213){
distances13=Math.min(distances13, distances24+passabilities24);
distances24=Math.min(distances13+passabilities13, distances24);
}
if (validEdges313){
distances13=Math.min(distances13, distances14+passabilities14);
distances14=Math.min(distances13+passabilities13, distances14);
}
if (validEdges020){
distances21=Math.min(distances21, distances30+passabilities30);
distances30=Math.min(distances21+passabilities21, distances30);
}
if (validEdges120){
distances20=Math.min(distances20, distances30+passabilities30);
distances30=Math.min(distances20+passabilities20, distances30);
}
if (validEdges220){
distances20=Math.min(distances20, distances31+passabilities31);
distances31=Math.min(distances20+passabilities20, distances31);
}
if (validEdges320){
distances20=Math.min(distances20, distances21+passabilities21);
distances21=Math.min(distances20+passabilities20, distances21);
}
if (validEdges021){
distances22=Math.min(distances22, distances31+passabilities31);
distances31=Math.min(distances22+passabilities22, distances31);
}
if (validEdges121){
distances21=Math.min(distances21, distances31+passabilities31);
distances31=Math.min(distances21+passabilities21, distances31);
}
if (validEdges221){
distances21=Math.min(distances21, distances32+passabilities32);
distances32=Math.min(distances21+passabilities21, distances32);
}
if (validEdges321){
distances21=Math.min(distances21, distances22+passabilities22);
distances22=Math.min(distances21+passabilities21, distances22);
}
if (validEdges022){
distances23=Math.min(distances23, distances32+passabilities32);
distances32=Math.min(distances23+passabilities23, distances32);
}
if (validEdges122){
distances22=Math.min(distances22, distances32+passabilities32);
distances32=Math.min(distances22+passabilities22, distances32);
}
if (validEdges222){
distances22=Math.min(distances22, distances33+passabilities33);
distances33=Math.min(distances22+passabilities22, distances33);
}
if (validEdges322){
distances22=Math.min(distances22, distances23+passabilities23);
distances23=Math.min(distances22+passabilities22, distances23);
}
if (validEdges023){
distances24=Math.min(distances24, distances33+passabilities33);
distances33=Math.min(distances24+passabilities24, distances33);
}
if (validEdges123){
distances23=Math.min(distances23, distances33+passabilities33);
distances33=Math.min(distances23+passabilities23, distances33);
}
if (validEdges223){
distances23=Math.min(distances23, distances34+passabilities34);
distances34=Math.min(distances23+passabilities23, distances34);
}
if (validEdges323){
distances23=Math.min(distances23, distances24+passabilities24);
distances24=Math.min(distances23+passabilities23, distances24);
}
if (validEdges030){
distances31=Math.min(distances31, distances40+passabilities40);
distances40=Math.min(distances31+passabilities31, distances40);
}
if (validEdges130){
distances30=Math.min(distances30, distances40+passabilities40);
distances40=Math.min(distances30+passabilities30, distances40);
}
if (validEdges230){
distances30=Math.min(distances30, distances41+passabilities41);
distances41=Math.min(distances30+passabilities30, distances41);
}
if (validEdges330){
distances30=Math.min(distances30, distances31+passabilities31);
distances31=Math.min(distances30+passabilities30, distances31);
}
if (validEdges031){
distances32=Math.min(distances32, distances41+passabilities41);
distances41=Math.min(distances32+passabilities32, distances41);
}
if (validEdges131){
distances31=Math.min(distances31, distances41+passabilities41);
distances41=Math.min(distances31+passabilities31, distances41);
}
if (validEdges231){
distances31=Math.min(distances31, distances42+passabilities42);
distances42=Math.min(distances31+passabilities31, distances42);
}
if (validEdges331){
distances31=Math.min(distances31, distances32+passabilities32);
distances32=Math.min(distances31+passabilities31, distances32);
}
if (validEdges032){
distances33=Math.min(distances33, distances42+passabilities42);
distances42=Math.min(distances33+passabilities33, distances42);
}
if (validEdges132){
distances32=Math.min(distances32, distances42+passabilities42);
distances42=Math.min(distances32+passabilities32, distances42);
}
if (validEdges232){
distances32=Math.min(distances32, distances43+passabilities43);
distances43=Math.min(distances32+passabilities32, distances43);
}
if (validEdges332){
distances32=Math.min(distances32, distances33+passabilities33);
distances33=Math.min(distances32+passabilities32, distances33);
}
if (validEdges033){
distances34=Math.min(distances34, distances43+passabilities43);
distances43=Math.min(distances34+passabilities34, distances43);
}
if (validEdges133){
distances33=Math.min(distances33, distances43+passabilities43);
distances43=Math.min(distances33+passabilities33, distances43);
}
if (validEdges233){
distances33=Math.min(distances33, distances44+passabilities44);
distances44=Math.min(distances33+passabilities33, distances44);
}
if (validEdges333){
distances33=Math.min(distances33, distances34+passabilities34);
distances34=Math.min(distances33+passabilities33, distances34);
}
if (validEdges104){
distances04=Math.min(distances04, distances14+passabilities14);
distances14=Math.min(distances04+passabilities04, distances14);
}
if (validEdges114){
distances14=Math.min(distances14, distances24+passabilities24);
distances24=Math.min(distances14+passabilities14, distances24);
}
if (validEdges124){
distances24=Math.min(distances24, distances34+passabilities34);
distances34=Math.min(distances24+passabilities24, distances34);
}
if (validEdges134){
distances34=Math.min(distances34, distances44+passabilities44);
distances44=Math.min(distances34+passabilities34, distances44);
}
if (validEdges340){
distances40=Math.min(distances40, distances41+passabilities41);
distances41=Math.min(distances40+passabilities40, distances41);
}
if (validEdges341){
distances41=Math.min(distances41, distances42+passabilities42);
distances42=Math.min(distances41+passabilities41, distances42);
}
if (validEdges342){
distances42=Math.min(distances42, distances43+passabilities43);
distances43=Math.min(distances42+passabilities42, distances43);
}
if (validEdges343){
distances43=Math.min(distances43, distances44+passabilities44);
distances44=Math.min(distances43+passabilities43, distances44);
}
//System.out.println("Bytecodes used in setup and iteration: " + (Clock.getBytecodeNum() - initialBytecodeCount));
distances11+= passabilities11;
distances12+= passabilities12;
distances13+= passabilities13;
distances21+= passabilities21;
distances22+= passabilities22;
distances23+= passabilities23;
distances31+= passabilities31;
distances32+= passabilities32;
distances33+= passabilities33;
Direction bestDir = null;
double minDistance = distances22;
Direction directPath = rc.getLocation().directionTo(destination);
System.out.println("Direct path: " + directPath);
if (rc.canMove(directPath)) {
System.out.println("Initializing variables to direct path");
bestDir = directPath;
if(directPath == Direction.NORTHWEST) minDistance = distances13;
if(directPath == Direction.WEST) minDistance = distances12;
if(directPath == Direction.SOUTHWEST) minDistance = distances11;
if(directPath == Direction.SOUTH) minDistance = distances21;
if(directPath == Direction.SOUTHEAST) minDistance = distances31;
if(directPath == Direction.EAST) minDistance = distances32;
if(directPath == Direction.NORTHEAST) minDistance = distances33;
if(directPath == Direction.NORTH) minDistance = distances23;
//System.out.println("minDistance initialized to " + minDistance);
}
//System.out.println("NORTHWEST: " + distances13);
//System.out.println("WEST: " + distances12);
//System.out.println("SOUTHWEST: " + distances11);
//System.out.println("SOUTH: " + distances21);
//System.out.println("SOUTHEAST: " + distances31);
//System.out.println("EAST: " + distances32);
//System.out.println("NORTHEAST: " + distances33);
//System.out.println("NORTH: " + distances23);
if (validEdges012 && distances13 < minDistance) {
bestDir=Direction.NORTHWEST;
minDistance=distances13;
}
if (validEdges021 && distances31 < minDistance) {
bestDir=Direction.SOUTHEAST;
minDistance=distances31;
}
if (validEdges112 && distances12 < minDistance) {
bestDir=Direction.WEST;
minDistance=distances12;
}
if (validEdges122 && distances32 < minDistance) {
bestDir=Direction.EAST;
minDistance=distances32;
}
if (validEdges211 && distances11 < minDistance) {
bestDir=Direction.SOUTHWEST;
minDistance=distances11;
}
if (validEdges222 && distances33 < minDistance) {
bestDir=Direction.NORTHEAST;
minDistance=distances33;
}
if (validEdges321 && distances21 < minDistance) {
bestDir=Direction.SOUTH;
minDistance=distances21;
}
if (validEdges322 && distances23 < minDistance) {
bestDir=Direction.NORTH;
minDistance=distances23;
}
//System.out.println("Best movement direction: " + bestDir);
//System.out.println("Minimum distance: " + minDistance);
//System.out.println("Current distance: " + distances22);
if (minDistance == distances22) return;
//System.out.println("Total bytecodes used: " + (Clock.getBytecodeNum() - initialBytecodeCount));
if (bestDir != null && rc.canMove(bestDir)) rc.move(bestDir);
}
}
