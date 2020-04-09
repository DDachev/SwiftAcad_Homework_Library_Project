package com.swiftacad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("library")
public class BookInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@NotNull
	@Size(min = 3, max = 30)
	private String genre;
	
	@Column
	@NotNull
	@Size(min = 3, max = 40)
	private String author;
	
	@Column
	@Min(3)
	private int pages;
	
	@Column
	@Min(100)
	@Max(2020)
	private int yearOfPublishing;
	
	@OneToOne(mappedBy = "bookInfo")
	@JsonIgnoreProperties("bookInfo")
	private Book book;
	
	public BookInfo() {
		
	}

	public BookInfo(Long id, String genre, String author, int pages, int yearOfPublishing) {
		super();
		this.id = id;
		this.genre = genre;
		this.author = author;
		this.pages = pages;
		this.yearOfPublishing = yearOfPublishing;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getYearOfPublishing() {
		return yearOfPublishing;
	}

	public void setYearOfPublishing(int yearOfPublishing) {
		this.yearOfPublishing = yearOfPublishing;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
