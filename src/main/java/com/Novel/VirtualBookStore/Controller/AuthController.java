package com.Novel.VirtualBookStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Novel.VirtualBookStore.Jwt.JwtUtil;
import com.Novel.VirtualBookStore.util.loginBody;


/*Implementing session based login*/

@RestController
@RequestMapping("/api/user")
public class AuthController {
    
	 @Autowired
	 AuthenticationManager authenticationManager;
	 
	 @Autowired
	 JwtUtil jwtUtil;

	 @PostMapping("/login")
	 public ResponseEntity<String> loginUser(@RequestBody loginBody login) { //we will send token here instead of credentials
		 try{
	            Authentication authentication = authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
//	            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());  //i will try to do this without it and without security contex
	            String jwt = jwtUtil.generateToken(authentication.getName());  //creating token using userName as a data
	            return new ResponseEntity<>(jwt, HttpStatus.OK);
	        }catch (Exception e){
	            System.err.println("Exception occurred while createAuthenticationToken :"+ e.getMessage());
	            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
	        }

	 }
}