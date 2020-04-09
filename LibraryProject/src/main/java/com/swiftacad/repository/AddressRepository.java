package com.swiftacad.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swiftacad.entity.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long>{

}
