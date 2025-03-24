package com.Novel.VirtualBookStore.Service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Novel.VirtualBookStore.entity.Carts;
import com.Novel.VirtualBookStore.entity.User;

@Service
public class UserCartService {
	 @Autowired
	    private UserService userService;

	    @Autowired
	    private CartsService cartsService;

	    public User saveUserWithCart(User user) {
	        User createdUser = userService.saveUser(user);
	        Carts carts = new Carts();
	        carts.setCreatedAt(new Date());
	        carts.setUser(createdUser);
	        cartsService.saveCart(carts);
	        return createdUser;
	    }
	    
	    

	
}
