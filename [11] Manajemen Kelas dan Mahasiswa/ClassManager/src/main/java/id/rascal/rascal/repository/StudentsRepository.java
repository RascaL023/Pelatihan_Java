package id.rascal.rascal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.rascal.rascal.entity.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long>{
  Page<Students> findByNameContainingIgnoreCase(String name, PageRequest page);
  boolean existsByEmailContainingIgnoreCase(String email);
}

