package id.rascal.student_managements.controller;

import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import id.rascal.student_managements.dto.StudentRequestDTO;
import id.rascal.student_managements.dto.StudentResponseDTO;
import id.rascal.student_managements.service.StudentService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {
  @Autowired StudentService studentService;
  private static int shownRec = 6;

  @GetMapping
  public String index(){
    return "SPAindex";
  }


  @GetMapping("/add")
  public String addForm(Model model){
    model.addAttribute("student", new StudentRequestDTO(
      "", "", "", Year.now()
    ));
    return "pages/add-student :: addForm";
  }

  @PostMapping("/add")
  public String addSubmit(
    @Valid @ModelAttribute StudentRequestDTO request, 
    RedirectAttributes red,
    Model model,
    BindingResult result
  ) { 
    if(result.hasErrors()) return "pages/add-student :: addForm";

    try {
      studentService.addStudent(request);
      
      red.addFlashAttribute("status", "Sukses");
      red.addFlashAttribute("msg", "Berhasil menambahkan mahasiswa baru!");
        
      Page<StudentResponseDTO> paged = studentService.getPage(0, shownRec);
      model.addAttribute("students", paged);
      model.addAttribute("curr", 0);
      model.addAttribute("pageTotal", paged.getTotalPages());
 
      return "pages/student-list :: student-list";
    } catch (IllegalArgumentException e) {
      red.addFlashAttribute("msg", e.getMessage());
      System.out.println(e.getMessage());
    } catch (Exception e) {
      red.addFlashAttribute("msg", e.getMessage());
      System.out.println(e.getMessage());
    } 
 
    // red.addFlashAttribute("status", "Gagal");
    model.addAttribute("student", request);
    return "pages/add-student :: addForm";
  }



  @GetMapping("/list")
  public String getPage(
      Model model, 
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "7") int size
  ){

    Page<StudentResponseDTO> paged = studentService.getPage(page, size);
    model.addAttribute("students", paged);
    model.addAttribute("curr", page);
    model.addAttribute("pageTotal", paged.getTotalPages());

    return "pages/student-list :: student-list";
  }


  @GetMapping("/edit/{id}")
  public String editForm(Model model, @PathVariable Long id){
    model.addAttribute("student", studentService.getById(id));
    model.addAttribute("id", id);
    return "pages/edit-student :: editForm";
  }

  @PostMapping("/edit/{id}")
  public String editSubmit( 
    @PathVariable Long id,
    @Valid @ModelAttribute StudentRequestDTO request,
    Model model,
    RedirectAttributes red,
    BindingResult result
  ){ boolean val = false;
    if(result.hasErrors()) return "pages/edit-student :: editForm";

    try{
      studentService.editById(id, request);

      red.addFlashAttribute("status", "Sukses");
      red.addFlashAttribute("msg", "Berhasil update mahasiswa id " + id);
      // model.addAttribute("status", "Sukses");
      // model.addAttribute("msg", "Berhasil update mahasiswa id " + id);

      Page<StudentResponseDTO> paged = studentService.getPage(0, shownRec);
      model.addAttribute("students", paged);
      model.addAttribute("curr", 0);
      model.addAttribute("pageTotal", paged.getTotalPages());
      val = true;
    } catch (IllegalArgumentException e){
      red.addFlashAttribute("msg", e.getMessage());
    } catch (RuntimeException e){
      red.addFlashAttribute("msg", e.getMessage());
    }

    if(val) return "pages/student-list :: student-list";
    red.addFlashAttribute("status", "Gagal");
    return "pages/edit-student :: editForm";
  }


  @PostMapping("/delete/{id}")
  public String deleteById(
    @PathVariable Long id,
    RedirectAttributes red,
    Model model
  ){
    try {
      studentService.deleteById(id);
      red.addFlashAttribute("status", "Sukses");
      red.addFlashAttribute("msg", 
        "Mahasiswa dengan id " + id + " berhasil dihapus");
    } catch (IllegalArgumentException e){
      red.addFlashAttribute("msg", e.getMessage());
    } catch (Exception e) {
      red.addFlashAttribute("msg", e.getMessage());
    }

    Page<StudentResponseDTO> paged = studentService.getPage(0, shownRec);
    model.addAttribute("students", paged);
    model.addAttribute("curr", 0);
    model.addAttribute("pageTotal", paged.getTotalPages());

    return "pages/student-list :: student-list";    
  }


  @GetMapping("/search")
  public String searchByName(
    @RequestParam String q,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "7") int size,
    Model model
  ){
    Page<StudentResponseDTO> paged = studentService.searchByName(q, page, size);

    model.addAttribute("students", paged);
    model.addAttribute("curr", page);
    model.addAttribute("pageTotal", paged.getTotalPages());
    model.addAttribute("query", q);

    return "pages/student-list :: student-list";
  }
}
