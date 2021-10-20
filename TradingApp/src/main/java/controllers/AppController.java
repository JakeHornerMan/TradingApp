package controllers;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import services.UserService;

@Controller
public class AppController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showLanding() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return new ModelAndView( "register");
    }

    @PostMapping("/save_user")
    public String saveUser(User user,
                           @ModelAttribute("user_email") String user_email,
                           @ModelAttribute("user_password") String user_password,
                           Model model) {

        //SPRING SECURITY PASSWORD ENCODER
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encodedPassword = passwordEncoder.encode(user.getUser_password());
//        user.setUser_password(encodedPassword);

        model.addAttribute("user_email", user_email);
        model.addAttribute("user_password", user_password);

            service.save(user);
            return "register_success";
        }
    }
