package com.ab.tradingapp.controllers;

import com.ab.tradingapp.models.Exchange;
import com.ab.tradingapp.models.Order;
import com.ab.tradingapp.models.Stocks;
import com.ab.tradingapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.ab.tradingapp.services.CustomUserDetailsService;
import com.ab.tradingapp.services.ExchangeService;
import com.ab.tradingapp.services.OrderService;
import com.ab.tradingapp.services.StockService;
import com.ab.tradingapp.services.UserService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class UserController {

	@Autowired
    private UserService service;
    
    @Autowired
    private StockService stockservice;
    
    @Autowired
    private ExchangeService exchangeservice;
    
    @Autowired
    private CustomUserDetailsService customUserService;

    @Autowired
    private OrderService orderservice;
    // VIEW HANDLER METHODS
    
    private int currentStockId;

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

    @RequestMapping("/cart")
    public ModelAndView viewCart() {
        return new ModelAndView("cart");
    }
    
    @RequestMapping(value="/exchangePage")
	public ModelAndView getExchagesByStockId(@RequestParam("stock_id") int stock_id, Model model) {

        Order order = new Order();
        model.addAttribute("order", order);
    	
    	ModelAndView mv = new ModelAndView();
    	List<Exchange> elist = exchangeservice.FindAllExchagesForStock(stock_id);
    	
    	currentStockId = elist.get(0).getStock_id();
    	/*for (Exchange e : elist) {
    		System.out.println(e.toString());
    	}*/
    	
		mv.setViewName("exchangePage");
		mv.addObject("listExchanges",elist);
		return mv;
	}
    
    @PostMapping(value="/create_purchase")
    public ModelAndView addAndViewCart(@RequestParam("exchange_code") String exchange_code,
    		@ModelAttribute("transaction_amount") double transaction_amount, @ModelAttribute("order") Order reqOrder) {
    	
		 List<Exchange> exchangeList = exchangeservice.listAll();

    	 reqOrder.setUser_id(customUserService.returnUserID());
    	 reqOrder.setStock_id(currentStockId);
    	 reqOrder.setExchange_code(exchange_code);
    	 
    	 System.out.print(reqOrder.getExchange_code());
    	 reqOrder.setType("BUY");
    	 reqOrder.setTransaction_amount(transaction_amount);
    	 
    	  
    	 double cost = (exchangeList.get(0).getStock_value()*transaction_amount)+exchangeList.get(0).getStock_fee();
    	 
    	 reqOrder.setTransaction_cost(cost); //replace later transaction_amount * STOCK_VALUE
    	 
    	 LocalDateTime Date = LocalDateTime.now(); 
    	 reqOrder.setDateTime(Date);
    	 
    	 orderservice.addToCart(reqOrder);
    	 
    	 //Integer d = orderservice.createOrder(reqOrder.getUser_id(), reqOrder.getStock_id(), reqOrder.getExchange_code(), reqOrder.getType(), reqOrder.getTransaction_amount(), cost, Date); 
    	 
    	 ModelAndView mav = new ModelAndView (); 
    	 mav.addObject("listCart", orderservice.getCart());
    	 mav.setViewName("/cart");
    	 
    	 return mav;
    }
    
    /*@PostMapping(value="/wallet")
    public ModelAndView purchaseCart() {
		
    	
    	return null;
    	
    }*/
    
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
