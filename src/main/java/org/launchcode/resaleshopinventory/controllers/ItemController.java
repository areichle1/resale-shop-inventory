package org.launchcode.resaleshopinventory.controllers;

import org.launchcode.resaleshopinventory.models.Category;
import org.launchcode.resaleshopinventory.models.Item;
import org.launchcode.resaleshopinventory.models.Store;
import org.launchcode.resaleshopinventory.models.User;
import org.launchcode.resaleshopinventory.models.data.CategoryDao;
import org.launchcode.resaleshopinventory.models.data.ItemDao;
import org.launchcode.resaleshopinventory.models.data.StoreDao;
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
import javax.validation.constraints.Null;

@Controller
@RequestMapping(value = "item")
public class ItemController {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private StoreDao storeDao;

    public String current_user;

   //
//    @Autowired
//    private UserDao userDao;
//
    // Request path: /item
    @RequestMapping(value = "")
    public String index(Model model) {
        //current_user = UserController.current_user;
        //String user_items = Null;

//        if (current_user != Null) {
//            user_items = Items;
//            for (Item item: itemDao.findAll()) {
//                if (item.getUserId() == current_user.getId()) {
//                    user_items.addtolist(item);
//                }
//            }
//        } else { // current_user == Null thus we display everything
//            //I want to do if current_user is logged out we display the login page
//            user_items = Null;
//        }

        //model.addAttribute("items", user_items);
        model.addAttribute("title", "My Items");
        return "item/index";
    }

    // Request path: item/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddItemForm(Model model) {

        /// check if user is logged in
        // if logged in, display web page
        // if not logged in, display error
        //if clicking on any other page and not logged in link to login page
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
//        current_user = User.current_user;
//
//        if (current_user != Null) {
//            newItem.setCategory(categoryId);
//            newItem.setStore(storeId);
//            newItem.setUserId(current_user);
//            itemDao.save(newItem);
//        } else {
//            // throw error
//            // put item in without user
//            // user has to be logged in to add an item
//        }

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
