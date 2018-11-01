public class KnapsackProblemSolverBAB implements KnapsackProblemSolver {

    private int[] bestSolution;
    private int bestPrice = 0;

    @Override
    public KnapsackProblemSolution solve(Knapsack knapsack) {
        int itemIndex = knapsack.getItems().size() - 1;
        int currentPrice = 0;
        int currentWeight = 0;
        int maxPrice = knapsack.getItems().stream().mapToInt(Item::getValue).sum();
        int[] itemSelection = new int[knapsack.getItems().size()];
        addItem(itemIndex, currentPrice, currentWeight, maxPrice, itemSelection, knapsack);
        return new KnapsackProblemSolution(knapsack, bestPrice, bestSolution);
    }

    private void addItem(int itemIndex, int currentValue, int currentWeight, int maxValue, int[] itemSelection, Knapsack knapsack) {
        if (itemIndex == -1) {
            if (currentValue >= bestPrice) {
                bestPrice = currentValue;
                bestSolution = itemSelection;
            }
            return;
        }

        if (maxValue + currentValue < bestPrice)
            return;

        Item item = knapsack.getItems().get(itemIndex);

        if (currentWeight + item.getWeight() <= knapsack.getMaxWeight()) {
            itemSelection[itemIndex] = 1;
            addItem(itemIndex - 1, currentValue + item.getValue(), currentWeight + item.getWeight(), maxValue - item.getValue(), itemSelection.clone(), knapsack);
        }

        itemSelection[itemIndex] = 0;
        addItem(itemIndex - 1, currentValue, currentWeight, maxValue - item.getValue(), itemSelection.clone(), knapsack);

    }
}
