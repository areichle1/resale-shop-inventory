package org.launchcode.resaleshopinventory.services;

import org.launchcode.resaleshopinventory.models.Item;
import org.launchcode.resaleshopinventory.models.User;
import org.launchcode.resaleshopinventory.models.data.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemDao itemDao;

    public List<Item> findUserItems(User user) {
        return itemDao.findByUser(user);
    }
}
