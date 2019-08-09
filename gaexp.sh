#!/bin/bash


[ -e ins.dat ] && rm ins.dat;
[ -e data/time/averageTimes.txt ] && rm data/time/averageTimes.txt;
[ -e data/error/measureError.txt ] && rm data/error/measureError.txt;

TYPE="ga";
OUTPUTFILE="data/time/averageTimes.txt";
COUNT_TIME=true;
INTERATIONS=1;
GENERATION=90;
POPULATION=4;
TOURNAMENT_SIZE=5;
CROSSOVER_PROBABILITY=0.8; #0..1
MUTATION_RATE=0.6; #0..1
ELITISM=16;

java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar data/inst/knap_40.inst.dat 'dynamic_value' false 1 0 > data/test/test_output_correct;

for ELITISM in 2 4 6 8 10 12 14 16 20 22
do

java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar data/inst/knap_40.inst.dat $TYPE $COUNT_TIME $INTERATIONS $GENERATION $POPULATION $TOURNAMENT_SIZE $CROSSOVER_PROBABILITY $MUTATION_RATE $ELITISM > data/test/test_output;

java -jar KnapsackMeasureError/out/artifacts/KnapsackMeasureError_jar/KnapsackMeasureError.jar data/test/test_output data/test/test_output_correct average

done

