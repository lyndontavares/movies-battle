package com.mycompany.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycompany.models.Round;
import com.mycompany.models.User;
import com.mycompany.models.enums.Choice;

@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {
 
	void deleteAllByUser(User user);
	
	List<Round> findByUser(User user);
	
	//List<Round> findByUserAndChoice(User user, Choice choice);
	
	Optional<Round> findByUserAndChoice(User user, Choice choice);
}
