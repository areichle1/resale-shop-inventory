package org.launchcode.resaleshopinventory.controllers;

import org.launchcode.resaleshopinventory.services.ItemService;
import org.launchcode.resaleshopinventory.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.security.Principal;

@Controller
public class HomeController extends AbstractBaseController {

    @Autowired
    ItemService itemService;

    //shown when user logs in for their first session
    @RequestMapping(value = "/welcome")
    public String welcome(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute(MESSAGE_KEY, "success|Welcome, "+ user.getFullName());
        model.addAttribute("items", itemService.findUserItems(user));

        return "index";
    }

    @RequestMapping(value = "/")
    public String index(Principal principal, Model model) {

        User user = userService.findByEmail(principal.getName());
        model.addAttribute("items", itemService.findUserItems(user));

        return "index";
    }

}
