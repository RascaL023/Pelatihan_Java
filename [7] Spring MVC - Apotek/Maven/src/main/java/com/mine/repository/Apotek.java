package com.mine.repository;

import java.util.List;
import java.util.ArrayList;
import com.mine.model.Obat;

public class Apotek {
    private List<Obat> listObat = new ArrayList<>();

    public Apotek(){
        listObat.add(new Obat("001", "Paramex", "Analgesik", 20, 3000, "Obat Sakit Kepala", "Pak Iwan", "089541889351"));
        listObat.add(new Obat("002", "Panadol", "Analgesik", 25, 2500, "Sama seperti 001", "Buk Iwan", "085941809334"));
    }
    
    public List<Obat> getAllObat(){ return listObat; }

    public Obat find(String kode){
        for(int i = 0; i < listObat.size(); i++)
            if(listObat.get(i).getKode().equals(kode)) return listObat.get(i);
        return null;
    }

    public void addNew(Obat o){ listObat.add(o); }
    public void delete(String kode){ listObat.remove(find(kode)); }
}
