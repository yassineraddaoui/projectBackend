package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.NiveauEtude;
@Repository
public interface NiveauEtudeRepository extends JpaRepository<NiveauEtude, Long> {
	@Transactional
    @Modifying
	@Query(value = "DELETE FROM niveau_etude f where (f.candidat_cin=?) ",nativeQuery = true)
	void deleteAllByCin(@Param("candidat_cin") String c);

}
