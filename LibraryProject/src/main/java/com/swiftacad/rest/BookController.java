package com.swiftacad.rest;

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
import com.swiftacad.repository.BookRepository;
import com.swiftacad.repository.LibraryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository bookrepo;
	
	@Autowired
	private LibraryRepository libraryRepo;
	
	@RequestMapping(value = "/library/{l_id}/book", method = RequestMethod.POST)
	public ResponseEntity<Book> addBookInLibrary(@PathVariable("l_id") Long l_id, @Valid @RequestBody Book book) {
		Optional<Library> optional = libraryRepo.findById(l_id);
		if (optional.isPresent()) {
			book.setLibrary(optional.get());
			bookrepo.save(book);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/library/{l_id}/book", method = RequestMethod.PUT)
	public ResponseEntity<Book> changeBook(@PathVariable("l_id") Long l_id, @Valid @RequestBody Book book) {
		Optional<Library> optional = libraryRepo.findById(l_id);
		if (optional.isPresent()) {
			book.setLibrary(optional.get());
			bookrepo.save(book);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "book/{id}", method = RequestMethod.GET)
	public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
		Optional<Book> optional = bookrepo.findById(id);
		if (optional.isPresent()) {
			return new ResponseEntity<>(optional.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "book/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteBookById(@PathVariable("id") Long id) {
		if (bookrepo.existsById(id)) {
			bookrepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "book/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<Book> getByNameOfBook(@PathVariable("name") String nameOfBook) {
		Book book = bookrepo.getByNameOfBook(nameOfBook);
		if (!book.equals(null)) {
			return new ResponseEntity<>(book, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
