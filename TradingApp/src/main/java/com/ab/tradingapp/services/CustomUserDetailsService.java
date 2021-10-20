//package services;
//
//import models.CustomUserDetails;
//import models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import repos.UserRepo;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepo userRepo;
//
//    private User user;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepo.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        return new CustomUserDetails(user);
//    }
//
//    public String returnUsername() {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        return authentication.getName();
//    }
//
//}