package com.swiftacad.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swiftacad.entity.BookInfo;

@Repository
public interface BookInfoRepository extends CrudRepository<BookInfo, Long>{

	List<BookInfo> getBookInfosByGenre(String genre);
	List<BookInfo> getBookInfosByAuthor(String author);
}
