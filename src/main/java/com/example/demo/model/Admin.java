package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	private String matricule;
	private String password;
	private String nom;
	private String prenom;
	  @ManyToMany(fetch = FetchType.EAGER)
	  @JoinTable(  name = "user_roles",
	  
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "role_id"))
		@OnDelete(action = OnDeleteAction.CASCADE)
	  private Set<Role> roles = new HashSet<>();
	  public Admin(String matricule, String password) {
		    this.matricule = matricule;
		    this.password = password;
		  }

}
