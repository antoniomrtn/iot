package com.test.antonio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.antonio.model.Sim;


@Repository
public interface SimRepository extends JpaRepository<Sim, Integer> {
	
}
