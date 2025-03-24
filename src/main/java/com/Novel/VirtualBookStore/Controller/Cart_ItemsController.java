package com.Novel.VirtualBookStore.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Novel.VirtualBookStore.Service.Carts_itemService;
import com.Novel.VirtualBookStore.entity.Carts_items;

@RestController
@RequestMapping("/api/cart_items")  //cart items can exist even without user as well
public class Cart_ItemsController {
	@Autowired
	Carts_itemService carts_itemService;
	
	@PostMapping
    public Carts_items saveCartItemsAfterOrder(@RequestBody Carts_items Carts_items,@RequestParam UUID cartId) {
		return carts_itemService.saveCartItem(Carts_items,cartId);
	}
	
    
}
