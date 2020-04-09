package com.swiftacad.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties("library")
public class Visitor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@NotNull
	@Size(min = 3, max = 40)
	private String name;
	
	@Column
	@Min(10)
	@Max(120)
	private int age;
	
	@ManyToOne
	@JoinColumn(name="library_id")
	@JsonIgnoreProperties("visitors")
	private Library library;
	
	@ManyToMany
	@JoinTable(name="visitor_book", 
	joinColumns = @JoinColumn(name="visitor_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name="book_id",referencedColumnName = "id"))
	@JsonIgnoreProperties("visitors")
	private List<Book> books;
	
	public Visitor() {
		
	}

	public Visitor(Long id, String name, int age, List<Book> books) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.books = books;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public void setBook(Book newBook) {
		this.books.add(newBook);
	}
	
}
