package com.Novel.VirtualBookStore.entity;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Carts_items {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="cartId",nullable = false)
	private Carts cart;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="bookId",nullable = false)
	private  Books book;
	
	private int quantity;
	
	//getter
	public UUID getId() {
		return id;
	};
	public Carts getCart() {
		return cart;
	}
	public Books getBook() {
		return book;
	}
	public int getQuantity() {
		return quantity;
	}
	
	//setter
	
	public void setCart(Carts cart) {
		this.cart = cart;
	}
	
	public void setBook(Books book) {
		this.book = book;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
