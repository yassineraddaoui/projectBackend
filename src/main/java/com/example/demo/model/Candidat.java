package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor

@SuppressWarnings("serial")
@Entity
@Table(name = "candidat", uniqueConstraints = {
		@UniqueConstraint(name ="candidat_mail_const",columnNames = "mail" ),
		@UniqueConstraint(name ="candidat_tel_const",columnNames = "tel" )
})
@Data

public class Candidat implements Serializable {
    
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
  
	@Column(name="cin")
	@Id private String cin;

	 
	@OneToMany(mappedBy = "candidat")
	private Collection<HandicapFamille> hf=new ArrayList<HandicapFamille>();
	
	@OneToMany(mappedBy = "candidat")
	private Collection<FamilleChomage> fc=new ArrayList<FamilleChomage>();
	
	@Column(name="code")
	private String code;
	
	private Parent parent;
	
	@Column(name="nom")
	private String nom;
	

	@Column(name="prenom")
	private String prenom;
	@Column(name="tel")
	private String tel;

	@Column(name="dateNaiss")

	private Date dateNaiss;
	@Column(name="mail")

	private String mail;
	@Column(name="created_date",nullable = false,updatable =false)
	private Date created_date;
	@Column(name="lieuNaiss")
	private String lieuNaiss;
	@Column(name="dateCin")
	private Date dateCin;
	@ColumnDefault(value = "0")
	private int rangCin;
	@ColumnDefault( "CURRENT_TIMESTAMP" )
	@Column( name = "last_update", nullable = false )
	private Date time;
	@ColumnDefault(value = "0")
	private int version;
	
    @OneToOne(mappedBy = "candidat")
	private FamilleCouple familleCouple;
    
    @OneToOne(mappedBy = "candidat")
    private NiveauEtude niveauEtude;
    @OneToOne(mappedBy = "candidat")
    private Formation formation;

    @OneToOne(mappedBy = "candidat")
    private NiveauSuperieur niveauSuperieur;

	private String situation;

	private String adresse;

	private String permis;

	private String delegation;
	private String sexe;

	

}
