package com.example.minpro.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.minpro.model.Peserta;

@Repository
public class RepoPeserta {
    private List<Peserta> db = new ArrayList<>();

    public List<Peserta> getAll(){ return db; }

    public void add(Peserta p){ db.add(p); }
    public void delete(Peserta p){ db.remove(p); }
}
