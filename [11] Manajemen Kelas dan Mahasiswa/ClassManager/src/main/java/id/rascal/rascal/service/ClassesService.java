package id.rascal.rascal.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.rascal.rascal.dto.mappers.ClassSignature;
import id.rascal.rascal.dto.mappers.ClassesMapper;
import id.rascal.rascal.dto.mappers.StudentsMapper;
import id.rascal.rascal.dto.requests.ClassesRequest;
import id.rascal.rascal.dto.responses.ClassesResponse;
import id.rascal.rascal.entity.Classes;
import id.rascal.rascal.repository.ClassesRepository;

@Service
public class ClassesService {
  @Autowired ClassesRepository classesRepository;

  public void addClass(ClassesRequest request){
    Classes classes = ClassesMapper.toEntity(request);
    classes.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Jakarta")));
    classes.setStudents(null);
    classesRepository.save(classes);
  }

  public void deleteClass(Long classId){
    try {
      classesRepository.deleteById(classId);
    } catch (EmptyResultDataAccessException e){
      throw new RuntimeException("Kelas tidak dapat ditemukkan!");
    } catch (Exception e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  public void editClass(Long classId, ClassesRequest request){
    Classes c = classesRepository.findById(classId)
      .orElseThrow(() -> new RuntimeException("ID tidak ditemukkan!"));
    
    c.setName(request.getName());
    c.setDescription(request.getDescription());
    classesRepository.save(c);
  }

  public Page<ClassesResponse> getPaged(int page, int size){
    Pageable pageable = PageRequest.of(page, size);
    return classesRepository.findAll(pageable)
      .map(ClassesMapper::toResponse);
  }

  public Page<ClassesResponse> getByName(
    String name, int page, int size
  ){
    return classesRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page, size))
      .map(ClassesMapper::toResponse);
  }

  public ClassesRequest getById(Long id){
    Classes c = classesRepository.findById(id).
      orElseThrow(() -> new RuntimeException("Kelas tidak ditemukkan!"));
    return new ClassesRequest(
      c.getName(),
      c.getDescription()
    );
  }

  public ClassesResponse getResponseById(Long id){
    Classes c = classesRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Kelas tidak ditemukkan!"));
    return ClassesMapper.toResponse(c);
  }

  public List<ClassSignature> getClassSignature(){
    return classesRepository.findAll().stream()
      .map(c -> new ClassSignature(c.getId(), c.getName()))
        .toList();
  }
}
