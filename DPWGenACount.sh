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

#for VALUE in 5 10 15 20
#do
#echo -e '\n'$VALUE >> $OUTPUTFILE
#for MAXWEIGHT in 500 1000 1500 2000 2500 3000
#do
#echo -e '\n'$MAXWEIGHT >> $OUTPUTFILE
#./knapgen/a.out -n $VALUE -N $N -W $MAXWEIGHT -C $C -m $m -k $k  -d $d -s $sort >> ins.dat
#
#java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat $TYPE true 10 0;
#
#done
#done


for VALUE in 25 30
do
echo -e '\n'$VALUE >> $OUTPUTFILE
for RATIO in 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9
do
echo -e '\n'$RATIO >> $OUTPUTFILE
./knapgen/a.out -n $VALUE -N $N -W $W -C $C -m $RATIO -k $k  -d $d -s $sort >> ins.dat

java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat $TYPE true 10 0;

done
done

