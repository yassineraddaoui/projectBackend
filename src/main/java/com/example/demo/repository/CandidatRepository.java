package com.example.demo.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Candidat;
@EnableJpaRepositories
@Repository
public interface CandidatRepository extends JpaRepository<Candidat, String>{
	  List<Candidat> findAllByCin(String cin);
	  Boolean existsByCin(String cin);
	  Optional<Candidat> findByCin(String cin);

}