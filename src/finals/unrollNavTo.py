# initialize tunable parameters
radius = 2
iterations = 3

debug = True

# other parameter computation
n = 2 * radius + 1

# print class headers
print("package finals;")
print('import battlecode.common.*;')
print('public class UnrolledNavigation extends Navigation {')
print('public UnrolledNavigation(RobotController r) {super(r);}')

# initialize valid locations
print("public void navTo (MapLocation destination) throws GameActionException {")
if (debug): print("int initialBytecodeCount = Clock.getBytecodeNum();")
if (debug): print('System.out.println("Intelligent navigation to " + destination);')
print("MapLocation myLoc = rc.getLocation();")
for x in range(n):
  for y in range(n):
    print("int elevations" + str(x) + str(y) + ";")
    strLoc = "loc" + str(x) + str(y)
    print("MapLocation " + strLoc + " = new MapLocation(myLoc.x + " + str(x - radius) + ", myLoc.y + " + str(y - radius) + ");")
    print("boolean validLocs" + str(x) + str(y) + " = rc.canSenseLocation(" + strLoc + ") && !rc.isLocationOccupied(" + strLoc + ");")
    print("double passabilities" + str(x) + str(y) + "= Double.MAX_VALUE; if (validLocs" + str(x) + str(y) + ") passabilities" + str(x) + str(y) + " = 1.0/rc.sensePassability(" + strLoc + ");")

print("validLocs" + str(radius) + str(radius) + " = true;")
print("passabilities" + str(radius) + str(radius) + " = 1.0/rc.sensePassability(loc" + str(radius) + str(radius) + ");")

if (debug): print('System.out.println("Bytecodes used in checking location validity: " + (Clock.getBytecodeNum() - initialBytecodeCount));')

# figure out which edges are valid
# TODO: actually do this correctly (with boundary conditions) here
for x in range(n-1):
  for y in range(n-1):
    print("boolean validEdges0" + str(x) + str(y) + " = (validLocs"+str(x)+""+str(y+1)+" && validLocs"+str(x+1)+""+str(y)+");")
    print("boolean validEdges1" + str(x) + str(y) + " = (validLocs"+str(x)+""+str(y)+" && validLocs"+str(x+1)+""+str(y)+");")
    print("boolean validEdges2" + str(x) + str(y) + " = (validLocs"+str(x)+""+str(y)+" && validLocs"+str(x+1)+""+str(y+1)+");")
    print("boolean validEdges3" + str(x) + str(y) + " = (validLocs"+str(x)+""+str(y)+" && validLocs"+str(x)+""+str(y+1)+");")
for x in range(n-1):
  y = n-1
  print("boolean validEdges1" + str(x) + str(y) + " = (validLocs"+str(x)+""+str(y)+" && validLocs"+str(x+1)+""+str(y)+");")
for y in range(n-1):
  x = n-1
  print("boolean validEdges3" + str(x) + str(y) + " = (validLocs"+str(x)+""+str(y)+" && validLocs"+str(x)+""+str(y+1)+");")

if (debug): print('System.out.println("Bytecodes used in setup: " + (Clock.getBytecodeNum() - initialBytecodeCount));')

print('int shiftedX = myLoc.x - destination.x - ' + str(radius) + ';')
print('int shiftedY = myLoc.y - destination.y - ' + str(radius) + ';')
for x in range(n):
  for y in range(n):
    print('double distances' + str(x) + str(y) + ' = Math.max(Math.abs(shiftedX + ' + str(x) + '), Math.abs(shiftedY + ' + str(y) + ')) * 100 + (Math.sqrt(Math.pow(shiftedX + ' + str(x) + ', 2) + Math.pow(shiftedY + ' + str(y) + ', 2)) * 0.01);')

if (debug): print('System.out.println("Bytecodes used in setup and distance initialization: " + (Clock.getBytecodeNum() - initialBytecodeCount));')

