package com.example.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Programmeur;


public interface ProgrammeurRepository extends JpaRepository<Programmeur,Integer> {
  
	Programmeur findByNomAndPrenom(String nom, String Prenom);
	
}
