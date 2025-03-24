package com.Novel.VirtualBookStore.RepositoryInterface;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Novel.VirtualBookStore.entity.Books;

@Repository
public interface BooksRespository extends JpaRepository<Books, UUID> {

	List<Books> findByTitle(String title);

}
