package com.example.demo.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Consommation;

public interface ConsommationRepository extends JpaRepository<Consommation, Integer>{

	List<Consommation> findByNumSemaine(int numSemaine);
	
}
