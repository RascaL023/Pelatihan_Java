package src.main;

import java.util.Scanner;
import src.data.Obat;
import src.manage.Apotek;


class Helper{
    int menus(Scanner inp){
        System.out.println("====== MENU APOTEK ======");
        System.out.println("1. Tambah Obat");
        System.out.println("2. Lihat Daftar Obat");
        System.out.println("3. Cari Obat");
        System.out.println("4. Ubah Data Obat");
        System.out.println("5. Hapus Obat");
        System.out.println("6. Keluar");
        System.out.print("Pilih menu: ");
        return inp.nextInt();
    }

    void add(Apotek ap, Scanner inp){
        String kode, nama, kat, desk, supp, noSupp;
        int stok; double harga;

        System.out.print("Kode Obat         : ");
        kode = inp.nextLine();
        System.out.print("Nama Obat         : ");
        nama = inp.nextLine();
        System.out.print("Kategori Obat     : ");
        kat = inp.nextLine();
        System.out.print("Deskripsi Obat    : ");
        desk = inp.nextLine();
        System.out.print("Stok Obat         : ");
        stok = inp.nextInt();
        System.out.print("Harga Obat        : ");
        harga = inp.nextDouble(); inp.nextLine();
        System.out.print("Supplier Obat     : ");
        supp = inp.nextLine();
        System.out.print("No. Supplier Obat : ");
        noSupp = inp.nextLine();

        ap.tambahObat(new Obat(
            kode, nama, kat, stok, harga,
            supp, desk, noSupp
        ));

        System.out.println("Obat berhasil ditambahkan!");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        Apotek apt = new Apotek();
        Helper h = new Helper(); int o;

        while(true){
            o = h.menus(inp); System.out.println(""); inp.nextLine();

            if(o == 6) break;
            switch(o){
                case 1: h.add(apt, inp); break;
                case 2: apt.tampilSemuaObat(); break;
                case 3:
                    System.out.print("Masukkan kode obat yang ingin dicari: ");
                    Obat temp = apt.cariObat(inp.nextLine());
                    if(temp != null) temp.showInfo();
                break;
                case 4:
                    System.out.print("Masukkan kode obat yang ingin diubah: ");
                    apt.ubahObat(inp.nextLine(), inp);
                break;
                case 5:
                    System.out.print("Masukkan kode obat yang ingin dihapus: ");
                    apt.hapusObat(inp.nextLine());
                break;
            }
        }
    }
}
