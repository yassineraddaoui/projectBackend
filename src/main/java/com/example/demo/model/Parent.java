package com.example.demo.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Parent {

	private String nom_pere;
	private String prenom_pere;
	private String cin_pere;
	
	private String nom_mere;
	private String prenom_mere;
	private String cin_mere;

}
