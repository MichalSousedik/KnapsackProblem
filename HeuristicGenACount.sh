#!/bin/bash

[ -e ins.dat ] && rm ins.dat
[ -e data/time/averageTimes.txt ] && rm data/time/averageTimes.txt

TYPE="heuristic_ratio";
OUTPUTFILE="data/time/averageTimes.txt";
OUTPUTERRORFILE="data/error/measureError.txt";
N=20;
n=20;
W=300;
C=300;
m=0.5;
k=1;
d=0;
sort=0;

echo $TYPE > $OUTPUTFILE

#echo "time:" >> $OUTPUTFILE
#for VALUE in 10 20 30 40 50 60 70 80 90
#do
#echo -e '\n'$VALUE >> $OUTPUTFILE
#    ./knapgen/a.out -n $VALUE -N $N -W $W -C $C -m $m -k $k  -d $d -s $sort >> ins.dat
#
#    java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat $TYPE true 10 0;
#done
#
#
#
#echo -e "\nerror:" >> $OUTPUTERRORFILE
#echo "maximální váha:" >> $OUTPUTERRORFILE
#for MAXWEIGHT in 30 60 90 120 150 180 210 240 270 300 330 360
#do
#echo -e '\n'$MAXWEIGHT >> $OUTPUTERRORFILE
#./knapgen/a.out -n $n -N $N -W $MAXWEIGHT -C $C -m $m -k $k  -d $d -s $sort > ins.dat
#
#
#java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat 'bab' false 1 0 > data/test/test_output_correct;
#
#java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat $TYPE false 1 0 > data/test/test_output;
#
#java -jar KnapsackMeasureError/out/artifacts/KnapsackMeasureError_jar/KnapsackMeasureError.jar data/test/test_output data/test/test_output_correct average
#
#rm data/test/*
#rm ins.dat
#done
#
##rm data/test/*
##
#echo -e "\nmaximální cena:" >> $OUTPUTERRORFILE
#for MAXVALUE in 30 60 90 120 150 180 210 240 270 300 330 360
#do
#echo -e '\n'$MAXVALUE >> $OUTPUTERRORFILE
#./knapgen/a.out -n $n -N $N -W $W -C $MAXVALUE -m $m -k $k  -d $d -s $sort > ins.dat
#
#
#java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat 'bab' false 1 0 > data/test/test_output_correct;
#
#java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat $TYPE false 1 0 > data/test/test_output;
#
#java -jar KnapsackMeasureError/out/artifacts/KnapsackMeasureError_jar/KnapsackMeasureError.jar data/test/test_output data/test/test_output_correct average
#
#rm data/test/*
#rm ins.dat
#done
#
#
#echo -e "\nPoměr nosnost váha:" >> $OUTPUTERRORFILE
#for RATIO in 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9
#do
#echo -e '\n'$RATIO >> $OUTPUTERRORFILE
#./knapgen/a.out -n $n -N $N -W $W -C $C -m $RATIO -k $k  -d $d -s $sort > ins.dat
#
#
#java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat 'bab' false 1 0 > data/test/test_output_correct;
#
#java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat $TYPE false 1 0 > data/test/test_output;
#
#java -jar KnapsackMeasureError/out/artifacts/KnapsackMeasureError_jar/KnapsackMeasureError.jar data/test/test_output data/test/test_output_correct average
#
#rm data/test/*
#rm ins.dat
#
#done
#
echo -e "\nGranularita: vice velkych veci" >> $OUTPUTERRORFILE
for KR in 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9 1
do
echo -e '\n'$KR >> $OUTPUTERRORFILE
./knapgen/a.out -n $n -N $N -W $W -C $C -m $m -k $KR  -d 1 -s $sort > ins.dat


java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat 'bab' false 1 0 > data/test/test_output_correct;

java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat $TYPE false 1 0 > data/test/test_output;

java -jar KnapsackMeasureError/out/artifacts/KnapsackMeasureError_jar/KnapsackMeasureError.jar data/test/test_output data/test/test_output_correct average


rm data/test/*
rm ins.dat
done
#
#echo -e "\nGranularita: vice malych veci" >> $OUTPUTERRORFILE
#for KR in 0.1 0.2 0.3 0.4 0.5 0.6 0.7 0.8 0.9
#do
#echo -e '\n'$KR >> $OUTPUTERRORFILE
#./knapgen/a.out -n $n -N $N -W $W -C $C -m $m -k $KR  -d -1 -s $sort > ins.dat
#
#
#java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat 'bab' false 1 0 > data/test/test_output_correct;
#
#java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar ins.dat $TYPE false 1 0 > data/test/test_output;
#
#java -jar KnapsackMeasureError/out/artifacts/KnapsackMeasureError_jar/KnapsackMeasureError.jar data/test/test_output data/test/test_output_correct average
#
#
#rm data/test/*
#rm ins.dat
#done
#
#
