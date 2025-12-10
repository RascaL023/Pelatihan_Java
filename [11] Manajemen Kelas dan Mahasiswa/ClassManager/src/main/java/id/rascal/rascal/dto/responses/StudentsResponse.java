package id.rascal.rascal.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class StudentsResponse {
  private Long id;
  private String name;
  private String email;
  private String className;
}
