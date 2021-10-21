package com.ab.tradingapp.controllers;

import com.ab.tradingapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.ab.tradingapp.services.UserService;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    // VIEW HANDLER METHODS

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showLanding() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView showDash() {
        return new ModelAndView("dashboard");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register_form";
    }

    @RequestMapping(value = "/login")
    public ModelAndView showLogin() {
        return new ModelAndView("index");
    }

    @RequestMapping("/logout")
    public ModelAndView viewLogout() {
        return new ModelAndView("index");
    }


    // USER METHODS

    @PostMapping("/save_user")
    public String saveUser(User user, @ModelAttribute("user_email") String username,
                           @ModelAttribute("user_password") String email,
                           Model model) {

        //SPRING SECURITY PASSWORD ENCODER
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getUser_password());
        user.setUser_password(encodedPassword);


        model.addAttribute("user_email", username);
        model.addAttribute("user_password", email);
        service.save(user);
        return "register_success";
    }

    @Controller
    public class SecurityController {

        @RequestMapping(value = "/user_email", method = RequestMethod.GET)
        @ResponseBody
        public String currentUserName(Principal principal) {
            return principal.getName();
        }
    }
}
