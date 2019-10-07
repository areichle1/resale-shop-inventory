package org.launchcode.resaleshopinventory.controllers;

import org.launchcode.resaleshopinventory.models.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "item")
public class ItemController {

    static ArrayList<Item> items = new ArrayList<>();

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
    public String processAddItemForm(@RequestParam String itemName, @RequestParam String itemDescription) {

        Item newItem = new Item(itemName, itemDescription);
        items.add(newItem);

        //Redirect to item/
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveItemForm(Model model) {
        model.addAttribute("items", items);
        model.addAttribute("title", "Remove Item");
        return "item/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveItemForm(@RequestParam ArrayList<String> item) {
        for (String anItem : item) {
            items.remove(anItem);
        }
        return "redirect:";
    }

}
