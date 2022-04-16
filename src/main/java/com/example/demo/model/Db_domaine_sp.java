package com.example.demo.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "db_domaine_sp")
@Data
public class Db_domaine_sp {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int delegation;
	private String CAP;

	private String BTP;
	private String sexe;
	private String lib_domaine;
	private String lib_specialite;




}
