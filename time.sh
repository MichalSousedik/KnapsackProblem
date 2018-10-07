#!/bin/bash


[ -e file ] && rm data/time/averageTimes.txt
for VARIABLE in 4 10 15
do

java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar data/inst/knap_$VARIABLE.inst.dat explicit true 10;
java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar data/inst/knap_$VARIABLE.inst.dat heuristic true 10;


done