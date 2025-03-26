package com.Novel.VirtualBookStore.entity;

import java.util.ArrayList;
import java.util.List;
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
   
   @Column(nullable = false)
   private List<String> role=new ArrayList<>();
   
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
   public List<String> getRole() {
	return role;
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
   public void setRole(String role) {
	 this.role.add(role);
  }
   
}
