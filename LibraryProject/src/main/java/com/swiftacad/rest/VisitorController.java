package com.swiftacad.rest;

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

import com.swiftacad.entity.Visitor;
import com.swiftacad.entity.Book;
import com.swiftacad.entity.Library;
import com.swiftacad.repository.BookRepository;
import com.swiftacad.repository.LibraryRepository;
import com.swiftacad.repository.VisitorRepository;

@Controller
public class VisitorController {

	@Autowired
	private VisitorRepository visitorRepo;
	
	@Autowired
	private LibraryRepository libraryRepo;
	
	@Autowired
	private BookRepository bookRepo;
	
	
	@RequestMapping(value = "visitor/library/{id}", method = RequestMethod.POST)
	public ResponseEntity<Visitor> addVisitorInLibrary(@Valid @RequestBody Visitor visitor, @PathVariable("id") Long id) {
		Optional<Library> optional = libraryRepo.findById(id);
		if (optional.isPresent()) {
			visitor.setLibrary(optional.get());
			visitorRepo.save(visitor);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "visitor/library/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Visitor> changeVisitor(@Valid @RequestBody Visitor visitor, @PathVariable("id") Long id) {
		Optional<Library> optional = libraryRepo.findById(id);
		if (optional.isPresent()) {
			visitor.setLibrary(optional.get());
			visitorRepo.save(visitor);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "visitor/{v_id}/book/{b_id}", method = RequestMethod.PATCH)
	public ResponseEntity<Visitor> addBookToVisitorToReadIt(@PathVariable("v_id") Long v_id, @PathVariable("b_id") Long b_id) {
		Optional<Visitor> visitor = visitorRepo.findById(v_id);
		Optional<Book> book = bookRepo.findById(b_id);
		if (visitor.isPresent() && book.isPresent()) {
			visitor.get().setBook(book.get());
			visitorRepo.save(visitor.get());
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "visitor/{id}", method = RequestMethod.GET)
	public ResponseEntity<Visitor> getVisitorById(@PathVariable("id") Long id) {
		Optional<Visitor> optional = visitorRepo.findById(id);
		if (optional.isPresent()) {
			return new ResponseEntity<>(optional.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "visitor/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Visitor> deleteVisitorById(@PathVariable("id") Long id) {
		if (visitorRepo.existsById(id)) {
			visitorRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "visitor/age/{age}", method = RequestMethod.GET)
	public ResponseEntity<List<Visitor>> getVisitorsByAge(@PathVariable("age") int age) {
		List<Visitor> visitors = visitorRepo.getVisitorsByAge(age);
		if(visitors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Visitor>>(visitors, HttpStatus.OK);
	}
	
}
