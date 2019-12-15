package org.launchcode.resaleshopinventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=30)
    private String name;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Store store;

    @ManyToOne
    private User user;

    public Item(String name, Category category, Store store) {
        this();
        this.name = name;
        this.category = category;
        this.store = store;
    }

    public Item() { }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
