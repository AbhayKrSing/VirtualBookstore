package com.Novel.VirtualBookStore.util;

import java.util.UUID;

public class CartBody {
  private UUID bookId;
  private UUID userId;
  private int quantity;
public UUID getBookId() {
	return bookId;
}
public void setBookId(UUID bookId) {
	this.bookId = bookId;
}
public UUID getUserId() {
	return userId;
}
public void setUserId(UUID userId) {
	this.userId = userId;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
  
}
