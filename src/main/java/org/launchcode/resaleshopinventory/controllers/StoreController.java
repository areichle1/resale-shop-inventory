package org.launchcode.resaleshopinventory.controllers;

import org.launchcode.resaleshopinventory.models.Item;
import org.launchcode.resaleshopinventory.models.Store;
import org.launchcode.resaleshopinventory.models.data.ItemDao;
import org.launchcode.resaleshopinventory.models.data.StoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("store")
public class StoreController {

    @Autowired
    StoreDao storeDao;

    @Autowired
    ItemDao itemDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("stores", storeDao.findAll());
        model.addAttribute("title", "Stores");

        return "store/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {

        model.addAttribute(new Store());
        model.addAttribute("title", "Add Store");
        return "store/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Store newStore,
                      Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Store");
            return "store/add";
        }

        storeDao.save(newStore);

        return "redirect:view/" + newStore.getId();
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
    public String displayRemoveStoreForm(Model model) {
        model.addAttribute("stores", storeDao.findAll());
        model.addAttribute("title", "Remove Store");
        return "store/remove";
    }

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
