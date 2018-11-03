class Item {

    private int weight;
    private int value;


    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    int getWeight() {
        return weight;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }
}
