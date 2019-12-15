package org.launchcode.resaleshopinventory.services;

import org.launchcode.resaleshopinventory.models.EmailExistsException;
import org.launchcode.resaleshopinventory.models.User;
import org.launchcode.resaleshopinventory.models.data.UserDao;
import org.launchcode.resaleshopinventory.models.forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User save(UserForm userDto) throws EmailExistsException {

        User existingUser = userDao.findByEmail(userDto.getEmail());
        if (existingUser != null)
            throw new EmailExistsException("The email address "
                    + userDto.getEmail() + " already exists in the system");

        User newUser = new User(
                userDto.getFullName(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()));
        userDao.save(newUser);

        return newUser;
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

}
