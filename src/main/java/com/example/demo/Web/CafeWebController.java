package com.example.demo.Web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DAO.ConsommationRepository;
import com.example.demo.DAO.ProgrammeurRepository;
import com.example.demo.model.Consommation;
import com.example.demo.model.Programmeur;
@RestController
public class CafeWebController {
	
	@Autowired
	private ConsommationRepository consommationRepository;
	@Autowired
	private ProgrammeurRepository programmeurRepository;
	
	@GetMapping(path="/")
	public String hello() {
		return "Bienvenue App Cafe WEB";
	}
	
	//Recuperer les consommations
	@GetMapping(path="/consos")
	public List<Consommation> listConsommation() {
		return consommationRepository.findAll();
	}
	//Ajouter une consommation
	@PostMapping(path="consos")
	public Consommation save(@RequestBody Consommation c){
		return consommationRepository.save(c);
		
		/*test Postman
		 * {"numSemaine":9,
            "nbTasses":25,
            "conso_Id":null,
	        "programmeur":{
		                 "id": 4 }
            } 
           */
	}
	//recuperer les programmeurs
	@GetMapping(path="/devs")
	public List<Programmeur> listProgrammeur() {
		return programmeurRepository.findAll();
	}
    //Ajouter un programmeur
	@PostMapping(path="/devs")
	public Programmeur save(@RequestBody Programmeur p){
		return programmeurRepository.save(p);
		/* Test Postman{
            "id": null,
            "nom":"john",
            "prenom":"dani",
            "nobureau":100
                          }*/
	}
	//filter les consommations par semaine
	@GetMapping(path="/consos/{num_semaine}")
		public List<Consommation> listConsoparSem(@PathVariable("num_semaine") int num_semaine){
		return consommationRepository.findByNumSemaine(num_semaine);
	}
			
}
