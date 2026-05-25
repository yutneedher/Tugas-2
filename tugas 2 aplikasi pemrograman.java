public class Menu {
    private String nama;
    private double harga;
    private String kategori;
    
    // Constructor
    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
    
    // Getter methods
    public String getNama() {
        return nama;
    }
    
    public double getHarga() {
        return harga;
    }
    
    public String getKategori() {
        return kategori;
    }
    
    // Setter methods
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setHarga(double harga) {
        this.harga = harga;
    }
    
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    
    // Method untuk menampilkan menu
    public void tampilkanInfo() {
        System.out.printf("%-25s Rp %-12.2f %s\n", nama, harga, kategori);
    }
}
