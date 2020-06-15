package com.example.demo.model;


import java.io.Serializable;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;



@Entity
@Table(name="LesConsommations",uniqueConstraints=@UniqueConstraint(columnNames= {"numSemaine","programmeur_id"}))


public class Consommation implements Serializable {
    @Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer consommationId;
    
    @NotNull(message = "Veillez selectionner une semaine")
	private int numSemaine;  
    
    @NotNull(message = "Veillez indiquer le nombre de tasses consommé")
    private int nbTasses;
    
    @ManyToOne
    @JoinColumn(name="programmeur_id") 
    @JsonBackReference //erreur de boucle infinie
    private Programmeur programmeur;
    
   
	public Consommation() {
		super();
		
	}

	public Consommation(Integer consommationId,  int numSemaine,
			 int nbTasses, Programmeur programmeur) {
		super();
		this.consommationId = consommationId;
		this.numSemaine = numSemaine;
		this.nbTasses = nbTasses;
		this.programmeur = programmeur;
	}



	public int getProgrammeur_Id() {		
		return programmeur.getProgrammeurId();
	}



	public Integer getConsommationId() {
		return consommationId;
	}



	public void setConsommationId(Integer consommationId) {
		this.consommationId = consommationId;
	}



	public int getNumSemaine() {
		return numSemaine;
	}



	public void setNumSemaine(int numSemaine) {
		this.numSemaine = numSemaine;
	}



	public int getNbTasses() {
		return nbTasses;
	}



	public void setNbTasses(int nbTasses) {
		this.nbTasses = nbTasses;
	}



	public Programmeur getProgrammeur() {
		return programmeur;
	}



	public void setProgrammeur(Programmeur programmeur) {
		this.programmeur = programmeur;
	}



	
	/*
	public String getNomCompletProgrammeur() {
		return programmeur.getNom() + "  " +programmeur.getPrenom();
	}*/
	
	


	
    
    

	
	
    
    
}




