package com.taik.webshop.controllers;

import com.taik.webshop.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping({"", "/"})
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error") //чтобы пользователь попал на 404-page
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping("/registration")
    public ModelAndView registration(ModelAndView modelAndView) {
        modelAndView.setViewName("registration");
        modelAndView.addObject("user", new UserDto());
        return modelAndView;
    }

    @RequestMapping("/delivery")
    public String delivery(){return "deliveryPage";}

    @RequestMapping("/contacts")
    public String contacts(){return "contacts";}
}
