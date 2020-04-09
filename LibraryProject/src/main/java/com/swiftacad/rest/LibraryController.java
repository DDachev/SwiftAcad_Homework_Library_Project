package com.swiftacad.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swiftacad.entity.Book;
import com.swiftacad.entity.Library;
import com.swiftacad.entity.Visitor;
import com.swiftacad.repository.AddressRepository;
import com.swiftacad.repository.BookInfoRepository;
import com.swiftacad.repository.BookRepository;
import com.swiftacad.repository.LibraryRepository;
import com.swiftacad.repository.VisitorRepository;

@Controller
public class LibraryController {

	@Autowired
	private LibraryRepository libraryRepo;

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private VisitorRepository visitorRepo;

	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private BookInfoRepository bookInfoRepo;

	@RequestMapping(value = "library", method = RequestMethod.POST)
	public ResponseEntity<Library> createLibrary(@Valid @RequestBody Library library) {
		Library l = libraryRepo.save(library);
		addressRepo.save(l.getAddress());
		for (Book b : l.getBooks()) {
			b.setLibrary(l);
			bookRepo.save(b);
			bookInfoRepo.save(b.getBookInfo());
		}
		for (Visitor v : l.getVisitors()) {
			v.setLibrary(l);
			v.setBooks(getBooksByNameFromLibrary(l, v));
			visitorRepo.save(v);
		}
		return new ResponseEntity<Library>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "library", method = RequestMethod.PUT)
	public ResponseEntity<Library> changeLibrary(@Valid @RequestBody Library library) {
		Library l = libraryRepo.save(library);
		addressRepo.save(l.getAddress());
		for (Book b : l.getBooks()) {
			b.setLibrary(l);
			bookRepo.save(b);
			bookInfoRepo.save(b.getBookInfo());
		}
		for (Visitor v : l.getVisitors()) {
			v.setLibrary(l);
			v.setBooks(getBooksByNameFromLibrary(l, v));
			visitorRepo.save(v);
		}
		return new ResponseEntity<Library>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "library/{id}", method = RequestMethod.GET)
	public ResponseEntity<Library> getLibraryById(@PathVariable("id") Long id) {
		Optional<Library> optional = libraryRepo.findById(id);
		if (optional.isPresent()) {
			return new ResponseEntity<>(optional.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "library/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Library> deleteLibraryById(@PathVariable("id") Long id) {
		if (libraryRepo.existsById(id)) {
			Optional<Library> optional = libraryRepo.findById(id);
			visitorRepo.deleteAll(optional.get().getVisitors());
			bookRepo.deleteAll(optional.get().getBooks());
			libraryRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	private static List<Book> getBooksByNameFromLibrary(Library library, Visitor visitor) {
		List<Book> booksOfLibrary = library.getBooks();
		List<Book> exsistBook = new ArrayList<>();
		for (Book book : visitor.getBooks()) {
			for (int i = 0; i < booksOfLibrary.size(); i++) {
				if (book.getNameOfBook().equals(booksOfLibrary.get(i).getNameOfBook())) {
					exsistBook.add(booksOfLibrary.get(i));
				}
			}
		}
		return exsistBook;
	}
}
