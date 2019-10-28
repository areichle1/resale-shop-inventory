package org.launchcode.resaleshopinventory.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Item {

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @NotNull
    @Size(min=1, message = "Must enter a category")
    private String category;
    
    private int itemId;
    private static int nextId = 1;

    public Item(String name, String category) {
        this();
        this.name = name;
        this.category = category;
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

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }
}
