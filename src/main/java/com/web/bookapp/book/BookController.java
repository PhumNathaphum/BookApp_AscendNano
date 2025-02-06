package com.web.bookapp.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.bookapp.respond.BookRespond;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {
	
	private final BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@GetMapping
	public ResponseEntity<BookRespond> getBookByAuthor(@RequestParam String author){
		return bookService.getBookByAuthor(author);
	}
	
	@PostMapping
	public ResponseEntity<BookRespond> saveBook(@Valid @RequestBody Book book) {
		return bookService.saveBook(book);
	}
}
