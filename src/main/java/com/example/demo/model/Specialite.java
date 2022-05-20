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
@Table(name = "specialite")
@Data
public class Specialite {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String delegation;
	private String CAP;
	private String BTP;
	private String BTS;

	private String sexe;
	private String lib_specialite;
	private String permis;




}
