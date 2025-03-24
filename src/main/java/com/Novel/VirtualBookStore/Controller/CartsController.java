package com.Novel.VirtualBookStore.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Novel.VirtualBookStore.Service.CartsService;
import com.Novel.VirtualBookStore.entity.Carts;

@RestController
@RequestMapping("/api/user/cart")
public class CartsController {
  
	@Autowired
	CartsService cartsService;
	
	@GetMapping("/{id}")
	public Carts getCartOfUser(@PathVariable UUID id) {
		return cartsService.getCartByUserId(id);
	}
}
