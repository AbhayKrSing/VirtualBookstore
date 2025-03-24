package com.Novel.VirtualBookStore.RepositoryInterface;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Novel.VirtualBookStore.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, UUID> {

	List<Orders> findByUserId(UUID id);

}
