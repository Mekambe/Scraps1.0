package com.example.resztki.dao;

import com.example.resztki.domain.ImageDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageDomain, Integer> {


}
