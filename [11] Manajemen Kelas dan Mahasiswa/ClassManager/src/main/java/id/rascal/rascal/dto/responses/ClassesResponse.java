package id.rascal.rascal.dto.responses;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ClassesResponse {
  private Long id;
  private String name;
  private String description;
  private LocalDateTime createdAt;
  private List<StudentsResponse> students;
  
  public int getTotalStudents(){
    return students == null ?
      0 : students.size();
  }
}
