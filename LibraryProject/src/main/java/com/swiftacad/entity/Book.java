package com.swiftacad.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("library")
public class Book{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@NotNull
	@Size(min = 3, max = 50)
	private String nameOfBook;
	
	@ManyToOne
	@JoinColumn(name="library_id")
	@JsonIgnoreProperties("books")
	private Library library;
	
	@ManyToMany(mappedBy = "books")
	@JsonIgnoreProperties("books")
	private List<Visitor> visitors;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true, referencedColumnName = "id")
	@JsonIgnoreProperties("book")
	private BookInfo bookInfo;
	
	public Book() {
		
	}

	public Book(Long id, String nameOfBook) {
		super();
		this.id = id;
		this.nameOfBook = nameOfBook;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameOfBook() {
		return nameOfBook;
	}

	public void setNameOfBook(String nameOfBook) {
		this.nameOfBook = nameOfBook;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public List<Visitor> getVisitors() {
		return visitors;
	}

	public void setVisitors(List<Visitor> visitors) {
		this.visitors = visitors;
	}

	public BookInfo getBookInfo() {
		return bookInfo;
	}

	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	
}
