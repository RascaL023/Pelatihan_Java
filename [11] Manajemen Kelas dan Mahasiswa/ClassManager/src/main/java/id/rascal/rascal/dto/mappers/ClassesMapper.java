package id.rascal.rascal.dto.mappers;

import id.rascal.rascal.dto.requests.ClassesRequest;
import id.rascal.rascal.dto.responses.ClassesResponse;
import id.rascal.rascal.entity.Classes;

public class ClassesMapper {
  public static Classes toEntity(ClassesRequest request){
    Classes classes = new Classes();
    classes.setName(request.getName());
    classes.setDescription(request.getDescription());
    return classes;
  }

  public static ClassesResponse toResponse(Classes classes){
    return new ClassesResponse(
      classes.getId(),
      classes.getName(),
      classes.getDescription(),
      classes.getCreatedAt(),
      classes.getStudents().stream()
        .map(StudentsMapper::toResponse).toList()
    );
  }
}
