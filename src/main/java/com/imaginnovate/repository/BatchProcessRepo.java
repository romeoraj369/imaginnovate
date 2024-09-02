package com.imaginnovate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imaginnovate.entity.BatchProcess;

@Repository
public interface BatchProcessRepo extends JpaRepository<BatchProcess, Long>{

}
