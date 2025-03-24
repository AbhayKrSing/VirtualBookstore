package com.Novel.VirtualBookStore.Controller;

import com.Novel.VirtualBookStore.entity.Books;
import com.Novel.VirtualBookStore.Service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    @Autowired
    private BooksService bookService;

    @PostMapping
    public Books createBook(@RequestBody Books book) {
        return bookService.saveBook(book);
    }

    @GetMapping
    public List<Books> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Books getBookById(@PathVariable UUID id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public Books updateBook(@PathVariable UUID id, @RequestBody Books book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable UUID id) {
        return bookService.deleteBook(id);
    }

    @GetMapping("/search")
    public List<Books> searchBooksByTitle(@RequestParam String title) {
        return bookService.findBooksByTitle(title);
    }
}