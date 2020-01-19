package org.launchcode.resaleshopinventory.controllers;

import org.launchcode.resaleshopinventory.models.*;
import org.launchcode.resaleshopinventory.models.data.CategoryDao;
import org.launchcode.resaleshopinventory.models.data.ItemDao;
import org.launchcode.resaleshopinventory.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("category")
public class CategoryController extends AbstractBaseController {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ItemDao itemDao;

    // Request path: /category
    @RequestMapping(value = "")
    public String index(Principal principal, Model model) {

        User user = userService.findByEmail(principal.getName());
        model.addAttribute("categories", categoryService.findUserCategories(user));
        model.addAttribute("title", "Categories");

        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        if (user.isEnabled()) {
            Category category = new Category();
            category.setUser(user);
            model.addAttribute("category", category);
            model.addAttribute("title", "Add Category");
            return "category/add";
        } else {
            return "category/add";
        }
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, Principal principal, @ModelAttribute @Valid
            Category newCategory, Errors errors) {

        User user = userService.findByEmail(principal.getName());
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            return "category/add";
        } else if (user.isEnabled()) {
            Category category = new Category();
            category.setUser(user);
            categoryDao.save(newCategory);
            model.addAttribute("category", category);
            model.addAttribute("title", "Add Category");
            return "redirect:view/" + newCategory.getId();
        } else {
            return "/login";
        }

    }

    @RequestMapping(value = "view/{categoryId}", method = RequestMethod.GET)
    public String viewCategory(Model model, @PathVariable int categoryId) {

        Optional<Category> optional = categoryDao.findById(categoryId);
        optional.ifPresent(item -> {
            model.addAttribute("title", item.getName());
            model.addAttribute("items", item.getItems());
            model.addAttribute("categoryId", item.getId());
        });
        return "category/view";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCategoryForm(Model model, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        model.addAttribute("categories", categoryService.findUserCategories(user));
        model.addAttribute("title", "Remove Category");
        return "category/remove";
    }

    //deleting a category will also delete all items associated with that category
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCategoryForm(@RequestParam int[] categoryIds) {

        for (int categoryId : categoryIds) {
            for (Item item : itemDao.findAll() ) {
                if (item.getCategory().getId() == categoryId) {
                    itemDao.deleteById(item.getId());
                }
            }
            categoryDao.deleteById(categoryId);
        }

        return "redirect:";
    }
}
