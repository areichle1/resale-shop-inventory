package org.launchcode.resaleshopinventory.controllers;

import org.launchcode.resaleshopinventory.models.Category;
import org.launchcode.resaleshopinventory.models.Item;
import org.launchcode.resaleshopinventory.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
//<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//=======
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//>>>>>>> parent of c189e85... added remove feature to CategoryController, added remove feature to StoreController, added necessary remove.html pages

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("category")
public class CategoryController {
//extends AbstractBaseController

//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @GetMapping
//    public String listCategories(Model model) {
//        model.addAttribute("title", "Categories");
//        model.addAttribute("categories", categoryRepository.findAll());
//        return "category/index";
//    }
//
//    @GetMapping(value = "add")
//    public String displayAddCategoryForm(Model model, HttpServletRequest request) {
//        model.addAttribute("title", "Create Category");
//        model.addAttribute(new Category());
//        model.addAttribute("actionUrl", request.getRequestURI());
//        return "category/add.html";
//    }
//
//    @PostMapping(value = "add")
//    public String processAddCategoryForm(@ModelAttribute @Valid Category category,
//                                             Errors errors,
//                                             RedirectAttributes model) {
//
//        if (errors.hasErrors())
//            return "category/add";
//
//        categoryRepository.save(category);
//        model.addFlashAttribute(MESSAGE_KEY, "success|New category added: " + category.getName());
//
//        return "redirect:/category";
//    }




    //below here was all my stuff before
    @Autowired
    CategoryDao categoryDao;

//    @Autowired
//    ItemDao itemDao;

    // Request path: /category
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


//    @RequestMapping(value = "remove", method = RequestMethod.GET)
//    public String displayRemoveCategoryForm(Model model) {
//        model.addAttribute("categories", categoryDao.findAll());
//        model.addAttribute("title", "Remove Category");
//        return "category/remove";
//    }
//
//    @RequestMapping(value = "remove", method = RequestMethod.POST)
//    public String processRemoveCategoryForm(@RequestParam int[] categoryIds) {
//
//        for (int categoryId : categoryIds) {
//            for (Item item : itemDao.findAll() ) {
//                if (item.getCategory().getId() == categoryId) {
//                    itemDao.deleteById(item.getId());
//                }
//            }
//            categoryDao.deleteById(categoryId);
//        }
//
//        return "redirect:";
//    }
}
