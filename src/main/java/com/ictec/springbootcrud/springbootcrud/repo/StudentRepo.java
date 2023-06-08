package com.ictec.springbootcrud.springbootcrud.repo;

import com.ictec.springbootcrud.springbootcrud.httpentities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> { }
