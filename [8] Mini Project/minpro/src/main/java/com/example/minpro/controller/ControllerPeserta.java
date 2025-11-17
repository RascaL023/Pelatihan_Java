package com.example.minpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.minpro.model.Peserta;
import com.example.minpro.service.ServiceDetailKelas;
import com.example.minpro.service.ServiceKelas;
import com.example.minpro.service.ServicePeserta;

@Controller
@RequestMapping("/peserta")
public class ControllerPeserta {
	@Autowired private ServicePeserta servicePeserta;  
	@Autowired private ServiceKelas serviceKelas;
	@Autowired private ServiceDetailKelas serviceDetailKelas;

	@GetMapping("/tambah/{kode}")
	public String tambahPeserta(@PathVariable String kode, Model m){
		m.addAttribute("kelas", serviceKelas.getByKode(kode));
		m.addAttribute("peserta", new Peserta("", "", ""));
		return "tambah-peserta";
	}

	@PostMapping("/simpan/{kode}")
	public String tambahPeserta(@ModelAttribute() Peserta p, @PathVariable String kode){
		if(serviceDetailKelas.addPesertaByKode(kode, p)) servicePeserta.addPeserta(p);
		return "redirect:/kelas/detail/" + kode;
	}

	@GetMapping("/daftar/{kode}")
	public String batalkanForm(@PathVariable String kode, Model m){
		m.addAttribute("kelas", serviceKelas.getByKode(kode));
		m.addAttribute("peserta", serviceDetailKelas.getPesertaByKode(kode));
		return "daftar-peserta";
	}

	@GetMapping("/hapus/{kode}/{email}")
	public String batalkan(@PathVariable String kode, @PathVariable String email){
		serviceDetailKelas.removePesertaByEmail(email, kode);
		return "redirect:/kelas/detail/" + kode;
	}
}
