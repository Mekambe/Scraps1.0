package com.example.resztki.dao;


import com.example.resztki.domain.UsersDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersDomain, Integer> {
}
