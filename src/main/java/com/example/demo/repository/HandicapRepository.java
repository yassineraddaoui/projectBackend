package com.example.demo.repository;
import java.util.Optional;


import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.HandicapFamille;
@EnableJpaRepositories
@Repository
public interface HandicapRepository extends JpaRepository<HandicapFamille,Long> {
	@Transactional
    @Modifying
	@Query(value = "DELETE FROM handicap_famille f where (f.candidat_cin=?) ",nativeQuery = true)
	void deleteAllByCin(@Param("candidat_cin") String c);
	
	Page<HandicapFamille> findByCandidatCin(String cin, Pageable pageable);
    Optional<HandicapFamille> findByIdAndCandidatCin(Long id, String cin);
	
}
