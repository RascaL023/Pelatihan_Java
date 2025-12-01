package com.rascal.product_mng.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ProductRequestDTO {
  private String name;
  private double price;
  private int stock;
}
