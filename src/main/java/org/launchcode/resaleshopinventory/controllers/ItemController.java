package org.launchcode.resaleshopinventory.controllers;

import org.launchcode.resaleshopinventory.models.Item;
import org.launchcode.resaleshopinventory.models.ItemData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "item")
public class ItemController {

    // Request path: /item
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("items", ItemData.getAll());
        model.addAttribute("title", "My Items");
        return "item/index";
    }

    // Request path: item/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddItemForm(Model model) {

        model.addAttribute("title", "Add Item");
        model.addAttribute(new Item());
        return "item/add";
    }

    // Request path: item/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddItemForm(@ModelAttribute @Valid Item newItem, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Item");
            return "item/add";
        }

        ItemData.add(newItem);
        //Redirect to item/
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveItemForm(Model model) {
        model.addAttribute("items", ItemData.getAll());
        model.addAttribute("title", "Remove Item");
        return "item/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveItemForm(@RequestParam int[] itemIds) {
        for (int itemId : itemIds) {
            ItemData.remove(itemId);
        }
        return "redirect:";
    }

}
