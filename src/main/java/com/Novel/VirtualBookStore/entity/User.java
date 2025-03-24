package com.Novel.VirtualBookStore.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;
   
   @Column(nullable = false)
   private String username;
   
   @Column(nullable = false,unique = true)
   private String email;
   
   @Column(nullable = false)
   private String password;
   
   
   //getter
   public UUID getId() {
	return id;
   }
   public String getUsername() {
	return username;
   }
   public String getEmail() {
	return email;
   }
   public String getPassword() {
	return password;
   }
   //setter
   public void setUsername(String username) {
	this.username = username;
   }
   public void setEmail(String email) {
	this.email = email;
   }
   public void setPassword(String password) {
	this.password = password;
   }
   
   
}
