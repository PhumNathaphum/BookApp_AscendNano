package com.web.bookapp.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface  BookRepository extends JpaRepository<Book, Integer>{
	
	@Query("SELECT b FROM Book b WHERE b.author = ?1 ")
	List<Book> findByAuthor(String author);
	
}
