package com.example.minpro.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.minpro.model.Kelas;

@Repository
public class RepoKelas {
    private List<Kelas> db = new ArrayList<>();

    public List<Kelas> getAll(){ return db; }

    public void add(Kelas k){ db.add(k); }
    public void delete(Kelas k){ db.remove(k); }

    public Kelas getKelas(String kode){
	for(Kelas k : db){
		if(k.getKode().equals(kode)) return k;
	}return null;
    }
}
