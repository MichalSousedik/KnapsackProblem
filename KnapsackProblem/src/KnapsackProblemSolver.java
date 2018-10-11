import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class KnapsackProblemSolver {

    static KnapsackProblemSolution findSolutionExplicitly(Knapsack knapsack) {
        List<Item> items = knapsack.getItems();
        int[] best = new int[items.size()];
        int maxValue = 0;
        for (long i = 1; i < Math.pow(2, items.size()); i++) {
            int[] tmpBest = new int[items.size()];
            int currentValue = 0;
            int currentWeight = 0;
            for (int j = 0; j < items.size(); j++) {
                if ((i & (long) Math.pow(2, j)) > 0) {
                    // Include j in set
                    tmpBest[j] = 1;
                    currentWeight += items.get(j).getWeight();
                    if (currentWeight > knapsack.getMaxWeight()) {
                        currentValue = 0;
                        break;
                    }
                    currentValue += items.get(j).getValue();
                }
            }
            if (maxValue < currentValue) {
                best = tmpBest;
                maxValue = currentValue;
            }
        }
        return new KnapsackProblemSolution(knapsack, maxValue, best);
    }

    static KnapsackProblemSolution findSolutionHeuristicRatio(Knapsack knapsack) {
        List<Pair> pairs = new ArrayList<>();
        int i = 0;
        for (Item item : knapsack.getItems()) {
            pairs.add(new Pair(i++, (double) item.getValue() / item.getWeight()));
        }
        return findSolutionHeuristic(knapsack, pairs, Comparator.comparingDouble(Pair::getValue).reversed());
    }

    static KnapsackProblemSolution findSolutionHeuristicValue(Knapsack knapsack) {
        List<Pair> pairs = new ArrayList<>();
        int i = 0;
        for (Item item : knapsack.getItems()) {
            pairs.add(new Pair(i++, item.getValue()));
        }
        return findSolutionHeuristic(knapsack, pairs, Comparator.comparingDouble(Pair::getValue).reversed());
    }

    static KnapsackProblemSolution findSolutionHeuristicWeight(Knapsack knapsack) {
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
