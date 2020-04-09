package com.swiftacad.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="library")
@JsonIgnoreProperties("library")
public class Library {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	@NotNull
	@Size(min = 3, max = 50)
	private String name;
	
	@Column
	@Min(1000)
	@Max(2020)
	private int yearOfConstruction;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(unique = true, referencedColumnName = "id")
	private Address address;
	
	@OneToMany(mappedBy = "library")
	private List<Book> books;
	
	@OneToMany(mappedBy = "library")
	private List<Visitor> visitors;
	
	public Library() {
		
	}

	public Library(Long id, String name, int yearOfConstruction, Address address, List<Book> books,
			List<Visitor> visitors) {
		super();
		this.id = id;
		this.name = name;
		this.yearOfConstruction = yearOfConstruction;
		this.address = address;
		this.books = books;
		this.visitors = visitors;
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

	public int getYearOfConstruction() {
		return yearOfConstruction;
	}

	public void setYearOfConstruction(int yearOfConstruction) {
		this.yearOfConstruction = yearOfConstruction;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Visitor> getVisitors() {
		return visitors;
	}

	public void setVisitors(List<Visitor> visitors) {
		this.visitors = visitors;
	}
	
}
