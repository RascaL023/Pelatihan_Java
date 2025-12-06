package id.rascal.student_managements.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.rascal.student_managements.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
  boolean existsByEmailContaining(String email);
  Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);
}

