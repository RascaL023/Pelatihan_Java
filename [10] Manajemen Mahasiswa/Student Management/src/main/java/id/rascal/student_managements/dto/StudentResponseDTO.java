package id.rascal.student_managements.dto;

import java.time.LocalDateTime;
import java.time.Year;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class StudentResponseDTO {
  private Long id;
  private String name, major;
  private Year registrationYear;
  @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
  private LocalDateTime createdAt;
  private String email;
}
