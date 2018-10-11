#!/bin/bash


TYPE="explicit";

[ -e data/time/averageTimes.txt ] && rm data/time/averageTimes.txt
echo $TYPE >> data/time/averageTimes.txt
for VARIABLE in 4
do
java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar data/inst/knap_$VARIABLE.inst.dat $TYPE true 10;


done