package org.launchcode.resaleshopinventory.controllers;

import org.launchcode.resaleshopinventory.models.Category;
import org.launchcode.resaleshopinventory.models.Item;
import org.launchcode.resaleshopinventory.models.Store;
import org.launchcode.resaleshopinventory.models.User;
import org.launchcode.resaleshopinventory.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/item")
public class ItemController extends AbstractBaseController{
    //

//    @Autowired
//    ItemRepository itemRepository;
//
//    @Autowired
//    StoreRepository storeRepository;
//
//    @Autowired
//    CategoryRepository categoryRepository;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private StoreDao storeDao;

    @Autowired
    private UserDao userDao;

    private int Id;

//    @GetMapping
//    public String listItems(Model model) {
//        List<Item> allItems = itemRepository.findAll();
//        model.addAttribute("items", allItems);
//        return "item/index";
//    }
//
//    @GetMapping(value = "add")
//    public String displayAddItemForm(Model model, HttpServletRequest request) {
//        model.addAttribute(new Item());
//        model.addAttribute("actionUrl", request.getRequestURI());
//        model.addAttribute("title", "Create Item");
//        model.addAttribute("store", storeRepository.findAll());
//        model.addAttribute("category", categoryRepository.findAll());
//        return "item/add";
//    }
//
//    @PostMapping(value = "add")
//    public String processAddItemForm(@Valid @ModelAttribute Item item,
//                                         Errors errors,
//                                         @RequestParam(name = "volunteers", required = false) List<Integer> volunteerUids) {
//
//        if (errors.hasErrors())
//            return "item/add";
//
//        //syncVolunteerLists(volunteerUids, item.getVolunteers());
//        itemRepository.save(item);
//
//        return "redirect:/item/detail/" + item.getUid();
//    }
//
//    @GetMapping(value = "detail/{uid}")
//    public String displayItemDetails(@PathVariable int uid, Model model) {
//
//        model.addAttribute("title", "Item Details");
//
//        Optional<Item> result = itemRepository.findById(uid);
//        if (result.isPresent()) {
//            Item item = result.get();
//            model.addAttribute(item);
//            //model.addAttribute("volunteerNames", item.getVolunteersFormatted());
//        } else {
//            model.addAttribute(MESSAGE_KEY, "warning|No item found with id: " + Integer.toString(uid));
//        }
//
//        return "item/details";
//    }
//
//    @GetMapping(value = "update/{uid}")
//    public String displayUpdateItemForm(@PathVariable int uid, Model model, HttpServletRequest request) {
//
//        model.addAttribute("title", "Update Item");
//        model.addAttribute("actionUrl", request.getRequestURI());
//
//        Optional<Item> item = itemRepository.findById(uid);
//        if (item.isPresent()) {
//            model.addAttribute(item.get());
//            model.addAttribute("store", storeRepository.findAll());
//            model.addAttribute("category", categoryRepository.findAll());
//        } else {
//            model.addAttribute(MESSAGE_KEY, "warning|No item found with id: " + Integer.toString(uid));
//        }
//
//        return "item/add";
//    }
//
//    @PostMapping(value = "update/{uid}")
//    public String processUpdateItemForm(@Valid @ModelAttribute Item item,
//                                         RedirectAttributes model,
//                                         Errors errors,
//                                         @RequestParam(name = "volunteers", required = false) List<Integer> volunteerUids) {
//
//        if (errors.hasErrors())
//            return "item/add";
//
//        //syncVolunteerLists(volunteerUids, item.getVolunteers());
//        itemRepository.save(item);
//        model.addFlashAttribute(MESSAGE_KEY, "success|Updated item: " + item.getName());
//
//        return "redirect:/item/detail/" + item.getUid();
//    }
//
//    @PostMapping(value = "delete/{uid}")
//    public String processDeleteItemForm(@PathVariable int uid, RedirectAttributes model) {
//
//        Optional<Item> result = itemRepository.findById(uid);
//        if (result.isPresent()) {
//            itemRepository.delete(result.get());
//            model.addFlashAttribute(MESSAGE_KEY, "success|Item deleted");
//            return "redirect:/item";
//        } else {
//            model.addFlashAttribute(MESSAGE_KEY, "danger|Item with ID does not exist: " +  uid);
//            return "redirect:/item";
//        }
//    }

    //not sure if i need anything like this?
//    private void syncVolunteerLists(List<Integer> volunteerUids, List<Volunteer> volunteers) {
//
//        if (volunteerUids == null)
//            return;
//
//        List<Volunteer> newVolunteerList = volunteerRepository.findAllById(volunteerUids);
//        volunteers.removeIf(v -> volunteerUids.contains(v.getUid()));
//        volunteers.addAll(newVolunteerList);
//    }







//STARTING HERE IS MY OLD STUFF THAT I WROTE
    // Request path: /item
    @RequestMapping(value = "")
    public String index(Model model) {
        //current_user = UserController.current_user;
        //String user_items = null;
        List<Item> user_items = new ArrayList<>();

//        if (current_user != null) {
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
        //add below this back in once I get user login working
//        for (User user : userDao.findAll()) {
//            if (user.getId() == Id) {
//                user_items = user.getItems();
//            }
//        }

        model.addAttribute("items", user_items);
        model.addAttribute("title", "My Items");
        return "item/index";
    }

    //new way to do view list of items

//    @GetMapping
//    public String listItems(Model model) {
//        List<Item> user_items = itemDao.findAllById(user_uid);
//        model.addAttribute("items", user_items);
//        return "item/index";
//    }

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
                                     Errors errors, Model model, Principal user, @RequestParam Category categoryId,
                                     @RequestParam Store storeId) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Item");
            return "item/add";
        }
//add below this back in once user login works
//        User current_user;
//        current_user = User.current_user;
//        userService.findByEmail(user.getName())
//        if (user != null) {
//            newItem.setCategory(categoryId);
//            newItem.setStore(storeId);
//            newItem.setUser(current_user);
//
//            itemDao.save(newItem);
//        } else {
//            // throw error
//            // put item in without user?
//            // user has to be logged in to add an item
//            System.out.println("error happening");
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
