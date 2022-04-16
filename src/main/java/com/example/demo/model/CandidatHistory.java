//package com.example.demo.model;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "Candidat_history")
//public class CandidatHistory {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	private String cin;
//
//	 
//	private Collection<HandicapFamille> hf=new ArrayList<HandicapFamille>();
//	
//	private Collection<FamilleChomage> fc=new ArrayList<FamilleChomage>();
//	
//	private String code;
//	
//	private Parent parent;
//	
//	@Column(name="nom")
//	private String nom;
//	
//
//	@Column(name="prenom")
//	private String prenom;
//	@Column(name="tel")
//	private String tel;
//
//	@Column(name="dateNaiss")
//
//	private Date dateNaiss;
//
//	private String mail;
//	private String lieuNaiss;
//	private Date dateCin;
//	private int rangCin;
//	private Date time;
//	private int version;
//	
//	private FamilleCouple familleCouple;
//    
//    private NiveauEtude niveauEtude;
//    private Formation formation;
//
//    private NiveauSuperieur niveauSuperieur;
//
//	private String situation;
//
//	private String adresse;
//
//	private String permis;
//
//	private String delegation;
//	private String sexe;
//
//}
