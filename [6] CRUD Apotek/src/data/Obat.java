package src.data;

public class Obat {
    private String kode, nama, kategori, supplier;
    private String deskripsi, noSupplier;
    private int stok; private double harga;

    public Obat(String k, String n, String kat, int s, double h, String supplier, String deskripsi, String noSupplier){
        kode = k; nama = n; kategori = kat;
        stok = s; harga = h;
        this.supplier = supplier;
        this.deskripsi = deskripsi;
        this.noSupplier = noSupplier;
    }

    public String getKode(){ return kode; }
    public String getName(){ return nama; }
    public String getKategori(){ return kategori; }
    public String getDeskripsi(){ return deskripsi; }
    public String getSupplier(){ return supplier; }
    public String getNoSupplier(){ return noSupplier; }
    public int getStok(){ return stok; }
    public double getHarga(){ return harga; }

    public void setKode(String kode){ this.kode = kode; }
    public void setName(String nama){ this.nama = nama; }
    public void setKategori(String kategori){ this.kategori = kategori; }
    public void setDeskripsi(String deskripsi){ this.deskripsi = deskripsi; }
    public void setSupplier(String supplier){ this.supplier = supplier; }
    public void setNoSupplier(String noSupplier){ this.noSupplier = noSupplier; }
    public void setStok(int Stok){ this.stok = stok; }
    public void setHarga(double harga){ this.harga = harga; }

    public void showInfo(){
        System.out.println("Kode Obat    : " + kode);
        System.out.println("Nama Obat    : " + nama);
        System.out.println("Kategori     : " + kategori);
        System.out.println("Deskripsi    : " + deskripsi);
        System.out.println("Stok         : " + stok);
        System.out.printf("Harga        : Rp%.1f\n", harga);
        System.out.println("Nama Supplier: " + supplier);
        System.out.println("No. Supplier : " + noSupplier);
    }
}
