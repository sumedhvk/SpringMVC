package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.request.CreateStudentRequest;
import com.example.request.UpdateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;

@RestController
@RequestMapping("/api/student/")
public class StudentController 
{
//	@Value("${app.name:Default}")
//	private String appName;
//	
//	@GetMapping("/get")
//	@RequestMapping(value = "/get", method = RequestMethod.GET)
//	public StudentResponse getStudent()
//	{
//		StudentResponse studentResponse = new StudentResponse(101, "John", "Cena");
//		return studentResponse;
//	}
	
	@Autowired
	StudentService studentService;	
	
	@GetMapping("/getAll")
	public List<StudentResponse> getAllStudents ()
	{
		List<Student> studentList =  studentService.getAllStudents();
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		
		return studentResponseList;
	}
	
	@PostMapping("create")
	public StudentResponse createStudent (@Valid @RequestBody CreateStudentRequest createStudentRequest)
	{
		Student student = studentService.createStudent(createStudentRequest);
		
		return new StudentResponse(student);
	}
	
	@PutMapping("update")
	public StudentResponse updateStudent (@Valid @RequestBody UpdateStudentRequest updateStudentRequest)
	{
		Student student = studentService.updateStudent(updateStudentRequest);
		
		return new StudentResponse(student);
	}
	
//	@DeleteMapping("delete")
//	public String deleteStudent(@RequestParam("id") long id)
//	{
//		return studentService.deleteStudent(id);
//	}
	
	@DeleteMapping("delete/{id}")
	public String deleleStudent (@PathVariable("id") long id)
	{
		return studentService.deleteStudent(id);
	}
}
