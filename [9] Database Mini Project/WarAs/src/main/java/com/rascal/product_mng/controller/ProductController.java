package com.rascal.product_mng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rascal.product_mng.dto.ProductRequestDTO;
import com.rascal.product_mng.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
  @Autowired ProductService productService;

  @GetMapping
  public String getAll(
    @RequestParam(required = false) String keyword,
    Model model
  ){
    if(keyword != null && !keyword.isBlank()) model.addAttribute("products", productService.searchByName(keyword));
    else model.addAttribute("products", productService.getAll());
    return "products";
  }

  @GetMapping("/add")
  public String addForm(Model model){
    model.addAttribute("productRequest", new ProductRequestDTO("", 0, 0));
    return "add-product";
  }

  @PostMapping("/add")
  public String addProduct(@ModelAttribute ProductRequestDTO request, RedirectAttributes red){
    boolean val = false;

    if(request.getName().length() <= 3){
      red.addFlashAttribute("status", "Peringatan");
      red.addFlashAttribute("message", "Nama produk terlalu pendek...");
      return "redirect:/products/add";
    }

    try {
      productService.addProduct(request);

      red.addFlashAttribute("status", "Sukses");
      red.addFlashAttribute("message", "Produk Berhasil Ditambahkan");
      val = true;

    } catch (IllegalArgumentException e) {
      red.addFlashAttribute("status", "Peringatan");
      red.addFlashAttribute("message", e.getMessage());

    } catch (Exception e) {
      red.addFlashAttribute("status", "Gagal");
      red.addFlashAttribute("message", "Mohon maaf, terjadi kesalahan...");
      
    }
    if(val) return "redirect:/products";
    return "redirect:/products/add";
  }

  @GetMapping("/edit/{id}")
  public String editForm(@PathVariable Long id, Model model){
    model.addAttribute("product", productService.getById(id));
    model.addAttribute("id", id);
    return "edit-product";
  }

  @PostMapping("/edit/{id}")
  public String editProduct(
    @PathVariable Long id, 
    @ModelAttribute ProductRequestDTO request,
    RedirectAttributes red
  ){
    boolean res = false;

    if(request.getName().length() <= 3){
      red.addFlashAttribute("status", "Peringatan");
      red.addFlashAttribute("message", "Nama produk terlalu pendek...");
      return "redirect:/products/edit/" + id;
    }

    try {
      productService.editById(id, request);

      red.addFlashAttribute("status", "Sukses");
      red.addFlashAttribute("message", "Produk Berhasil Diedit");
      res = true;
      
    } catch (IllegalArgumentException e) {
      red.addFlashAttribute("status", "Peringatan");
      red.addFlashAttribute("message", e.getMessage());

    } catch (Exception e) {
      red.addFlashAttribute("status", "Gagal");
      red.addFlashAttribute("message", "Mohon maaf, gagal edit produk");
    }
    if(res) return "redirect:/products";
    return "redirect:/products/edit/" + id;
  }

  @GetMapping("/delete/{id}")
  public String deleteProduct(@PathVariable Long id, RedirectAttributes red){
    productService.deleteById(id);
    red.addFlashAttribute("status", "Sukses");
    red.addFlashAttribute("message", "Produk dengan id " + id + " berhasil dihapus");
    return "redirect:/products";
  }
}
