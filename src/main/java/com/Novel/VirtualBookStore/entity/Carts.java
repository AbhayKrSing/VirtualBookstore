package com.Novel.VirtualBookStore.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Carts {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
	
	
	@Column(nullable = false)
	private Date createdAt;
	
	@OneToOne(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	@JoinColumn(name="UserId",nullable = false,unique = true )
	private User user;               //techinally we need to put cart into user.
	
	
	//getter
	public UUID getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	
	//setter
   public void setUser(User user) {
	this.user = user;
   };
   public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
 }
	
}
