package finals;
import battlecode.common.*;
public class UnrolledNavigation extends Navigation {
public UnrolledNavigation(RobotController r) {super(r);}
MapLocation myLoc;
MapLocation loc00;
boolean validLocs00;
double passabilities00;
MapLocation loc01;
boolean validLocs01;
double passabilities01;
MapLocation loc02;
boolean validLocs02;
double passabilities02;
MapLocation loc03;
boolean validLocs03;
double passabilities03;
MapLocation loc04;
boolean validLocs04;
double passabilities04;
MapLocation loc05;
boolean validLocs05;
double passabilities05;
MapLocation loc06;
boolean validLocs06;
double passabilities06;
MapLocation loc10;
boolean validLocs10;
double passabilities10;
MapLocation loc11;
boolean validLocs11;
double passabilities11;
MapLocation loc12;
boolean validLocs12;
double passabilities12;
MapLocation loc13;
boolean validLocs13;
double passabilities13;
MapLocation loc14;
boolean validLocs14;
double passabilities14;
MapLocation loc15;
boolean validLocs15;
double passabilities15;
MapLocation loc16;
boolean validLocs16;
double passabilities16;
MapLocation loc20;
boolean validLocs20;
double passabilities20;
MapLocation loc21;
boolean validLocs21;
double passabilities21;
MapLocation loc22;
boolean validLocs22;
double passabilities22;
MapLocation loc23;
boolean validLocs23;
double passabilities23;
MapLocation loc24;
boolean validLocs24;
double passabilities24;
MapLocation loc25;
boolean validLocs25;
double passabilities25;
MapLocation loc26;
boolean validLocs26;
double passabilities26;
MapLocation loc30;
boolean validLocs30;
double passabilities30;
MapLocation loc31;
boolean validLocs31;
double passabilities31;
MapLocation loc32;
boolean validLocs32;
double passabilities32;
MapLocation loc33;
boolean validLocs33;
double passabilities33;
MapLocation loc34;
boolean validLocs34;
double passabilities34;
MapLocation loc35;
boolean validLocs35;
double passabilities35;
MapLocation loc36;
boolean validLocs36;
double passabilities36;
MapLocation loc40;
boolean validLocs40;
double passabilities40;
MapLocation loc41;
boolean validLocs41;
double passabilities41;
MapLocation loc42;
boolean validLocs42;
double passabilities42;
MapLocation loc43;
boolean validLocs43;
double passabilities43;
MapLocation loc44;
boolean validLocs44;
double passabilities44;
MapLocation loc45;
boolean validLocs45;
double passabilities45;
MapLocation loc46;
boolean validLocs46;
double passabilities46;
MapLocation loc50;
boolean validLocs50;
double passabilities50;
MapLocation loc51;
boolean validLocs51;
double passabilities51;
MapLocation loc52;
boolean validLocs52;
double passabilities52;
MapLocation loc53;
boolean validLocs53;
double passabilities53;
MapLocation loc54;
boolean validLocs54;
double passabilities54;
MapLocation loc55;
boolean validLocs55;
double passabilities55;
MapLocation loc56;
boolean validLocs56;
double passabilities56;
MapLocation loc60;
boolean validLocs60;
double passabilities60;
MapLocation loc61;
boolean validLocs61;
double passabilities61;
MapLocation loc62;
boolean validLocs62;
double passabilities62;
MapLocation loc63;
boolean validLocs63;
double passabilities63;
MapLocation loc64;
boolean validLocs64;
double passabilities64;
MapLocation loc65;
boolean validLocs65;
double passabilities65;
MapLocation loc66;
boolean validLocs66;
double passabilities66;
boolean validEdges000;
boolean validEdges100;
boolean validEdges200;
boolean validEdges300;
boolean validEdges001;
boolean validEdges101;
boolean validEdges201;
boolean validEdges301;
boolean validEdges002;
boolean validEdges102;
boolean validEdges202;
boolean validEdges302;
boolean validEdges003;
boolean validEdges103;
boolean validEdges203;
boolean validEdges303;
boolean validEdges004;
boolean validEdges104;
boolean validEdges204;
boolean validEdges304;
boolean validEdges005;
boolean validEdges105;
boolean validEdges205;
boolean validEdges305;
boolean validEdges010;
boolean validEdges110;
boolean validEdges210;
boolean validEdges310;
boolean validEdges011;
boolean validEdges111;
boolean validEdges211;
boolean validEdges311;
boolean validEdges012;
boolean validEdges112;
boolean validEdges212;
boolean validEdges312;
boolean validEdges013;
boolean validEdges113;
boolean validEdges213;
boolean validEdges313;
boolean validEdges014;
boolean validEdges114;
boolean validEdges214;
boolean validEdges314;
boolean validEdges015;
boolean validEdges115;
boolean validEdges215;
boolean validEdges315;
boolean validEdges020;
boolean validEdges120;
boolean validEdges220;
boolean validEdges320;
boolean validEdges021;
boolean validEdges121;
boolean validEdges221;
boolean validEdges321;
boolean validEdges022;
boolean validEdges122;
boolean validEdges222;
boolean validEdges322;
boolean validEdges023;
boolean validEdges123;
boolean validEdges223;
boolean validEdges323;
boolean validEdges024;
boolean validEdges124;
boolean validEdges224;
boolean validEdges324;
boolean validEdges025;
boolean validEdges125;
boolean validEdges225;
boolean validEdges325;
boolean validEdges030;
boolean validEdges130;
boolean validEdges230;
boolean validEdges330;
boolean validEdges031;
boolean validEdges131;
boolean validEdges231;
boolean validEdges331;
boolean validEdges032;
boolean validEdges132;
boolean validEdges232;
boolean validEdges332;
boolean validEdges033;
boolean validEdges133;
boolean validEdges233;
boolean validEdges333;
boolean validEdges034;
boolean validEdges134;
boolean validEdges234;
boolean validEdges334;
boolean validEdges035;
boolean validEdges135;
boolean validEdges235;
boolean validEdges335;
boolean validEdges040;
boolean validEdges140;
boolean validEdges240;
boolean validEdges340;
boolean validEdges041;
boolean validEdges141;
boolean validEdges241;
boolean validEdges341;
boolean validEdges042;
boolean validEdges142;
boolean validEdges242;
boolean validEdges342;
boolean validEdges043;
boolean validEdges143;
boolean validEdges243;
boolean validEdges343;
boolean validEdges044;
boolean validEdges144;
boolean validEdges244;
boolean validEdges344;
boolean validEdges045;
boolean validEdges145;
boolean validEdges245;
boolean validEdges345;
boolean validEdges050;
boolean validEdges150;
boolean validEdges250;
boolean validEdges350;
boolean validEdges051;
boolean validEdges151;
boolean validEdges251;
boolean validEdges351;
boolean validEdges052;
boolean validEdges152;
boolean validEdges252;
boolean validEdges352;
boolean validEdges053;
boolean validEdges153;
boolean validEdges253;
boolean validEdges353;
boolean validEdges054;
boolean validEdges154;
boolean validEdges254;
boolean validEdges354;
boolean validEdges055;
boolean validEdges155;
boolean validEdges255;
boolean validEdges355;
boolean validEdges106;
boolean validEdges116;
boolean validEdges126;
boolean validEdges136;
boolean validEdges146;
boolean validEdges156;
boolean validEdges360;
boolean validEdges361;
boolean validEdges362;
boolean validEdges363;
boolean validEdges364;
boolean validEdges365;
double distances00;
double distances01;
double distances02;
double distances03;
double distances04;
double distances05;
double distances06;
double distances10;
double distances11;
double distances12;
double distances13;
double distances14;
double distances15;
double distances16;
double distances20;
double distances21;
double distances22;
double distances23;
double distances24;
double distances25;
double distances26;
double distances30;
double distances31;
double distances32;
double distances33;
double distances34;
double distances35;
double distances36;
double distances40;
double distances41;
double distances42;
double distances43;
double distances44;
double distances45;
double distances46;
double distances50;
double distances51;
double distances52;
double distances53;
double distances54;
double distances55;
double distances56;
double distances60;
double distances61;
double distances62;
double distances63;
double distances64;
double distances65;
double distances66;
public void navTo (MapLocation destination) throws GameActionException {
MapLocation myLoc = rc.getLocation();
loc00 = new MapLocation(myLoc.x + -3, myLoc.y + -3);
validLocs00 = rc.onTheMap(loc00);
passabilities00= Double.MAX_VALUE; if (validLocs00) passabilities00 = 1.0/rc.sensePassability(loc00);
loc01 = new MapLocation(myLoc.x + -3, myLoc.y + -2);
validLocs01 = rc.onTheMap(loc01);
passabilities01= Double.MAX_VALUE; if (validLocs01) passabilities01 = 1.0/rc.sensePassability(loc01);
loc02 = new MapLocation(myLoc.x + -3, myLoc.y + -1);
validLocs02 = rc.onTheMap(loc02);
passabilities02= Double.MAX_VALUE; if (validLocs02) passabilities02 = 1.0/rc.sensePassability(loc02);
loc03 = new MapLocation(myLoc.x + -3, myLoc.y + 0);
validLocs03 = rc.onTheMap(loc03);
passabilities03= Double.MAX_VALUE; if (validLocs03) passabilities03 = 1.0/rc.sensePassability(loc03);
loc04 = new MapLocation(myLoc.x + -3, myLoc.y + 1);
validLocs04 = rc.onTheMap(loc04);
passabilities04= Double.MAX_VALUE; if (validLocs04) passabilities04 = 1.0/rc.sensePassability(loc04);
loc05 = new MapLocation(myLoc.x + -3, myLoc.y + 2);
validLocs05 = rc.onTheMap(loc05);
passabilities05= Double.MAX_VALUE; if (validLocs05) passabilities05 = 1.0/rc.sensePassability(loc05);
loc06 = new MapLocation(myLoc.x + -3, myLoc.y + 3);
validLocs06 = rc.onTheMap(loc06);
passabilities06= Double.MAX_VALUE; if (validLocs06) passabilities06 = 1.0/rc.sensePassability(loc06);
loc10 = new MapLocation(myLoc.x + -2, myLoc.y + -3);
validLocs10 = rc.onTheMap(loc10);
passabilities10= Double.MAX_VALUE; if (validLocs10) passabilities10 = 1.0/rc.sensePassability(loc10);
loc11 = new MapLocation(myLoc.x + -2, myLoc.y + -2);
validLocs11 = rc.onTheMap(loc11);
passabilities11= Double.MAX_VALUE; if (validLocs11) passabilities11 = 1.0/rc.sensePassability(loc11);
loc12 = new MapLocation(myLoc.x + -2, myLoc.y + -1);
validLocs12 = rc.onTheMap(loc12);
passabilities12= Double.MAX_VALUE; if (validLocs12) passabilities12 = 1.0/rc.sensePassability(loc12);
loc13 = new MapLocation(myLoc.x + -2, myLoc.y + 0);
validLocs13 = rc.onTheMap(loc13);
passabilities13= Double.MAX_VALUE; if (validLocs13) passabilities13 = 1.0/rc.sensePassability(loc13);
loc14 = new MapLocation(myLoc.x + -2, myLoc.y + 1);
validLocs14 = rc.onTheMap(loc14);
passabilities14= Double.MAX_VALUE; if (validLocs14) passabilities14 = 1.0/rc.sensePassability(loc14);
loc15 = new MapLocation(myLoc.x + -2, myLoc.y + 2);
validLocs15 = rc.onTheMap(loc15);
passabilities15= Double.MAX_VALUE; if (validLocs15) passabilities15 = 1.0/rc.sensePassability(loc15);
loc16 = new MapLocation(myLoc.x + -2, myLoc.y + 3);
validLocs16 = rc.onTheMap(loc16);
passabilities16= Double.MAX_VALUE; if (validLocs16) passabilities16 = 1.0/rc.sensePassability(loc16);
loc20 = new MapLocation(myLoc.x + -1, myLoc.y + -3);
validLocs20 = rc.onTheMap(loc20);
passabilities20= Double.MAX_VALUE; if (validLocs20) passabilities20 = 1.0/rc.sensePassability(loc20);
loc21 = new MapLocation(myLoc.x + -1, myLoc.y + -2);
validLocs21 = rc.onTheMap(loc21);
passabilities21= Double.MAX_VALUE; if (validLocs21) passabilities21 = 1.0/rc.sensePassability(loc21);
loc22 = new MapLocation(myLoc.x + -1, myLoc.y + -1);
validLocs22 = rc.onTheMap(loc22)&& !rc.isLocationOccupied(loc22);
passabilities22= Double.MAX_VALUE; if (validLocs22) passabilities22 = 1.0/rc.sensePassability(loc22);
loc23 = new MapLocation(myLoc.x + -1, myLoc.y + 0);
validLocs23 = rc.onTheMap(loc23)&& !rc.isLocationOccupied(loc23);
passabilities23= Double.MAX_VALUE; if (validLocs23) passabilities23 = 1.0/rc.sensePassability(loc23);
loc24 = new MapLocation(myLoc.x + -1, myLoc.y + 1);
validLocs24 = rc.onTheMap(loc24)&& !rc.isLocationOccupied(loc24);
passabilities24= Double.MAX_VALUE; if (validLocs24) passabilities24 = 1.0/rc.sensePassability(loc24);
loc25 = new MapLocation(myLoc.x + -1, myLoc.y + 2);
validLocs25 = rc.onTheMap(loc25);
passabilities25= Double.MAX_VALUE; if (validLocs25) passabilities25 = 1.0/rc.sensePassability(loc25);
loc26 = new MapLocation(myLoc.x + -1, myLoc.y + 3);
validLocs26 = rc.onTheMap(loc26);
passabilities26= Double.MAX_VALUE; if (validLocs26) passabilities26 = 1.0/rc.sensePassability(loc26);
loc30 = new MapLocation(myLoc.x + 0, myLoc.y + -3);
validLocs30 = rc.onTheMap(loc30);
passabilities30= Double.MAX_VALUE; if (validLocs30) passabilities30 = 1.0/rc.sensePassability(loc30);
loc31 = new MapLocation(myLoc.x + 0, myLoc.y + -2);
validLocs31 = rc.onTheMap(loc31);
passabilities31= Double.MAX_VALUE; if (validLocs31) passabilities31 = 1.0/rc.sensePassability(loc31);
loc32 = new MapLocation(myLoc.x + 0, myLoc.y + -1);
validLocs32 = rc.onTheMap(loc32)&& !rc.isLocationOccupied(loc32);
passabilities32= Double.MAX_VALUE; if (validLocs32) passabilities32 = 1.0/rc.sensePassability(loc32);
loc33 = new MapLocation(myLoc.x + 0, myLoc.y + 0);
validLocs33 = rc.onTheMap(loc33);
passabilities33= Double.MAX_VALUE; if (validLocs33) passabilities33 = 1.0/rc.sensePassability(loc33);
loc34 = new MapLocation(myLoc.x + 0, myLoc.y + 1);
validLocs34 = rc.onTheMap(loc34)&& !rc.isLocationOccupied(loc34);
passabilities34= Double.MAX_VALUE; if (validLocs34) passabilities34 = 1.0/rc.sensePassability(loc34);
loc35 = new MapLocation(myLoc.x + 0, myLoc.y + 2);
validLocs35 = rc.onTheMap(loc35);
passabilities35= Double.MAX_VALUE; if (validLocs35) passabilities35 = 1.0/rc.sensePassability(loc35);
loc36 = new MapLocation(myLoc.x + 0, myLoc.y + 3);
validLocs36 = rc.onTheMap(loc36);
passabilities36= Double.MAX_VALUE; if (validLocs36) passabilities36 = 1.0/rc.sensePassability(loc36);
loc40 = new MapLocation(myLoc.x + 1, myLoc.y + -3);
validLocs40 = rc.onTheMap(loc40);
passabilities40= Double.MAX_VALUE; if (validLocs40) passabilities40 = 1.0/rc.sensePassability(loc40);
loc41 = new MapLocation(myLoc.x + 1, myLoc.y + -2);
validLocs41 = rc.onTheMap(loc41);
passabilities41= Double.MAX_VALUE; if (validLocs41) passabilities41 = 1.0/rc.sensePassability(loc41);
loc42 = new MapLocation(myLoc.x + 1, myLoc.y + -1);
validLocs42 = rc.onTheMap(loc42)&& !rc.isLocationOccupied(loc42);
passabilities42= Double.MAX_VALUE; if (validLocs42) passabilities42 = 1.0/rc.sensePassability(loc42);
loc43 = new MapLocation(myLoc.x + 1, myLoc.y + 0);
validLocs43 = rc.onTheMap(loc43)&& !rc.isLocationOccupied(loc43);
passabilities43= Double.MAX_VALUE; if (validLocs43) passabilities43 = 1.0/rc.sensePassability(loc43);
loc44 = new MapLocation(myLoc.x + 1, myLoc.y + 1);
validLocs44 = rc.onTheMap(loc44)&& !rc.isLocationOccupied(loc44);
passabilities44= Double.MAX_VALUE; if (validLocs44) passabilities44 = 1.0/rc.sensePassability(loc44);
loc45 = new MapLocation(myLoc.x + 1, myLoc.y + 2);
validLocs45 = rc.onTheMap(loc45);
passabilities45= Double.MAX_VALUE; if (validLocs45) passabilities45 = 1.0/rc.sensePassability(loc45);
loc46 = new MapLocation(myLoc.x + 1, myLoc.y + 3);
validLocs46 = rc.onTheMap(loc46);
passabilities46= Double.MAX_VALUE; if (validLocs46) passabilities46 = 1.0/rc.sensePassability(loc46);
loc50 = new MapLocation(myLoc.x + 2, myLoc.y + -3);
validLocs50 = rc.onTheMap(loc50);
passabilities50= Double.MAX_VALUE; if (validLocs50) passabilities50 = 1.0/rc.sensePassability(loc50);
loc51 = new MapLocation(myLoc.x + 2, myLoc.y + -2);
validLocs51 = rc.onTheMap(loc51);
passabilities51= Double.MAX_VALUE; if (validLocs51) passabilities51 = 1.0/rc.sensePassability(loc51);
loc52 = new MapLocation(myLoc.x + 2, myLoc.y + -1);
validLocs52 = rc.onTheMap(loc52);
passabilities52= Double.MAX_VALUE; if (validLocs52) passabilities52 = 1.0/rc.sensePassability(loc52);
loc53 = new MapLocation(myLoc.x + 2, myLoc.y + 0);
validLocs53 = rc.onTheMap(loc53);
passabilities53= Double.MAX_VALUE; if (validLocs53) passabilities53 = 1.0/rc.sensePassability(loc53);
loc54 = new MapLocation(myLoc.x + 2, myLoc.y + 1);
validLocs54 = rc.onTheMap(loc54);
passabilities54= Double.MAX_VALUE; if (validLocs54) passabilities54 = 1.0/rc.sensePassability(loc54);
loc55 = new MapLocation(myLoc.x + 2, myLoc.y + 2);
validLocs55 = rc.onTheMap(loc55);
passabilities55= Double.MAX_VALUE; if (validLocs55) passabilities55 = 1.0/rc.sensePassability(loc55);
loc56 = new MapLocation(myLoc.x + 2, myLoc.y + 3);
validLocs56 = rc.onTheMap(loc56);
passabilities56= Double.MAX_VALUE; if (validLocs56) passabilities56 = 1.0/rc.sensePassability(loc56);
loc60 = new MapLocation(myLoc.x + 3, myLoc.y + -3);
validLocs60 = rc.onTheMap(loc60);
passabilities60= Double.MAX_VALUE; if (validLocs60) passabilities60 = 1.0/rc.sensePassability(loc60);
loc61 = new MapLocation(myLoc.x + 3, myLoc.y + -2);
validLocs61 = rc.onTheMap(loc61);
passabilities61= Double.MAX_VALUE; if (validLocs61) passabilities61 = 1.0/rc.sensePassability(loc61);
loc62 = new MapLocation(myLoc.x + 3, myLoc.y + -1);
validLocs62 = rc.onTheMap(loc62);
passabilities62= Double.MAX_VALUE; if (validLocs62) passabilities62 = 1.0/rc.sensePassability(loc62);
loc63 = new MapLocation(myLoc.x + 3, myLoc.y + 0);
validLocs63 = rc.onTheMap(loc63);
passabilities63= Double.MAX_VALUE; if (validLocs63) passabilities63 = 1.0/rc.sensePassability(loc63);
loc64 = new MapLocation(myLoc.x + 3, myLoc.y + 1);
validLocs64 = rc.onTheMap(loc64);
passabilities64= Double.MAX_VALUE; if (validLocs64) passabilities64 = 1.0/rc.sensePassability(loc64);
loc65 = new MapLocation(myLoc.x + 3, myLoc.y + 2);
validLocs65 = rc.onTheMap(loc65);
passabilities65= Double.MAX_VALUE; if (validLocs65) passabilities65 = 1.0/rc.sensePassability(loc65);
loc66 = new MapLocation(myLoc.x + 3, myLoc.y + 3);
validLocs66 = rc.onTheMap(loc66);
passabilities66= Double.MAX_VALUE; if (validLocs66) passabilities66 = 1.0/rc.sensePassability(loc66);
validEdges000 = (validLocs01 && validLocs10);
validEdges100 = (validLocs00 && validLocs10);
validEdges200 = (validLocs00 && validLocs11);
validEdges300 = (validLocs00 && validLocs01);
validEdges001 = (validLocs02 && validLocs11);
validEdges101 = (validLocs01 && validLocs11);
validEdges201 = (validLocs01 && validLocs12);
validEdges301 = (validLocs01 && validLocs02);
validEdges002 = (validLocs03 && validLocs12);
validEdges102 = (validLocs02 && validLocs12);
validEdges202 = (validLocs02 && validLocs13);
validEdges302 = (validLocs02 && validLocs03);
validEdges003 = (validLocs04 && validLocs13);
validEdges103 = (validLocs03 && validLocs13);
validEdges203 = (validLocs03 && validLocs14);
validEdges303 = (validLocs03 && validLocs04);
validEdges004 = (validLocs05 && validLocs14);
validEdges104 = (validLocs04 && validLocs14);
validEdges204 = (validLocs04 && validLocs15);
validEdges304 = (validLocs04 && validLocs05);
validEdges005 = (validLocs06 && validLocs15);
validEdges105 = (validLocs05 && validLocs15);
validEdges205 = (validLocs05 && validLocs16);
validEdges305 = (validLocs05 && validLocs06);
validEdges010 = (validLocs11 && validLocs20);
validEdges110 = (validLocs10 && validLocs20);
validEdges210 = (validLocs10 && validLocs21);
validEdges310 = (validLocs10 && validLocs11);
validEdges011 = (validLocs12 && validLocs21);
validEdges111 = (validLocs11 && validLocs21);
validEdges211 = (validLocs11 && validLocs22);
validEdges311 = (validLocs11 && validLocs12);
validEdges012 = (validLocs13 && validLocs22);
validEdges112 = (validLocs12 && validLocs22);
validEdges212 = (validLocs12 && validLocs23);
validEdges312 = (validLocs12 && validLocs13);
validEdges013 = (validLocs14 && validLocs23);
validEdges113 = (validLocs13 && validLocs23);
validEdges213 = (validLocs13 && validLocs24);
validEdges313 = (validLocs13 && validLocs14);
validEdges014 = (validLocs15 && validLocs24);
validEdges114 = (validLocs14 && validLocs24);
validEdges214 = (validLocs14 && validLocs25);
validEdges314 = (validLocs14 && validLocs15);
validEdges015 = (validLocs16 && validLocs25);
validEdges115 = (validLocs15 && validLocs25);
validEdges215 = (validLocs15 && validLocs26);
validEdges315 = (validLocs15 && validLocs16);
validEdges020 = (validLocs21 && validLocs30);
validEdges120 = (validLocs20 && validLocs30);
validEdges220 = (validLocs20 && validLocs31);
validEdges320 = (validLocs20 && validLocs21);
validEdges021 = (validLocs22 && validLocs31);
validEdges121 = (validLocs21 && validLocs31);
validEdges221 = (validLocs21 && validLocs32);
validEdges321 = (validLocs21 && validLocs22);
validEdges022 = (validLocs23 && validLocs32);
validEdges122 = (validLocs22 && validLocs32);
validEdges222 = (validLocs22 && validLocs33);
validEdges322 = (validLocs22 && validLocs23);
validEdges023 = (validLocs24 && validLocs33);
validEdges123 = (validLocs23 && validLocs33);
validEdges223 = (validLocs23 && validLocs34);
validEdges323 = (validLocs23 && validLocs24);
validEdges024 = (validLocs25 && validLocs34);
validEdges124 = (validLocs24 && validLocs34);
validEdges224 = (validLocs24 && validLocs35);
validEdges324 = (validLocs24 && validLocs25);
validEdges025 = (validLocs26 && validLocs35);
validEdges125 = (validLocs25 && validLocs35);
validEdges225 = (validLocs25 && validLocs36);
validEdges325 = (validLocs25 && validLocs26);
validEdges030 = (validLocs31 && validLocs40);
validEdges130 = (validLocs30 && validLocs40);
validEdges230 = (validLocs30 && validLocs41);
validEdges330 = (validLocs30 && validLocs31);
validEdges031 = (validLocs32 && validLocs41);
validEdges131 = (validLocs31 && validLocs41);
validEdges231 = (validLocs31 && validLocs42);
validEdges331 = (validLocs31 && validLocs32);
validEdges032 = (validLocs33 && validLocs42);
validEdges132 = (validLocs32 && validLocs42);
validEdges232 = (validLocs32 && validLocs43);
validEdges332 = (validLocs32 && validLocs33);
validEdges033 = (validLocs34 && validLocs43);
validEdges133 = (validLocs33 && validLocs43);
validEdges233 = (validLocs33 && validLocs44);
validEdges333 = (validLocs33 && validLocs34);
validEdges034 = (validLocs35 && validLocs44);
validEdges134 = (validLocs34 && validLocs44);
validEdges234 = (validLocs34 && validLocs45);
validEdges334 = (validLocs34 && validLocs35);
validEdges035 = (validLocs36 && validLocs45);
validEdges135 = (validLocs35 && validLocs45);
validEdges235 = (validLocs35 && validLocs46);
validEdges335 = (validLocs35 && validLocs36);
validEdges040 = (validLocs41 && validLocs50);
validEdges140 = (validLocs40 && validLocs50);
validEdges240 = (validLocs40 && validLocs51);
validEdges340 = (validLocs40 && validLocs41);
validEdges041 = (validLocs42 && validLocs51);
validEdges141 = (validLocs41 && validLocs51);
validEdges241 = (validLocs41 && validLocs52);
validEdges341 = (validLocs41 && validLocs42);
validEdges042 = (validLocs43 && validLocs52);
validEdges142 = (validLocs42 && validLocs52);
validEdges242 = (validLocs42 && validLocs53);
validEdges342 = (validLocs42 && validLocs43);
validEdges043 = (validLocs44 && validLocs53);
validEdges143 = (validLocs43 && validLocs53);
validEdges243 = (validLocs43 && validLocs54);
validEdges343 = (validLocs43 && validLocs44);
validEdges044 = (validLocs45 && validLocs54);
validEdges144 = (validLocs44 && validLocs54);
validEdges244 = (validLocs44 && validLocs55);
validEdges344 = (validLocs44 && validLocs45);
validEdges045 = (validLocs46 && validLocs55);
validEdges145 = (validLocs45 && validLocs55);
validEdges245 = (validLocs45 && validLocs56);
validEdges345 = (validLocs45 && validLocs46);
validEdges050 = (validLocs51 && validLocs60);
validEdges150 = (validLocs50 && validLocs60);
validEdges250 = (validLocs50 && validLocs61);
validEdges350 = (validLocs50 && validLocs51);
validEdges051 = (validLocs52 && validLocs61);
validEdges151 = (validLocs51 && validLocs61);
validEdges251 = (validLocs51 && validLocs62);
validEdges351 = (validLocs51 && validLocs52);
validEdges052 = (validLocs53 && validLocs62);
validEdges152 = (validLocs52 && validLocs62);
validEdges252 = (validLocs52 && validLocs63);
validEdges352 = (validLocs52 && validLocs53);
validEdges053 = (validLocs54 && validLocs63);
validEdges153 = (validLocs53 && validLocs63);
validEdges253 = (validLocs53 && validLocs64);
validEdges353 = (validLocs53 && validLocs54);
validEdges054 = (validLocs55 && validLocs64);
validEdges154 = (validLocs54 && validLocs64);
validEdges254 = (validLocs54 && validLocs65);
validEdges354 = (validLocs54 && validLocs55);
validEdges055 = (validLocs56 && validLocs65);
validEdges155 = (validLocs55 && validLocs65);
validEdges255 = (validLocs55 && validLocs66);
validEdges355 = (validLocs55 && validLocs56);
validEdges106 = (validLocs06 && validLocs16);
validEdges116 = (validLocs16 && validLocs26);
validEdges126 = (validLocs26 && validLocs36);
validEdges136 = (validLocs36 && validLocs46);
validEdges146 = (validLocs46 && validLocs56);
validEdges156 = (validLocs56 && validLocs66);
validEdges360 = (validLocs60 && validLocs61);
validEdges361 = (validLocs61 && validLocs62);
validEdges362 = (validLocs62 && validLocs63);
validEdges363 = (validLocs63 && validLocs64);
validEdges364 = (validLocs64 && validLocs65);
validEdges365 = (validLocs65 && validLocs66);
int shiftedX = myLoc.x - destination.x - 3;
int shiftedY = myLoc.y - destination.y - 3;
distances00 = Math.max(Math.abs(shiftedX + 0), Math.abs(shiftedY + 0)) * 100 + (Math.sqrt(Math.pow(shiftedX + 0, 2) + Math.pow(shiftedY + 0, 2)) * 0.01);
distances01 = Math.max(Math.abs(shiftedX + 0), Math.abs(shiftedY + 1)) * 100 + (Math.sqrt(Math.pow(shiftedX + 0, 2) + Math.pow(shiftedY + 1, 2)) * 0.01);
distances02 = Math.max(Math.abs(shiftedX + 0), Math.abs(shiftedY + 2)) * 100 + (Math.sqrt(Math.pow(shiftedX + 0, 2) + Math.pow(shiftedY + 2, 2)) * 0.01);
distances03 = Math.max(Math.abs(shiftedX + 0), Math.abs(shiftedY + 3)) * 100 + (Math.sqrt(Math.pow(shiftedX + 0, 2) + Math.pow(shiftedY + 3, 2)) * 0.01);
distances04 = Math.max(Math.abs(shiftedX + 0), Math.abs(shiftedY + 4)) * 100 + (Math.sqrt(Math.pow(shiftedX + 0, 2) + Math.pow(shiftedY + 4, 2)) * 0.01);
distances05 = Math.max(Math.abs(shiftedX + 0), Math.abs(shiftedY + 5)) * 100 + (Math.sqrt(Math.pow(shiftedX + 0, 2) + Math.pow(shiftedY + 5, 2)) * 0.01);
distances06 = Math.max(Math.abs(shiftedX + 0), Math.abs(shiftedY + 6)) * 100 + (Math.sqrt(Math.pow(shiftedX + 0, 2) + Math.pow(shiftedY + 6, 2)) * 0.01);
distances10 = Math.max(Math.abs(shiftedX + 1), Math.abs(shiftedY + 0)) * 100 + (Math.sqrt(Math.pow(shiftedX + 1, 2) + Math.pow(shiftedY + 0, 2)) * 0.01);
distances11 = Math.max(Math.abs(shiftedX + 1), Math.abs(shiftedY + 1)) * 100 + (Math.sqrt(Math.pow(shiftedX + 1, 2) + Math.pow(shiftedY + 1, 2)) * 0.01);
distances12 = Math.max(Math.abs(shiftedX + 1), Math.abs(shiftedY + 2)) * 100 + (Math.sqrt(Math.pow(shiftedX + 1, 2) + Math.pow(shiftedY + 2, 2)) * 0.01);
distances13 = Math.max(Math.abs(shiftedX + 1), Math.abs(shiftedY + 3)) * 100 + (Math.sqrt(Math.pow(shiftedX + 1, 2) + Math.pow(shiftedY + 3, 2)) * 0.01);
distances14 = Math.max(Math.abs(shiftedX + 1), Math.abs(shiftedY + 4)) * 100 + (Math.sqrt(Math.pow(shiftedX + 1, 2) + Math.pow(shiftedY + 4, 2)) * 0.01);
distances15 = Math.max(Math.abs(shiftedX + 1), Math.abs(shiftedY + 5)) * 100 + (Math.sqrt(Math.pow(shiftedX + 1, 2) + Math.pow(shiftedY + 5, 2)) * 0.01);
distances16 = Math.max(Math.abs(shiftedX + 1), Math.abs(shiftedY + 6)) * 100 + (Math.sqrt(Math.pow(shiftedX + 1, 2) + Math.pow(shiftedY + 6, 2)) * 0.01);
distances20 = Math.max(Math.abs(shiftedX + 2), Math.abs(shiftedY + 0)) * 100 + (Math.sqrt(Math.pow(shiftedX + 2, 2) + Math.pow(shiftedY + 0, 2)) * 0.01);
distances21 = Math.max(Math.abs(shiftedX + 2), Math.abs(shiftedY + 1)) * 100 + (Math.sqrt(Math.pow(shiftedX + 2, 2) + Math.pow(shiftedY + 1, 2)) * 0.01);
distances22 = Math.max(Math.abs(shiftedX + 2), Math.abs(shiftedY + 2)) * 100 + (Math.sqrt(Math.pow(shiftedX + 2, 2) + Math.pow(shiftedY + 2, 2)) * 0.01);
distances23 = Math.max(Math.abs(shiftedX + 2), Math.abs(shiftedY + 3)) * 100 + (Math.sqrt(Math.pow(shiftedX + 2, 2) + Math.pow(shiftedY + 3, 2)) * 0.01);
distances24 = Math.max(Math.abs(shiftedX + 2), Math.abs(shiftedY + 4)) * 100 + (Math.sqrt(Math.pow(shiftedX + 2, 2) + Math.pow(shiftedY + 4, 2)) * 0.01);
distances25 = Math.max(Math.abs(shiftedX + 2), Math.abs(shiftedY + 5)) * 100 + (Math.sqrt(Math.pow(shiftedX + 2, 2) + Math.pow(shiftedY + 5, 2)) * 0.01);
distances26 = Math.max(Math.abs(shiftedX + 2), Math.abs(shiftedY + 6)) * 100 + (Math.sqrt(Math.pow(shiftedX + 2, 2) + Math.pow(shiftedY + 6, 2)) * 0.01);
distances30 = Math.max(Math.abs(shiftedX + 3), Math.abs(shiftedY + 0)) * 100 + (Math.sqrt(Math.pow(shiftedX + 3, 2) + Math.pow(shiftedY + 0, 2)) * 0.01);
distances31 = Math.max(Math.abs(shiftedX + 3), Math.abs(shiftedY + 1)) * 100 + (Math.sqrt(Math.pow(shiftedX + 3, 2) + Math.pow(shiftedY + 1, 2)) * 0.01);
distances32 = Math.max(Math.abs(shiftedX + 3), Math.abs(shiftedY + 2)) * 100 + (Math.sqrt(Math.pow(shiftedX + 3, 2) + Math.pow(shiftedY + 2, 2)) * 0.01);
distances33 = Math.max(Math.abs(shiftedX + 3), Math.abs(shiftedY + 3)) * 100 + (Math.sqrt(Math.pow(shiftedX + 3, 2) + Math.pow(shiftedY + 3, 2)) * 0.01);
distances34 = Math.max(Math.abs(shiftedX + 3), Math.abs(shiftedY + 4)) * 100 + (Math.sqrt(Math.pow(shiftedX + 3, 2) + Math.pow(shiftedY + 4, 2)) * 0.01);
distances35 = Math.max(Math.abs(shiftedX + 3), Math.abs(shiftedY + 5)) * 100 + (Math.sqrt(Math.pow(shiftedX + 3, 2) + Math.pow(shiftedY + 5, 2)) * 0.01);
distances36 = Math.max(Math.abs(shiftedX + 3), Math.abs(shiftedY + 6)) * 100 + (Math.sqrt(Math.pow(shiftedX + 3, 2) + Math.pow(shiftedY + 6, 2)) * 0.01);
distances40 = Math.max(Math.abs(shiftedX + 4), Math.abs(shiftedY + 0)) * 100 + (Math.sqrt(Math.pow(shiftedX + 4, 2) + Math.pow(shiftedY + 0, 2)) * 0.01);
distances41 = Math.max(Math.abs(shiftedX + 4), Math.abs(shiftedY + 1)) * 100 + (Math.sqrt(Math.pow(shiftedX + 4, 2) + Math.pow(shiftedY + 1, 2)) * 0.01);
distances42 = Math.max(Math.abs(shiftedX + 4), Math.abs(shiftedY + 2)) * 100 + (Math.sqrt(Math.pow(shiftedX + 4, 2) + Math.pow(shiftedY + 2, 2)) * 0.01);
distances43 = Math.max(Math.abs(shiftedX + 4), Math.abs(shiftedY + 3)) * 100 + (Math.sqrt(Math.pow(shiftedX + 4, 2) + Math.pow(shiftedY + 3, 2)) * 0.01);
distances44 = Math.max(Math.abs(shiftedX + 4), Math.abs(shiftedY + 4)) * 100 + (Math.sqrt(Math.pow(shiftedX + 4, 2) + Math.pow(shiftedY + 4, 2)) * 0.01);
distances45 = Math.max(Math.abs(shiftedX + 4), Math.abs(shiftedY + 5)) * 100 + (Math.sqrt(Math.pow(shiftedX + 4, 2) + Math.pow(shiftedY + 5, 2)) * 0.01);
distances46 = Math.max(Math.abs(shiftedX + 4), Math.abs(shiftedY + 6)) * 100 + (Math.sqrt(Math.pow(shiftedX + 4, 2) + Math.pow(shiftedY + 6, 2)) * 0.01);
distances50 = Math.max(Math.abs(shiftedX + 5), Math.abs(shiftedY + 0)) * 100 + (Math.sqrt(Math.pow(shiftedX + 5, 2) + Math.pow(shiftedY + 0, 2)) * 0.01);
distances51 = Math.max(Math.abs(shiftedX + 5), Math.abs(shiftedY + 1)) * 100 + (Math.sqrt(Math.pow(shiftedX + 5, 2) + Math.pow(shiftedY + 1, 2)) * 0.01);
distances52 = Math.max(Math.abs(shiftedX + 5), Math.abs(shiftedY + 2)) * 100 + (Math.sqrt(Math.pow(shiftedX + 5, 2) + Math.pow(shiftedY + 2, 2)) * 0.01);
distances53 = Math.max(Math.abs(shiftedX + 5), Math.abs(shiftedY + 3)) * 100 + (Math.sqrt(Math.pow(shiftedX + 5, 2) + Math.pow(shiftedY + 3, 2)) * 0.01);
distances54 = Math.max(Math.abs(shiftedX + 5), Math.abs(shiftedY + 4)) * 100 + (Math.sqrt(Math.pow(shiftedX + 5, 2) + Math.pow(shiftedY + 4, 2)) * 0.01);
distances55 = Math.max(Math.abs(shiftedX + 5), Math.abs(shiftedY + 5)) * 100 + (Math.sqrt(Math.pow(shiftedX + 5, 2) + Math.pow(shiftedY + 5, 2)) * 0.01);
distances56 = Math.max(Math.abs(shiftedX + 5), Math.abs(shiftedY + 6)) * 100 + (Math.sqrt(Math.pow(shiftedX + 5, 2) + Math.pow(shiftedY + 6, 2)) * 0.01);
distances60 = Math.max(Math.abs(shiftedX + 6), Math.abs(shiftedY + 0)) * 100 + (Math.sqrt(Math.pow(shiftedX + 6, 2) + Math.pow(shiftedY + 0, 2)) * 0.01);
distances61 = Math.max(Math.abs(shiftedX + 6), Math.abs(shiftedY + 1)) * 100 + (Math.sqrt(Math.pow(shiftedX + 6, 2) + Math.pow(shiftedY + 1, 2)) * 0.01);
distances62 = Math.max(Math.abs(shiftedX + 6), Math.abs(shiftedY + 2)) * 100 + (Math.sqrt(Math.pow(shiftedX + 6, 2) + Math.pow(shiftedY + 2, 2)) * 0.01);
distances63 = Math.max(Math.abs(shiftedX + 6), Math.abs(shiftedY + 3)) * 100 + (Math.sqrt(Math.pow(shiftedX + 6, 2) + Math.pow(shiftedY + 3, 2)) * 0.01);
distances64 = Math.max(Math.abs(shiftedX + 6), Math.abs(shiftedY + 4)) * 100 + (Math.sqrt(Math.pow(shiftedX + 6, 2) + Math.pow(shiftedY + 4, 2)) * 0.01);
distances65 = Math.max(Math.abs(shiftedX + 6), Math.abs(shiftedY + 5)) * 100 + (Math.sqrt(Math.pow(shiftedX + 6, 2) + Math.pow(shiftedY + 5, 2)) * 0.01);
distances66 = Math.max(Math.abs(shiftedX + 6), Math.abs(shiftedY + 6)) * 100 + (Math.sqrt(Math.pow(shiftedX + 6, 2) + Math.pow(shiftedY + 6, 2)) * 0.01);
Direction cardinalDir = null;
if (shiftedX >= shiftedY && shiftedX >= -shiftedY) cardinalDir = Direction.WEST;
else if (shiftedX <= shiftedY && shiftedX <= -shiftedY) cardinalDir = Direction.EAST;
else if (shiftedY <= shiftedX && shiftedY <= -shiftedX) cardinalDir = Direction.NORTH;
else if (shiftedY >= shiftedX && shiftedY >= -shiftedX) cardinalDir = Direction.SOUTH;
if (cardinalDir == Direction.EAST) relaxEast();
else if (cardinalDir == Direction.WEST) relaxWest();
else if (cardinalDir == Direction.SOUTH) relaxSouth();
else if (cardinalDir == Direction.NORTH) relaxNorth();
distances22+= passabilities22;
distances23+= passabilities23;
distances24+= passabilities24;
distances32+= passabilities32;
distances33+= passabilities33;
distances34+= passabilities34;
distances42+= passabilities42;
distances43+= passabilities43;
distances44+= passabilities44;
Direction bestDir = null;
double minDistance = distances33;
Direction directPath = rc.getLocation().directionTo(destination);
if (rc.canMove(directPath)) {
bestDir = directPath;
if(directPath == Direction.NORTHWEST) minDistance = distances24;
if(directPath == Direction.WEST) minDistance = distances23;
if(directPath == Direction.SOUTHWEST) minDistance = distances22;
if(directPath == Direction.SOUTH) minDistance = distances32;
if(directPath == Direction.SOUTHEAST) minDistance = distances42;
if(directPath == Direction.EAST) minDistance = distances43;
if(directPath == Direction.NORTHEAST) minDistance = distances44;
if(directPath == Direction.NORTH) minDistance = distances34;
}
if (validEdges023 && distances24 < minDistance) {
bestDir=Direction.NORTHWEST;
minDistance=distances24;
}
if (validEdges032 && distances42 < minDistance) {
bestDir=Direction.SOUTHEAST;
minDistance=distances42;
}
if (validEdges123 && distances23 < minDistance) {
bestDir=Direction.WEST;
minDistance=distances23;
}
if (validEdges133 && distances43 < minDistance) {
bestDir=Direction.EAST;
minDistance=distances43;
}
if (validEdges222 && distances22 < minDistance) {
bestDir=Direction.SOUTHWEST;
minDistance=distances22;
}
if (validEdges233 && distances44 < minDistance) {
bestDir=Direction.NORTHEAST;
minDistance=distances44;
}
if (validEdges332 && distances32 < minDistance) {
bestDir=Direction.SOUTH;
minDistance=distances32;
}
if (validEdges333 && distances34 < minDistance) {
bestDir=Direction.NORTH;
minDistance=distances34;
}
if (minDistance == distances33) return;
if (bestDir != null && rc.canMove(bestDir)) rc.move(bestDir);
}
public void relaxWest() {
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
if (validEdges004){
distances05=Math.min(distances05, distances14+passabilities14);
distances14=Math.min(distances05+passabilities05, distances14);
}
if (validEdges104){
distances04=Math.min(distances04, distances14+passabilities14);
distances14=Math.min(distances04+passabilities04, distances14);
}
if (validEdges204){
distances04=Math.min(distances04, distances15+passabilities15);
distances15=Math.min(distances04+passabilities04, distances15);
}
if (validEdges304){
distances04=Math.min(distances04, distances05+passabilities05);
distances05=Math.min(distances04+passabilities04, distances05);
}
if (validEdges005){
distances06=Math.min(distances06, distances15+passabilities15);
distances15=Math.min(distances06+passabilities06, distances15);
}
if (validEdges105){
distances05=Math.min(distances05, distances15+passabilities15);
distances15=Math.min(distances05+passabilities05, distances15);
}
if (validEdges205){
distances05=Math.min(distances05, distances16+passabilities16);
distances16=Math.min(distances05+passabilities05, distances16);
}
if (validEdges305){
distances05=Math.min(distances05, distances06+passabilities06);
distances06=Math.min(distances05+passabilities05, distances06);
}
if (validEdges106){
distances06=Math.min(distances06, distances16+passabilities16);
distances16=Math.min(distances06+passabilities06, distances16);
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
if (validEdges014){
distances15=Math.min(distances15, distances24+passabilities24);
distances24=Math.min(distances15+passabilities15, distances24);
}
if (validEdges114){
distances14=Math.min(distances14, distances24+passabilities24);
distances24=Math.min(distances14+passabilities14, distances24);
}
if (validEdges214){
distances14=Math.min(distances14, distances25+passabilities25);
distances25=Math.min(distances14+passabilities14, distances25);
}
if (validEdges314){
distances14=Math.min(distances14, distances15+passabilities15);
distances15=Math.min(distances14+passabilities14, distances15);
}
if (validEdges015){
distances16=Math.min(distances16, distances25+passabilities25);
distances25=Math.min(distances16+passabilities16, distances25);
}
if (validEdges115){
distances15=Math.min(distances15, distances25+passabilities25);
distances25=Math.min(distances15+passabilities15, distances25);
}
if (validEdges215){
distances15=Math.min(distances15, distances26+passabilities26);
distances26=Math.min(distances15+passabilities15, distances26);
}
if (validEdges315){
distances15=Math.min(distances15, distances16+passabilities16);
distances16=Math.min(distances15+passabilities15, distances16);
}
if (validEdges116){
distances16=Math.min(distances16, distances26+passabilities26);
distances26=Math.min(distances16+passabilities16, distances26);
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
if (validEdges024){
distances25=Math.min(distances25, distances34+passabilities34);
distances34=Math.min(distances25+passabilities25, distances34);
}
if (validEdges124){
distances24=Math.min(distances24, distances34+passabilities34);
distances34=Math.min(distances24+passabilities24, distances34);
}
if (validEdges224){
distances24=Math.min(distances24, distances35+passabilities35);
distances35=Math.min(distances24+passabilities24, distances35);
}
if (validEdges324){
distances24=Math.min(distances24, distances25+passabilities25);
distances25=Math.min(distances24+passabilities24, distances25);
}
if (validEdges025){
distances26=Math.min(distances26, distances35+passabilities35);
distances35=Math.min(distances26+passabilities26, distances35);
}
if (validEdges125){
distances25=Math.min(distances25, distances35+passabilities35);
distances35=Math.min(distances25+passabilities25, distances35);
}
if (validEdges225){
distances25=Math.min(distances25, distances36+passabilities36);
distances36=Math.min(distances25+passabilities25, distances36);
}
if (validEdges325){
distances25=Math.min(distances25, distances26+passabilities26);
distances26=Math.min(distances25+passabilities25, distances26);
}
if (validEdges126){
distances26=Math.min(distances26, distances36+passabilities36);
distances36=Math.min(distances26+passabilities26, distances36);
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
if (validEdges034){
distances35=Math.min(distances35, distances44+passabilities44);
distances44=Math.min(distances35+passabilities35, distances44);
}
if (validEdges134){
distances34=Math.min(distances34, distances44+passabilities44);
distances44=Math.min(distances34+passabilities34, distances44);
}
if (validEdges234){
distances34=Math.min(distances34, distances45+passabilities45);
distances45=Math.min(distances34+passabilities34, distances45);
}
if (validEdges334){
distances34=Math.min(distances34, distances35+passabilities35);
distances35=Math.min(distances34+passabilities34, distances35);
}
if (validEdges035){
distances36=Math.min(distances36, distances45+passabilities45);
distances45=Math.min(distances36+passabilities36, distances45);
}
if (validEdges135){
distances35=Math.min(distances35, distances45+passabilities45);
distances45=Math.min(distances35+passabilities35, distances45);
}
if (validEdges235){
distances35=Math.min(distances35, distances46+passabilities46);
distances46=Math.min(distances35+passabilities35, distances46);
}
if (validEdges335){
distances35=Math.min(distances35, distances36+passabilities36);
distances36=Math.min(distances35+passabilities35, distances36);
}
if (validEdges136){
distances36=Math.min(distances36, distances46+passabilities46);
distances46=Math.min(distances36+passabilities36, distances46);
}
if (validEdges040){
distances41=Math.min(distances41, distances50+passabilities50);
distances50=Math.min(distances41+passabilities41, distances50);
}
if (validEdges140){
distances40=Math.min(distances40, distances50+passabilities50);
distances50=Math.min(distances40+passabilities40, distances50);
}
if (validEdges240){
distances40=Math.min(distances40, distances51+passabilities51);
distances51=Math.min(distances40+passabilities40, distances51);
}
if (validEdges340){
distances40=Math.min(distances40, distances41+passabilities41);
distances41=Math.min(distances40+passabilities40, distances41);
}
if (validEdges041){
distances42=Math.min(distances42, distances51+passabilities51);
distances51=Math.min(distances42+passabilities42, distances51);
}
if (validEdges141){
distances41=Math.min(distances41, distances51+passabilities51);
distances51=Math.min(distances41+passabilities41, distances51);
}
if (validEdges241){
distances41=Math.min(distances41, distances52+passabilities52);
distances52=Math.min(distances41+passabilities41, distances52);
}
if (validEdges341){
distances41=Math.min(distances41, distances42+passabilities42);
distances42=Math.min(distances41+passabilities41, distances42);
}
if (validEdges042){
distances43=Math.min(distances43, distances52+passabilities52);
distances52=Math.min(distances43+passabilities43, distances52);
}
if (validEdges142){
distances42=Math.min(distances42, distances52+passabilities52);
distances52=Math.min(distances42+passabilities42, distances52);
}
if (validEdges242){
distances42=Math.min(distances42, distances53+passabilities53);
distances53=Math.min(distances42+passabilities42, distances53);
}
if (validEdges342){
distances42=Math.min(distances42, distances43+passabilities43);
distances43=Math.min(distances42+passabilities42, distances43);
}
if (validEdges043){
distances44=Math.min(distances44, distances53+passabilities53);
distances53=Math.min(distances44+passabilities44, distances53);
}
if (validEdges143){
distances43=Math.min(distances43, distances53+passabilities53);
distances53=Math.min(distances43+passabilities43, distances53);
}
if (validEdges243){
distances43=Math.min(distances43, distances54+passabilities54);
distances54=Math.min(distances43+passabilities43, distances54);
}
if (validEdges343){
distances43=Math.min(distances43, distances44+passabilities44);
distances44=Math.min(distances43+passabilities43, distances44);
}
if (validEdges044){
distances45=Math.min(distances45, distances54+passabilities54);
distances54=Math.min(distances45+passabilities45, distances54);
}
if (validEdges144){
distances44=Math.min(distances44, distances54+passabilities54);
distances54=Math.min(distances44+passabilities44, distances54);
}
if (validEdges244){
distances44=Math.min(distances44, distances55+passabilities55);
distances55=Math.min(distances44+passabilities44, distances55);
}
if (validEdges344){
distances44=Math.min(distances44, distances45+passabilities45);
distances45=Math.min(distances44+passabilities44, distances45);
}
if (validEdges045){
distances46=Math.min(distances46, distances55+passabilities55);
distances55=Math.min(distances46+passabilities46, distances55);
}
if (validEdges145){
distances45=Math.min(distances45, distances55+passabilities55);
distances55=Math.min(distances45+passabilities45, distances55);
}
if (validEdges245){
distances45=Math.min(distances45, distances56+passabilities56);
distances56=Math.min(distances45+passabilities45, distances56);
}
if (validEdges345){
distances45=Math.min(distances45, distances46+passabilities46);
distances46=Math.min(distances45+passabilities45, distances46);
}
if (validEdges146){
distances46=Math.min(distances46, distances56+passabilities56);
distances56=Math.min(distances46+passabilities46, distances56);
}
if (validEdges050){
distances51=Math.min(distances51, distances60+passabilities60);
distances60=Math.min(distances51+passabilities51, distances60);
}
if (validEdges150){
distances50=Math.min(distances50, distances60+passabilities60);
distances60=Math.min(distances50+passabilities50, distances60);
}
if (validEdges250){
distances50=Math.min(distances50, distances61+passabilities61);
distances61=Math.min(distances50+passabilities50, distances61);
}
if (validEdges350){
distances50=Math.min(distances50, distances51+passabilities51);
distances51=Math.min(distances50+passabilities50, distances51);
}
if (validEdges051){
distances52=Math.min(distances52, distances61+passabilities61);
distances61=Math.min(distances52+passabilities52, distances61);
}
if (validEdges151){
distances51=Math.min(distances51, distances61+passabilities61);
distances61=Math.min(distances51+passabilities51, distances61);
}
if (validEdges251){
distances51=Math.min(distances51, distances62+passabilities62);
distances62=Math.min(distances51+passabilities51, distances62);
}
if (validEdges351){
distances51=Math.min(distances51, distances52+passabilities52);
distances52=Math.min(distances51+passabilities51, distances52);
}
if (validEdges052){
distances53=Math.min(distances53, distances62+passabilities62);
distances62=Math.min(distances53+passabilities53, distances62);
}
if (validEdges152){
distances52=Math.min(distances52, distances62+passabilities62);
distances62=Math.min(distances52+passabilities52, distances62);
}
if (validEdges252){
distances52=Math.min(distances52, distances63+passabilities63);
distances63=Math.min(distances52+passabilities52, distances63);
}
if (validEdges352){
distances52=Math.min(distances52, distances53+passabilities53);
distances53=Math.min(distances52+passabilities52, distances53);
}
if (validEdges053){
distances54=Math.min(distances54, distances63+passabilities63);
distances63=Math.min(distances54+passabilities54, distances63);
}
if (validEdges153){
distances53=Math.min(distances53, distances63+passabilities63);
distances63=Math.min(distances53+passabilities53, distances63);
}
if (validEdges253){
distances53=Math.min(distances53, distances64+passabilities64);
distances64=Math.min(distances53+passabilities53, distances64);
}
if (validEdges353){
distances53=Math.min(distances53, distances54+passabilities54);
distances54=Math.min(distances53+passabilities53, distances54);
}
if (validEdges054){
distances55=Math.min(distances55, distances64+passabilities64);
distances64=Math.min(distances55+passabilities55, distances64);
}
if (validEdges154){
distances54=Math.min(distances54, distances64+passabilities64);
distances64=Math.min(distances54+passabilities54, distances64);
}
if (validEdges254){
distances54=Math.min(distances54, distances65+passabilities65);
distances65=Math.min(distances54+passabilities54, distances65);
}
if (validEdges354){
distances54=Math.min(distances54, distances55+passabilities55);
distances55=Math.min(distances54+passabilities54, distances55);
}
if (validEdges055){
distances56=Math.min(distances56, distances65+passabilities65);
distances65=Math.min(distances56+passabilities56, distances65);
}
if (validEdges155){
distances55=Math.min(distances55, distances65+passabilities65);
distances65=Math.min(distances55+passabilities55, distances65);
}
if (validEdges255){
distances55=Math.min(distances55, distances66+passabilities66);
distances66=Math.min(distances55+passabilities55, distances66);
}
if (validEdges355){
distances55=Math.min(distances55, distances56+passabilities56);
distances56=Math.min(distances55+passabilities55, distances56);
}
if (validEdges156){
distances56=Math.min(distances56, distances66+passabilities66);
distances66=Math.min(distances56+passabilities56, distances66);
}
if (validEdges360){
distances60=Math.min(distances60, distances61+passabilities61);
distances61=Math.min(distances60+passabilities60, distances61);
}
if (validEdges361){
distances61=Math.min(distances61, distances62+passabilities62);
distances62=Math.min(distances61+passabilities61, distances62);
}
if (validEdges362){
distances62=Math.min(distances62, distances63+passabilities63);
distances63=Math.min(distances62+passabilities62, distances63);
}
if (validEdges363){
distances63=Math.min(distances63, distances64+passabilities64);
distances64=Math.min(distances63+passabilities63, distances64);
}
if (validEdges364){
distances64=Math.min(distances64, distances65+passabilities65);
distances65=Math.min(distances64+passabilities64, distances65);
}
if (validEdges365){
distances65=Math.min(distances65, distances66+passabilities66);
distances66=Math.min(distances65+passabilities65, distances66);
}
} public void relaxEast() {
if (validEdges360){
distances60=Math.min(distances60, distances61+passabilities61);
distances61=Math.min(distances60+passabilities60, distances61);
}
if (validEdges361){
distances61=Math.min(distances61, distances62+passabilities62);
distances62=Math.min(distances61+passabilities61, distances62);
}
if (validEdges362){
distances62=Math.min(distances62, distances63+passabilities63);
distances63=Math.min(distances62+passabilities62, distances63);
}
if (validEdges363){
distances63=Math.min(distances63, distances64+passabilities64);
distances64=Math.min(distances63+passabilities63, distances64);
}
if (validEdges364){
distances64=Math.min(distances64, distances65+passabilities65);
distances65=Math.min(distances64+passabilities64, distances65);
}
if (validEdges365){
distances65=Math.min(distances65, distances66+passabilities66);
distances66=Math.min(distances65+passabilities65, distances66);
}
if (validEdges050){
distances51=Math.min(distances51, distances60+passabilities60);
distances60=Math.min(distances51+passabilities51, distances60);
}
if (validEdges150){
distances50=Math.min(distances50, distances60+passabilities60);
distances60=Math.min(distances50+passabilities50, distances60);
}
if (validEdges250){
distances50=Math.min(distances50, distances61+passabilities61);
distances61=Math.min(distances50+passabilities50, distances61);
}
if (validEdges350){
distances50=Math.min(distances50, distances51+passabilities51);
distances51=Math.min(distances50+passabilities50, distances51);
}
if (validEdges051){
distances52=Math.min(distances52, distances61+passabilities61);
distances61=Math.min(distances52+passabilities52, distances61);
}
if (validEdges151){
distances51=Math.min(distances51, distances61+passabilities61);
distances61=Math.min(distances51+passabilities51, distances61);
}
if (validEdges251){
distances51=Math.min(distances51, distances62+passabilities62);
distances62=Math.min(distances51+passabilities51, distances62);
}
if (validEdges351){
distances51=Math.min(distances51, distances52+passabilities52);
distances52=Math.min(distances51+passabilities51, distances52);
}
if (validEdges052){
distances53=Math.min(distances53, distances62+passabilities62);
distances62=Math.min(distances53+passabilities53, distances62);
}
if (validEdges152){
distances52=Math.min(distances52, distances62+passabilities62);
distances62=Math.min(distances52+passabilities52, distances62);
}
if (validEdges252){
distances52=Math.min(distances52, distances63+passabilities63);
distances63=Math.min(distances52+passabilities52, distances63);
}
if (validEdges352){
distances52=Math.min(distances52, distances53+passabilities53);
distances53=Math.min(distances52+passabilities52, distances53);
}
if (validEdges053){
distances54=Math.min(distances54, distances63+passabilities63);
distances63=Math.min(distances54+passabilities54, distances63);
}
if (validEdges153){
distances53=Math.min(distances53, distances63+passabilities63);
distances63=Math.min(distances53+passabilities53, distances63);
}
if (validEdges253){
distances53=Math.min(distances53, distances64+passabilities64);
distances64=Math.min(distances53+passabilities53, distances64);
}
if (validEdges353){
distances53=Math.min(distances53, distances54+passabilities54);
distances54=Math.min(distances53+passabilities53, distances54);
}
if (validEdges054){
distances55=Math.min(distances55, distances64+passabilities64);
distances64=Math.min(distances55+passabilities55, distances64);
}
if (validEdges154){
distances54=Math.min(distances54, distances64+passabilities64);
distances64=Math.min(distances54+passabilities54, distances64);
}
if (validEdges254){
distances54=Math.min(distances54, distances65+passabilities65);
distances65=Math.min(distances54+passabilities54, distances65);
}
if (validEdges354){
distances54=Math.min(distances54, distances55+passabilities55);
distances55=Math.min(distances54+passabilities54, distances55);
}
if (validEdges055){
distances56=Math.min(distances56, distances65+passabilities65);
distances65=Math.min(distances56+passabilities56, distances65);
}
if (validEdges155){
distances55=Math.min(distances55, distances65+passabilities65);
distances65=Math.min(distances55+passabilities55, distances65);
}
if (validEdges255){
distances55=Math.min(distances55, distances66+passabilities66);
distances66=Math.min(distances55+passabilities55, distances66);
}
if (validEdges355){
distances55=Math.min(distances55, distances56+passabilities56);
distances56=Math.min(distances55+passabilities55, distances56);
}
if (validEdges156){
distances56=Math.min(distances56, distances66+passabilities66);
distances66=Math.min(distances56+passabilities56, distances66);
}
if (validEdges040){
distances41=Math.min(distances41, distances50+passabilities50);
distances50=Math.min(distances41+passabilities41, distances50);
}
if (validEdges140){
distances40=Math.min(distances40, distances50+passabilities50);
distances50=Math.min(distances40+passabilities40, distances50);
}
if (validEdges240){
distances40=Math.min(distances40, distances51+passabilities51);
distances51=Math.min(distances40+passabilities40, distances51);
}
if (validEdges340){
distances40=Math.min(distances40, distances41+passabilities41);
distances41=Math.min(distances40+passabilities40, distances41);
}
if (validEdges041){
distances42=Math.min(distances42, distances51+passabilities51);
distances51=Math.min(distances42+passabilities42, distances51);
}
if (validEdges141){
distances41=Math.min(distances41, distances51+passabilities51);
distances51=Math.min(distances41+passabilities41, distances51);
}
if (validEdges241){
distances41=Math.min(distances41, distances52+passabilities52);
distances52=Math.min(distances41+passabilities41, distances52);
}
if (validEdges341){
distances41=Math.min(distances41, distances42+passabilities42);
distances42=Math.min(distances41+passabilities41, distances42);
}
if (validEdges042){
distances43=Math.min(distances43, distances52+passabilities52);
distances52=Math.min(distances43+passabilities43, distances52);
}
if (validEdges142){
distances42=Math.min(distances42, distances52+passabilities52);
distances52=Math.min(distances42+passabilities42, distances52);
}
if (validEdges242){
distances42=Math.min(distances42, distances53+passabilities53);
distances53=Math.min(distances42+passabilities42, distances53);
}
if (validEdges342){
distances42=Math.min(distances42, distances43+passabilities43);
distances43=Math.min(distances42+passabilities42, distances43);
}
if (validEdges043){
distances44=Math.min(distances44, distances53+passabilities53);
distances53=Math.min(distances44+passabilities44, distances53);
}
if (validEdges143){
distances43=Math.min(distances43, distances53+passabilities53);
distances53=Math.min(distances43+passabilities43, distances53);
}
if (validEdges243){
distances43=Math.min(distances43, distances54+passabilities54);
distances54=Math.min(distances43+passabilities43, distances54);
}
if (validEdges343){
distances43=Math.min(distances43, distances44+passabilities44);
distances44=Math.min(distances43+passabilities43, distances44);
}
if (validEdges044){
distances45=Math.min(distances45, distances54+passabilities54);
distances54=Math.min(distances45+passabilities45, distances54);
}
if (validEdges144){
distances44=Math.min(distances44, distances54+passabilities54);
distances54=Math.min(distances44+passabilities44, distances54);
}
if (validEdges244){
distances44=Math.min(distances44, distances55+passabilities55);
distances55=Math.min(distances44+passabilities44, distances55);
}
if (validEdges344){
distances44=Math.min(distances44, distances45+passabilities45);
distances45=Math.min(distances44+passabilities44, distances45);
}
if (validEdges045){
distances46=Math.min(distances46, distances55+passabilities55);
distances55=Math.min(distances46+passabilities46, distances55);
}
if (validEdges145){
distances45=Math.min(distances45, distances55+passabilities55);
distances55=Math.min(distances45+passabilities45, distances55);
}
if (validEdges245){
distances45=Math.min(distances45, distances56+passabilities56);
distances56=Math.min(distances45+passabilities45, distances56);
}
if (validEdges345){
distances45=Math.min(distances45, distances46+passabilities46);
distances46=Math.min(distances45+passabilities45, distances46);
}
if (validEdges146){
distances46=Math.min(distances46, distances56+passabilities56);
distances56=Math.min(distances46+passabilities46, distances56);
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
if (validEdges034){
distances35=Math.min(distances35, distances44+passabilities44);
distances44=Math.min(distances35+passabilities35, distances44);
}
if (validEdges134){
distances34=Math.min(distances34, distances44+passabilities44);
distances44=Math.min(distances34+passabilities34, distances44);
}
if (validEdges234){
distances34=Math.min(distances34, distances45+passabilities45);
distances45=Math.min(distances34+passabilities34, distances45);
}
if (validEdges334){
distances34=Math.min(distances34, distances35+passabilities35);
distances35=Math.min(distances34+passabilities34, distances35);
}
if (validEdges035){
distances36=Math.min(distances36, distances45+passabilities45);
distances45=Math.min(distances36+passabilities36, distances45);
}
if (validEdges135){
distances35=Math.min(distances35, distances45+passabilities45);
distances45=Math.min(distances35+passabilities35, distances45);
}
if (validEdges235){
distances35=Math.min(distances35, distances46+passabilities46);
distances46=Math.min(distances35+passabilities35, distances46);
}
if (validEdges335){
distances35=Math.min(distances35, distances36+passabilities36);
distances36=Math.min(distances35+passabilities35, distances36);
}
if (validEdges136){
distances36=Math.min(distances36, distances46+passabilities46);
distances46=Math.min(distances36+passabilities36, distances46);
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
if (validEdges024){
distances25=Math.min(distances25, distances34+passabilities34);
distances34=Math.min(distances25+passabilities25, distances34);
}
if (validEdges124){
distances24=Math.min(distances24, distances34+passabilities34);
distances34=Math.min(distances24+passabilities24, distances34);
}
if (validEdges224){
distances24=Math.min(distances24, distances35+passabilities35);
distances35=Math.min(distances24+passabilities24, distances35);
}
if (validEdges324){
distances24=Math.min(distances24, distances25+passabilities25);
distances25=Math.min(distances24+passabilities24, distances25);
}
if (validEdges025){
distances26=Math.min(distances26, distances35+passabilities35);
distances35=Math.min(distances26+passabilities26, distances35);
}
if (validEdges125){
distances25=Math.min(distances25, distances35+passabilities35);
distances35=Math.min(distances25+passabilities25, distances35);
}
if (validEdges225){
distances25=Math.min(distances25, distances36+passabilities36);
distances36=Math.min(distances25+passabilities25, distances36);
}
if (validEdges325){
distances25=Math.min(distances25, distances26+passabilities26);
distances26=Math.min(distances25+passabilities25, distances26);
}
if (validEdges126){
distances26=Math.min(distances26, distances36+passabilities36);
distances36=Math.min(distances26+passabilities26, distances36);
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
if (validEdges014){
distances15=Math.min(distances15, distances24+passabilities24);
distances24=Math.min(distances15+passabilities15, distances24);
}
if (validEdges114){
distances14=Math.min(distances14, distances24+passabilities24);
distances24=Math.min(distances14+passabilities14, distances24);
}
if (validEdges214){
distances14=Math.min(distances14, distances25+passabilities25);
distances25=Math.min(distances14+passabilities14, distances25);
}
if (validEdges314){
distances14=Math.min(distances14, distances15+passabilities15);
distances15=Math.min(distances14+passabilities14, distances15);
}
if (validEdges015){
distances16=Math.min(distances16, distances25+passabilities25);
distances25=Math.min(distances16+passabilities16, distances25);
}
if (validEdges115){
distances15=Math.min(distances15, distances25+passabilities25);
distances25=Math.min(distances15+passabilities15, distances25);
}
if (validEdges215){
distances15=Math.min(distances15, distances26+passabilities26);
distances26=Math.min(distances15+passabilities15, distances26);
}
if (validEdges315){
distances15=Math.min(distances15, distances16+passabilities16);
distances16=Math.min(distances15+passabilities15, distances16);
}
if (validEdges116){
distances16=Math.min(distances16, distances26+passabilities26);
distances26=Math.min(distances16+passabilities16, distances26);
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
if (validEdges004){
distances05=Math.min(distances05, distances14+passabilities14);
distances14=Math.min(distances05+passabilities05, distances14);
}
if (validEdges104){
distances04=Math.min(distances04, distances14+passabilities14);
distances14=Math.min(distances04+passabilities04, distances14);
}
if (validEdges204){
distances04=Math.min(distances04, distances15+passabilities15);
distances15=Math.min(distances04+passabilities04, distances15);
}
if (validEdges304){
distances04=Math.min(distances04, distances05+passabilities05);
distances05=Math.min(distances04+passabilities04, distances05);
}
if (validEdges005){
distances06=Math.min(distances06, distances15+passabilities15);
distances15=Math.min(distances06+passabilities06, distances15);
}
if (validEdges105){
distances05=Math.min(distances05, distances15+passabilities15);
distances15=Math.min(distances05+passabilities05, distances15);
}
if (validEdges205){
distances05=Math.min(distances05, distances16+passabilities16);
distances16=Math.min(distances05+passabilities05, distances16);
}
if (validEdges305){
distances05=Math.min(distances05, distances06+passabilities06);
distances06=Math.min(distances05+passabilities05, distances06);
}
if (validEdges106){
distances06=Math.min(distances06, distances16+passabilities16);
distances16=Math.min(distances06+passabilities06, distances16);
}
} public void relaxSouth() {
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
if (validEdges040){
distances41=Math.min(distances41, distances50+passabilities50);
distances50=Math.min(distances41+passabilities41, distances50);
}
if (validEdges140){
distances40=Math.min(distances40, distances50+passabilities50);
distances50=Math.min(distances40+passabilities40, distances50);
}
if (validEdges240){
distances40=Math.min(distances40, distances51+passabilities51);
distances51=Math.min(distances40+passabilities40, distances51);
}
if (validEdges340){
distances40=Math.min(distances40, distances41+passabilities41);
distances41=Math.min(distances40+passabilities40, distances41);
}
if (validEdges050){
distances51=Math.min(distances51, distances60+passabilities60);
distances60=Math.min(distances51+passabilities51, distances60);
}
if (validEdges150){
distances50=Math.min(distances50, distances60+passabilities60);
distances60=Math.min(distances50+passabilities50, distances60);
}
if (validEdges250){
distances50=Math.min(distances50, distances61+passabilities61);
distances61=Math.min(distances50+passabilities50, distances61);
}
if (validEdges350){
distances50=Math.min(distances50, distances51+passabilities51);
distances51=Math.min(distances50+passabilities50, distances51);
}
if (validEdges360){
distances60=Math.min(distances60, distances61+passabilities61);
distances61=Math.min(distances60+passabilities60, distances61);
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
if (validEdges041){
distances42=Math.min(distances42, distances51+passabilities51);
distances51=Math.min(distances42+passabilities42, distances51);
}
if (validEdges141){
distances41=Math.min(distances41, distances51+passabilities51);
distances51=Math.min(distances41+passabilities41, distances51);
}
if (validEdges241){
distances41=Math.min(distances41, distances52+passabilities52);
distances52=Math.min(distances41+passabilities41, distances52);
}
if (validEdges341){
distances41=Math.min(distances41, distances42+passabilities42);
distances42=Math.min(distances41+passabilities41, distances42);
}
if (validEdges051){
distances52=Math.min(distances52, distances61+passabilities61);
distances61=Math.min(distances52+passabilities52, distances61);
}
if (validEdges151){
distances51=Math.min(distances51, distances61+passabilities61);
distances61=Math.min(distances51+passabilities51, distances61);
}
if (validEdges251){
distances51=Math.min(distances51, distances62+passabilities62);
distances62=Math.min(distances51+passabilities51, distances62);
}
if (validEdges351){
distances51=Math.min(distances51, distances52+passabilities52);
distances52=Math.min(distances51+passabilities51, distances52);
}
if (validEdges361){
distances61=Math.min(distances61, distances62+passabilities62);
distances62=Math.min(distances61+passabilities61, distances62);
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
if (validEdges042){
distances43=Math.min(distances43, distances52+passabilities52);
distances52=Math.min(distances43+passabilities43, distances52);
}
if (validEdges142){
distances42=Math.min(distances42, distances52+passabilities52);
distances52=Math.min(distances42+passabilities42, distances52);
}
if (validEdges242){
distances42=Math.min(distances42, distances53+passabilities53);
distances53=Math.min(distances42+passabilities42, distances53);
}
if (validEdges342){
distances42=Math.min(distances42, distances43+passabilities43);
distances43=Math.min(distances42+passabilities42, distances43);
}
if (validEdges052){
distances53=Math.min(distances53, distances62+passabilities62);
distances62=Math.min(distances53+passabilities53, distances62);
}
if (validEdges152){
distances52=Math.min(distances52, distances62+passabilities62);
distances62=Math.min(distances52+passabilities52, distances62);
}
if (validEdges252){
distances52=Math.min(distances52, distances63+passabilities63);
distances63=Math.min(distances52+passabilities52, distances63);
}
if (validEdges352){
distances52=Math.min(distances52, distances53+passabilities53);
distances53=Math.min(distances52+passabilities52, distances53);
}
if (validEdges362){
distances62=Math.min(distances62, distances63+passabilities63);
distances63=Math.min(distances62+passabilities62, distances63);
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
if (validEdges043){
distances44=Math.min(distances44, distances53+passabilities53);
distances53=Math.min(distances44+passabilities44, distances53);
}
if (validEdges143){
distances43=Math.min(distances43, distances53+passabilities53);
distances53=Math.min(distances43+passabilities43, distances53);
}
if (validEdges243){
distances43=Math.min(distances43, distances54+passabilities54);
distances54=Math.min(distances43+passabilities43, distances54);
}
if (validEdges343){
distances43=Math.min(distances43, distances44+passabilities44);
distances44=Math.min(distances43+passabilities43, distances44);
}
if (validEdges053){
distances54=Math.min(distances54, distances63+passabilities63);
distances63=Math.min(distances54+passabilities54, distances63);
}
if (validEdges153){
distances53=Math.min(distances53, distances63+passabilities63);
distances63=Math.min(distances53+passabilities53, distances63);
}
if (validEdges253){
distances53=Math.min(distances53, distances64+passabilities64);
distances64=Math.min(distances53+passabilities53, distances64);
}
if (validEdges353){
distances53=Math.min(distances53, distances54+passabilities54);
distances54=Math.min(distances53+passabilities53, distances54);
}
if (validEdges363){
distances63=Math.min(distances63, distances64+passabilities64);
distances64=Math.min(distances63+passabilities63, distances64);
}
if (validEdges004){
distances05=Math.min(distances05, distances14+passabilities14);
distances14=Math.min(distances05+passabilities05, distances14);
}
if (validEdges104){
distances04=Math.min(distances04, distances14+passabilities14);
distances14=Math.min(distances04+passabilities04, distances14);
}
if (validEdges204){
distances04=Math.min(distances04, distances15+passabilities15);
distances15=Math.min(distances04+passabilities04, distances15);
}
if (validEdges304){
distances04=Math.min(distances04, distances05+passabilities05);
distances05=Math.min(distances04+passabilities04, distances05);
}
if (validEdges014){
distances15=Math.min(distances15, distances24+passabilities24);
distances24=Math.min(distances15+passabilities15, distances24);
}
if (validEdges114){
distances14=Math.min(distances14, distances24+passabilities24);
distances24=Math.min(distances14+passabilities14, distances24);
}
if (validEdges214){
distances14=Math.min(distances14, distances25+passabilities25);
distances25=Math.min(distances14+passabilities14, distances25);
}
if (validEdges314){
distances14=Math.min(distances14, distances15+passabilities15);
distances15=Math.min(distances14+passabilities14, distances15);
}
if (validEdges024){
distances25=Math.min(distances25, distances34+passabilities34);
distances34=Math.min(distances25+passabilities25, distances34);
}
if (validEdges124){
distances24=Math.min(distances24, distances34+passabilities34);
distances34=Math.min(distances24+passabilities24, distances34);
}
if (validEdges224){
distances24=Math.min(distances24, distances35+passabilities35);
distances35=Math.min(distances24+passabilities24, distances35);
}
if (validEdges324){
distances24=Math.min(distances24, distances25+passabilities25);
distances25=Math.min(distances24+passabilities24, distances25);
}
if (validEdges034){
distances35=Math.min(distances35, distances44+passabilities44);
distances44=Math.min(distances35+passabilities35, distances44);
}
if (validEdges134){
distances34=Math.min(distances34, distances44+passabilities44);
distances44=Math.min(distances34+passabilities34, distances44);
}
if (validEdges234){
distances34=Math.min(distances34, distances45+passabilities45);
distances45=Math.min(distances34+passabilities34, distances45);
}
if (validEdges334){
distances34=Math.min(distances34, distances35+passabilities35);
distances35=Math.min(distances34+passabilities34, distances35);
}
if (validEdges044){
distances45=Math.min(distances45, distances54+passabilities54);
distances54=Math.min(distances45+passabilities45, distances54);
}
if (validEdges144){
distances44=Math.min(distances44, distances54+passabilities54);
distances54=Math.min(distances44+passabilities44, distances54);
}
if (validEdges244){
distances44=Math.min(distances44, distances55+passabilities55);
distances55=Math.min(distances44+passabilities44, distances55);
}
if (validEdges344){
distances44=Math.min(distances44, distances45+passabilities45);
distances45=Math.min(distances44+passabilities44, distances45);
}
if (validEdges054){
distances55=Math.min(distances55, distances64+passabilities64);
distances64=Math.min(distances55+passabilities55, distances64);
}
if (validEdges154){
distances54=Math.min(distances54, distances64+passabilities64);
distances64=Math.min(distances54+passabilities54, distances64);
}
if (validEdges254){
distances54=Math.min(distances54, distances65+passabilities65);
distances65=Math.min(distances54+passabilities54, distances65);
}
if (validEdges354){
distances54=Math.min(distances54, distances55+passabilities55);
distances55=Math.min(distances54+passabilities54, distances55);
}
if (validEdges364){
distances64=Math.min(distances64, distances65+passabilities65);
distances65=Math.min(distances64+passabilities64, distances65);
}
if (validEdges005){
distances06=Math.min(distances06, distances15+passabilities15);
distances15=Math.min(distances06+passabilities06, distances15);
}
if (validEdges105){
distances05=Math.min(distances05, distances15+passabilities15);
distances15=Math.min(distances05+passabilities05, distances15);
}
if (validEdges205){
distances05=Math.min(distances05, distances16+passabilities16);
distances16=Math.min(distances05+passabilities05, distances16);
}
if (validEdges305){
distances05=Math.min(distances05, distances06+passabilities06);
distances06=Math.min(distances05+passabilities05, distances06);
}
if (validEdges015){
distances16=Math.min(distances16, distances25+passabilities25);
distances25=Math.min(distances16+passabilities16, distances25);
}
if (validEdges115){
distances15=Math.min(distances15, distances25+passabilities25);
distances25=Math.min(distances15+passabilities15, distances25);
}
if (validEdges215){
distances15=Math.min(distances15, distances26+passabilities26);
distances26=Math.min(distances15+passabilities15, distances26);
}
if (validEdges315){
distances15=Math.min(distances15, distances16+passabilities16);
distances16=Math.min(distances15+passabilities15, distances16);
}
if (validEdges025){
distances26=Math.min(distances26, distances35+passabilities35);
distances35=Math.min(distances26+passabilities26, distances35);
}
if (validEdges125){
distances25=Math.min(distances25, distances35+passabilities35);
distances35=Math.min(distances25+passabilities25, distances35);
}
if (validEdges225){
distances25=Math.min(distances25, distances36+passabilities36);
distances36=Math.min(distances25+passabilities25, distances36);
}
if (validEdges325){
distances25=Math.min(distances25, distances26+passabilities26);
distances26=Math.min(distances25+passabilities25, distances26);
}
if (validEdges035){
distances36=Math.min(distances36, distances45+passabilities45);
distances45=Math.min(distances36+passabilities36, distances45);
}
if (validEdges135){
distances35=Math.min(distances35, distances45+passabilities45);
distances45=Math.min(distances35+passabilities35, distances45);
}
if (validEdges235){
distances35=Math.min(distances35, distances46+passabilities46);
distances46=Math.min(distances35+passabilities35, distances46);
}
if (validEdges335){
distances35=Math.min(distances35, distances36+passabilities36);
distances36=Math.min(distances35+passabilities35, distances36);
}
if (validEdges045){
distances46=Math.min(distances46, distances55+passabilities55);
distances55=Math.min(distances46+passabilities46, distances55);
}
if (validEdges145){
distances45=Math.min(distances45, distances55+passabilities55);
distances55=Math.min(distances45+passabilities45, distances55);
}
if (validEdges245){
distances45=Math.min(distances45, distances56+passabilities56);
distances56=Math.min(distances45+passabilities45, distances56);
}
if (validEdges345){
distances45=Math.min(distances45, distances46+passabilities46);
distances46=Math.min(distances45+passabilities45, distances46);
}
if (validEdges055){
distances56=Math.min(distances56, distances65+passabilities65);
distances65=Math.min(distances56+passabilities56, distances65);
}
if (validEdges155){
distances55=Math.min(distances55, distances65+passabilities65);
distances65=Math.min(distances55+passabilities55, distances65);
}
if (validEdges255){
distances55=Math.min(distances55, distances66+passabilities66);
distances66=Math.min(distances55+passabilities55, distances66);
}
if (validEdges355){
distances55=Math.min(distances55, distances56+passabilities56);
distances56=Math.min(distances55+passabilities55, distances56);
}
if (validEdges365){
distances65=Math.min(distances65, distances66+passabilities66);
distances66=Math.min(distances65+passabilities65, distances66);
}
if (validEdges106){
distances06=Math.min(distances06, distances16+passabilities16);
distances16=Math.min(distances06+passabilities06, distances16);
}
if (validEdges116){
distances16=Math.min(distances16, distances26+passabilities26);
distances26=Math.min(distances16+passabilities16, distances26);
}
if (validEdges126){
distances26=Math.min(distances26, distances36+passabilities36);
distances36=Math.min(distances26+passabilities26, distances36);
}
if (validEdges136){
distances36=Math.min(distances36, distances46+passabilities46);
distances46=Math.min(distances36+passabilities36, distances46);
}
if (validEdges146){
distances46=Math.min(distances46, distances56+passabilities56);
distances56=Math.min(distances46+passabilities46, distances56);
}
if (validEdges156){
distances56=Math.min(distances56, distances66+passabilities66);
distances66=Math.min(distances56+passabilities56, distances66);
}
} public void relaxNorth() {
if (validEdges106){
distances06=Math.min(distances06, distances16+passabilities16);
distances16=Math.min(distances06+passabilities06, distances16);
}
if (validEdges116){
distances16=Math.min(distances16, distances26+passabilities26);
distances26=Math.min(distances16+passabilities16, distances26);
}
if (validEdges126){
distances26=Math.min(distances26, distances36+passabilities36);
distances36=Math.min(distances26+passabilities26, distances36);
}
if (validEdges136){
distances36=Math.min(distances36, distances46+passabilities46);
distances46=Math.min(distances36+passabilities36, distances46);
}
if (validEdges146){
distances46=Math.min(distances46, distances56+passabilities56);
distances56=Math.min(distances46+passabilities46, distances56);
}
if (validEdges156){
distances56=Math.min(distances56, distances66+passabilities66);
distances66=Math.min(distances56+passabilities56, distances66);
}
if (validEdges005){
distances06=Math.min(distances06, distances15+passabilities15);
distances15=Math.min(distances06+passabilities06, distances15);
}
if (validEdges105){
distances05=Math.min(distances05, distances15+passabilities15);
distances15=Math.min(distances05+passabilities05, distances15);
}
if (validEdges205){
distances05=Math.min(distances05, distances16+passabilities16);
distances16=Math.min(distances05+passabilities05, distances16);
}
if (validEdges305){
distances05=Math.min(distances05, distances06+passabilities06);
distances06=Math.min(distances05+passabilities05, distances06);
}
if (validEdges015){
distances16=Math.min(distances16, distances25+passabilities25);
distances25=Math.min(distances16+passabilities16, distances25);
}
if (validEdges115){
distances15=Math.min(distances15, distances25+passabilities25);
distances25=Math.min(distances15+passabilities15, distances25);
}
if (validEdges215){
distances15=Math.min(distances15, distances26+passabilities26);
distances26=Math.min(distances15+passabilities15, distances26);
}
if (validEdges315){
distances15=Math.min(distances15, distances16+passabilities16);
distances16=Math.min(distances15+passabilities15, distances16);
}
if (validEdges025){
distances26=Math.min(distances26, distances35+passabilities35);
distances35=Math.min(distances26+passabilities26, distances35);
}
if (validEdges125){
distances25=Math.min(distances25, distances35+passabilities35);
distances35=Math.min(distances25+passabilities25, distances35);
}
if (validEdges225){
distances25=Math.min(distances25, distances36+passabilities36);
distances36=Math.min(distances25+passabilities25, distances36);
}
if (validEdges325){
distances25=Math.min(distances25, distances26+passabilities26);
distances26=Math.min(distances25+passabilities25, distances26);
}
if (validEdges035){
distances36=Math.min(distances36, distances45+passabilities45);
distances45=Math.min(distances36+passabilities36, distances45);
}
if (validEdges135){
distances35=Math.min(distances35, distances45+passabilities45);
distances45=Math.min(distances35+passabilities35, distances45);
}
if (validEdges235){
distances35=Math.min(distances35, distances46+passabilities46);
distances46=Math.min(distances35+passabilities35, distances46);
}
if (validEdges335){
distances35=Math.min(distances35, distances36+passabilities36);
distances36=Math.min(distances35+passabilities35, distances36);
}
if (validEdges045){
distances46=Math.min(distances46, distances55+passabilities55);
distances55=Math.min(distances46+passabilities46, distances55);
}
if (validEdges145){
distances45=Math.min(distances45, distances55+passabilities55);
distances55=Math.min(distances45+passabilities45, distances55);
}
if (validEdges245){
distances45=Math.min(distances45, distances56+passabilities56);
distances56=Math.min(distances45+passabilities45, distances56);
}
if (validEdges345){
distances45=Math.min(distances45, distances46+passabilities46);
distances46=Math.min(distances45+passabilities45, distances46);
}
if (validEdges055){
distances56=Math.min(distances56, distances65+passabilities65);
distances65=Math.min(distances56+passabilities56, distances65);
}
if (validEdges155){
distances55=Math.min(distances55, distances65+passabilities65);
distances65=Math.min(distances55+passabilities55, distances65);
}
if (validEdges255){
distances55=Math.min(distances55, distances66+passabilities66);
distances66=Math.min(distances55+passabilities55, distances66);
}
if (validEdges355){
distances55=Math.min(distances55, distances56+passabilities56);
distances56=Math.min(distances55+passabilities55, distances56);
}
if (validEdges365){
distances65=Math.min(distances65, distances66+passabilities66);
distances66=Math.min(distances65+passabilities65, distances66);
}
if (validEdges004){
distances05=Math.min(distances05, distances14+passabilities14);
distances14=Math.min(distances05+passabilities05, distances14);
}
if (validEdges104){
distances04=Math.min(distances04, distances14+passabilities14);
distances14=Math.min(distances04+passabilities04, distances14);
}
if (validEdges204){
distances04=Math.min(distances04, distances15+passabilities15);
distances15=Math.min(distances04+passabilities04, distances15);
}
if (validEdges304){
distances04=Math.min(distances04, distances05+passabilities05);
distances05=Math.min(distances04+passabilities04, distances05);
}
if (validEdges014){
distances15=Math.min(distances15, distances24+passabilities24);
distances24=Math.min(distances15+passabilities15, distances24);
}
if (validEdges114){
distances14=Math.min(distances14, distances24+passabilities24);
distances24=Math.min(distances14+passabilities14, distances24);
}
if (validEdges214){
distances14=Math.min(distances14, distances25+passabilities25);
distances25=Math.min(distances14+passabilities14, distances25);
}
if (validEdges314){
distances14=Math.min(distances14, distances15+passabilities15);
distances15=Math.min(distances14+passabilities14, distances15);
}
if (validEdges024){
distances25=Math.min(distances25, distances34+passabilities34);
distances34=Math.min(distances25+passabilities25, distances34);
}
if (validEdges124){
distances24=Math.min(distances24, distances34+passabilities34);
distances34=Math.min(distances24+passabilities24, distances34);
}
if (validEdges224){
distances24=Math.min(distances24, distances35+passabilities35);
distances35=Math.min(distances24+passabilities24, distances35);
}
if (validEdges324){
distances24=Math.min(distances24, distances25+passabilities25);
distances25=Math.min(distances24+passabilities24, distances25);
}
if (validEdges034){
distances35=Math.min(distances35, distances44+passabilities44);
distances44=Math.min(distances35+passabilities35, distances44);
}
if (validEdges134){
distances34=Math.min(distances34, distances44+passabilities44);
distances44=Math.min(distances34+passabilities34, distances44);
}
if (validEdges234){
distances34=Math.min(distances34, distances45+passabilities45);
distances45=Math.min(distances34+passabilities34, distances45);
}
if (validEdges334){
distances34=Math.min(distances34, distances35+passabilities35);
distances35=Math.min(distances34+passabilities34, distances35);
}
if (validEdges044){
distances45=Math.min(distances45, distances54+passabilities54);
distances54=Math.min(distances45+passabilities45, distances54);
}
if (validEdges144){
distances44=Math.min(distances44, distances54+passabilities54);
distances54=Math.min(distances44+passabilities44, distances54);
}
if (validEdges244){
distances44=Math.min(distances44, distances55+passabilities55);
distances55=Math.min(distances44+passabilities44, distances55);
}
if (validEdges344){
distances44=Math.min(distances44, distances45+passabilities45);
distances45=Math.min(distances44+passabilities44, distances45);
}
if (validEdges054){
distances55=Math.min(distances55, distances64+passabilities64);
distances64=Math.min(distances55+passabilities55, distances64);
}
if (validEdges154){
distances54=Math.min(distances54, distances64+passabilities64);
distances64=Math.min(distances54+passabilities54, distances64);
}
if (validEdges254){
distances54=Math.min(distances54, distances65+passabilities65);
distances65=Math.min(distances54+passabilities54, distances65);
}
if (validEdges354){
distances54=Math.min(distances54, distances55+passabilities55);
distances55=Math.min(distances54+passabilities54, distances55);
}
if (validEdges364){
distances64=Math.min(distances64, distances65+passabilities65);
distances65=Math.min(distances64+passabilities64, distances65);
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
if (validEdges043){
distances44=Math.min(distances44, distances53+passabilities53);
distances53=Math.min(distances44+passabilities44, distances53);
}
if (validEdges143){
distances43=Math.min(distances43, distances53+passabilities53);
distances53=Math.min(distances43+passabilities43, distances53);
}
if (validEdges243){
distances43=Math.min(distances43, distances54+passabilities54);
distances54=Math.min(distances43+passabilities43, distances54);
}
if (validEdges343){
distances43=Math.min(distances43, distances44+passabilities44);
distances44=Math.min(distances43+passabilities43, distances44);
}
if (validEdges053){
distances54=Math.min(distances54, distances63+passabilities63);
distances63=Math.min(distances54+passabilities54, distances63);
}
if (validEdges153){
distances53=Math.min(distances53, distances63+passabilities63);
distances63=Math.min(distances53+passabilities53, distances63);
}
if (validEdges253){
distances53=Math.min(distances53, distances64+passabilities64);
distances64=Math.min(distances53+passabilities53, distances64);
}
if (validEdges353){
distances53=Math.min(distances53, distances54+passabilities54);
distances54=Math.min(distances53+passabilities53, distances54);
}
if (validEdges363){
distances63=Math.min(distances63, distances64+passabilities64);
distances64=Math.min(distances63+passabilities63, distances64);
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
if (validEdges042){
distances43=Math.min(distances43, distances52+passabilities52);
distances52=Math.min(distances43+passabilities43, distances52);
}
if (validEdges142){
distances42=Math.min(distances42, distances52+passabilities52);
distances52=Math.min(distances42+passabilities42, distances52);
}
if (validEdges242){
distances42=Math.min(distances42, distances53+passabilities53);
distances53=Math.min(distances42+passabilities42, distances53);
}
if (validEdges342){
distances42=Math.min(distances42, distances43+passabilities43);
distances43=Math.min(distances42+passabilities42, distances43);
}
if (validEdges052){
distances53=Math.min(distances53, distances62+passabilities62);
distances62=Math.min(distances53+passabilities53, distances62);
}
if (validEdges152){
distances52=Math.min(distances52, distances62+passabilities62);
distances62=Math.min(distances52+passabilities52, distances62);
}
if (validEdges252){
distances52=Math.min(distances52, distances63+passabilities63);
distances63=Math.min(distances52+passabilities52, distances63);
}
if (validEdges352){
distances52=Math.min(distances52, distances53+passabilities53);
distances53=Math.min(distances52+passabilities52, distances53);
}
if (validEdges362){
distances62=Math.min(distances62, distances63+passabilities63);
distances63=Math.min(distances62+passabilities62, distances63);
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
if (validEdges041){
distances42=Math.min(distances42, distances51+passabilities51);
distances51=Math.min(distances42+passabilities42, distances51);
}
if (validEdges141){
distances41=Math.min(distances41, distances51+passabilities51);
distances51=Math.min(distances41+passabilities41, distances51);
}
if (validEdges241){
distances41=Math.min(distances41, distances52+passabilities52);
distances52=Math.min(distances41+passabilities41, distances52);
}
if (validEdges341){
distances41=Math.min(distances41, distances42+passabilities42);
distances42=Math.min(distances41+passabilities41, distances42);
}
if (validEdges051){
distances52=Math.min(distances52, distances61+passabilities61);
distances61=Math.min(distances52+passabilities52, distances61);
}
if (validEdges151){
distances51=Math.min(distances51, distances61+passabilities61);
distances61=Math.min(distances51+passabilities51, distances61);
}
if (validEdges251){
distances51=Math.min(distances51, distances62+passabilities62);
distances62=Math.min(distances51+passabilities51, distances62);
}
if (validEdges351){
distances51=Math.min(distances51, distances52+passabilities52);
distances52=Math.min(distances51+passabilities51, distances52);
}
if (validEdges361){
distances61=Math.min(distances61, distances62+passabilities62);
distances62=Math.min(distances61+passabilities61, distances62);
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
if (validEdges040){
distances41=Math.min(distances41, distances50+passabilities50);
distances50=Math.min(distances41+passabilities41, distances50);
}
if (validEdges140){
distances40=Math.min(distances40, distances50+passabilities50);
distances50=Math.min(distances40+passabilities40, distances50);
}
if (validEdges240){
distances40=Math.min(distances40, distances51+passabilities51);
distances51=Math.min(distances40+passabilities40, distances51);
}
if (validEdges340){
distances40=Math.min(distances40, distances41+passabilities41);
distances41=Math.min(distances40+passabilities40, distances41);
}
if (validEdges050){
distances51=Math.min(distances51, distances60+passabilities60);
distances60=Math.min(distances51+passabilities51, distances60);
}
if (validEdges150){
distances50=Math.min(distances50, distances60+passabilities60);
distances60=Math.min(distances50+passabilities50, distances60);
}
if (validEdges250){
distances50=Math.min(distances50, distances61+passabilities61);
distances61=Math.min(distances50+passabilities50, distances61);
}
if (validEdges350){
distances50=Math.min(distances50, distances51+passabilities51);
distances51=Math.min(distances50+passabilities50, distances51);
}
if (validEdges360){
distances60=Math.min(distances60, distances61+passabilities61);
distances61=Math.min(distances60+passabilities60, distances61);
}
}
}
