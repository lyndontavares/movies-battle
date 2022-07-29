package com.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.models.Round;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {
 

}
