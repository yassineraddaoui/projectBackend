package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@SuppressWarnings("serial")
@Entity
@Data
@Table(name="famille_couple", uniqueConstraints = {
		@UniqueConstraint(name ="cin_couple_const",columnNames = "cin_couple" ) ,
		@UniqueConstraint(name ="candidat_cin_const",columnNames = "candidat_cin" ) 

})
@NoArgsConstructor
@AllArgsConstructor

public class FamilleCouple implements Serializable {
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String cin_couple;
	@OneToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "candidat_cin", nullable=false)
	@JsonIgnore
	private Candidat candidat;
	private String nom_couple;
	private String prenom_couple;

}
