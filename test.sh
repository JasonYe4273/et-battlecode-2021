#!/bin/bash

team1=quals
team2=oldquals

for i in maptestsmall circle quadrants Andromeda Arena Bog Branches Chevron Corridor Cow CrossStitch CrownJewels ExesAndOhs FiveOfHearts Gridlock Illusion NotAPuzzle Rainbow SlowMusic Snowflake BadSnowflake CringyAsF FindYourWay GetShrekt Goldfish HexesAndOhms Licc MainCampus Punctuation Radial SeaFloor Sediment Smile SpaceInvaders Surprised VideoGames
do
  echo "Running map $i"
  ./gradlew run -PteamA=$team1 -PteamB=$team2 -Pmaps=$i -PprofilerEnabled=false >> log.log
  ./gradlew run -PteamA=$team2 -PteamB=$team1 -Pmaps=$i -PprofilerEnabled=false >> log.log
done
