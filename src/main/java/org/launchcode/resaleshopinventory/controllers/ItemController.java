package org.launchcode.resaleshopinventory.controllers;

import org.launchcode.resaleshopinventory.models.Category;
import org.launchcode.resaleshopinventory.models.Item;
import org.launchcode.resaleshopinventory.models.Store;
import org.launchcode.resaleshopinventory.models.data.CategoryDao;
import org.launchcode.resaleshopinventory.models.data.ItemDao;
import org.launchcode.resaleshopinventory.models.data.StoreDao;
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
@RequestMapping(value = "item")
public class ItemController {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private StoreDao storeDao;

    // Request path: /item
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("items", itemDao.findAll());
        model.addAttribute("title", "My Items");
        return "item/index";
    }

    // Request path: item/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddItemForm(Model model) {

        model.addAttribute("title", "Add Item");
        model.addAttribute(new Item());
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("stores", storeDao.findAll());
        return "item/add";
    }

    // Request path: item/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddItemForm(@ModelAttribute @Valid Item newItem,
                                     Errors errors, Model model, @RequestParam Category categoryId,
                                     @RequestParam Store storeId) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Item");
            return "item/add";
        }

        newItem.setCategory(categoryId);
        newItem.setStore(storeId);
        itemDao.save(newItem);

        //Redirect to item/
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveItemForm(Model model) {
        model.addAttribute("items", itemDao.findAll());
        model.addAttribute("title", "Remove Item");
        return "item/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveItemForm(@RequestParam int[] itemIds) {
        for (int itemId : itemIds) {
            itemDao.deleteById(itemId);
        }

        return "redirect:";
    }

}
