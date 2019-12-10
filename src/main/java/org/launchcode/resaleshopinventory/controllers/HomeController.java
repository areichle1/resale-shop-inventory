package org.launchcode.resaleshopinventory.controllers;

import org.launchcode.resaleshopinventory.models.Item;
import org.launchcode.resaleshopinventory.models.User;
import org.launchcode.resaleshopinventory.models.data.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController extends AbstractBaseController {

    //this should work but doesn't
//    @Autowired
//    ItemRepository itemRepository;
//
//    @GetMapping(value = "/")
//    public String index(Model model) {
//        List<Item> allItems = itemRepository.findAll();
//        model.addAttribute("items", allItems);
//        return "index";
//    }

//works but just takes you to a welcome screen and cant get past it, i added line 33-34
    @RequestMapping(value = "/welcome")
    public String welcome(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute(MESSAGE_KEY, "success|Welcome, "+ user.getFullName());
//        List<Item> allItems = itemRepository.findAll();
//        model.addAttribute("items", allItems);
        return "index";
    }

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/welcome";
    }

    //@Controller
//public class HomeController {
//
//    @RequestMapping("/")
//    public String home(){
//
//        return "/home";
//    }
//
////    @RequestMapping("/login")
////    public String loginPage() {
////        return "login.html";
////    }
////
////    @RequestMapping("/logout-success")
////    public String logoutPage() {
////        return "logout.html";
////    }
//}
}
