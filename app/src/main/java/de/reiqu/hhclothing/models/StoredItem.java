package de.reiqu.hhclothing.models;

/**
 * Stored Item class
 */
public class StoredItem {

    private final int id;
    private int amount;

    public StoredItem() {
        this(1, 2 );
    }

    /**
     * @param id id of item
     * @param amount amount of item
     */
    public StoredItem(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    // GETTER & SETTER
}
