package com.example.demo.controller;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Candidat;
import com.example.demo.repository.CandidatRepository;
import com.example.demo.services.AdminPDFExporter;
import com.example.demo.services.ExportPdfService;
import com.lowagie.text.DocumentException;
@CrossOrigin(origins= {"*"}, maxAge = 4800 )
@RestController
@RequestMapping("/admin")
public class AdminController {
	
    @Autowired
    private ExportPdfService exportPdfService;

	@Autowired 
	CandidatRepository candidatRepository;
	@GetMapping("/moderator/candidats")
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
	
	@GetMapping(value = "/moderator/exportcandidats")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Candidat> listUsers = candidatRepository.findAll(Sort.by("nom"));
         
        AdminPDFExporter exporter = new AdminPDFExporter(listUsers);
        exporter.export(response);
         
    }
	@GetMapping("/exportcandidat/{cin}")
	public void exportcandidat(HttpSession session,@PathVariable("cin") 
	String cin, HttpServletResponse response) throws DocumentException, IOException{
        Map<String, Object> data = createData(cin);
        ByteArrayInputStream exportedData = exportPdfService.exportReceiptPdf("Candidat", data);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=condidature.pdf");
        IOUtils.copy(exportedData, response.getOutputStream());
		}

	private Map<String, Object> createData(String cin) {
		Optional<Candidat> candidat= candidatRepository.findByCin(cin);
		if(candidat.isPresent()) {
			Candidat c=candidat.get();
		    Map<String, Object> data = new HashMap<>();
		    data.put("candidat", candidat.get());
		    String x= c.getDateNaiss().toString().substring(0, 10);
		    data.put("DateNaiss",x);
		    String y= c.getCreated_date().toString().substring(0, 10);
		    data.put("DateCreation",y);
		    data.put("specialite",c.getFc());
		    return data;
			}
		return null;
	}
	@DeleteMapping("/delete/{cin}")
	  public ResponseEntity<HttpStatus> deleteCandidat(@PathVariable("cin") String cin) {
	    try {
	    	candidatRepository.deleteById(cin);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}

