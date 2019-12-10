package org.launchcode.resaleshopinventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Item {
    //extends AbstractEntity

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
    //
    @ManyToOne
    private User user;
//
    //this below is never used but was in the last commit
    @ManyToMany(mappedBy = "items")
    private List<Store> stores;

//    @ManyToMany(mappedBy = "items")
//    private List<Category> categories;

    public Item(String name, String category, String store) {
        this();
        this.name = name;
//        this.category = category;
//        this.store = store;
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

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
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
