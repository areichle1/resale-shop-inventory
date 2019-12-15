package org.launchcode.resaleshopinventory.models.data;

import org.launchcode.resaleshopinventory.models.Store;
import org.launchcode.resaleshopinventory.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface StoreDao extends CrudRepository<Store, Integer> {

    List<Store> findByUser(User user);
}
