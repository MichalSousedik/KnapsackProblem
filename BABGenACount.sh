#!/bin/bash

[ -e ins.dat ] && rm ins.dat
[ -e ins.dat ] && rm data/time/averageTimes.txt

TYPE="bab";
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
for RATIO in  0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9
do
echo -e '\n'$RATIO >> $OUTPUTFILE
./knapgen/a.out -n $n -N $N -W $W -C $C -m $RATIO -k $k  -d $d -s $sort >> ins.dat

java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat $TYPE true 10 0;

done

for VALUE in  5 10 15 20
do
echo -e '\n Poměr \n'$VALUE >> $OUTPUTFILE;
echo -e 'asc' >> $OUTPUTFILE;
./knapgen/a.out -n $VALUE -N $N -W $W -C $C -m $m -k $k  -d $d -s 1 >> ins.dat
java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat $TYPE true 10 0;

echo -e 'random' >> $OUTPUTFILE;
./knapgen/a.out -n $VALUE -N $N -W $W -C $C -m $m -k $k  -d $d -s 0 >> ins.dat
java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat $TYPE true 10 0;

echo -e '\ndesc' >> $OUTPUTFILE;
./knapgen/a.out -n $VALUE -N $N -W $W -C $C -m $m -k $k  -d $d -s -1 >> ins.dat
java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat $TYPE true 10 0;

done

echo -e '\n Granularita d = 1'
for KD in 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9
do
echo -e '\n'$KD >> $OUTPUTFILE;
./knapgen/a.out -n $n -N $N -W $W -C $C -m $m -k $KD  -d 1 -s $sort >> ins.dat
java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat $TYPE true 10 0;
done

echo -e '\n Granularita d = -1'
for KD in 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9
do
echo -e '\n'$KD >> $OUTPUTFILE;
./knapgen/a.out -n $n -N $N -W $W -C $C -m $m -k $KD  -d -1 -s $sort >> ins.dat
java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat $TYPE true 10 0;
done

