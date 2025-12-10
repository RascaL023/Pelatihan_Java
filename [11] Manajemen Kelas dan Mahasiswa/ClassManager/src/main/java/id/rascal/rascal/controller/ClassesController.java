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

import id.rascal.rascal.dto.requests.ClassesRequest;
import id.rascal.rascal.dto.responses.ClassesResponse;
import id.rascal.rascal.service.ClassesService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/classes")
public class ClassesController {
  @Autowired ClassesService classesService;

  @GetMapping
  public String listPage(
    Model model,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size
  ){
    Page<ClassesResponse> paged = classesService.getPaged(page, size);

    model.addAttribute("classes", paged);
    model.addAttribute("currPage", page);
    model.addAttribute("totalPages", paged.getTotalPages());
    model.addAttribute("first", paged.isFirst());
    model.addAttribute("last", paged.isLast());

    return "classes";
  }

  @GetMapping("/add")
  public String addForm(Model model){
    model.addAttribute("classes", new ClassesRequest());
    return "add-class";
  }

  @PostMapping("/add")
  public String addSubmit(
    @Valid @ModelAttribute ClassesRequest request,
    RedirectAttributes red,
    BindingResult result
  ){
    if(result.hasErrors()) return "redirect:/add-class";

    try {
      classesService.addClass(request);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return "redirect:/classes";
  }

  @PostMapping("/delete/{id}")
  public String deleteClass(@PathVariable Long id){
    System.out.println("PPP");
    try {
      classesService.deleteClass(id);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    return "redirect:/classes";
  }

  @GetMapping("/edit/{id}")
  public String editForm(Model model, @PathVariable Long id){
    model.addAttribute("classes", classesService.getById(id));
    model.addAttribute("id", id);

    return "edit-class";
  }

  @PostMapping("/edit/{id}")
  public String editClass(
    @ModelAttribute ClassesRequest request,
    @PathVariable Long id
  ){
    classesService.editClass(id, request);
    return "redirect:/classes";
  }

  @GetMapping("/detail/{id}")
  public String detailPage(
    Model model,
    @PathVariable Long id
  ){
    model.addAttribute("detailedClass", classesService.getResponseById(id));

    return "class-detail";
  }
}
