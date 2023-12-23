package com.spring.boot.datajpa.repository;

import com.spring.boot.datajpa.entity.Course;
import com.spring.boot.datajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {

        Course course1 = Course.builder()
                .title("Physics")
                .credit(4)
                .build();

        Course course2 = Course.builder()
                .title("Math")
                .credit(6)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("H C")
                .lastName("Verma")
                .build();

        teacherRepository.save(teacher);
    }
}