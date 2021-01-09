#!/bin/bash
mkdir -p $2
for file in $(ls $1)
do
    sed "s/package $1/package $2/" $1/$file > $2/$file
done

	    
