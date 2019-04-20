package com.example.resztki.dao;

import com.example.resztki.domain.CustomerDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository <CustomerDomain,Integer> {


}
