package com.rascal.product_mng.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
@AllArgsConstructor @NoArgsConstructor
public class ProductResponseDTO {
  private Long id;
  private String name;
  private double price;
  private int stock;
  @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
  private LocalDateTime createdAt;
}
