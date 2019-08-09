import java.util.Comparator;
import java.util.Map;

public class KnapsackProblemSolverFPTAS extends KnapsackProblemSolverDynamic implements KnapsackProblemSolver {

    private double epsilon;
    private int cMax;
    private int itemsNumber;
    private int shift;

    KnapsackProblemSolverFPTAS(Map<String,String> parameters) {
        this.epsilon = Double.parseDouble(parameters.get(Constant.EPSILON));
    }


    @Override
    public KnapsackProblemSolution solve(Knapsack knapsack) {
        modifyItemsValue(knapsack);
        return modifyBackItemValue(solveValuesBottomTop(knapsack));
    }

    private void modifyItemsValue(Knapsack knapsack) {
        this.itemsNumber = knapsack.getItems().size();
        knapsack.getItems().stream().max(Comparator.comparingInt(Item::getValue)).ifPresent(item -> this.cMax = item.getValue());
        this.shift = bitsToShift();
        knapsack.getItems().forEach(this::modifyItemValue);
    }

    private int bitsToShift() {
        return (int) Math.floor(log2((epsilon * cMax) / this.itemsNumber));
    }

    private void modifyItemValue(Item item) {
        if (this.shift >= 1)
            item.setValue(item.getValue() >> this.shift);
    }

    private KnapsackProblemSolution modifyBackItemValue(KnapsackProblemSolution solveValuesBottomTop) {
        if (this.shift >= 1) {
            int maxValue = solveValuesBottomTop.getMaxValue();
            solveValuesBottomTop.setMaxValue(maxValue << shift);
        }
        return solveValuesBottomTop;
    }

   private double log2(double a)
    {
        return Math.log(a) / Math.log((double) 2);
    }

}
