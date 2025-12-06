package id.rascal.student_managements.service;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import id.rascal.student_managements.dto.StudentMapperDTO;
import id.rascal.student_managements.dto.StudentRequestDTO;
import id.rascal.student_managements.dto.StudentResponseDTO;
import id.rascal.student_managements.entity.Student;
import id.rascal.student_managements.repository.StudentRepository;

@Service
public class StudentService {
  @Autowired StudentRepository studentRepository;

  public StudentResponseDTO addStudent(StudentRequestDTO request){
    Year curr = Year.now();
    System.out.println(curr);
    System.out.println(request.getRegistrationYear());
    System.out.println(curr.isAfter(request.getRegistrationYear()));
    if(studentRepository.existsByEmailContaining(request.getEmail()))
      throw new IllegalArgumentException("Email sudah terdaftar!");
    else if(curr.isBefore(request.getRegistrationYear()))
      throw new IllegalArgumentException("Tahun daftar tidak valid!");
    else if(request.getRegistrationYear().isBefore(curr.minusYears(5)))
      throw new IllegalArgumentException("Tahun daftar tidak boleh kurang dari 5 tahun!");

    Student student = StudentMapperDTO.toEntity(request);
    student.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Jakarta")));
    studentRepository.save(student);
    return StudentMapperDTO.toResponse(student);
  }

  public Page<StudentResponseDTO> getPage(int page, int size){
    Pageable pageable = PageRequest.of(page, size);
    return studentRepository.findAll(pageable)
      .map(StudentMapperDTO::toResponse);
  }

  public StudentRequestDTO getById(Long id){
    Student student = studentRepository.findById(id).
      orElseThrow(() -> new RuntimeException("ID tidak ditemukkan!"));
    return new StudentRequestDTO(
      student.getName(), student.getEmail(), student.getMajor(), student.getRegistrationYear()
    );
  }

  public void editById(Long id, StudentRequestDTO request){
    Year curr = Year.now();

    if(curr.isBefore(request.getRegistrationYear()))
      throw new IllegalArgumentException("Tahun daftar tidak valid!");
    else if(request.getRegistrationYear().isBefore(curr.minusYears(5)))
      throw new IllegalArgumentException("Tahun daftar tidak boleh kurang dari 5 tahun!");

    Student student = studentRepository.findById(id).
      orElseThrow(() -> new RuntimeException("ID tidak ditemukkan!"));

    student.setName(request.getName());
    student.setEmail(request.getEmail());
    student.setMajor(request.getMajor());
    student.setRegistrationYear(request.getRegistrationYear());

    studentRepository.save(student);
  }

  public void deleteById(Long id){
    try {
        studentRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
        throw new RuntimeException("Mahasiswa tidak ditemukan!");
    }
  }

  public Page<StudentResponseDTO> searchByName(String name, int page, int size){
    Page<Student> raw = studentRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page, size));
    return raw.map(StudentMapperDTO::toResponse);
  }
}
