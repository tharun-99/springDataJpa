package com.spring.boot.datajpa.repository;

import java.util.*;

import com.spring.boot.datajpa.entity.Course;
import com.spring.boot.datajpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;


    @Test
    public void saveCourseMaterial() {

        Course course = Course.builder()
                .title("DSABasics")
                .credit(4)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .course(course)
                .url("www.someothercourse.com")
                .build();

        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void printAllCourseMaterials(){
        List<CourseMaterial> lst = courseMaterialRepository.findAll();

        System.out.println("lst = " + lst);
    }
}