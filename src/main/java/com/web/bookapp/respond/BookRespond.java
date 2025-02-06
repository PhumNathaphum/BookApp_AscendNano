package com.web.bookapp.respond;

import java.util.List;

import com.web.bookapp.book.Book;

public class BookRespond {
	private String status;
	private String desc;
	private List<Book> data;
	private Book book;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<Book> getData() {
		return data;
	}
	public void setData(List<Book> data) {
		this.data = data;
	}
	public BookRespond(String status, String desc) {
		this.status = status;
		this.desc = desc;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	@Override
	public String toString() {
		return "BookRespond [status=" + status + ", desc=" + desc + ", data=" + data + ", book=" + book + "]";
	}
	
	

	
	
}
