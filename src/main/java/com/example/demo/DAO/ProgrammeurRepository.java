package com.example.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Programmeur;

@Repository
public interface ProgrammeurRepository extends JpaRepository<Programmeur,Integer> {

}