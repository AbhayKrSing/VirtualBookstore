package com.Novel.VirtualBookStore.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.stereotype.Service;

import com.Novel.VirtualBookStore.RepositoryInterface.BooksRespository;
import com.Novel.VirtualBookStore.entity.Books;

@Service
public class BooksService {
	@Autowired
    private BooksRespository booksRespository;
	
    public Books saveBook(Books b) {
		return booksRespository.save(b);
    }
    
    public List<Books> getAllBooks(){
    	return booksRespository.findAll();
    }
    
    public Books getBookById(UUID id){
    	Optional<Books> bOptional= booksRespository.findById(id);
    	return bOptional.get();
    }
     
    public Books updateBook(UUID id,Books nbook){
    	Optional<Books> op= booksRespository.findById(id);
    	if(op.isPresent()) {
    	    Books oldbook= op.get();
    	    oldbook.setTitle(nbook.getTitle());
    	    oldbook.setPrice(nbook.getPrice());
    	    oldbook.setGenre(nbook.getGenre());
    	    oldbook.setStock(nbook.getStock());
    	  return  booksRespository.save(oldbook);
    	}
    	else {
    	  throw new RuntimeException();
    	}
    }
     
    
    public String deleteBook(UUID id) {
		 booksRespository.deleteById(id);
		 return "Book deleted";
	}

	public List<Books> findBooksByTitle(String title) {
		return booksRespository.findByTitle(title);
	}
    
    
}
