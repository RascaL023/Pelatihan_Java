package com.rascal.product_mng.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rascal.product_mng.dto.ProductMapperDTO;
import com.rascal.product_mng.dto.ProductRequestDTO;
import com.rascal.product_mng.dto.ProductResponseDTO;
import com.rascal.product_mng.entity.Product;
import com.rascal.product_mng.repository.ProductRepository;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter @Setter
public class ProductService {
  @Autowired ProductRepository productRepository;

  public ProductResponseDTO addProduct(ProductRequestDTO request){
    if(request.getPrice() < 500) throw new IllegalArgumentException("Harga terlalu rendah..");

    Product product = ProductMapperDTO.toEntity(request);
    product.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Jakarta")));
    productRepository.save(product);
    return ProductMapperDTO.toResponse(product);
  }


  public List<ProductResponseDTO> getAll(){
    return productRepository.findAll().stream()
      .map(ProductMapperDTO::toResponse).toList();
  }

  public ProductRequestDTO getById(Long id){
    Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("..."));
    return new ProductRequestDTO(product.getName(), product.getPrice(), product.getStock());
  }

  public List<ProductResponseDTO> searchByName(String name){
    return productRepository.findAllByNameContaining(name)
      .stream().map(ProductMapperDTO::toResponse).toList();
  }

  // public ProductResponseDTO getById(Long id){
  //   return ProductMapperDTO.toResponse(
  //     productRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't found"))
  //   );
  // }


  public void editById(Long id, ProductRequestDTO request){
    if(request.getPrice() < 500) throw new IllegalArgumentException("Harga terlalu rendah..");

    Product product = productRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("..."));

    product.setName(request.getName());
    product.setPrice(request.getPrice());
    product.setStock(request.getStock());

    productRepository.save(product);
  }

  
  public void deleteById(Long id){
    productRepository.deleteById(id);
  }
}
