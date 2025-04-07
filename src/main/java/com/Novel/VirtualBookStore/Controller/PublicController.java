package com.Novel.VirtualBookStore.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Novel.VirtualBookStore.Jwt.JwtUtil;
import com.Novel.VirtualBookStore.Service.UserCartService;
import com.Novel.VirtualBookStore.Service.UserService;
import com.Novel.VirtualBookStore.entity.User;
import com.Novel.VirtualBookStore.util.loginBody;


@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "*")
public class PublicController {
	@Autowired
    UserService userService;
	@Autowired
	UserCartService userCartService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtil jwtUtil;
	 @GetMapping
	 public List<User> getAllUserPublically() {
		 return userService.getAllUsers();
	 }
	 
	 @PostMapping("/signup")            //changes from createdUser-->signup
	 public User savePublicUser(@RequestBody User user) {
		return userCartService.saveUserWithCart(user); 
	 }
	 
	 @PostMapping("/login")
	 public ResponseEntity<String> loginUser(@RequestBody loginBody login) { //we will send token here instead of credentials
		 try{
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
//	            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());  //i will try to achieve this using security context
	            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	            String jwt = jwtUtil.generateToken(authentication.getName());  //creating token using userName as a data
	            return new ResponseEntity<>(jwt, HttpStatus.OK);
	        }catch (Exception e){
	            System.err.println("Exception occurred while createAuthenticationToken :"+ e.getMessage());
	            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
	        }

	 }
	 @DeleteMapping("/deleteUser/{id}")
	  public String deleteUser(@PathVariable UUID id) {
	        return userService.deleteUser(id);
	  }
	 
	
}
