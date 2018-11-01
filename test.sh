#!/bin/bash



for VARIABLE in 20
do
java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar data/inst/knap_$VARIABLE.inst.dat dynamic_weight false 1 > data/test/test_output_$VARIABLE;

done