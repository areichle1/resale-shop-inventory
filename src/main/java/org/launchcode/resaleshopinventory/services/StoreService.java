package org.launchcode.resaleshopinventory.services;

import org.launchcode.resaleshopinventory.models.Store;
import org.launchcode.resaleshopinventory.models.User;
import org.launchcode.resaleshopinventory.models.data.StoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreDao storeDao;

    public List<Store> findUserStores(User user) {
        return storeDao.findByUser(user);
    }
}
