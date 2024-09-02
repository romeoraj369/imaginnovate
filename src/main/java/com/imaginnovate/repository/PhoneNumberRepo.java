package com.imaginnovate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imaginnovate.entity.PhoneNumber;

@Repository
public interface PhoneNumberRepo extends JpaRepository<PhoneNumber, Long> {

}
