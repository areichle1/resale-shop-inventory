package org.launchcode.resaleshopinventory.models.data;

import org.launchcode.resaleshopinventory.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);
}
