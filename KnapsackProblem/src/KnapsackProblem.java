

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class KnapsackProblem {

    private static final int INDEX = 0;
    private static final int ITEMS_COUNT = 1;
    private static final int MAX_WEIGHT = 2;

    //        args[0] -- fileName
//        args[1] -- algorithm
//        args[2] -- count time
//        args[3] -- number of iteration for counting time
    public static void main(String[] args) throws IOException {
        if (args.length > 3) {
            String fileName = args[0];
            String algorithm = args[1];

            Boolean countTime = Boolean.parseBoolean(args[2]);
            int numberOfIterations = Integer.parseInt(args[3]);
            if (fileName != null) {
                List<Knapsack> knapsacks = readFile(fileName);
                if (countTime) {
                    CPUTimer cpuTimer = new CPUTimer();
                    for (int i = 0; i < numberOfIterations; i++) {
                        cpuTimer.start();
                        startAlgorithm(knapsacks, algorithm, countTime);
                        cpuTimer.stop();
                    }
                    cpuTimer.writeAverageTime(fileName, algorithm);
                }
                else
                    startAlgorithm(knapsacks, algorithm, countTime);
            }
        }
    }

    private static void startAlgorithm(List<Knapsack> knapsacks, String algorithm, Boolean countTime){
        for (Knapsack knapsack : knapsacks) {
            switch (algorithm) {
                case "explicit":
                    printSolution(KnapsackProblemSolver.findSolutionExplicitly(knapsack).toString(), countTime);
                    break;
                case "heuristic":
                    printSolution(KnapsackProblemSolver.findSolutionHeuristic(knapsack).toString(), countTime);
                    break;
            }
        }
    }

    private static void printSolution(String solution, Boolean countTime) {
        if (!countTime)
            System.out.print(solution);
    }

    private static List<Knapsack> readFile(String fileName) {
        List<Knapsack> knapsacks = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                int weightIndex = 3;
                int valueIndex = 4;
                String line = sc.nextLine();
                String[] blocks = line.split(" ");
                int[] blocksInt = Stream.of(blocks).mapToInt(Integer::parseInt).toArray();
                int itemsCount = blocksInt[ITEMS_COUNT];
                List<Item> items = new ArrayList<>();
                for (int i = 0; i < itemsCount; i++) {
                    items.add(new Item(blocksInt[weightIndex], blocksInt[valueIndex]));
                    weightIndex += 2;
                    valueIndex += 2;
                }
                knapsacks.add(new Knapsack(blocksInt[INDEX], blocksInt[MAX_WEIGHT], items));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be processed.");
        }
        return knapsacks;
    }
}
