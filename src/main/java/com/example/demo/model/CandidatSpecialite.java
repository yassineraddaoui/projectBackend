package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@ManyToOne
	public Specialite specialite;
	@Id
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="candidat_cin", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
	private Candidat candidat;


}