for counter in range(iterations):
  for x in range(n-1):
    for y in range(n-1):
      print('if (validEdges0'+str(x)+str(y)+'){')
      print('distances'+str(x)+str(y+1)+'=Math.min(distances'+str(x)+str(y+1)+', distances'+str(x+1)+str(y)+'+passabilities'+str(x+1)+str(y)+');')
      print('distances'+str(x+1)+str(y)+'=Math.min(distances'+str(x)+str(y+1)+'+passabilities'+str(x)+str(y+1)+', distances'+str(x+1)+str(y)+');')
      print('}')
      print('if (validEdges1'+str(x)+str(y)+'){')
      print('distances'+str(x)+str(y)+'=Math.min(distances'+str(x)+str(y)+', distances'+str(x+1)+str(y)+'+passabilities'+str(x+1)+str(y)+');')
      print('distances'+str(x+1)+str(y)+'=Math.min(distances'+str(x)+str(y)+'+passabilities'+str(x)+str(y)+', distances'+str(x+1)+str(y)+');')
      print('}')
      print('if (validEdges2'+str(x)+str(y)+'){')
      print('distances'+str(x)+str(y)+'=Math.min(distances'+str(x)+str(y)+', distances'+str(x+1)+str(y+1)+'+passabilities'+str(x+1)+str(y+1)+');')
      print('distances'+str(x+1)+str(y+1)+'=Math.min(distances'+str(x)+str(y)+'+passabilities'+str(x)+str(y)+', distances'+str(x+1)+str(y+1)+');')
      print('}')
      print('if (validEdges3'+str(x)+str(y)+'){')
      print('distances'+str(x)+str(y)+'=Math.min(distances'+str(x)+str(y)+', distances'+str(x)+str(y+1)+'+passabilities'+str(x)+str(y+1)+');')
      print('distances'+str(x)+str(y+1)+'=Math.min(distances'+str(x)+str(y)+'+passabilities'+str(x)+str(y)+', distances'+str(x)+str(y+1)+');')
      print('}')
  for x in range(n-1):
    y = n-1
    print('if (validEdges1'+str(x)+str(y)+'){')
    print('distances'+str(x)+str(y)+'=Math.min(distances'+str(x)+str(y)+', distances'+str(x+1)+str(y)+'+passabilities'+str(x+1)+str(y)+');')
    print('distances'+str(x+1)+str(y)+'=Math.min(distances'+str(x)+str(y)+'+passabilities'+str(x)+str(y)+', distances'+str(x+1)+str(y)+');')
    print('}')
  for y in range(n-1):
    x = n-1
    print('if (validEdges3'+str(x)+str(y)+'){')
    print('distances'+str(x)+str(y)+'=Math.min(distances'+str(x)+str(y)+', distances'+str(x)+str(y+1)+'+passabilities'+str(x)+str(y+1)+');')
    print('distances'+str(x)+str(y+1)+'=Math.min(distances'+str(x)+str(y)+'+passabilities'+str(x)+str(y)+', distances'+str(x)+str(y+1)+');')
    print('}')


if (debug): print('System.out.println("Bytecodes used in setup and iteration: " + (Clock.getBytecodeNum() - initialBytecodeCount));')

for x in range(3):
  for y in range(3):
    print('distances'+str(radius+x-1)+str(radius+y-1)+'+= passabilities'+str(radius+x-1)+str(radius+y-1)+';')

print('Direction bestDir = null;')
print('double minDistance = distances' + str(radius) + str(radius) +';')
print('Direction directPath = rc.getLocation().directionTo(destination);')
if(debug): print('System.out.println("Direct path: " + directPath);')
print('if (rc.canMove(directPath)) {')
if(debug): print('System.out.println("Initializing variables to direct path");')
print('bestDir = directPath;')
print('if(directPath == Direction.NORTHWEST) minDistance = distances'+str(radius-1)+str(radius+1)+';')
print('if(directPath == Direction.WEST) minDistance = distances'+str(radius-1)+str(radius)+';')
print('if(directPath == Direction.SOUTHWEST) minDistance = distances'+str(radius-1)+str(radius-1)+';')
print('if(directPath == Direction.SOUTH) minDistance = distances'+str(radius)+str(radius-1)+';')
print('if(directPath == Direction.SOUTHEAST) minDistance = distances'+str(radius+1)+str(radius-1)+';')
print('if(directPath == Direction.EAST) minDistance = distances'+str(radius+1)+str(radius)+';')
print('if(directPath == Direction.NORTHEAST) minDistance = distances'+str(radius+1)+str(radius+1)+';')
print('if(directPath == Direction.NORTH) minDistance = distances'+str(radius)+str(radius+1)+';')
if(debug): print('System.out.println("minDistance initialized to " + minDistance);')
print('}')

