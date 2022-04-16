package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Formation;
@EnableJpaRepositories
@Repository
public interface FormationRepository  extends JpaRepository<Formation, Long>{
@Modifying
@Transactional
@Query(value = "DELETE FROM formation f where (f.candidat_cin=?) ",nativeQuery = true)
	void deleteAllByCin(@Param("candidat_cin") String c);
}
