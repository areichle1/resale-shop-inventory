package org.launchcode.resaleshopinventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Store {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=15)
    private String name;

//    @ManyToMany
//    private List<Item> items;

    @OneToMany
    @JoinColumn(name = "store_id")
    private List<Item> items = new ArrayList<>();

    public void addIndItem(Item indItem) {
        items.add(indItem);
    }

    public Store() {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }
}
