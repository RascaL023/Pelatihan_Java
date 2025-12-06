package id.rascal.student_managements.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PopupController {
  @GetMapping("/popup")
  public String popup(
    @RequestParam String status,
    @RequestParam String msg,
    Model model
  ){
    model.addAttribute("status", status);
    model.addAttribute("msg", msg);
    return "fragments/popup :: popup";
  }
}
