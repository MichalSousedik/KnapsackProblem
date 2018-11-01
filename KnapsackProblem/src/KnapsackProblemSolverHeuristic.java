import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KnapsackProblemSolverHeuristic implements KnapsackProblemSolver {

    private String algorithm;

    KnapsackProblemSolverHeuristic(String algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public KnapsackProblemSolution solve(Knapsack knapsack) {
        switch (algorithm) {
            case "heuristic_ratio":
                return findSolutionHeuristicRatio(knapsack);
            case "heuristic_value":
                return findSolutionHeuristicValue(knapsack);
            case "heuristic_weight":
                return findSolutionHeuristicWeight(knapsack);
            default:
                return findSolutionHeuristicRatio(knapsack);
        }
    }


    private KnapsackProblemSolution findSolutionHeuristicRatio(Knapsack knapsack) {
        List<Pair> pairs = new ArrayList<>();
        int i = 0;
        for (Item item : knapsack.getItems()) {
            pairs.add(new Pair(i++, (double) item.getValue() / item.getWeight()));
        }
        return findSolutionHeuristic(knapsack, pairs, Comparator.comparingDouble(Pair::getValue).reversed());
    }

    private KnapsackProblemSolution findSolutionHeuristicValue(Knapsack knapsack) {
        List<Pair> pairs = new ArrayList<>();
        int i = 0;
        for (Item item : knapsack.getItems()) {
            pairs.add(new Pair(i++, item.getValue()));
        }
        return findSolutionHeuristic(knapsack, pairs, Comparator.comparingDouble(Pair::getValue).reversed());
    }

    private KnapsackProblemSolution findSolutionHeuristicWeight(Knapsack knapsack) {
        List<Pair> pairs = new ArrayList<>();
        int i = 0;
        for (Item item : knapsack.getItems()) {
            pairs.add(new Pair(i++, item.getWeight()));
        }
        return findSolutionHeuristic(knapsack, pairs, Comparator.comparingDouble(Pair::getValue));
    }

    private static KnapsackProblemSolution findSolutionHeuristic(Knapsack knapsack, List<Pair> pairs, Comparator<Pair> reversed) {
        pairs.sort(reversed);
        int maxValue = 0;
        int currentWeight = 0;
        int[] best = new int[knapsack.getItems().size()];
        for (Pair pair : pairs) {
            Item item = knapsack.getItems().get(pair.getPos());
            if (currentWeight + item.getWeight() > knapsack.getMaxWeight()) continue;
            maxValue += item.getValue();
            currentWeight += item.getWeight();
            best[pair.getPos()] = 1;
            if (currentWeight == knapsack.getMaxWeight())
                break;
        }
        return new KnapsackProblemSolution(knapsack, maxValue, best);
    }

}
