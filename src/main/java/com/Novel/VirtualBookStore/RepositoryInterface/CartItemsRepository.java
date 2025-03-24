package com.Novel.VirtualBookStore.RepositoryInterface;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Novel.VirtualBookStore.entity.Carts_items;

@Repository
public interface CartItemsRepository extends JpaRepository<Carts_items, UUID> {

}
