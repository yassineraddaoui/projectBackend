package com.example.demo.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
@Entity
@Table(name="famille_chomeur")
@Data
public class FamilleChomage implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cin_chomeur;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="candidat_cin", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	private Candidat candidat;
	private String nom_chomeur;
	private String prenom_chomeur;
	private Date date_naissance_chomeur;

}
