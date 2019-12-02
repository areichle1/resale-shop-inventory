package org.launchcode.resaleshopinventory.controllers;


import org.launchcode.resaleshopinventory.models.Category;
import org.launchcode.resaleshopinventory.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryDao categoryDao;

//    @Autowired
//    ItemDao itemDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Categories");

        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new Category());
        model.addAttribute("title", "Add Category");

        return "category/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid
            Category newCategory, Errors errors) {

        if (errors.hasErrors()){
            //model.addAttribute(new Category());
            model.addAttribute("title", "Add Category");
            return "category/add";
        }

        categoryDao.save(newCategory);
        return "redirect:view/" + newCategory.getId();
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
}
