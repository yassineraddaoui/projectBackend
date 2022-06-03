package com.example.demo.model;
import javax.persistence.Column;
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
	
	private String CAP;
	private String BTP;
	private String BTS;
	@Column(name = "delegation")
	private String delegation;

	private String sexe;
	private String lib_specialite;
	private String permis;
	public Specialite(String delegation, String CAP, String BTP, String BTS, String sexe, String lib_specialite,
			String permis) {
		this.delegation = delegation;
		this.CAP = CAP;
		this.BTP = BTP;
		this.BTS = BTS;
		this.sexe = sexe;
		this.lib_specialite = lib_specialite;
		this.permis = permis;
	}




}
