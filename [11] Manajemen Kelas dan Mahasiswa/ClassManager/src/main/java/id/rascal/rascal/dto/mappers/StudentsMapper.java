package id.rascal.rascal.dto.mappers;

import id.rascal.rascal.dto.requests.StudentsRequest;
import id.rascal.rascal.dto.responses.StudentsResponse;
import id.rascal.rascal.entity.Students;

public class StudentsMapper {
  public static Students toEntity(StudentsRequest request){
    Students students = new Students();
    students.setName(request.getName());
    students.setEmail(request.getEmail());
    return students;
  }

  public static StudentsResponse toResponse(Students students){
    return new StudentsResponse(
      students.getId(), 
      students.getName(), 
      students.getEmail(), 
      students.getSignedClass().getName()
    );
  }
}
