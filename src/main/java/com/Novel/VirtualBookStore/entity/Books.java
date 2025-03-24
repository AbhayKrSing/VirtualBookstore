package com.Novel.VirtualBookStore.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;
   	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String author;
	
	@Column(nullable = false)
	private String genre;
	
	@Column(nullable = false)
	private long price;
	
	@Column(nullable = false)
	private int stock;
	
	
	//getters
	public UUID getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getGenre() {
		return genre;
	}
	public int getStock() {
		return stock;
	}
	public long getPrice() {
		return price;
	}
	public String getAuthor() {
		return author;
	}
	
	//setter
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
}
