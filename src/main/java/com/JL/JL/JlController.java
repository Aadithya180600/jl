package com.JL.JL;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@SuppressWarnings("unused")
@RestController
@RequestMapping(path="/jl")
@CrossOrigin(origins="*")


public class JlController {
	
	@Autowired private JlService service;
	
	    //getting login details using username
		@GetMapping(value="getlogindetails/username/{username}")
		public List<Login> findByUsername(@PathVariable("username") String username) {
			return service.findByUsername(username);
		}
		
		//Getting login details
		@GetMapping(value="getlogindetails")
		public List<Login> list() {
			return service.findAll();
		}
		
		//saving login details
		@PostMapping(value="/savelogindetails",consumes = "application/json")
		public ResponseEntity<String> savelogin(@RequestBody Login cb) {
			if(cb.getUsername() != null) {
				 return new ResponseEntity<String>("email id is already registered", HttpStatus.FORBIDDEN);
			}
	        service.save(cb);
	        return new ResponseEntity<String>("Added successfully", HttpStatus.OK);
	    }
		
		//checking the passwords
		@PostMapping(value="checklogindetails")
		public ResponseEntity<String> checkpassword(@RequestBody Login cb) {
			List<Login> l=service.findByUsername(cb.getUsername());
			for(Login lo:l)
			{
				String s=lo.getPassword();
				if(s.equals(cb.getPassword()))
				{
					return new ResponseEntity<String>("passwords matched", HttpStatus.OK);
				}
			}
			return new ResponseEntity<String>("passwords unmatched", HttpStatus.BAD_REQUEST);	
		}
		
		//get student details
		@GetMapping(value="getstudentdetails")
		public List<student_details> list1() {
			return service.findAll1();
		}
		
		//saving student details
		@PostMapping(value="/savestudentdetails",consumes = "application/json")
		public ResponseEntity<String> savestudent_details(@RequestBody student_details sd) {
	        service.save(sd);
	        return new ResponseEntity<String>("Added successfully", HttpStatus.OK);
	    }
		
		@GetMapping(value="getstudentdetails/rollnumber/{rollnumber}")
		public student_details findByRollnumber(@PathVariable("rollnumber") String rollnumber) {
			return service.findByRollnumber(rollnumber);
		}
		
		@GetMapping(value="getfacultydetails")
		public List<faculty_details> listf() {
			return service.findAll2();
			
		}
		
		@PostMapping(value="/savefacultydetails",consumes = "application/json")
		public ResponseEntity<String> savefaculty_details(@RequestBody faculty_details fd) {
	        service.save(fd);
	        return new ResponseEntity<String>("Added successfully", HttpStatus.OK);
	    }
		
		@PostMapping("/photos/add")
		public String addFiles(@RequestParam("title") String title, @RequestParam("image") MultipartFile image, Model model) throws IOException {
		    String id = service.addFiles(title, image);
		    return "redirect:/photos/" + id;
		}
		
		@GetMapping("/photos")
		public List<Files> getPhoto() {
		    return service.getFiles();
		}
		@GetMapping("/photos/{id}")
		public String getPhoto(@PathVariable String id, Model model) {
		    Files photo = service.getPhoto(id);
		    model.addAttribute("title", photo.getTitle());
		    model.addAttribute("image", 
		      Base64.getEncoder().encodeToString(photo.getImage().getData()));
		    return "photos";
		}
		
	@GetMapping("files/{id}")
    @ResponseBody
    public ResponseEntity<Object> serveFile(@RequestParam("id") String id) throws UnsupportedEncodingException {

        Optional<Files> file = service.getFileById(id);

        if (file.isPresent()) {
            return ResponseEntity.ok().body(file.get().getImage().getData());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File was not found");
        }

    }
		
		@GetMapping(value="getclassdetails")
		public List<Newclasses> findAllc() {
			return service.findAllc();
		}
		
		@GetMapping(value="getsubjectdetails/{subcode}")
		public Subjects findBySubcode(@PathVariable("subcode") String subcode) {
			return service.findBySubcode(subcode);
		}
		
		@GetMapping(value="getsubjectdetailsf/{fid}")
		public List<Subjects> getsubd(@PathVariable("fid") String fid) {
			return service.getclasses(fid);
		}
		
		@GetMapping(value="getsubjectdetailss/{sid}")
		public List<Subjects> getsubds(@PathVariable("sid") String sid) {
			return service.getclassess(sid);
		}
		
		@PostMapping(value="/savesubjectdetails",consumes = "application/json")
		public ResponseEntity<String> savesubjectdetails(@RequestBody Subjects subd) {
	        service.save(subd);
	        return new ResponseEntity<String>("Added successfully", HttpStatus.OK);
	    }
		
		@PutMapping(value="/joinstudents/{subcode}",consumes = "application/json")
		public ResponseEntity<String> update(@RequestBody Subjects cb,@PathVariable("subcode") String subcode) throws NullPointerException {
			
				boolean s=service.update(cb, subcode);
			    if(s) {
			    	return new ResponseEntity<String>("Updated successfully", HttpStatus.OK);  
			}
			    else return new ResponseEntity<String>("Already enrolled", HttpStatus.BAD_REQUEST);
		}
		
		@PutMapping(value="/addmaterials/{subcode}",consumes = "application/json")
		public ResponseEntity<String> addMaterials(@RequestBody Subjects cb,@PathVariable("subcode") String subcode) throws NullPointerException {
				service.addmaterial(cb, subcode);
				return new ResponseEntity<String>("Updated successfully", HttpStatus.OK);
		}
		
		@GetMapping(value="getmaterials/{subcode}")
		public List<Subjects> getmaterials(@PathVariable("subcode") String subcode) {
			return service.getmaterilas(subcode);
		}
		
		
		@PostMapping(value="/saveAssignmentdetails/{subcode}",consumes = "application/json")
		public ResponseEntity<String> saveassi_details(@RequestBody Assignments fd,@PathVariable("subcode") String subcode) {
	        service.save(fd,subcode);
	        return new ResponseEntity<String>("Added successfully", HttpStatus.OK);
	    }
		
		
		@GetMapping(value="/getallassignments")
		public List<Assignments> findAlll() {
			return service.findAlll();
		}
		
		@GetMapping(value="/getallassignmentsofsub/{subcode}")
		public List<Assignments> aaos(@PathVariable("subcode") String subcode) {
			return service.aaos(subcode);
		}
		
		@PostMapping(value="/saveassians",consumes = "application/json")
		public ResponseEntity<String> saveassians(@RequestBody Assians as) {
	        service.save(as);
	        return new ResponseEntity<String>("Added successfully", HttpStatus.OK);
	    }
		
		@GetMapping(value="/getallassiansofassi/{aid}")
		public List<Assians> aaoa(@PathVariable("aid") String aid) {
			return service.assians(aid);
		}
		
		@PutMapping(value="/addmarks",consumes = "application/json")
		public ResponseEntity<String> assignmarks(@RequestBody Assians cb) throws NullPointerException {
				service.assignmarks(cb);
				return new ResponseEntity<String>("Updated successfully", HttpStatus.OK);
		}
		/*
		
		//filessssssssssss///
		  @PostMapping("/profile/pic")
		    public Object upload(@RequestParam("file") MultipartFile multipartFile) {
		        logger.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());
		        return service.upload(multipartFile);
		    }

		    @PostMapping("/profile/pic/{fileName}")
		    public Object download(@PathVariable String fileName) throws IOException {
		        logger.info("HIT -/download | File Name : {}", fileName);
		        return service.download(fileName);
		    }
		
		*/
		
		
		
		
		
		
		
		
		
		
		
}
