package com.example.minpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.minpro.model.Peserta;
import com.example.minpro.repo.RepoPeserta;

@Service
public class ServicePeserta {
    @Autowired
    private RepoPeserta repoPeserta;

    public List<Peserta> getAll(){ return repoPeserta.getAll(); }

    public void addPeserta(Peserta p){ repoPeserta.add(p); }
    public void deletePeserta(Peserta p){ repoPeserta.delete(p); }
}
