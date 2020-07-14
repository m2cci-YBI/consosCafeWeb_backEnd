package com.example.demo.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Programmeur;
/*Classe DAO des Programmeurs*/
public interface ProgrammeurRepository extends JpaRepository<Programmeur, Integer> {

	Programmeur findByNomAndPrenom(String nom, String Prenom);

	Programmeur findByNom(String nom);

}
