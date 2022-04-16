package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.HandicapFamille;
import com.example.demo.repository.CandidatRepository;
import com.example.demo.repository.HandicapRepository;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping("/api/v1/")
public class HandicapController {
		@Autowired
		private HandicapRepository handicapRepository;
		@Autowired
		private CandidatRepository candidatRepository;
		
	    @GetMapping("/candidat/{cin}/handicaps")
	    public Page<HandicapFamille> getAllHandicapsByCondidatCin(@PathVariable (value = "cin") String cin,
	                                                Pageable pageale) {
	        return handicapRepository.findByCandidatCin(cin,pageale);
    }


	    @PostMapping("/candidat/{cin}/handicap")
	    public HandicapFamille createHandicap(@PathVariable (value = "cin") String cin,
	                                  @RequestBody HandicapFamille handicapFamille) {
	        return candidatRepository.findById(cin).map(condidat -> {
	        	handicapFamille.setCandidat(condidat);
	            return handicapRepository.save(handicapFamille);
	        }).orElseThrow(() -> new ResourceNotFoundException("cin " + cin + " not found"));
	    }
//	    @PostMapping("/candidat/{cin}/handicaps")
//	    public ArrayList<HandicapFamille> createHandicaps(@PathVariable (value = "cin") String cin,
//	                                  @RequestBody ArrayList<HandicapFamille> handicapFamilleList) {
//	    			Optional<Condidat> candidat = condidatRepository.findById(cin);
//	    			for (HandicapFamille i:handicapFamilleList) {
//	    					    i.setCandidat(candidat);
//								handicapRepository.save(i);
//	    			}
//	    			return handicapFamilleList;
//
//	    
//
//
//	    }
	    }

