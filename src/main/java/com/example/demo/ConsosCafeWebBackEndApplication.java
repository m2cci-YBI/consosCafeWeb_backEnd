package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.DAO.ConsommationRepository;
import com.example.demo.DAO.ProgrammeurRepository;
import com.example.demo.model.Consommation;
import com.example.demo.model.Programmeur;


@SpringBootApplication
public class ConsosCafeWebBackEndApplication {

	
	public static void main(String[] args)   {
		SpringApplication.run(ConsosCafeWebBackEndApplication.class, args);
	    
	}

	
}
