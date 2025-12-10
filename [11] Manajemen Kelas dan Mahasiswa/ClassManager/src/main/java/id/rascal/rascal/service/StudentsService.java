package id.rascal.rascal.service;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.rascal.rascal.dto.mappers.StudentsMapper;
import id.rascal.rascal.dto.requests.StudentsRequest;
import id.rascal.rascal.dto.responses.StudentsResponse;
import id.rascal.rascal.entity.Classes;
import id.rascal.rascal.entity.Students;
import id.rascal.rascal.repository.ClassesRepository;
import id.rascal.rascal.repository.StudentsRepository;

@Service
public class StudentsService {
  @Autowired StudentsRepository studentsRepository;
  @Autowired ClassesRepository classesRepository;

  public String addStudent(StudentsRequest request){
    String msg = "valid", email = request.getEmail().toLowerCase();
    if(studentsRepository.existsByEmailContainingIgnoreCase(email))
      throw new IllegalArgumentException("Email sudah terdaftar!!");

    Students students = StudentsMapper.toEntity(request);
    Classes c = classesRepository.findById(request.getClassId())
      .orElseThrow(() -> new RuntimeException(
      "Tidak dapat menemukkan kelas dengan id " + request.getClassId() + "!")
    );

    students.setSignedClass(c);
    students.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Jakarta")));

    try {
      if(!email.equals(request.getEmail())) 
        msg = "Email di ubah menjadi lowercase!";

      students.setEmail(email);
      studentsRepository.save(students);

      return msg;
    } catch (Exception e) {
      throw new RuntimeException("Email sudah terdaftar");
    }
  }

  public void deleteStudent(Long id){
    try {
      studentsRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e){
      throw new RuntimeException("ID tidak ditemukkan!");
    } catch (Exception e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  public void editStudent(Long id, StudentsRequest request){
    Students s = studentsRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Mahasiswa tidak ditemukan!"));
    Classes newClass = classesRepository.findById(request.getClassId())
      .orElseThrow(() -> new RuntimeException("Kelas tidak ditemukkan!"));

    s.setName(request.getName());
    s.setEmail(request.getEmail());
    s.setSignedClass(newClass);

    studentsRepository.save(s);
  }

  public StudentsRequest getById(Long id){
    Students s = studentsRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Mahasiswa tidak ditemukkan!"));

    return new StudentsRequest(
      s.getName(),
      s.getEmail(),
      s.getSignedClass().getId()
    );
  }

  public Page<StudentsResponse> getPaged(int page, int size){
    Pageable pageable = PageRequest.of(page, size);
    return studentsRepository.findAll(pageable)
      .map(StudentsMapper::toResponse);
  }

  public Page<StudentsResponse> getByName(
    String name, int page, int size
  ){
    return studentsRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page, size))
      .map(StudentsMapper::toResponse);
  }
}
