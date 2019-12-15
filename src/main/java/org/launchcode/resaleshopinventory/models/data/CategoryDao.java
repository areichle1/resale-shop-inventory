package org.launchcode.resaleshopinventory.models.data;

import org.launchcode.resaleshopinventory.models.Category;
import org.launchcode.resaleshopinventory.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {

    List<Category> findByUser(User user);
}
