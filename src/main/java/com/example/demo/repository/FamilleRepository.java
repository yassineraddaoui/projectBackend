package com.example.demo.repository;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.FamilleChomage;

public interface FamilleRepository extends JpaRepository<FamilleChomage,Long> {
	@Transactional
    @Modifying
	@Query(value = "DELETE FROM famille_chomeur f where (f.candidat_cin=?) ",nativeQuery = true)
	void deleteAllByCin(@Param("candidat_cin") String c);

	void deleteById(String cin);
}
