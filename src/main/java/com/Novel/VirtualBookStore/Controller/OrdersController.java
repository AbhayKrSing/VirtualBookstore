package com.Novel.VirtualBookStore.Controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Novel.VirtualBookStore.Service.OrdersService;
import com.Novel.VirtualBookStore.Service.UserService;
import com.Novel.VirtualBookStore.entity.Orders;
import com.Novel.VirtualBookStore.entity.User;

@RestController
@RequestMapping("/api/user/order")
public class OrdersController {
	@Autowired
	OrdersService ordersService;
	
	@Autowired
	UserService userService;
	//save order
	@PostMapping
	public Orders saveOrder(@RequestBody Orders order,@RequestParam UUID id) {
		User user = userService.getUserById(id);
		order.setUser(user);
		order.setOrderDate(new Date());
		return ordersService.saveOrder(order);
	}
	
	//get order by id
	@GetMapping
	public List<Orders> getOrdersByUserId(@RequestParam UUID id) {
		return ordersService.getOrdersByUserId(id);
	}
	
	
	
	

}
