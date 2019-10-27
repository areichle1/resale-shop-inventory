package org.launchcode.resaleshopinventory.models;

public class Item {

    private String name;
    private String description;
    private int itemId;
    private static int nextId = 1;

    public Item(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public Item() {
        itemId = nextId;
        nextId++;
    }

    public int getItemId() { return itemId; }

    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
