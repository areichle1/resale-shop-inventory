package org.launchcode.resaleshopinventory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @RequestMapping(value = "")
    @ResponseBody
    public String index() {
        return "My first item";
    }
}
