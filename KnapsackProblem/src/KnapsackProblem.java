

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
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

//            String fileName = "knap_40.inst.dat";
//            String algorithm = "ga";
//            Boolean countTime = false;
//            int numberOfIterations = 1;



            if (fileName != null) {
                List<Knapsack> knapsacks = readFile(fileName);
                if (countTime) {
                    CPUTimer cpuTimer = new CPUTimer();
                    for (int i = 0; i < numberOfIterations; i++) {
                        cpuTimer.start();
                        startAlgorithm(knapsacks, algorithm, countTime, createParameters(args));
                        cpuTimer.stop();
                    }
                    cpuTimer.writeAverageTime();
                }
                else
                    startAlgorithm(knapsacks, algorithm, countTime, createParameters(args));
            }
        }
    }

    private static Map<String, String> createParameters(String[] args){
        Map<String, String> parameters = new HashMap<>();
        if(args.length > 4 && args[1].equals(Constant.EPSILON)){
            parameters.put(Constant.EPSILON, args[4]);
        }
        else if(args.length > 9 && args[1].equals(Constant.GA)){
            parameters.put(Constant.GENERATIONS, args[4]);
            parameters.put(Constant.POPULATION, args[5]);
            parameters.put(Constant.TOURNAMENT_SIZE, args[6]);
            parameters.put(Constant.CROSSOVER_PROBABILITY, args[7]);
            parameters.put(Constant.MUTATION_RATE, args[8]);
            parameters.put(Constant.ELITISM, args[9]);

//        parameters.put(Constant.GENERATIONS, "100");
//        parameters.put(Constant.POPULATION, "3");
//        parameters.put(Constant.TOURNAMENT_SIZE, "10");
//        parameters.put(Constant.CROSSOVER_PROBABILITY, "0.8");
//        parameters.put(Constant.MUTATION_RATE, "0.1");
//        parameters.put(Constant.ELITISM, "2");
        }
        return parameters;
    }

    private static void startAlgorithm(List<Knapsack> knapsacks, String algorithm, Boolean countTime, Map<String, String> parameters){
        for (Knapsack knapsack : knapsacks) {
            printSolution(KnapsackProblemSolverFactory.getSolver(algorithm, parameters).solve(knapsack).toString(), countTime);
        }
    }


    private static void printSolution(String solution, Boolean countTime) {
//        if (!countTime)
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
