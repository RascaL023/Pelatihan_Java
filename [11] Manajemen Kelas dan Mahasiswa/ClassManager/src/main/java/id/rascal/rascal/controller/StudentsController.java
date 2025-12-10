package id.rascal.rascal.controller;

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

import id.rascal.rascal.dto.requests.StudentsRequest;
import id.rascal.rascal.dto.responses.StudentsResponse;
import id.rascal.rascal.service.ClassesService;
import id.rascal.rascal.service.StudentsService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentsController {
  @Autowired StudentsService studentsService;
  @Autowired ClassesService classesService;

  @GetMapping
  public String listPage(
    Model model,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size
  ){
    Page<StudentsResponse> paged = studentsService.getPaged(page, size);

    model.addAttribute("students", paged);
    model.addAttribute("currPage", page);
    model.addAttribute("totalPages", paged.getTotalPages());
    model.addAttribute("first", paged.isFirst());
    model.addAttribute("last", paged.isLast());

    return "students";
  }

  @GetMapping("/add")
  public String addForm(Model model){
    model.addAttribute("classes", classesService.getClassSignature());
    model.addAttribute("students", new StudentsRequest());
    return "add-student";
  }

  @PostMapping("/add")
  public String addSubmit(
    @Valid @ModelAttribute StudentsRequest request,
    RedirectAttributes red,
    BindingResult result
  ){
    if(result.hasErrors()) return "redirect:/add-student";
    
    try {
      String msg = studentsService.addStudent(request);
      System.out.println(msg);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return "redirect:/students";
  }

  @PostMapping("/delete/{id}")
  public String deleteById(@PathVariable Long id){
    System.out.println("PPP");
    try {
      studentsService.deleteStudent(id);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    
    return "redirect:/students";
  }

  @GetMapping("/edit/{id}")
  public String editForm(
    Model model,
    @PathVariable Long id
  ){
    model.addAttribute("student", studentsService.getById(id));
    model.addAttribute("classList", classesService.getClassSignature());
    model.addAttribute("id", id);
    return "edit-student";
  }

  @PostMapping("/edit/{id}")
  public String editStudent(
    @PathVariable Long id,
    @ModelAttribute StudentsRequest request
  ){
    studentsService.editStudent(id, request);
    return "redirect:/students";
  }
}
