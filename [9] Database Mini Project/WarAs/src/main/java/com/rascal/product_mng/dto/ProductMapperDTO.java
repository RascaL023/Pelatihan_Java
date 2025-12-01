package com.rascal.product_mng.dto;

import com.rascal.product_mng.entity.Product;

public class ProductMapperDTO {
  public static ProductResponseDTO toResponse(Product product){
    return new ProductResponseDTO(
      product.getId(), 
      product.getName(),
      product.getPrice(), 
      product.getStock(),
      product.getCreatedAt()
    );
  }

  public static Product toEntity(ProductRequestDTO request){
    Product product = new Product();
    product.setName(request.getName());
    product.setPrice(request.getPrice());
    product.setStock(request.getStock());
    return product;
  }
}
