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
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="LesProgrammeurs")
@NoArgsConstructor@AllArgsConstructor
public class Programmeur implements Serializable {
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Integer programmeurId;
	
	
	@Size(min = 2,message="nom doit avoir au moins 2 caracteres")
    private  String nom;
	
	@Size(min = 2,message="prenom doit avoir au moins 2 caracteres")	
    private  String prenom;
	
    private  Integer numbureau;
    
    @OneToMany(mappedBy="programmeur",cascade=CascadeType.ALL)
    @JsonManagedReference //erreur de boucle infine
    private Set<Consommation> consosCafe=new HashSet<Consommation>();
    
    
	public Programmeur(String nom, String prenom, Integer nobureau) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.numbureau = nobureau;
		
	}


	
    

}
