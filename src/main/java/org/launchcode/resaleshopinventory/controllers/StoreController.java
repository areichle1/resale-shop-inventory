package org.launchcode.resaleshopinventory.controllers;

import org.launchcode.resaleshopinventory.models.Item;
import org.launchcode.resaleshopinventory.models.Store;
import org.launchcode.resaleshopinventory.services.StoreService;
import org.launchcode.resaleshopinventory.models.User;
import org.launchcode.resaleshopinventory.models.data.ItemDao;
import org.launchcode.resaleshopinventory.models.data.StoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("store")
public class StoreController extends AbstractBaseController {

    @Autowired
    StoreDao storeDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    StoreService storeService;

    @RequestMapping(value = "")
    public String index(Principal principal, Model model) {

        User user = userService.findByEmail(principal.getName());
        model.addAttribute("stores", storeService.findUserStores(user));
        model.addAttribute("title", "Stores");

        return "store/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        if (user.isEnabled()) {
            Store store = new Store();
            store.setUser(user);
            model.addAttribute("store", store);
            model.addAttribute("title", "Add Store");
            return "store/add";
        } else {
            return "store/add";
        }
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, Principal principal,
                      @ModelAttribute @Valid Store newStore,
                      Errors errors) {

        User user = userService.findByEmail(principal.getName());
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Store");
            return "store/add";
        } else if (user.isEnabled()) {
            Store store = new Store();
            store.setUser(user);
            storeDao.save(newStore);
            model.addAttribute("store", store);
            model.addAttribute("title", "Add Store");
            return "redirect:view/" + newStore.getId();
        } else {
            return "/login";
        }
    }

    @RequestMapping(value = "view/{storeId}", method = RequestMethod.GET)
    public String viewStore(Model model, @PathVariable int storeId) {

        Optional<Store> optional = storeDao.findById(storeId);
            optional.ifPresent(item -> {
                model.addAttribute("title", item.getName());
                model.addAttribute("items", item.getItems());
                model.addAttribute("storeId", item.getId());
            });
            return "store/view";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveStoreForm(Model model, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        model.addAttribute("stores", storeService.findUserStores(user));
        model.addAttribute("title", "Remove Store");
        return "store/remove";
    }

    //deleting a store name will also delete all items associated with that store
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveStoreForm(@RequestParam int[] storeIds) {

        for (int storeId : storeIds) {
            for (Item item : itemDao.findAll()) {
                if (item.getStore().getId() == storeId) {
                    itemDao.deleteById(item.getId());
                }
            }
            storeDao.deleteById(storeId);
        }

        return "redirect:";
    }
}
