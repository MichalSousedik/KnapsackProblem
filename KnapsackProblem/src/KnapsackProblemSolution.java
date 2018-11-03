public class KnapsackProblemSolution {

        private Knapsack knapsack;
        private int maxValue;
        private int[]bestCombination;

    KnapsackProblemSolution(Knapsack knapsack, int maxValue, int[] bestCombination) {
        this.knapsack = knapsack;
        this.maxValue = maxValue;
        this.bestCombination = bestCombination;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(knapsack.getId() + " " + knapsack.getItems().size() + " " + maxValue + "  ");
        for(int i=0; i<bestCombination.length; i++) {
            s.append(bestCombination[i]);
            if(i != bestCombination.length-1)
                s.append(" ");
            else
                s.append("\n");
        }
        return s.toString();
    }

    int getMaxValue() {
        return maxValue;
    }

    void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

}
