package org.launchcode.resaleshopinventory.models;

//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//public class User {
//
//    public static User current_user;
//
//    @Id
//    @GeneratedValue
//    private int id;
//
//    @NotNull
//    @Size(min = 5, max = 15, message = "Please enter a username. Username must not be blank.")
//    private String username;
//
//    @NotNull
//    @Size(min = 1, message = "Please enter a password. Password must not be blank.")
//    private String password;
//
//    @Email
//    private String email = "";
//
//    //not sure this is how to connect the tables
//    //
////    @ManyToOne
////    @JoinColumn(name = "login_id")
////    private Login login;
//    //
//
//    @OneToMany
//    @JoinColumn(name = "user_id")
//    private List<Item> items = new ArrayList<>();
//
//    public User() {
//    }
//
//    public User(String username, String email, String password) {
//        this.username = username;
//        this.email = email;
//        this.password = password;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getUsername() { return username; }
//
//    public void setUsername(String username) { this.username = username; }
//
//    public String getPassword() { return password; }
//
//    public void setPassword(String password) { this.password = password; }
//
//    public String getEmail() { return email; }
//
//    public void setEmail(String email) { this.email = email; }
//
//    public List<Item> getItems() { return items; }
//
//    public void setItems(List<Item> items) { this.items = items; }
//}

//Below here is from chris, above was mine from the beginning
//import org.dom4j.tree.AbstractEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import java.util.List;
//
//@Entity
//public class User extends AbstractEntity {
//
//    @NotNull
//    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9_-]{4,11}", message = "Invalid username")
//    private String username;
//
//    @NotNull
//    private String pwHash;
//    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//    public User() {}
//
//    public User(String username, String password) {
//        this.username = username;
//        this.pwHash = hashPassword(password);
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    private static String hashPassword(String password) {
//        return encoder.encode(password);
//    }
//
//    public boolean isMatchingPassword(String password) {
//        return encoder.matches(password, pwHash);
//    }
//
//    public String getUid() {
//        return getUid();
//    }
//}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String email;

    @NotNull
    private String fullName;

    @NotNull
    private String password;

    @NotNull
    private Boolean enabled = true;

    public User() {}

    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public List<String> getRoles() {
        ArrayList<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        return roles;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User user = (User) obj;
        if (!email.equals(user.email)) {
            return false;
        }
        return true;
    }
}
