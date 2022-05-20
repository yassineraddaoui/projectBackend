package com.example.demo.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	  Optional<Admin> findByMatricule(String mat);


	  Boolean existsByMatricule(String matricule);
	  @Transactional
	    @Modifying
		@Query(value = "DELETE FROM admin a where (a.matricule=?) ",nativeQuery = true)
	  
		void deleteByMatricule(String matricule);

}
