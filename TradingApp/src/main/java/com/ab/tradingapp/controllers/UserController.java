package com.ab.tradingapp.controllers;

import com.ab.tradingapp.models.Exchange;
import com.ab.tradingapp.models.Order;
import com.ab.tradingapp.models.Stocks;
import com.ab.tradingapp.models.User;
import com.ab.tradingapp.models.Wallet;

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
import com.ab.tradingapp.services.WalletService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private CustomUserDetailsService detailservice;

    @Autowired
    private OrderService orderservice;
    
    @Autowired
    private WalletService walletservice;
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
    public ModelAndView viewCart(Model model) {
        Exchange exchangeObj = new Exchange();
        model.addAttribute("exchangeObj", exchangeObj);
        return new ModelAndView("cart");
    }
    
    /*@RequestMapping("/wallet")
    public ModelAndView viewWallet() {
        return new ModelAndView("wallet");
    }*/
    
    @RequestMapping(value="/exchangePage")
	public ModelAndView getExchagesByStockId(@RequestParam("stock_id") int stock_id, Model model) {

        Order order = new Order();
        Exchange exchangeObj = new Exchange();
        model.addAttribute("order", order);
        model.addAttribute("exchangeObj", exchangeObj);
    	
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
    public ModelAndView addAndViewCart(@ModelAttribute("exchange_code") String exchange_code,
    		@ModelAttribute("transaction_amount") double transaction_amount, @ModelAttribute("order") Order reqOrder,
                                       @ModelAttribute("exchangeObj") Exchange exchangeObj) {
    	
		 List<Exchange> exchangeList = exchangeservice.listAll();
         
    	 reqOrder.setUser_id(detailservice.returnUserID());
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
    	 
    	 List<Order> or = orderservice.getCart();
    	 double endPrice = 0;
    	 for(Order o : or) {
    		 endPrice += o.getTransaction_cost();
    	 }
    	 
    	 //Integer d = orderservice.createOrder(reqOrder.getUser_id(), reqOrder.getStock_id(), reqOrder.getExchange_code(), reqOrder.getType(), reqOrder.getTransaction_amount(), cost, Date); 
    	 
    	 ModelAndView mav = new ModelAndView (); 
    	 mav.addObject("listCart", or);
    	 mav.addObject("endPrice", endPrice);
    	 mav.setViewName("/cart");
    	 
    	 return mav;
    }
    
    @PostMapping(value="/purchaseCart")
    public ModelAndView purchaseCart() {
		
    	orderservice.purchaseCart();
    	addToWallet(orderservice.getCart());
    	orderservice.clearCart();
    	
    	ModelAndView mav = new ModelAndView (); 
	   	mav.setViewName("/cart");
	   	
	   	return mav;
    }
    
    private void addToWallet(List<Order> cart) {
		
		for(Order o : cart) {
			Wallet w = new Wallet();
			w.setUser_Id(detailservice.returnUserID());
			w.setStock_Id(o.getStock_id());
			w.setStock_Amount(o.getTransaction_amount());
			
			walletservice.save(w);
		}
	}

	@GetMapping(value="/wallet")
    public ModelAndView getWallet() {
		
    	List<Wallet> w1 = walletservice.listAll();
    	List<Wallet> wallet = new ArrayList<>();
    	
    	for(Wallet w : w1) {
    		if(w.getUser_Id() == detailservice.returnUserID()) {
    			wallet.add(w);
    		}
    	}
    	
    	ModelAndView mav = new ModelAndView (); 
	   	mav.addObject("listWallet", wallet);
	   	mav.setViewName("/wallet");
		
    	return mav;
    	
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
