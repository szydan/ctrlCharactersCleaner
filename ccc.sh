#!/bin/bash

# copy list of files taken from the list.txt from input dir to output dir
# usage
# ./ccc.sh list.txt inputFolder outputFolder

IDIR=$2
ODIR=$3
while read FILENAME
do
    echo "copy from $IDIR/$FILENAME to $ODIR"
    cp $IDIR/$FILENAME $ODIR   
done < $1