if(debug):
  print('System.out.println("NORTHWEST: " + distances'+str(radius-1)+str(radius+1)+');')
  print('System.out.println("WEST: " + distances'+str(radius-1)+str(radius)+');')
  print('System.out.println("SOUTHWEST: " + distances'+str(radius-1)+str(radius-1)+');')
  print('System.out.println("SOUTH: " + distances'+str(radius)+str(radius-1)+');')
  print('System.out.println("SOUTHEAST: " + distances'+str(radius+1)+str(radius-1)+');')
  print('System.out.println("EAST: " + distances'+str(radius+1)+str(radius)+');')
  print('System.out.println("NORTHEAST: " + distances'+str(radius+1)+str(radius+1)+');')
  print('System.out.println("NORTH: " + distances'+str(radius)+str(radius+1)+');')



print('if (validEdges0'+str(radius-1)+str(radius)+' && distances'+str(radius-1)+str(radius+1)+' < minDistance) {')
print('bestDir=Direction.NORTHWEST;')
print('minDistance=distances'+str(radius-1)+str(radius+1)+';')
print('}')
print('if (validEdges0'+str(radius)+str(radius-1)+' && distances'+str(radius+1)+str(radius-1)+' < minDistance) {')
print('bestDir=Direction.SOUTHEAST;')
print('minDistance=distances'+str(radius+1)+str(radius-1)+';')
print('}')

print('if (validEdges1'+str(radius-1)+str(radius)+' && distances'+str(radius-1)+str(radius)+' < minDistance) {')
print('bestDir=Direction.WEST;')
print('minDistance=distances'+str(radius-1)+str(radius)+';')
print('}')
print('if (validEdges1'+str(radius)+str(radius)+' && distances'+str(radius+1)+str(radius)+' < minDistance) {')
print('bestDir=Direction.EAST;')
print('minDistance=distances'+str(radius+1)+str(radius)+';')
print('}')

print('if (validEdges2'+str(radius-1)+str(radius-1)+' && distances'+str(radius-1)+str(radius-1)+' < minDistance) {')
print('bestDir=Direction.SOUTHWEST;')
print('minDistance=distances'+str(radius-1)+str(radius-1)+';')
print('}')
print('if (validEdges2'+str(radius)+str(radius)+' && distances'+str(radius+1)+str(radius+1)+' < minDistance) {')
print('bestDir=Direction.NORTHEAST;')
print('minDistance=distances'+str(radius+1)+str(radius+1)+';')
print('}')

print('if (validEdges3'+str(radius)+str(radius-1)+' && distances'+str(radius)+str(radius-1)+' < minDistance) {')
print('bestDir=Direction.SOUTH;')
print('minDistance=distances'+str(radius)+str(radius-1)+';')
print('}')
print('if (validEdges3'+str(radius)+str(radius)+' && distances'+str(radius)+str(radius+1)+' < minDistance) {')
print('bestDir=Direction.NORTH;')
print('minDistance=distances'+str(radius)+str(radius+1)+';')
print('}')

if(debug): print('System.out.println("Best movement direction: " + bestDir);')
if(debug): print('System.out.println("Minimum distance: " + minDistance);')
if(debug): print('System.out.println("Current distance: " + distances' + str(radius) + str(radius) + ');')

print('if (minDistance == distances' + str(radius) + str(radius) + ') return;')
if(debug): print('System.out.println("Total bytecodes used: " + (Clock.getBytecodeNum() - initialBytecodeCount));')
print('if (bestDir != null && rc.canMove(bestDir)) rc.move(bestDir);')
print('}')

print('}')
