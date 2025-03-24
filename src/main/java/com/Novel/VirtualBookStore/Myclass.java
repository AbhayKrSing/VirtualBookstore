package com.Novel.VirtualBookStore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Myclass {
	@GetMapping("/test")
	String testing(){
		return "mapped";
	}
}
