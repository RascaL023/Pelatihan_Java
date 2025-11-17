package com.example.minpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.minpro.model.Kelas;
import com.example.minpro.repo.RepoDetailKelas;
import com.example.minpro.repo.RepoKelas;

@Service
public class ServiceKelas {
    @Autowired private RepoKelas repoKelas;
    @Autowired private RepoDetailKelas repoDetailKelas;

    public List<Kelas> getAll(){ return repoKelas.getAll(); }
    public Kelas getByKode(String kode){ return repoKelas.getKelas(kode); }

    public void deleteKelas(Kelas k){ repoKelas.delete(k); repoDetailKelas.removePeserta(k.getKode()); }
    public void addKelas(Kelas nk){
	for(Kelas k : repoKelas.getAll()){
		if(k.getKode().equals(nk.getKode())){
			return;
		}
	}
        repoKelas.add(nk);
        repoDetailKelas.sinkronKelas(nk.getKode());
    }
}
