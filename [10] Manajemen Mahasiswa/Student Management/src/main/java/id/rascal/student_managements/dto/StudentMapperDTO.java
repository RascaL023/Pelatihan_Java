package id.rascal.student_managements.dto;

import id.rascal.student_managements.entity.Student;

public class StudentMapperDTO {
  public static StudentResponseDTO toResponse(Student student){
    return new StudentResponseDTO(
      student.getId(),
      student.getName(),
      student.getMajor(),
      student.getRegistrationYear(),
      student.getCreatedAt(),
      student.getEmail()
    );
  }

  public static Student toEntity(StudentRequestDTO request){
    Student student = new Student();
    student.setName(request.getName());
    student.setMajor(request.getMajor());
    student.setEmail(request.getEmail());
    student.setRegistrationYear(request.getRegistrationYear());
    return student;
  }
}
