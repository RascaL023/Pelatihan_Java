package com.example.minpro.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.example.minpro.model.Kelas;
import com.example.minpro.model.Peserta; 
import com.example.minpro.repo.RepoDetailKelas;
import com.example.minpro.repo.RepoKelas;
import com.example.minpro.repo.RepoPeserta;
  
@Service 
public class ServiceDetailKelas { 
    @Autowired private RepoDetailKelas repoDetailKelas;
    @Autowired private RepoPeserta repoPeserta;
    @Autowired private RepoKelas repoKelas;
    
    public Map<String, List<Peserta>> getAll(){ return repoDetailKelas.getAll(); }

    public List<Peserta> getPesertaByKode(String kode){
	return repoDetailKelas.getAll().get(kode);
    }

    public boolean addPesertaByKode(String kode, Peserta p){
	Kelas k = repoKelas.getKelas(kode);
	boolean val = true;
	for(Peserta ps : getPesertaByKode(kode)){
		if(ps.getEmail().equals(p.getEmail())){
			val = false;
			break;
		}
	}

	if(k.getJumlahPeserta() < k.getKapasitas() && val){
		repoDetailKelas.addPeserta(kode, p);
		return false;
	}return true;
    }

    public void removePeserta(String kode){
	repoDetailKelas.removePeserta(kode);
    }

    public void removePesertaByEmail(String email, String kode){
	repoDetailKelas.removePesertaByEmail(email, kode);
    }
}
