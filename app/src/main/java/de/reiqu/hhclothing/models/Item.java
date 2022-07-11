package de.reiqu.hhclothing.models;

/**
 * Item class
 */
public class Item {
    private final int id;
    private String name;
    private String description;

    public Item() {
        this(1, "Test", "Testgegenstand" );
    }

    /**
     * @param id id of item
     * @param name name of item
     * @param description description of item
     */
    public Item(int id, String name, String description) {
        this.id = id;
        this.description = description;
        this.name = name;
    }



    // GETTER & SETTER

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
