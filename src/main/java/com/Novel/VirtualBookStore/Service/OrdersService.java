package com.Novel.VirtualBookStore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Novel.VirtualBookStore.entity.Orders; 
import com.Novel.VirtualBookStore.RepositoryInterface.OrdersRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;
    
    @Autowired
    private UserService userService;
    // Save an Order
    public Orders saveOrder(Orders order) {
    	order.setOrderDate(new Date());
        return ordersRepository.save(order);
    }

    // Get all Orders
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    // Get an Order by ID
    public Orders getOrderById(UUID id) {
        Optional<Orders> orderOptional = ordersRepository.findById(id);
        return orderOptional.orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Update an Order
    public Orders updateOrder(UUID id, Orders newOrder) {
        Optional<Orders> orderOptional = ordersRepository.findById(id);
        if (orderOptional.isPresent()) {
            Orders oldOrder = orderOptional.get();
            oldOrder.setTotalAmount(newOrder.getTotalAmount()); 
            return ordersRepository.save(oldOrder);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

    // Delete an Order
    public String deleteOrder(UUID id) {
        ordersRepository.deleteById(id);
        return "Order deleted";
    }
    //find order by user id
	public List<Orders> getOrdersByUserId(UUID id) {
		userService.getUserById(id);
		return ordersRepository.findByUserId(id);
	}
}