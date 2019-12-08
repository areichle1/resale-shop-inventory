package org.launchcode.resaleshopinventory.controllers;

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

import javax.validation.Valid;

@Controller
@RequestMapping(value= "user")
public class UserController {
//not sure if I need these
    @Autowired
    UserDao userDao;
//
//    @Autowired
//    ItemDao itemDao;
//
    public String current_user;

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add User");
        model.addAttribute("user", new User());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid User user, Errors errors, @RequestParam  String verify_password,
                      @RequestParam String username, @RequestParam String password, @RequestParam String email) {
        model.addAttribute("user", user);

        if (errors.hasErrors()) {
            return "user/add";
        }

        if (user.getPassword().equals(verify_password)) {
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            userDao.save(user);
            return "user/index";
        }

        model.addAttribute("error_message", "Password does not match verify password, Please try again.");
        return "user/add";
    }
}
