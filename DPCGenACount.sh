#!/bin/bash

[ -e ins.dat ] && rm ins.dat
[ -e ins.dat ] && rm data/time/averageTimes.txt

TYPE="dynamic_value";
OUTPUTFILE="data/time/averageTimes.txt";
N=20;
n=20;
W=300;
C=300;
m=0.5;
k=1;
d=0;
sort=0;

echo $TYPE > $OUTPUTFILE

for VALUE in 5 10 15 20
do
echo -e '\n'$VALUE >> $OUTPUTFILE
    for MAXVALUE in 500 1000 1500 2000 2500 3000
    do
    echo -e '\n'$MAXVALUE >> $OUTPUTFILE
    ./knapgen/a.out -n $VALUE -N $N -W $W -C $MAXVALUE -m $m -k $k  -d $d -s $sort >> ins.dat

    java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat $TYPE true 10 0;

    done
done
