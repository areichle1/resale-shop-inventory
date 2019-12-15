package org.launchcode.resaleshopinventory.services;

import org.launchcode.resaleshopinventory.models.Category;
import org.launchcode.resaleshopinventory.models.User;
import org.launchcode.resaleshopinventory.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public List<Category> findUserCategories(User user) {
        return categoryDao.findByUser(user);
    }
}
