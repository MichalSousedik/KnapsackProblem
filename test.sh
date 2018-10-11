#!/bin/bash



for VARIABLE in 4 10 
do
java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar data/inst/knap_$VARIABLE.inst.dat heuristic_weight false 1 > data/test/test_output_$VARIABLE;

done