package com.Novel.VirtualBookStore.Service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Novel.VirtualBookStore.entity.Carts;
import com.Novel.VirtualBookStore.entity.User;

@Service
public class UserCartService {
	 @Autowired
	    private UserService userService;

	    @Autowired
	    private CartsService cartsService;

	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    public User saveUserWithCart(User user) {
	    	user.setPassword(passwordEncoder.encode(user.getPassword()));
	    	user.setRole("User");
	        User createdUser = userService.saveUser(user);
	        Carts carts = new Carts();
	        carts.setCreatedAt(new Date());
	        carts.setUser(createdUser);
	        cartsService.saveCart(carts);
	        return createdUser;
	    }
	    
	    

	
}
