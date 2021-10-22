package com.ab.tradingapp.controllers;

import com.ab.tradingapp.models.Exchange;
import com.ab.tradingapp.models.Stocks;
import com.ab.tradingapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.ab.tradingapp.services.ExchangeService;
import com.ab.tradingapp.services.StockService;
import com.ab.tradingapp.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService service;
    
    @Autowired
    private StockService stockservice;
    
    @Autowired
    private ExchangeService exchangeservice;

    // VIEW HANDLER METHODS

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showLanding() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public ModelAndView showDash(
    		@ModelAttribute Stocks stock) {
    	
    	ModelAndView v = new ModelAndView();
    	List<Stocks> s = stockservice.listAll();

    	v.addObject("listStocks", s);
    	v.setViewName("/dashboard");

        return v;
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
    
    @RequestMapping(value = "/ExchangePage/{stock_id}", method = RequestMethod.POST)
    @ResponseBody 
    public ModelAndView getStockId(
    		@PathVariable (name = "Stockid")int stockId ){
		
    	ModelAndView mav = new ModelAndView();
    	List<Exchange> e = exchangeservice.FindAllExchagesForStock(stockId);
    	
    	mav.addObject("listExchanges", e);
    	mav.setViewName("/ExchangePage");

        return mav;
    }
    
    @RequestMapping(value="/ExchangePage")
	public String nextPage() {
		return "ExchangePage";
	}
    
    @RequestMapping(value="/viewStockOptions", method = RequestMethod.GET)
    public ModelAndView viewStockOptions(@ModelAttribute Exchange exchange) {
		
    	
    	return null;
    	
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
