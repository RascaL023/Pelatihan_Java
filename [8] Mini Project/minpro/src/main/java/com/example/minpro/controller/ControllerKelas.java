package com.example.minpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.minpro.model.Kelas;
import com.example.minpro.service.ServiceDetailKelas;
import com.example.minpro.service.ServiceKelas;

@Controller
@RequestMapping("/kelas")
public class ControllerKelas {
    @Autowired private ServiceKelas serviceKelas;
    @Autowired private ServiceDetailKelas serviceDetailKelas;

    @GetMapping("")
    public String daftarKelas(Model m){
        m.addAttribute("kelas", serviceKelas.getAll());
        return "daftar-kelas";
    }

    @GetMapping("/detail/{kode}")
    public String detailKelas(@PathVariable String kode, Model m) {
        Kelas kelas = null;
        for(Kelas k : serviceKelas.getAll()) if(k.getKode().equals(kode)){ kelas = k; break; }
        
        if(kelas != null){
            m.addAttribute("kelas", kelas);
	    m.addAttribute("peserta", serviceDetailKelas.getPesertaByKode(kode));
            return "detail-kelas";
        }return "daftar-kelas";
    }

    @GetMapping("/list")
    public String listKelas(Model m){
	m.addAttribute("kelas", serviceKelas.getAll());
	return "card-kelas";
    }

    @GetMapping("/tambah")
    public String tambahKelas(Model m){
	m.addAttribute("kelas", new Kelas("", "", "", 0, 0));
	return "tambah-kelas";
    }

    @PostMapping("/simpan")
    public String save(@ModelAttribute Kelas k){
	serviceKelas.addKelas(k);
	return "redirect:/kelas";
    }

    @GetMapping("/hapus/{kode}") 
    // Harusnya PostMapping, tapi karena style formnya udah terlalu universal, jadi ribet kalau di paksa PostMapping teh... :)
    public String hapusKelas(@PathVariable String kode){
	serviceKelas.deleteKelas(serviceKelas.getByKode(kode));
	return "redirect:/kelas";
    }
}
