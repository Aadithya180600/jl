package com.JL.JL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.services.storage.Storage;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;


@SuppressWarnings("unused")
@Service
public class JlServiceImpl implements JlService {
	@Autowired private LoginRepository lrepo;
	@Autowired private student_detailsRepository srepo;
	@Autowired private faculty_detailsRepository frepo;
	@Autowired private FilesRepository flrepo;
	@Autowired private NewclassesRepository ncrepo;
	@Autowired private AssignmentsRepository asrepo;
	@Autowired private SubjectsRepository sbrepo;
	@Autowired private AssiansRepository ansrepo;
	@Override
	public List<Login> findByUsername(String username) {
		return lrepo.findByUsername(username);
	}
	@Override
	public void save(Login cb) {
		lrepo.save(cb);
	}
	@Override
	public Login checkpassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Login> findAll() {
		return lrepo.findAll();
	}
	@Override
	public List<student_details> findAll1() {
		return srepo.findAll();
	}
	@Override
	public student_details findByRollnumber(String rollnumber) {
		return srepo.findByRollnumber(rollnumber);
	}
	@Override
	public student_details findByPhoneno(String phoneno) {
		// TODO Auto-generated method stub
		return srepo.findByPhoneno(phoneno);
	}
	@Override
	public student_details findByEmailid(String emailid) {
		// TODO Auto-generated method stub
		return srepo.findByEmailid(emailid);
	}
	@Override
	public void save(student_details sd) {
		srepo.save(sd);
	}
	@Override
	public void save(faculty_details fd) {
		// TODO Auto-generated method stub
		frepo.save(fd);
	}
	@Override
	public List<faculty_details> findAll2() {
		// TODO Auto-generated method stub
		return frepo.findAll();
	}
	@Override
	public faculty_details findByEmailidf(String emailid) {
		// TODO Auto-generated method stub
		return frepo.findByEmailid(emailid);
	}
	
	@Override
	public Optional<faculty_details> findById(String id) {
		return frepo.findById(id);
	}
	
	/////////////////////////////////Files////////////////////////////////////////////////
	
