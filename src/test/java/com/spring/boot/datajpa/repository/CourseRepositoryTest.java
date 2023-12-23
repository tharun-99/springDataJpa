package com.spring.boot.datajpa.repository;

import com.spring.boot.datajpa.entity.Course;
import com.spring.boot.datajpa.entity.Student;
import com.spring.boot.datajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


@SpringBootTest
class CourseRepositoryTest {
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Test
    public void printCourses() {
        List<Course> lst = courseRepository.findAll();
        System.out.println("lst = " + lst);
    }

    @Test
    public void saveCourseWithTeacher(){


        Teacher teacher = Teacher.builder()
                .firstName("Som")
                .lastName("Shekhar")
                .build();

        Course course = Course.builder()
                .title("Mathematics")
                .credit(2)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);

        List<Course> courses =
                courseRepository.findAll(firstPageWithThreeRecords).getContent();

        long totalElements =
                courseRepository.findAll(firstPageWithThreeRecords).getTotalElements();

        long totalPages =
                courseRepository.findAll(firstPageWithThreeRecords).getTotalPages();

        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);
        System.out.println("courses = " + courses);
    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle =
                PageRequest.of(
                        0,5,
                        Sort.by("title")

                );
        Pageable sortByCreditsDesc =
                PageRequest.of(
                        0, 5,
                        Sort.by("credit").descending()
                );

        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(
                        0,5,
                        Sort.by("title")
                                .and(Sort.by("credit").descending())
                );

        List<Course> courses = courseRepository.findAll(sortByTitleAndCreditDesc).getContent();

        System.out.println("COURSES");
        for(Course c:courses){
            System.out.println(c);
        }
    }

    @Test
    public void printFindByTitleContaining() {
        Pageable firstPageWithTenRecords =
                PageRequest.of(0, 10);

        List<Course> courses = courseRepository.findByTitleContaining("M", firstPageWithTenRecords).getContent();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("lizzy")
                .lastName("morgan")
                .build();

        Student student = Student.builder()
                .firstName("abhishek")
                .lastName("singh")
                .email("abhishek@gmail.com")
                .build();

        Course course = Course.builder()
                .title("AI")
                .credit(12)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }
}