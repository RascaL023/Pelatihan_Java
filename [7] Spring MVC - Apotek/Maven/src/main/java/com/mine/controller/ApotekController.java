package com.mine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;
import com.mine.model.Obat;
import com.mine.services.ApotekServices;

@Controller
@RequestMapping("/obat")
public class ApotekController {
    @Autowired
    private ApotekServices apotek;

    @GetMapping("/list")
    public String listObat(Model model){
        List<Obat> obat = apotek.getsObat();
        model.addAttribute("obat", obat);
        return "list-obat";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("obat", new Obat());
        model.addAttribute("up", false);
        return "form-obat";
    }

    @GetMapping("/update/{kode}")
    public String editForm(@PathVariable("kode") String kode, Model model) {
        Obat exist = apotek.findObat(kode);
        model.addAttribute("obat", exist);
        model.addAttribute("up", true);
        return "form-obat";
    }

    @GetMapping("/delete/{kode}")
    public String delete(@PathVariable("kode") String kode, Model model) {
        Obat exist = apotek.findObat(kode);
        if(exist != null) apotek.delete(kode);
        return "redirect:/obat/list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("obat") Obat obat) {
        Obat exist = apotek.findObat(obat.getKode());

        if (exist != null) {
            exist.setName(obat.getName());
            exist.setKategori(obat.getKategori());
            exist.setDeskripsi(obat.getDeskripsi());
            exist.setSupplier(obat.getSupplier());
            exist.setNoSupplier(obat.getNoSupplier());
            exist.setStok(obat.getStok());
            exist.setHarga(obat.getHarga());
        } else {
            apotek.addNew(obat);
        }

        return "redirect:/obat/list";
    }


}
