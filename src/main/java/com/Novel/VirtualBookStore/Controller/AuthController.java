package com.Novel.VirtualBookStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Novel.VirtualBookStore.util.loginBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/*Implementing session based login*/

@RestController
@RequestMapping("/api/user")
public class AuthController {
    
	 @Autowired
	 AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody loginBody login,HttpServletRequest httpRequest) {
 
    		//1.creating UserName/password authentication token
    		Authentication authToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()); 
    		//<principal is whom trying to loginIn,crediential is generally password>
			try {
		    //2.verification
				Authentication authentication= authenticationManager.authenticate(authToken);
		    // 3.Session Creation
			        SecurityContextHolder.getContext().setAuthentication(authentication);
			        HttpSession session = httpRequest.getSession(true); // Create new session
			        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
			        
			        return ResponseEntity.ok().body("Authentication successful");
			} catch (BadCredentialsException e) { //for debugging
				System.out.println(e.getMessage());
				return ResponseEntity.status(401).body("Invalid credentials");
			}
	
    	
    	
    }
}