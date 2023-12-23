package com.spring.boot.datajpa.repository;

import java.util.List;

import com.spring.boot.datajpa.entity.Guardian;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.boot.datajpa.entity.Student;

@SpringBootTest
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void saveStudent() {
		Student student = Student.builder().firstName("tharun")
							.lastName("kumar").email("tharun@kuma.com")
							.build();

		
		studentRepository.save(student);
	}

	@Test
	public void saveStudentWithGuardian(){
		Guardian guardian = Guardian.builder()
							.name("reddy")
							.email("reddy@gmail.com")
							.mobile("00909090999")
							.build();

		Student student = Student.builder()
							.guardian(guardian)
							.firstName("tharun")
							.lastName("kuamr")
							.email("redddi@gmail.com")
							.build();

		studentRepository.save(student);
	}

	@Test
	public void printStudentByFirstName(){
		List<Student> student  = studentRepository.findByFirstName("tharun");
		System.out.println("Student by first name : " + student);
	}

	@Test
	public void printStudentByFirstNameContaining(){
		List<Student> student  = studentRepository.findByFirstNameContaining("tha");
		System.out.println("Student by first name : " + student);
	}
	
	@Test
	public void printStudentByGuardianName(){
		List<Student> students = studentRepository.findByGuardianName("reddy");
		System.out.println("students = " + students);
	}

	@Test
	public void printAllStudents() {
		List<Student> list = studentRepository.findAll();

		System.out.println("List of students : " + list);
	}

	@Test
	public void printStudentFirstNameUsingEmailAddress(){
		String firstName = studentRepository.findFirstNameByEmailAddress("redddi@gmail.com");

		System.out.println("firstName = " + firstName);

	}

	@Test
	public void printStudentFirstNameUsingEmailAddressNative(){
		String firstName = studentRepository.findFirstNameByEmailAddressNative("tharun@kuma.com");

		System.out.println("firstName = " + firstName);

	}

	@Test
	public void printStudentFirstNameUsingEmailAddressNativeNamed(){
		String firstName = studentRepository.findFirstNameByEmailAddressNativeNamed("tharun@kuma.com");

		System.out.println("firstName = " + firstName);

	}

	@Test
	public void updateStudentFirstNameByEmailId(){
		int result = studentRepository.updateStudentFirstNameByEmailId("tarun",
				"tharun@kumar.com");
		System.out.println("result = " + result);
	}
}
