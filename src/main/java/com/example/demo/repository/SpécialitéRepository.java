package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Specialite;

public interface SpécialitéRepository extends JpaRepository<Specialite, Long> {
		
	@Query(value = " select d.lib_specialite from specialite d , candidat c , formation f where "
			+ "	    d.sexe=c.sexe "
			+ "	    and d.delegation=c.delegation "
			+ "     and c.cin=?1"
			+ "     and f.candidat_cin =?1 "
			+ "     and (d.permis=c.permis "
			+ "     or (d.CAP=f.specialite_formation and f.diplome_formation like 'CAP') "
			+ "     or (d.BTP=f.specialite_formation and f.diplome_formation like 'BTP' ) "
			+ "     or (d.BTS=f.specialite_formation and f.diplome_formation like 'BTS' ) )"
	,nativeQuery = true)
	List<String>  genererListSp(String cin);
	
	
	@Query(value = " select s.* from specialite s where s.lib_specialite=?1"
	,nativeQuery = true)
	Specialite lookForIdByLib(String name);
}
