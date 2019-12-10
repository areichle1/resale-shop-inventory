package org.launchcode.resaleshopinventory.models;

import org.launchcode.resaleshopinventory.models.forms.UserForm;

public interface UserService {

    public User save(UserForm userDto) throws EmailExistsException;
    public User findByEmail(String email);

}