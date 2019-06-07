package com.example.resztki.dao;

import com.example.resztki.domain.PriceDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceDomain, Integer> {
}
