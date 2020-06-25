package com.example.demo.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;



import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name="LesProgrammeurs")
public class Programmeur implements Serializable {
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
	private  Integer programmeurId;
	
	
	@Size(min = 2,message="nom doit avoir au moins 2 caracteres")
    private  String nom;
	
	private String motDePasse;
	
	private String roles;
	
	@Size(min = 2,message="prenom doit avoir au moins 2 caracteres")	
    private  String prenom;
	
    private  Integer numbureau;
    
    @OneToMany(mappedBy="programmeur",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JsonManagedReference //erreur de boucle infine
    private Collection<Consommation> consosCafe=new HashSet<Consommation>();
    
    
	public void setProgrammeurId(Integer programmeurId) {
		this.programmeurId = programmeurId;
	}


	public Programmeur() {
		super();
		
	}


	public Programmeur(Integer programmeurId,
			 String nom,
			 String prenom,String motDePasse,String roles, Integer numbureau,
			 Collection<Consommation> consosCafe) {
		super();
		this.programmeurId = programmeurId;
		this.nom = nom;
		this.motDePasse=motDePasse;
		this.prenom = prenom;
		this.roles=roles;
		this.numbureau = numbureau;
		this.consosCafe = consosCafe;
	}


	public Integer getProgrammeurId() {
		return programmeurId;
	}



	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public Integer getNumbureau() {
		return numbureau;
	}


	public void setNumbureau(Integer numbureau) {
		this.numbureau = numbureau;
	}


	public Collection<Consommation> getConsosCafe() {
		return consosCafe;
	}


	public void setConsosCafe(Collection<Consommation> consosCafe) {
		this.consosCafe = consosCafe;
	}


	public String getMotDePasse() {
		return motDePasse;
	}


	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}


	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}


	


	


	
    

}
