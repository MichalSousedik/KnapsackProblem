#!/bin/bash



for VARIABLE in 4
do
java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar data/inst/knap_$VARIABLE.inst.dat fptas false 1 0.1 > data/test/test_output_$VARIABLE;

done