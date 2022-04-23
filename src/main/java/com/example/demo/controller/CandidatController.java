package com.example.demo.controller;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Generator;
import com.example.demo.model.Candidat;
import com.example.demo.model.FamilleChomage;
import com.example.demo.model.FamilleCouple;
import com.example.demo.model.Formation;
import com.example.demo.model.HandicapFamille;
import com.example.demo.model.NiveauEtude;
import com.example.demo.model.NiveauSuperieur;
import com.example.demo.repository.CandidatRepository;
import com.example.demo.repository.FamilleCoupleRepository;
import com.example.demo.repository.FamilleRepository;
import com.example.demo.repository.FormationRepository;
import com.example.demo.repository.HandicapRepository;
import com.example.demo.repository.NiveauEtudeRepository;
import com.example.demo.repository.NiveauSuperieurRepository;
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping("/api/v1/")
public class CandidatController  {
	@Autowired
	private CandidatRepository candidatRepository;
	@Autowired 
	private HandicapRepository handicapRepository;
	@Autowired
	private FamilleRepository familleRepository;
	@Autowired
	private FamilleCoupleRepository familleCoupleRepository;
	@Autowired 
	private NiveauEtudeRepository niveauEtudeRepository; 
	@Autowired
	private NiveauSuperieurRepository niveauSupRepository;
	@Autowired
	private FormationRepository formationRepository;
    @SuppressWarnings("unused")
	private final static String USER_NOT_FOUND_MSG =
            "user with CIN %s not found";

	@GetMapping("/candidats")
	  public ResponseEntity<List<Candidat>> getAllCandidats(@RequestParam(required = false) String cin) {
	    try {
	      List<Candidat> candidats = new ArrayList<Candidat>();
	      if (cin == null)
	    	  candidatRepository.findAll().forEach(candidats::add);
	      else
	    	  candidatRepository.findAllByCin(cin).forEach(candidats::add);
	      if (candidats.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(candidats, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  @PostMapping("/candidats")
	  public ResponseEntity<Candidat> createCandidat(@RequestBody Candidat candidat) {
	    try {
	    	System.out.println(candidat);
		    Optional<Candidat> cond = candidatRepository.findById(candidat.getCin());
		    if (cond.isPresent()) {
			      return new ResponseEntity<>(cond.get(), HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	      Candidat _condidat = candidatRepository
	          .save(new Candidat(null, candidat.getCin(),Generator.generateRandomPassword(6),new Date(), candidat.getDelegation())
);
	      return new ResponseEntity<>(_condidat, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
//	@PostMapping("/candidats")
//	  public void createCandidat(@RequestBody Candidat candidat) {
//		  candidatRepository.save(new Candidat(null, candidat.getCin(),Generator.generateRandomPassword(6),new Date(), candidat.getDelegation() ));
//	  }
	  @GetMapping("/candidat/{cin}")
	  public ResponseEntity<Candidat> getTutorialById(@PathVariable("cin") String cin) {
	    Optional<Candidat> cond = candidatRepository.findById(cin);
	    if (cond.isPresent()) {
	      return new ResponseEntity<>(cond.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  @PutMapping("/candidat/{cin}")
	  public ResponseEntity<Candidat> updateTutorial(@PathVariable("cin") String cin, @RequestBody Candidat c) {

	    Optional<Candidat> candidatx = candidatRepository.findById(cin);
	    if (candidatx.isPresent()) {
	    	handicapRepository.deleteAllByCin(cin);
	    	familleRepository.deleteAllByCin(cin);
	    	familleCoupleRepository.deleteBycin(cin);
	    	niveauSupRepository.deleteAllByCin(cin);
	    	niveauEtudeRepository.deleteAllByCin(cin);
	    	formationRepository.deleteAllByCin(cin);
	      Candidat _candidat = candidatx.get();
	      _candidat.setAdresse(c.getAdresse());
	        _candidat.setNom(c.getNom());
			_candidat.setPrenom(c.getPrenom());
			_candidat.setMail(c.getMail());
			_candidat.setLieuNaiss(c.getLieuNaiss());
			_candidat.setDateNaiss(c.getDateNaiss());
			_candidat.setDateCin(c.getDateCin());
			_candidat.setRangCin(c.getRangCin());
			_candidat.setVersion(_candidat.getVersion()+1);
		    _candidat.setTel(c.getTel());
			_candidat.setTime(new Date());
			_candidat.setSexe(c.getSexe());
			_candidat.setPermis(c.getPermis());
			_candidat.setSituation(c.getSituation());
			_candidat.setParent(c.getParent());
			
			if(c.getSituation().equals("Marié")) {
	    	FamilleCouple familleCouple = c.getFamilleCouple();
	    	familleCouple.setCandidat(_candidat);
	    	familleCoupleRepository.save(familleCouple);
			}
			if(c.getHf().size()>0)	
			for (HandicapFamille i:c.getHf()) {
			    i.setCandidat(_candidat);
				handicapRepository.save(i);
	}
			if(c.getFc().size()>0)	
			for (FamilleChomage j:c.getFc()) {
			    j.setCandidat(_candidat);
			    familleRepository.save(j);
	}
		    NiveauEtude nivEtud=c.getNiveauEtude();
		    nivEtud.setCandidat(_candidat);
		  //  System.out.println(nivEtud);
		    niveauEtudeRepository.save(nivEtud);
		    
		    if(nivEtud.getNiveau_candidat().equals("Superieur")) {
				NiveauSuperieur ns=c.getNiveauSuperieur();
				ns.setCandidat(_candidat);
				niveauSupRepository.save(ns);
		    }
			else if(nivEtud.getNiveau_candidat().equals("Formation Professionnelle")) {
				Formation fr=c.getFormation();
				fr.setCandidat(_candidat);
				formationRepository.save(fr);
			}
		    
	      return new ResponseEntity<>(candidatRepository.save(_candidat), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  @DeleteMapping("/candidats/{cin}")
	  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("cin") String cin) {
	    try {
	    	candidatRepository.deleteById(cin);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	
}
