package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor

@SuppressWarnings("serial")
@Entity
@Table(name = "domaine_specialite")
@Data
public class DomainSpecialite implements Serializable {
	@Id
	@ManyToOne
	private Db_domaine_sp domaine;
	@Id
	@ManyToOne()
	private Candidat candidat;


}
