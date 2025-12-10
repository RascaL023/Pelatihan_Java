package id.rascal.rascal.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
  name = "students",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})
  }
)
@Getter @Setter
public class Students {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;
  @ManyToOne private Classes signedClass;
  private LocalDateTime createdAt;
}
