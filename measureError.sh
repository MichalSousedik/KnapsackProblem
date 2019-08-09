#!/bin/bash



for VARIABLE in 4 10 15 20 22 25 27 30 32 35 37 40
do

java -jar KnapsackProblem/out/artifacts/KnapsackProblem_jar/KnapsackProblem.jar data/inst/knap_$VARIABLE.inst.dat fptas false 1 0.25> data/test/test_output_$VARIABLE;


java -jar KnapsackMeasureError/out/artifacts/KnapsackMeasureError_jar/KnapsackMeasureError.jar data/test/test_output_$VARIABLE data/sol/knap_$VARIABLE.sol.dat max

done

