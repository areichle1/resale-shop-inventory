package org.launchcode.resaleshopinventory.models.data;

import org.launchcode.resaleshopinventory.models.Item;
import org.launchcode.resaleshopinventory.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ItemDao extends CrudRepository<Item, Integer> {

    List<Item> findByUser(User user);
}
