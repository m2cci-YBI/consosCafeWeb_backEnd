package com.example.demo.Web;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	@GetMapping(path="/consommations")
	public List<Consommation> listConsommation() {
		return consommationRepository.findAll();
	}
	//Ajouter une consommation
	@PostMapping(path="consommations")
	public Consommation save(@RequestBody @Valid Consommation c) throws SQLIntegrityConstraintViolationException,MethodArgumentNotValidException {
		
			return consommationRepository.save(c);  
	}
	//recuperer les programmeurs
	@GetMapping(path="/programmeurs")
	public List<Programmeur> listProgrammeur() {
		return programmeurRepository.findAll();
	}
    //Ajouter un programmeur
	@PostMapping(path="/programmeurs")
	public Programmeur save(@RequestBody @Valid Programmeur p) throws Exception{
		if(programmeurRepository.findByNomAndPrenom(p.getNom(), p.getPrenom())!= null) {
			
			throw new Exception("Ce programmeur existe Deja");
		}else{
			return programmeurRepository.save(p);
		
	}
		}
	//filter les consommations par semaine
	@GetMapping(path="/consommations/{num_semaine}")
		public List<Consommation> listConsoparSem(@PathVariable("num_semaine") int num_semaine){
		return consommationRepository.findByNumSemaine(num_semaine);
	}
			
}



/* Test  programmeur{
"programmeurId": null,
"nom":"john",
"prenom":"dani",
"numbureau":100
               
test consommation

{"numSemaine":9,
    "nbTasses":25,
    "conso_Id":null,
      "programmeur":{
                 "programmeurId": 4 
				}
    }}*/
