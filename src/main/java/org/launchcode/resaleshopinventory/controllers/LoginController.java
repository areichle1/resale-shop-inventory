package org.launchcode.resaleshopinventory.controllers;

import org.launchcode.resaleshopinventory.models.Item;
import org.launchcode.resaleshopinventory.models.User;
import org.launchcode.resaleshopinventory.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value= "login")
public class LoginController {

    @Autowired
    private UserDao userDao;

    public String current_user;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayLoginForm(Model model) {
        model.addAttribute("username", "New User");
        model.addAttribute("password", "Test Password");
        model.addAttribute("user", new User());
        return "user/login";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processLoginForm(Model model, @RequestParam String username,
                                   @RequestParam String password) {
        System.out.println(username);
        System.out.println(password);
        //System.out.println("Username is : " + new_user.getUsername());

        for (User user : userDao.findAll()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                List<Item> items = user.getItems();
                //i think use this "items" on the next page it links to?

                //when this works link to item/index and pass in the userId
                model.addAttribute("user", "Welcome back, " + user.getUsername());
                return "items/index";
            }
        }
        model.addAttribute("error_message", "invalid username or password");
        return "user/login";
    }

//    public loginfunc: {
//        // pull user out of the database by login username
//        // compare user's password with database password in that row
//        if above is true {
//            current_user = user_row.name;
//        }
//    }

    public String logout() {
        //current_user = Null;
        //is this right?
        return "user/login";
    }
}
