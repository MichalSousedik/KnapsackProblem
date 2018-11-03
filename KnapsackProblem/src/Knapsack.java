import java.util.List;

class Knapsack {

    private int id;
    private int maxWeight;
    private List<Item> items;

    Knapsack(int id, int maxWeight, List<Item> items) {
        this.id = id;
        this.maxWeight = maxWeight;
        this.items = items;
    }

    List<Item> getItems() {
        return items;
    }

    int getId() {
        return id;
    }

    int getMaxWeight() {
        return maxWeight;
    }

}
