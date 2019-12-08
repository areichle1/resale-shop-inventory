package org.launchcode.resaleshopinventory.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 5, max = 15, message = "Please enter a username. Username must not be blank.")
    private String username;

    @NotNull
    @Size(min = 1, message = "Please enter a password. Password must not be blank.")
    private String password;

    @Email
    private String email = "";

    //not sure this is how to connect the tables
    //
//    @ManyToOne
//    @JoinColumn(name = "login_id")
//    private Login login;
    //

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Item> items = new ArrayList<>();

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public List<Item> getItems() { return items; }

    public void setItems(List<Item> items) { this.items = items; }
}
