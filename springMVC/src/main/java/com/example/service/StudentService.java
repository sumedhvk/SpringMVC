package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.DeleteMapping;

import com.example.entity.Student;
import com.example.repository.StudentRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.UpdateStudentRequest;

@Service
public class StudentService 
{
	@Autowired
	StudentRepository studentRepository;
	
	public List<Student> getAllStudents()
	{
		return studentRepository.findAll();   //Equivalent to select * query in MySql
	}
	
	public Student createStudent (CreateStudentRequest createStudentRequest)
	{
		Student student = new Student(createStudentRequest);
		
		student = studentRepository.save(student);
			
		return student;
	}
	
	public Student updateStudent (UpdateStudentRequest updateStudentRequest)
	{
		Student student = studentRepository.findById(updateStudentRequest.getId()).get();
		
		if(updateStudentRequest.getFirstName() !=null && !updateStudentRequest.getFirstName().isEmpty())
		{
			student.setFirstName(updateStudentRequest.getFirstName());
		}
		
		student = studentRepository.save(student);
		
		return student;
	}
	
	public String deleteStudent (long id)
	{
		studentRepository.deleteById(id);
		
		return "Student has been deleted successfully";
	}
}
