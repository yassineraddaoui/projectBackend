package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	private String matricule;
	private Date date;
	private String message;
	
	public Notification(String matricule, Date date, String message) {
		this.matricule = matricule;
		this.date = date;
		this.message = message;
	}
	
}
