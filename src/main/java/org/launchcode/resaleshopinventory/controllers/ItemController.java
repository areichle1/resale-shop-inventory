package org.launchcode.resaleshopinventory.controllers;

import org.launchcode.resaleshopinventory.models.*;
import org.launchcode.resaleshopinventory.models.data.*;
import org.launchcode.resaleshopinventory.services.CategoryService;
import org.launchcode.resaleshopinventory.services.ItemService;
import org.launchcode.resaleshopinventory.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/item")
public class ItemController extends AbstractBaseController {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    CategoryService categoryService;

    @Autowired
    StoreService storeService;

    @Autowired
    ItemService itemService;

    // Request path: /item
    @RequestMapping(value = "")
    public String index(Model model) {
        List<Item> user_items = new ArrayList<>();
        model.addAttribute("items", user_items);
        model.addAttribute("title", "My Items");
        return "item/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddItemForm(Model model, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        if (user.isEnabled()) {
            Item item = new Item();
            item.setUser(user);
            model.addAttribute("item", item);
            model.addAttribute("title", "Add Item");
            model.addAttribute("categories", categoryService.findUserCategories(user));
            model.addAttribute("stores", storeService.findUserStores(user));
            return "item/add";
        } else {
            return "/login";
        }

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddItemForm(Model model, Principal principal,
                                     @ModelAttribute @Valid Item newItem,
                                     Errors errors, @RequestParam Category categoryId,
                                     @RequestParam Store storeId) {

        User user = userService.findByEmail(principal.getName());
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Item");
            return "item/add";
        } else if (user.isEnabled()) {
            newItem.setUser(user);
            newItem.setCategory(categoryId);
            newItem.setStore(storeId);
            itemDao.save(newItem);
            model.addAttribute("item", newItem);
            model.addAttribute("title", "Add Item");
            return "index";
        } else {
            return "/login";
        }
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveItemForm(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("items", itemService.findUserItems(user));
        model.addAttribute("title", "Remove Item");
        return "item/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveItemForm(@RequestParam int[] itemIds) {
        for (int itemId : itemIds) {
            itemDao.deleteById(itemId);
        }

        return "index";
    }

}
