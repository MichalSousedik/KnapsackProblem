import java.util.ArrayList;
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

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }
}
