package com.web.bookapp.book;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Table(name = "book")
@Entity
public class Book {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "title must not be empty")
	@Column(name = "title")
	private String title;
	
	@NotBlank(message = "author must not be empty")
	@Column(name = "author")
	private String author;
	@Column(name = "published_date")
	private LocalDate publishedDate;
	
	public Book(int id, String title, String author, LocalDate publishedDate) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publishedDate = publishedDate;
	}
	public Book(String title, String author, LocalDate publishedDate) {
		this.title = title;
		this.author = author;
		this.publishedDate = publishedDate;
	}
	public Book() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public LocalDate getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publishedDate=" + publishedDate + "]";
	}
	

}
