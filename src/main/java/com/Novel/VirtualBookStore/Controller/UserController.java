package com.Novel.VirtualBookStore.Controller;

import com.Novel.VirtualBookStore.entity.User;
import com.Novel.VirtualBookStore.Service.UserCartService;
import com.Novel.VirtualBookStore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

	
	//to-do: we will use this Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); if needed later
	
    @Autowired
    private UserService userService;
    @Autowired
    private UserCartService userCartService;
    // Create a new User
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userCartService.saveUserWithCart(user);
    }

    // Get all Users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get a User by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id) {
    	
        return userService.getUserById(id);
    }

    // Update a User by ID
    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Delete a User by ID
    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable UUID id) {
//        return userService.deleteUser(id);
    	return userCartService.deleteUserWithCard(id);
    }
    
    //Get a User by Email
    @GetMapping("email/{email}")
    public User getUserByEmail(@PathVariable String email) {
    	return userService.getUserByEmail(email);
    }
    
    //Change User Password by Email
    @PutMapping("email/{email}/change-password")
    public User changeUserPassword(@PathVariable String email,@RequestParam String newpassword) {
    	return userService.changePassword(email,newpassword);
    }
    
    //Get Users paginated
    @GetMapping("/paginated")
    public  Page<User> getUsersPaginated(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue ="10") int size) {
    	return userService.getAllUsersPaginated(page,size);
    }
    
    //Get totalUserCount
    @GetMapping("/count")
    public long getCountofTotalUsers(){
    	return userService.getCountofTotalUsers();
    }
    
}