package com.ab.tradingapp.controllers;

import com.ab.tradingapp.models.Exchange;
import com.ab.tradingapp.models.Order;
import com.ab.tradingapp.models.Stocks;
import com.ab.tradingapp.models.User;
import com.ab.tradingapp.models.Wallet;
import com.ab.tradingapp.repos.OrderRepo;

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
import java.util.Optional;

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
    public ModelAndView viewCart() {
        List<Order> or = orderservice.getCart();
        double endPrice = 0;
	   	for(Order o : or) {
	   		 endPrice += o.getTransaction_cost();
	   	}
        ModelAndView mav = new ModelAndView (); 
	   	mav.addObject("listCart", or);
	   	mav.addObject("endPrice", endPrice);
	   	mav.setViewName("/cart");
   	 
	   	return mav;
    }
    

//    @RequestMapping("/wallet")
//    public ModelAndView viewWallet() {
//        return new ModelAndView("wallet");
//    }

    
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
    
    @RequestMapping(value = "/sellPage")
    public ModelAndView sellStock
		(@RequestParam("wallet_id") int wallet_id) {
    	
    	Wallet w1 = new Wallet();
    	
    	for (Wallet w : walletservice.listAll()) {
    		if (w.getWallet_id() == wallet_id) {
    			w1 =w;
    		}
    	}
    	
		ModelAndView mv = new ModelAndView();
		mv.addObject("sellObject",w1);
		mv.setViewName("sellPage");
		return mv;
    }
    
    
    
    @RequestMapping(method=RequestMethod.GET, value ="/order_history")
    public ModelAndView OrderHistory (Principal principal) {
    	
    	// String userEmail = principal.getName();
    	
    	// User user = service.getUserId(userEmail);
    	
    	ModelAndView v = new ModelAndView(); 
    	List<Order> s = orderservice.viewOrderHistory(detailservice.returnUserID());
    	System.out.print(s);
    	v.addObject("listOrders", s);
    	v.setViewName("/order_history");
    	
		return v;
    	
    }
    
    @RequestMapping(value = "/deleteOrder/{orderId}", method = RequestMethod.GET)
    public String DeleteOrder(@PathVariable(name="orderId")int orderId) {
    	System.out.print("order Deleted " + orderId); 
    	orderservice.deleteCartItem(orderId);
        return "redirect:/cart";
    }
    
    @RequestMapping(value = "/replaceOrder/{orderId}", method = RequestMethod.GET)
    public String Replaceorder (@PathVariable(name="orderId") int orderId) {
    	orderservice.replaceOrder(orderId);
    	return "redirect:/cart"; 
    }
    
    
    
    @PostMapping(value ="/create_sell")
    public ModelAndView sellAndViewCart(@ModelAttribute("transaction_amount") double transaction_amount) {
		
    	
    	
    	return null;
    	
    }
    
    @PostMapping(value="/create_purchase")
    public ModelAndView addAndViewCart(@ModelAttribute("exchange_code") String exchange_code,
    		@ModelAttribute("transaction_amount") double transaction_amount, @ModelAttribute("order") Order reqOrder,
                                       @ModelAttribute("exchangeObj") Exchange exchangeObj) {
    	
		 List<Exchange> exchangeList = exchangeservice.listAll();
         
		 reqOrder.setOrder_id(orderservice.getCart().size());
    	 reqOrder.setUser_id(detailservice.returnUserID());
    	 reqOrder.setStock_id(currentStockId);
    	 reqOrder.setExchange_code(exchange_code);
    	 
    	 System.out.print(reqOrder.getExchange_code());
    	 reqOrder.setType("BUY");
    	 reqOrder.setTransaction_amount(transaction_amount);
    	 reqOrder.setStock_name(stockservice.returnStockById(currentStockId).getStock_name());
    	 
    	  
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
			w.setStock_name(o.getStock_name());
			
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
