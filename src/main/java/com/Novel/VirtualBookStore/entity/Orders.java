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
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {
    
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", nullable = false)  //for database perspective only[means in database foreign key named userId is added]
	private User user;  //for java code so that we can travel directionally or bi-directionally
	
	@Column(nullable = false)
	private Date orderDate;
	
	@Column(nullable = false)
	private Long totalAmount;	
	
	//getter
	public UUID getId() {
		return id;
	}
	public User getUser() {
		return user;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public Long getTotalAmount() {
		return totalAmount;
	}
	//setter
	public void setUser(User user) {
		this.user = user;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
}
