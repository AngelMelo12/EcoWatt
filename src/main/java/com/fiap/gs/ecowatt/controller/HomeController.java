package com.fiap.gs.ecowatt.controller;

import com.fiap.gs.ecowatt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired private UserService userService;

    @GetMapping
    public String home(Model model) {
        model.addAttribute("user", userService.getAuthenticatedUser());
        return "index";
    }
}
