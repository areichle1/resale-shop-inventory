package org.launchcode.resaleshopinventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Store store;

    @ManyToMany(mappedBy = "items")
    private List<Store> stores;

    public Item(String name, String category, String store) {
        this();
        this.name = name;
    }

    public Item() { }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    public Store getStore() { return store; }

    public void setStore(Store store) { this.store = store; }
}
