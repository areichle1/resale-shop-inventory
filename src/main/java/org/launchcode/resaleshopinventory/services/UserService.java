package org.launchcode.resaleshopinventory.services;

import org.launchcode.resaleshopinventory.models.EmailExistsException;
import org.launchcode.resaleshopinventory.models.User;
import org.launchcode.resaleshopinventory.models.forms.UserForm;

public interface UserService {

    public User save(UserForm userDto) throws EmailExistsException;
    public User findByEmail(String email);

}