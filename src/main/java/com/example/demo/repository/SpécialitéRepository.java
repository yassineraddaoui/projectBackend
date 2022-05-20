package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Candidat;
import com.example.demo.model.Specialite;

public interface SpécialitéRepository extends JpaRepository<Specialite, Long> {
	@Query(value = "select * from specialite d where "
			+ "d.sexe=?.sexe "
			+ "and d.delegation=?.delegation and "
			+ "(d.permis=?.permis or"
			+ "(d.CAP=?.formation.specialite_formation and ?.formation.diplome_formation like'CAP' )or"
			+ "(d.BTP=?.formation.specialite_formation and ?.formation.diplome_formation like 'BTP' )or"
			+ "(d.BTS=?.formation.specialite_formation and ?.formation.diplome_formation like 'BTS' ))"
			,nativeQuery = true)
	List<Specialite>  genererListSp(Candidat c);
	@Query(value = "select * from specialite d where "
			+ "d.permis=?"
			,nativeQuery = true)
	List<Specialite> genererListSptst(String permis);
}
