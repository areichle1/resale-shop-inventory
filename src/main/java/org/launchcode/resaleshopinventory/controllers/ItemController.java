package org.launchcode.resaleshopinventory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("item")
public class ItemController {

    static ArrayList<String> items = new ArrayList<>();

    // Request path: /item
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("items", items);
        model.addAttribute("title", "My Items");
        return "item/index";
    }

    // Request path: item/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddItemForm(Model model) {

        model.addAttribute("title", "Add Item");
        return "item/add";
    }

    // Request path: item/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddItemForm(@RequestParam String itemName) {

        items.add(itemName);

        //Redirect to item/
        return "redirect:";
    }

}
