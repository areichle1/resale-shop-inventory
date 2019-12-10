package org.launchcode.resaleshopinventory.models.data;

import org.launchcode.resaleshopinventory.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Integer> {

    //add back in once I get user login working
//    User findByUsername(String username);
}
