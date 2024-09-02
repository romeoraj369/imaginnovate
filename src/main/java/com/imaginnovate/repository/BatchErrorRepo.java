package com.imaginnovate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imaginnovate.entity.BatchErrors;

@Repository
public interface BatchErrorRepo extends JpaRepository<BatchErrors, Long>{

}
