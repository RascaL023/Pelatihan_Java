package id.rascal.student_managements.dto;

import java.time.Year;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class StudentRequestDTO {
  @NotNull private String name;
  @Email @Size(min = 14, max = 19)
  private String email;
  @NotNull @Size(min = 2, max = 20)
  private String major;
  @NotNull private Year registrationYear;
}
