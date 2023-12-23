package com.spring.boot.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.boot.datajpa.entity.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

    public List<Student> findByFirstName(String firstName);
    public List<Student> findByFirstNameContaining(String name);
    public List<Student> findByGuardianName(String name);

    // JPQL
    @Query("select s.firstName from Student s where s.email=?1")
    public String findFirstNameByEmailAddress(String email);

    // Native query
    @Query(
            value = "select first_name from tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    public String findFirstNameByEmailAddressNative(String email);

    // Native named query
    @Query(
            value = "select first_name from tbl_student s where s.email_address = :email",
            nativeQuery = true
    )
    public String findFirstNameByEmailAddressNativeNamed(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name = :firstName where email_address = :email",
            nativeQuery = true
    )
    public int updateStudentFirstNameByEmailId(@Param("firstName") String firstName,
                                               @Param("email") String email);

}
