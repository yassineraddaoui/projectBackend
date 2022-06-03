package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Candidat;
import com.example.demo.model.CandidatSpecialite;

public interface CandidatSpécialitéRepository extends JpaRepository<CandidatSpecialite,Candidat>{
	@Transactional
	@Modifying
	@Query(value = "delete from candidat_specialite cs where cs.candidat_cin=?1"
	,nativeQuery = true)

	void deleteAllById(String cin);

}
