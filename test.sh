#!/bin/bash

#for i in maptestsmall circle quadrants Andromeda Arena Bog Branches Chevron Corridor CrossStitch CrownJewels ExesAndOhs FiveOfHearts Gridlock Illusion NotAPuzzle Rainbow SlowMusic Snowflake
for i in Andromeda Arena Bog Branches Chevron Corridor CrossStitch CrownJewels ExesAndOhs FiveOfHearts Gridlock Illusion NotAPuzzle Rainbow SlowMusic Snowflake
do
  echo "Running map $i"
  ./gradlew run -PteamA=oldSprint2 -PteamB=sprint2 -Pmaps=$i -PprofilerEnabled=false >> log.log
  ./gradlew run -PteamA=sprint2 -PteamB=oldSprint2 -Pmaps=$i -PprofilerEnabled=false >> log.log
done
