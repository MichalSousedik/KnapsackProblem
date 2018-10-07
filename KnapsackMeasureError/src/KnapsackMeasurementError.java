import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class KnapsackMeasurementError {

    private static final int VALUE = 2;

//        args[0] -- my solution file
//        args[1] -- correct solution file
    public static void main(String[] args) throws IOException {

        if (args.length > 1) {
            String solutionFile = args[0];
            String correctSolutionFile = args[1];
            List<Integer> approxValues = readSolutionFile(solutionFile);
            List<Integer> correctValues = readSolutionFile(correctSolutionFile);
            List<Double> measureErrors = measureSolutionErrors(approxValues, correctValues);
            Optional<Double> max = measureErrors.stream().max(Double::compareTo);
            if(max.isPresent()){
                writeToFile("max", max.get(), solutionFile);
            }
            OptionalDouble average = measureErrors.stream()
                    .mapToDouble(a -> a)
                    .average();
            if(average.isPresent()){
                writeToFile("average", average.getAsDouble(), solutionFile);
            }
        }
    }

    private static void writeToFile(String type, Double value, String solutionFile) throws IOException {
        String time = String.format ("%f", value);
        FileWriter fw = new FileWriter("data/error/measureError.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.newLine();
        bw.write(solutionFile + " -- " + type + ": " + value);
        bw.close();
    }

    private static List<Double> measureSolutionErrors(List<Integer> approxValues, List<Integer> correctValues) {
        List<Double> measureErrors = new ArrayList<>();
        int i = 0;
        for(Integer approxValue: approxValues){
            Integer correctValue = correctValues.get(i++);
            measureErrors.add((double)(correctValue - approxValue) / correctValue);
        }
        return measureErrors;
    }

    private static List<Integer> readSolutionFile(String solutionFile) {
        List<Integer> knapsackSolutions = new ArrayList<>();
        try {
            File file = new File(solutionFile);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] blocks = line.split(" ");
                String valueBlock = blocks[VALUE];
                knapsackSolutions.add(Integer.parseInt(valueBlock));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be processed.");
        }
        return knapsackSolutions;
    }


}
