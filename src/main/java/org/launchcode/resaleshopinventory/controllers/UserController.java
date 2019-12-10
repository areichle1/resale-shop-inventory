package org.launchcode.resaleshopinventory.controllers;

import org.launchcode.resaleshopinventory.models.Item;
import org.launchcode.resaleshopinventory.models.User;
import org.launchcode.resaleshopinventory.models.data.ItemDao;
import org.launchcode.resaleshopinventory.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value= "user")
public class UserController {

//not sure if I need these
    @Autowired
    UserDao userDao;

    @Autowired
    ItemDao itemDao;

    public String current_user;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(WebRequest request,Model model) {
        model.addAttribute("title", "Add User");
        model.addAttribute("user", new User());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors, @RequestParam  String verify_password,
                      @RequestParam String username, @RequestParam String password, @RequestParam String email) {

        model.addAttribute("user", user);
//add this in once user login works
//        if (errors.hasErrors()) {
//            return "user/add";
//        }
//
//        if (user.getPassword().equals(verify_password)) {
//            user.setEmail(email);
//            user.setUsername(username);
//            user.setPassword(password);
//            userDao.save(user);
//            return "user/index";
//        }

        model.addAttribute("error_message", "Password does not match verify password, Please try again.");
        return "user/add";
    }

    // /user
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayLoginForm(Model model) {
        model.addAttribute("username", "New User");
        model.addAttribute("password", "Test Password");
        model.addAttribute("user", new User());
        return "user/login";
        //user/login
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processLoginForm(Model model, @RequestParam String username,
                                   @RequestParam String password) {

        //add this back in once user login works
//        for (User user : userDao.findAll()) {
//            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
//                List<Item> items = user.getItems();
//                //i think use this "items" on the next page it links to?
//
//                //when this works link to item/index and pass in the userId
//                model.addAttribute("user", "Welcome back, " + user.getUsername());
//                return "user/index";
//                //or item/index
//            }
//        }
//        model.addAttribute("error_message", "invalid username or password");
        return "user/login";
        //user/login
    }

    public String logout() {
        //current_user = Null;
        //is this right?
        return "user/login";
    }
}
