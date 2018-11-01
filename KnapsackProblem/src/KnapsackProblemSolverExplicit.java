import java.util.List;

public class KnapsackProblemSolverExplicit implements KnapsackProblemSolver {
    @Override
    public KnapsackProblemSolution solve(Knapsack knapsack) {
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
}