	@Override
	public String addFiles(String title, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
	    Files photo = new Files(title); 
	        photo.setImage(
	          new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
	        photo = flrepo.insert(photo); 
	        return photo.getId(); 
	}
	
	@Override
	public List<Files> getFiles() {
		return flrepo.findAll();
	}
	
	@Override
	public Files getPhoto(String id) {
		return flrepo.findById(id).get();
	}
	
	@Override
	public Optional<Files> getFileById(String id) {
		return flrepo.findById(id);
	}
	
	
	///////////////////////////////Files//////////////////////////////////////////////////
	
	@Override
	public Subjects findBySubcode(String subcode) {
		return sbrepo.findBySubcode(subcode);
	}
	
	@Override
	public List<Newclasses> findAllc() {
		return ncrepo.findAll();
	}
	
	@Override
	public Boolean update(Subjects nc, String subcode) throws NullPointerException {
		 String k=nc.getSubstuds().get(0);
		 student_details s = findByRollnumber(k);
		 List<String> l=s.getClassesenrolled();
		 for(String i:l) {
			 if(i.equals(subcode)) return false;
		 }
		 l.add(subcode);
		 s.setClassesenrolled(l);
		 srepo.save(s);
	     Subjects s1=findBySubcode(subcode);
		 s1.getSubstuds().add(k);
		 sbrepo.save(s1);
	     return true;
	}
	
	@Override
	public void addmaterial(Subjects nc, String subcode) {
	     Subjects s= findBySubcode(subcode);
	     s.getMaterials().addAll(nc.getMaterials());
	     sbrepo.save(s);
	}
	

	@Override
	public void save(Assignments as,String subcode) {
		asrepo.save(as);
		String e=as.getId();
		Subjects s=findBySubcode(subcode);
		s.getAssignment().add(e);
		sbrepo.save(s);
	}
	
	@Override
	public List<Assignments> findAlll() {
		return asrepo.findAll();
	}
	
	@Override
	public List<Assignments> aaos(String subcode) {
		List<String> l=findBySubcode(subcode).getAssignment();
		List<Assignments> s=new ArrayList<Assignments>();
		for(String i:l) {
			Optional<Assignments> k=asrepo.findById(i);
			if(k.isPresent()) {
				s.add(k.get());
			}
		}
		return s;
	}
	
	@Override
	public void save(Subjects sb) {
		sbrepo.save(sb);
		Optional<faculty_details> f = findById(sb.getFacultyid());
		faculty_details fd=null;
		if(f.isPresent()) {
			fd=f.get();
			fd.getClassescreated().add(sb.getSubcode());
			frepo.save(fd);
		}
		
	}
	
	@Override
	public List<Subjects> getclasses(String fid) {
		List < Subjects > l = new ArrayList<Subjects>();
		Optional<faculty_details> s=frepo.findById(fid);
		if(s.isPresent()) {
			faculty_details f=s.get();
			List<String> str=f.getClassescreated();
			for(String i:str) {
				l.add(findBySubcode(i));
			}
		}
		return l;
	}
	
	@Override
	public void save(Assians as) {
		ansrepo.save(as);
		String r=as.getId();
		Assignments k=null;
		Optional<Assignments> s=asrepo.findById(as.getAssignmentid());
		if(s.isPresent()) {
			k=s.get();
			k.getAssians().add(r);
		}
		asrepo.save(k);
	}
	
	@Override
	public List<Assians> assians(String aid) {
		Assignments k=null;
		List<String> l = new ArrayList<String>();
		Optional<Assignments> s=asrepo.findById(aid);
		List<Assians> a = new ArrayList<Assians>();
		if(s.isPresent()) {
			k=s.get();
			l=k.getAssians();
		}
		for(String i:l) {
			Optional<Assians> as = ansrepo.findById(i);
			if(as.isPresent()) a.add(as.get());
		}
		return a;
	}
	@Override
	public void assignmarks(Assians as) {
		Assians k=null;
		Optional<Assians> s=ansrepo.findById(as.getId());
		if(s.isPresent()) {
			k=s.get();
			k.setMarks(as.getMarks());
		}
		ansrepo.save(k);
	}
	@Override
	public List<Subjects> getclassess(String sid) {
		List < Subjects > l = new ArrayList<Subjects>();
		Optional<student_details> s=srepo.findById(sid);
		if(s.isPresent()) {
			student_details f=s.get();
			List<String> str=f.getClassesenrolled();
			for(String i:str) {
				l.add(findBySubcode(i));
			}
		}
		return l;
	}
	@Override
	public List<Subjects> getmaterilas(String subcode) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	@Override
	public String uploadFile(File file, String fileName) {
        BlobId blobId = BlobId.of("bucket name", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("downloaded private key JSON file path"));
        com.google.cloud.storage.Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        //storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        //return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }
	
	@Override
	public File convertToFile(MultipartFile multipartFile, String fileName) throws FileNotFoundException, IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }
	
	@Override
	public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
	@Override
	public String uploadFile(File file, String fileName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object download(String fileName) {
		 String destFileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));     // to set random strinh for destination file name
	        String destFilePath = "Z:\\New folder\\" + destFileName;                                    // to set destination file path
	        
	        ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
	        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("path of JSON with genarated private key"));
	        com.google.cloud.storage.Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
	        com.google.cloud.storage.Blob blob = storage.get(BlobId.of("your bucket name", fileName));
	        blob.downloadTo(Paths.get(destFilePath));
	        return sendResponse("200", "Successfully Downloaded!");
	}
	@Override
	public Object upload(MultipartFile multipartFile) {
		try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name. 

            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            TEMP_URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return sendResponse("Successfully Uploaded !", TEMP_URL);                     // Your customized response
        } catch (Exception e) {
            e.printStackTrace();
            return sendResponse("500", e, "Unsuccessfully Uploaded!");
        }

	}
	*/
		
}
