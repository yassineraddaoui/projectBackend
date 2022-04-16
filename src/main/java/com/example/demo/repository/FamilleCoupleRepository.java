package com.example.demo.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.FamilleCouple;

public interface FamilleCoupleRepository extends JpaRepository<FamilleCouple,String> {
	@Transactional
    @Modifying
	@Query(value = "DELETE FROM famille_couple f where (f.candidat_cin=?) ",nativeQuery = true)
	void deleteBycin(@Param("cin")String cin);
	@Query(value = "SELECT * FROM famille_couple f where (f.candidat_cin=?) ",nativeQuery = true)
	Optional<FamilleCouple> findByCandidat(@Param("cin")String candidat_cin);
	
	
}
