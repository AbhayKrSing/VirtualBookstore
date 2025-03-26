package com.Novel.VirtualBookStore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Novel.VirtualBookStore.RepositoryInterface.CartItemsRepository;
import com.Novel.VirtualBookStore.entity.Books;
import com.Novel.VirtualBookStore.entity.Carts;
import com.Novel.VirtualBookStore.entity.Carts_items; // Assuming this is your CartItems entity
import com.Novel.VirtualBookStore.util.CartBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class Carts_itemService {

    @Autowired
    private CartItemsRepository cartItemsRepository;
    
    @Autowired
    private CartsService cartsService;
    @Autowired
    private BooksService booksService;
    // Save a CartItem
    public Carts_items saveCartItem(CartBody cartbody) {
    	Carts_items carts_items=new Carts_items();
    	Carts cart = cartsService.getCartByUserId(cartbody.getUserId());
    	Books book=booksService.getBookById(cartbody.getBookId());
    	carts_items.setBook(book);
    	carts_items.setCart(cart);
    	carts_items.setQuantity(cartbody.getQuantity());
        return cartItemsRepository.save(carts_items);
    }

    // Get all CartItems
    public List<Carts_items> getAllCartItems() {
        return cartItemsRepository.findAll();
    }

    // Get a CartItem by ID
    public Carts_items getCartItemById(UUID id) {
        Optional<Carts_items> cartItemOptional = cartItemsRepository.findById(id);
        return cartItemOptional.orElseThrow(() -> new RuntimeException("CartItem not found"));
    }

    // Update a CartItem
    public Carts_items updateCartItem(UUID id, Carts_items newCartItem) {
        Optional<Carts_items> cartItemOptional = cartItemsRepository.findById(id);
        if (cartItemOptional.isPresent()) {
        	Carts_items oldCartItem = cartItemOptional.get();
            // Update fields as needed
            oldCartItem.setBook(newCartItem.getBook()); // Assuming CartItems has a reference to a Book entity
            return cartItemsRepository.save(oldCartItem);
        } else {
            throw new RuntimeException("CartItem not found");
        }
    }

    // Delete a CartItem
    public String deleteCartItem(UUID id) {
        cartItemsRepository.deleteById(id);
        return "CartItem deleted";
    }
}