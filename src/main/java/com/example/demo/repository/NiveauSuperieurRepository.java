package com.example.demo.repository;

import javax.transaction.Transactional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.NiveauSuperieur;

public interface NiveauSuperieurRepository extends JpaRepository<NiveauSuperieur, Long> {
	@Transactional
    @Modifying
	@Query(value = "DELETE FROM niveau_superieur f where (f.candidat_cin=?) ",nativeQuery = true)
	void deleteAllByCin(@Param("candidat_cin") String c);

}
