package com.swiftacad.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swiftacad.entity.Visitor;

@Repository
public interface VisitorRepository extends CrudRepository<Visitor, Long>{

	List<Visitor> getVisitorsByAge(int age);
}
