package com.example.demo.controller;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.example.demo.model.Admin;
import com.example.demo.model.Candidat;
import com.example.demo.model.CandidatSpecialite;
import com.example.demo.model.ERole;
import com.example.demo.model.Notification;
import com.example.demo.model.Role;
import com.example.demo.model.Specialite;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.CandidatRepository;
import com.example.demo.repository.NotificationRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.SpécialitéRepository;
import com.example.demo.services.AdminPDFExporter;
import com.example.demo.services.ExportPdfService;
import com.lowagie.text.DocumentException;
@CrossOrigin(origins= {"*"}, maxAge = 4800 )
@RestController
@RequestMapping("/admin")
public class AdminController {
	 @Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  AdminRepository userRepository;


	  @Autowired
	  PasswordEncoder encoder;

    @Autowired
    private ExportPdfService exportPdfService;
    @Autowired
    RoleRepository roleRepository;
	@Autowired 
	CandidatRepository candidatRepository;
	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	SpécialitéRepository specialiteRepository;

	 @DeleteMapping("/{mat}")
	  public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable("mat") String mat) {
	    try {
	      adminRepository.deleteByMatricule(mat);
  		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
		String username = userDetails.getUsername();

	      Notification n =new Notification(mat,new Date(),"Admin "+mat+" Was Deleted By Admin : "+username);
	      notificationRepository.save(n);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
		@GetMapping("/list")
		public List<Admin> getAdmins(){
			return adminRepository.findAll();		
		}
		@GetMapping("/specialite")
		public List<Specialite> getSp(){
			return specialiteRepository.findAll();	
		}
		@PostMapping("/specialite/add")
		public ResponseEntity<HttpStatus>  addSp(@RequestBody Specialite sp) {
			
			try {
				specialiteRepository.save(sp);
			      return new ResponseEntity<>(HttpStatus.ACCEPTED);
			    } catch (Exception e) {
			      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			    }
			  }
		@DeleteMapping("/specialite/delete/{id}")
		public ResponseEntity<HttpStatus>  deleteSp(@PathVariable("id") Long id) {
			
				try {
					specialiteRepository.deleteById(id);
				
			      return new ResponseEntity<>(HttpStatus.ACCEPTED);
				}
			     catch (Exception e) {
			      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			    }
			  }
		@DeleteMapping("/notification")
		public ResponseEntity<HttpStatus>  deleteNotif() {
			
				try {
					notificationRepository.deleteAll();
				
			      return new ResponseEntity<>(HttpStatus.ACCEPTED);
				}
			     catch (Exception e) {
			      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			    }
			  }


		
		@PutMapping("/specialite/modifier/{id}")
		public ResponseEntity<HttpStatus>  modifierSp(@PathVariable("id") Long id,@RequestBody Specialite sp) {
			
			try {
				Optional<Specialite> s= specialiteRepository.findById(id);
				if(s.isPresent()) {
					Specialite sP=s.get();
					sP.setBTP(sp.getBTP());
					sP.setBTS(sp.getBTS());
					sP.setCAP(sp.getCAP());
					sP.setDelegation(sp.getDelegation());
					sP.setLib_specialite(sp.getLib_specialite());
					sP.setPermis(sp.getPermis());
					sP.setSexe(sp.getSexe());
					specialiteRepository.save(sP);
				}
			      return new ResponseEntity<>(HttpStatus.ACCEPTED);
			    } catch (Exception e) {
			      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			    }
			  }

		
	@GetMapping("/notification")
	public List<Notification> getNotifications(){
		return notificationRepository.findAll();		
	}
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
		String sp1="",sp2="";
		if(candidat.isPresent()) {
			Candidat c=candidat.get();
		    Map<String, Object> data = new HashMap<>();
		    data.put("candidat", c);
		    String x= c.getDateNaiss().toString().substring(0, 10);
		    data.put("DateNaiss",x);
		    String y= c.getCreated_date().toString().substring(0, 10);
		    data.put("DateCreation",y);
		    for(CandidatSpecialite i:c.getCs()) {
		    	if(sp1.isBlank()) {
		    		sp1=i.specialite.getLib_specialite();
		    	}
		    	sp2=i.specialite.getLib_specialite();
		    }
		    data.put("sp",sp1+" , "+sp2);
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
	@GetMapping("/modifier/{mat}/{strrole}")
	public ResponseEntity<HttpStatus> modifierAdmin( @PathVariable("strrole") String strRoles ,@PathVariable("mat") String mat) {
	    try {	    	
	    	Optional<Admin> _admin=adminRepository.findByMatricule(mat);
	    	if(_admin.isPresent()) {
	    		Admin admin =_admin.get();
	    		Set<Role> roles = new HashSet<>();
		    	System.out.println(strRoles.equals("admin"));
		    	System.out.println(strRoles.equals("mod"));;
		    	System.out.println(strRoles);

	    		if(strRoles.equals("admin")) {
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

	    		}
	    		else if(strRoles.equals("mod")) {
	    			Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);	
	    		}
	    		else {
	  	          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	  		              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	  		          roles.add(userRole);

	    		}
	    		admin.setRoles(roles);
	    		adminRepository.save(admin);
	    		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal();
	    		String username = userDetails.getUsername();
	    		Notification n =new Notification (mat,new Date(),"admin "+ mat+ " role was changed by Admin "+ username);
	    		notificationRepository.save(n);
	    	}
	    	else {
	    		throw new RuntimeException("Error: Admin is not found.");
	    	}
	      return new ResponseEntity<>(HttpStatus.ACCEPTED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  } 
	  @PostMapping("/addadmin")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (userRepository.existsByMatricule(signUpRequest.getMatricule())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Username is already taken!"));
	    }

	    // Create new user's account
	    Admin user = new Admin(signUpRequest.getMatricule(),
	               encoder.encode(signUpRequest.getPassword()));

	    Set<String> strRoles = signUpRequest.getRole();
	    Set<Role> roles = new HashSet<>();

	    if (strRoles == null) {
	      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	      roles.add(userRole);
	    } else {
	      strRoles.forEach(role -> {
	        switch (role) {
	        case "admin":
	          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(adminRole);

	          break;
	        case "mod":
	          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(modRole);

	          break;
	        default:
	          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(userRole);
	        }
	      });
	    }

	    user.setRoles(roles);
	    userRepository.save(user);

	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	  }

}

