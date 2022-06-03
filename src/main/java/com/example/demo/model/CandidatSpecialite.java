package com.example.demo.model;

import java.io.Serializable;

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
@Table(name = "candidat_specialite")
@Data
public class CandidatSpecialite implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	@ManyToOne
	public Specialite specialite;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="candidat_cin", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	private Candidat candidat;
	public CandidatSpecialite(Specialite specialite, Candidat candidat) {
		super();
		this.specialite = specialite;
		this.candidat = candidat;
	}


}
