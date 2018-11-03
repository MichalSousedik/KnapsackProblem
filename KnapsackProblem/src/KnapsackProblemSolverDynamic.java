public class KnapsackProblemSolverDynamic implements KnapsackProblemSolver {

    private KnapsackCell[][] table;
    private String algorithm;

    class KnapsackCell {
        int interest;
        int[] itemSelection;

        KnapsackCell(int interest, int[] itemSelection) {
            this.interest = interest;
            this.itemSelection = itemSelection;
        }
    }

    KnapsackProblemSolverDynamic() {
    }

    KnapsackProblemSolverDynamic(String algorithm) {
        this.algorithm = algorithm;
    }

    private int getItemsTotalValue(Knapsack knapsack) {
        return knapsack.getItems().stream().mapToInt(Item::getValue).sum();
    }

    @Override
    public KnapsackProblemSolution solve(Knapsack knapsack) {

        switch (this.algorithm) {
            case "dynamic_value":
                return solveValuesBottomTop(knapsack);
            case "dynamic_weight":
                return solveWeightsBottomTop(knapsack);
            default:
                return null;
        }
    }

    private KnapsackProblemSolution solveWeightsBottomTop(Knapsack knapsack) {

        int itemsNumber = knapsack.getItems().size();

        int maxWeight = knapsack.getMaxWeight();

        this.table = new KnapsackCell[maxWeight + 1][itemsNumber + 1];

        for (int weight = 0; weight <= maxWeight; weight++) {
            for (int itemNumber = 0; itemNumber <= itemsNumber; itemNumber++) {

                if (weight == 0 || itemNumber == 0) {
                    table[weight][itemNumber] = new KnapsackCell(0, new int[itemsNumber]);
                    continue;
                }

                Item selectedItem = knapsack.getItems().get(itemNumber - 1);
                KnapsackCell previousCell = table[weight][itemNumber - 1];
                int indexOfRowWithoutItemWeight = weight - selectedItem.getWeight();
                if (selectedItem.getWeight() <= weight) {
                    int withItem = selectedItem.getValue() + table[indexOfRowWithoutItemWeight][itemNumber - 1].interest;
                    if (withItem > previousCell.interest) {
                        int[] solution = table[indexOfRowWithoutItemWeight][itemNumber - 1].itemSelection.clone();
                        solution[itemNumber - 1] = 1;
                        table[weight][itemNumber] = new KnapsackCell(withItem, solution);
                    } else {
                        table[weight][itemNumber] = new KnapsackCell(previousCell.interest, previousCell.itemSelection.clone());
                    }
                } else {
                    table[weight][itemNumber] = new KnapsackCell(previousCell.interest, previousCell.itemSelection.clone());
                }
            }
        }


        KnapsackCell knapsackCell = table[maxWeight][itemsNumber];
        return new KnapsackProblemSolution(knapsack, knapsackCell.interest, knapsackCell.itemSelection);

    }


    KnapsackProblemSolution solveValuesBottomTop(Knapsack knapsack) {

        int totalValue = getItemsTotalValue(knapsack);
        int itemsNumber = knapsack.getItems().size();

        this.table = new KnapsackCell[totalValue + 1][knapsack.getItems().size() + 1];
        for (int value = 0; value <= totalValue; ++value) {
            for (int itemNumber = 0; itemNumber <= itemsNumber; ++itemNumber) {

                if (value == 0 || itemNumber == 0) {
                    table[value][itemNumber] = new KnapsackCell(0, new int[itemsNumber]);
                    continue;
                }


                Item selectedItem = knapsack.getItems().get(itemNumber - 1);

                if (itemNumber == 1) {
                    if (value == selectedItem.getValue()) {
                        int[] selection = new int[itemsNumber];
                        selection[itemNumber - 1] = 1;
                        table[value][itemNumber] = new KnapsackCell(selectedItem.getWeight(), selection);
                    } else {
                        table[value][itemNumber] = new KnapsackCell(Integer.MAX_VALUE, new int[itemNumber]);
                    }
                    continue;
                }


                KnapsackCell previousCell = table[value][itemNumber - 1];
                int indexOfRowWithoutItemValue = value - selectedItem.getValue();

                if (indexOfRowWithoutItemValue < 0) {
                    table[value][itemNumber] = new KnapsackCell(previousCell.interest, previousCell.itemSelection.clone());
                } else {
                    KnapsackCell cellWithoutItemValue = table[indexOfRowWithoutItemValue][itemNumber - 1];
                    if (cellWithoutItemValue.interest == Integer.MAX_VALUE) {
                        table[value][itemNumber] = new KnapsackCell(previousCell.interest, previousCell.itemSelection.clone());
                    } else {
                        if (previousCell.interest < (cellWithoutItemValue.interest + selectedItem.getWeight())) {
                            table[value][itemNumber] = new KnapsackCell(previousCell.interest, previousCell.itemSelection.clone());
                        } else {
                            int[] selection = table[indexOfRowWithoutItemValue][itemNumber - 1].itemSelection.clone();
                            selection[itemNumber-1] = 1;
                            table[value][itemNumber] = new KnapsackCell(cellWithoutItemValue.interest + selectedItem.getWeight(), selection);
                        }
                    }
                }
            }
        }

        return getSolutionValue(knapsack, totalValue, itemsNumber);
    }

    private KnapsackProblemSolution getSolutionValue(Knapsack knapsack, int rowCount, int itemsNumber) {
        for (int row = rowCount; row >= 0; row--) {
            if (table[row][itemsNumber].interest <= knapsack.getMaxWeight()) {
                return new KnapsackProblemSolution(knapsack, row, table[row][itemsNumber].itemSelection);
            }
        }
        return new KnapsackProblemSolution(knapsack, 0, new int[itemsNumber]);
    }


}
