package id.rascal.rascal.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.rascal.rascal.entity.Classes;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long>{
  Page<Classes> findByNameContainingIgnoreCase(String name, PageRequest page);
}
