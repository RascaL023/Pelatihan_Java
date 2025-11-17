package src.manage;

import java.util.ArrayList;
import java.util.Scanner;
import src.data.Obat;

public class Apotek {
    ArrayList<Obat> listObat = new ArrayList<Obat>();

    public void tampilSemuaObat(){
        System.out.println("===== DAFTAR OBAT =====");
        for(Obat o : listObat){
            o.showInfo();
            System.out.println("-------------------------------------");
        }
    }

    public void tambahObat(Obat obat){ listObat.add(obat); }

    public Obat cariObat(String kode){
        for(int i = 0; i < listObat.size(); i++)
            if(listObat.get(i).getKode().equals(kode)) return listObat.get(i);
        return null;
    }

    public void ubahObat(String kode, Scanner inp){
        Obat o = cariObat(kode);
        if(o == null){
            System.out.println("Obat tidak ditemukan...");
            return;
        }

        System.out.print("Nama Baru           : ");
        o.setName(inp.nextLine());
        System.out.print("Kategori Baru       : ");
        o.setKategori(inp.nextLine());
        System.out.print("Deskripsi Baru      : ");
        o.setDeskripsi(inp.nextLine());
        System.out.print("Stok Baru           : ");
        o.setStok(inp.nextInt());
        System.out.print("Harga Baru          : ");
        o.setHarga(inp.nextFloat()); inp.nextLine();
        System.out.print("Nama Supplier Baru  : ");
        o.setSupplier(inp.nextLine());
        System.out.print("Nomor Supplier Baru : ");
        o.setNoSupplier(inp.nextLine());
    }

    public void hapusObat(String kode){
        Obat o = cariObat(kode);
        if(o == null) System.out.println("Obat tidak ditemukkan...");
        else{
            listObat.remove(o);
            System.out.println("Obat berhasil dihapus!");
        }
    }
}
