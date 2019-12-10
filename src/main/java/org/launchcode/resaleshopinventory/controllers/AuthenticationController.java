package org.launchcode.resaleshopinventory.controllers;


import org.launchcode.resaleshopinventory.models.EmailExistsException;
import org.launchcode.resaleshopinventory.models.data.UserDao;
import org.launchcode.resaleshopinventory.models.forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AuthenticationController extends AbstractBaseController {

    @GetMapping(value = "/register")
    public String registerForm(Model model) {
        model.addAttribute(new UserForm());
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute @Valid UserForm userForm, Errors errors) {

        if (errors.hasErrors())
            return "register";

        try {
            userService.save(userForm);
        } catch (EmailExistsException e) {
            errors.rejectValue("email", "email.alreadyexists", e.getMessage());
            return "register";
        }

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String login(Model model, Principal user, String error, String logout) {

        if (user != null)
            return "redirect:/";

        if (error != null)
            model.addAttribute(MESSAGE_KEY, "danger|Your username and password are invalid");

        if (logout != null)
            model.addAttribute(MESSAGE_KEY, "info|You have logged out");

        if (user != null){
            System.out.println(user.getName());
        }

        return "login";
    }

}

//import org.launchcode.resaleshopinventory.models.User;
//import org.launchcode.resaleshopinventory.models.forms.LoginForm;
//import org.launchcode.resaleshopinventory.models.forms.RegisterForm;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//@Controller
//public class AuthenticationController extends AbstractController {
//
//    @RequestMapping(value = "")
//    public String index() {
//        return "index";
//    }
//
//    @RequestMapping(value = "/register")
//    public String registerForm(Model model) {
//        model.addAttribute(new RegisterForm());
//        model.addAttribute("title", "Register");
//        return "register";
//    }
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public String register(@ModelAttribute @Valid RegisterForm form, Errors errors, HttpServletRequest request) {
//
//        if (errors.hasErrors()) {
//            return "register";
//        }
//
//        User existingUser = userDao.findByUsername(form.getUsername());
//
//        if (existingUser != null) {
//            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
//            return "register";
//        }
//
//        User newUser = new User(form.getUsername(), form.getPassword());
//        userDao.save(newUser);
//        setUserInSession(request.getSession(), newUser);
//
//        return "redirect:";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(Model model) {
//        model.addAttribute(new LoginForm());
//        model.addAttribute("title", "Log In");
//        return "login";
//    }
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(@ModelAttribute @Valid LoginForm form, Errors errors, HttpServletRequest request) {
//
//        if (errors.hasErrors()) {
//            return "login";
//        }
//
//        User theUser = userDao.findByUsername(form.getUsername());
//        String password = form.getPassword();
//
//        if (theUser == null) {
//            errors.rejectValue("username", "user.invalid", "The given username does not exist");
//            return "login";
//        }
//
//        if (!theUser.isMatchingPassword(password)) {
//            errors.rejectValue("password", "password.invalid", "Invalid password");
//            return "login";
//        }
//
//        setUserInSession(request.getSession(), theUser);
//
//        return "redirect:";
//    }
//
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout(HttpServletRequest request){
//        request.getSession().invalidate();
//        return "redirect:/login";
//    }
//
//}
