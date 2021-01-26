#!/bin/bash

currentBranch=$(git symbolic-ref --short HEAD)
git checkout $1 >/dev/null
./a.sh finals $1
cp -r src/$1 tmp
rm -r src/$1
git checkout $2 >/dev/null
./a.sh finals $2
cp -r tmp/$1 src
sed "s/teamA=.\+/teamA=$1/" gradle.properties | sed "s/teamB=.\+/teamB=$2/" > aaa.txt
mv aaa.txt gradle.properties 
./gradlew run >> log.log
sed "s/teamA=.\+/teamA=$2/" gradle.properties | sed "s/teamB=.\+/teamB=$1/" > aaa.txt
mv aaa.txt gradle.properties 
./gradlew run >> log.log
rm -r src/$1
rm -r src/$2
git checkout $currentBranch
