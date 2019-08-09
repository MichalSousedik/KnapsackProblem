import java.util.List;

public class Knapsack {

    private int id;
    private int maxWeight;
    private List<Item> items;

    public Knapsack(int id, int maxWeight, List<Item> items) {
        this.id = id;
        this.maxWeight = maxWeight;
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

}
