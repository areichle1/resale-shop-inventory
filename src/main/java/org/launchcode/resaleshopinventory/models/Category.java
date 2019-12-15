package org.launchcode.resaleshopinventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=30)
    private String name;

    @ManyToOne
    private User user;

    @OneToMany
    @JoinColumn(name = "category_id")
    private List<Item> items = new ArrayList<>();

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() { return items; }

    public void setItems(List<Item> items) { this.items = items; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
