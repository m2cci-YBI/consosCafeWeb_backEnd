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
public class ConsosCafeWebBackEndApplication implements CommandLineRunner{

	
	public static void main(String[] args)   {
		SpringApplication.run(ConsosCafeWebBackEndApplication.class, args);
	    
	}

	@Autowired
	private ProgrammeurRepository programmeurRepository;
	@Autowired
	private ConsommationRepository consommationRepository;
	@Override
	public void run(String... args) throws Exception {
	
		
		
		/*Jeux de donnee pour remplir la bd  */
		
		Consommation c1=new Consommation(1,24);
		Consommation c2=new Consommation(3,25);
		Consommation c3=new Consommation(1,50);
		Consommation c4=new Consommation(2,22);
		Consommation c5=new Consommation(1,18);
		Consommation c6=new Consommation(2,10);
		Consommation c7=new Consommation(1,27);
		Consommation c8=new Consommation(3,35);
		
		Programmeur p1=new Programmeur("FAVRE","Jean Marie",412);
		Programmeur p2=new Programmeur("PARENT","Catherine",201);
		Programmeur p3=new Programmeur("ENE","Christian",208);
		Programmeur p4=new Programmeur("WAILLE","Philippe",401);
		
		c1.setProgrammeur(p1);
		c2.setProgrammeur(p1);
		c3.setProgrammeur(p2);		
		c4.setProgrammeur(p2); 
		c5.setProgrammeur(p3);
		c6.setProgrammeur(p3);
		c7.setProgrammeur(p4);
		c8.setProgrammeur(p4);
		
	p1.getConsosCafe().add(c1);
	p1.getConsosCafe().add(c2);
	p2.getConsosCafe().add(c3);
	p2.getConsosCafe().add(c4);
	p3.getConsosCafe().add(c5);
	p3.getConsosCafe().add(c6);
	p4.getConsosCafe().add(c7);
	p4.getConsosCafe().add(c8); 
	
   programmeurRepository.save(p1);
   programmeurRepository.save(p2);
   programmeurRepository.save(p3);
   programmeurRepository.save(p4);
   
		
   
   
   
	}

}
