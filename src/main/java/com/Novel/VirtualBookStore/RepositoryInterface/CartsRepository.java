package com.Novel.VirtualBookStore.RepositoryInterface;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Novel.VirtualBookStore.entity.Carts;
import com.Novel.VirtualBookStore.entity.User;

@Repository
public interface CartsRepository extends JpaRepository<Carts, UUID> {


	Carts findByUserId(UUID id);


}
