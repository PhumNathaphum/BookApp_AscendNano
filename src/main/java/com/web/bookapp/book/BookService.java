package com.web.bookapp.book;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.bookapp.constant.MessageText;
import com.web.bookapp.respond.BookRespond;

@Service
public class BookService {
	
	private final BookRepository bookRepository;
	
	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public ResponseEntity<BookRespond> getBookByAuthor(String author){
		BookRespond res = new BookRespond("200",MessageText.SEARCH_SUCCESS);
		res.setData(bookRepository.findByAuthor(author));
		return new ResponseEntity<BookRespond>(res, HttpStatus.OK);
	}
	
	public ResponseEntity<BookRespond> saveBook(Book newBook) {
		//validate published date
		if(newBook.getPublishedDate().getYear() < 1000) {
			BookRespond res = new BookRespond("400",MessageText.ERROR_PDATE_INVALID);
			return new ResponseEntity<BookRespond>(res, HttpStatus.BAD_REQUEST);
		}
		if(newBook.getPublishedDate().isAfter(LocalDate.now())) {
			if(newBook.getPublishedDate().isAfter(LocalDate.now().plusYears(543))){
				BookRespond res = new BookRespond("400",MessageText.ERROR_PDATE_INVALID);
				return new ResponseEntity<BookRespond>(res, HttpStatus.BAD_REQUEST);
			} else {
				newBook.setPublishedDate(newBook.getPublishedDate().minusYears(543));
			}
		}
		
		BookRespond res = new BookRespond("201",MessageText.CREATE_SUCCESS);
		res.setBook(bookRepository.save(newBook));
		return new ResponseEntity<BookRespond>(res, HttpStatus.CREATED);
	}
}
