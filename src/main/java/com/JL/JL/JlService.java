package com.JL.JL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

//import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;

@SuppressWarnings("unused")
public interface JlService {
	List<student_details> findAll1();
	student_details findByRollnumber(String rollnumber);
	student_details findByPhoneno(String phoneno);
	student_details findByEmailid(String emailid);
	void save(student_details sd);
	List<Login> findByUsername(String username);
	void save(Login cb);
	Login checkpassword(String username, String password);
	List<Login> findAll();
	void save(faculty_details fd);
	List<faculty_details> findAll2();
	faculty_details findByEmailidf(String emailid);
	Optional<faculty_details> findById(String id);
	String addFiles(String title, MultipartFile file) throws IOException;
	List<Files> getFiles();
	Files getPhoto(String id);
	Subjects findBySubcode(String subcode);
	List<Newclasses> findAllc();
	void save(Subjects sb);
	//Newclasses joinstud(String subcode);
	Boolean update(Subjects cb,String subcode);
	void addmaterial(Subjects cb,String subcode);
	//void addAssignment(Assignments as,String subcode);
	//void submitassi(String id, Assians as, String subcode);
	void save(Assignments as,String subcode);
	List<Assignments> findAlll();
	List<Assignments> aaos(String subcode);
	List<Subjects> getclasses(String fid);
	void save (Assians as);
	List<Assians> assians(String aid);
	void assignmarks(Assians cb);
	List<Subjects> getclassess(String sid);
	List<Subjects> getmaterilas(String subcode);
	//String uploadFile(File file, String fileName);
	//File convertToFile(MultipartFile multipartFile, String fileName) throws FileNotFoundException, IOException; 
	//String getExtension(String fileName);
	//Object download(String fileName);
	//Object upload(MultipartFile multipartFile);  
	Optional<Files> getFileById(String id);
}
