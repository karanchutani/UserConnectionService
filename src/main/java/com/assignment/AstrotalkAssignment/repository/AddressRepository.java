package com.assignment.AstrotalkAssignment.repository;

import com.assignment.AstrotalkAssignment.model.AddresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddresEntity,Long> {

}
