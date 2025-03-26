package com.Novel.VirtualBookStore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Novel.VirtualBookStore.Service.UserCartService;
import com.Novel.VirtualBookStore.Service.UserService;
import com.Novel.VirtualBookStore.entity.User;

@RestController
@RequestMapping("/public")
public class PublicController {
	@Autowired
    UserService userService;
	@Autowired
	UserCartService userCartService;
	 @GetMapping
	 public List<User> getAllUserPublically() {
		 return userService.getAllUsers();
	 }
	 
	 @PostMapping("/createUser")
	 public User savePublicUser(@RequestBody User user) {
		return userCartService.saveUserWithCart(user); 
	 }
	
}
