#!/bin/bash


TYPE="fptas";

[ -e data/time/averageTimes.txt ] && rm data/time/averageTimes.txt
echo $TYPE >> data/time/averageTimes.txt
for VARIABLE in  4 10 15 20 22 25 27 30 32 35 37 40
do
java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar data/inst/knap_$VARIABLE.inst.dat $TYPE true 10 0.9;


done