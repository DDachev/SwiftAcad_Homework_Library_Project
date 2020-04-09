package com.swiftacad.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swiftacad.entity.Library;

@Repository
public interface LibraryRepository extends CrudRepository<Library, Long>{

}
