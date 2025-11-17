package com.example.minpro.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.minpro.model.Kelas;
import com.example.minpro.model.Peserta;

import jakarta.annotation.PostConstruct;

@Repository
public class RepoDetailKelas {
    @Autowired RepoKelas repoKelas;
    @Autowired RepoPeserta repoPeserta;
    private Map<String, List<Peserta>> db = new HashMap<>();

    @PostConstruct
    public void init(){
        for(Kelas k : repoKelas.getAll()) db.put(k.getKode(), new ArrayList<>());        
    }

    public Map<String, List<Peserta>> getAll(){ return db; }

    public void addPeserta(String kodeKelas, Peserta p){ 
    	List<Peserta> list = db.get(kodeKelas);
	if(list == null){
        	list = new ArrayList<>();
        	db.put(kodeKelas, list);
	}list.add(p);
	repoKelas.getKelas(kodeKelas).addPeserta();
    }

    public void sinkronKelas(String kodeKelas){
        db.put(kodeKelas, new ArrayList<>());
    }

    public void removePeserta(String kode){
	db.remove(kode);
    }

    public void removePesertaByEmail(String email, String kode){
	if(db.containsKey(kode)){
		for(int i = 0; i < db.get(kode).size(); i++){
			if(db.get(kode).get(i).getEmail().equals(email)){
				db.get(kode).remove(i);
				repoKelas.getKelas(kode).minPeserta();
				break;
			}
		}
	}
    }
}
