package com.mycompany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.models.Round;
import com.mycompany.models.User;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {
 
	void deleteAllByUser(User user);
	
	List<Round> findByUser(User user);

}
