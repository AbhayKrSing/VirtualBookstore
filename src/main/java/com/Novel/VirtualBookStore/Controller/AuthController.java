package com.Novel.VirtualBookStore.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Novel.VirtualBookStore.Service.UserService;
import com.Novel.VirtualBookStore.entity.User;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/api/user/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
    	
        return null; //just to remove error
    }
}