package com.mine.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.mine.model.Obat;
import com.mine.repository.Apotek;

@Service
public class ApotekServices {
    private Apotek db = new Apotek();

    public List<Obat> getsObat(){ return db.getAllObat(); }
    public Obat findObat(String kode){ return db.find(kode); }
    public void addNew(Obat o){ db.addNew(o); }
    public void delete(String kode){ db.delete(kode); }

}
