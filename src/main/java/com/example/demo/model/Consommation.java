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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="LesConsommations",uniqueConstraints=@UniqueConstraint(columnNames= {"numSemaine","programmeur_id"}))
@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
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
    
    
	public Consommation(int numSemaine, Integer nbTasses) {
		super();
		this.numSemaine = numSemaine;
		this.nbTasses = nbTasses;
	}


	
	public int getProgrammeur_Id() {		
		return programmeur.getProgrammeurId();
	}



	
	/*
	public String getNomCompletProgrammeur() {
		return programmeur.getNom() + "  " +programmeur.getPrenom();
	}*/
	
	


	
    
    

	
	
    
    
}




