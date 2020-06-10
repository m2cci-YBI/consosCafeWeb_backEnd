package com.example.demo.model;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
public class Programmeur implements Serializable {
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Integer id;
    private  String nom;
    private  String prenom;
    private  Integer nobureau;
    @OneToMany(mappedBy="programmeur",cascade=CascadeType.ALL)
    @JsonManagedReference //erreur de boucle infine
    private Set<Consommation> consosCafe=new HashSet<Consommation>();
    
    
	public Programmeur(String nom, String prenom, Integer nobureau) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.nobureau = nobureau;
		
	}


	
    

}
