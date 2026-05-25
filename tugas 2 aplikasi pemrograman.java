// Kelas Menu untuk merepresentasikan menu makanan dan minuman di restoran
public class Menu {
    // Atribut-atribut menu
    private String nama;
    private double harga;
    private String kategori; // "Makanan" atau "Minuman"
    private String kodeMenu;  // Kode unik untuk setiap menu
    
    // Constructor
    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.kodeMenu = generateKodeMenu();
    }
    
    // Generate kode menu otomatis
    private String generateKodeMenu() {
        String prefix = kategori.equals("Makanan") ? "MKN" : "MNM";
        return prefix + System.currentTimeMillis() % 10000;
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
    
    public String getKodeMenu() {
        return kodeMenu;
    }
    
    // Setter methods
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setHarga(double harga) {
        this.harga = harga;
    }
    
    // Method untuk menampilkan informasi menu
    public void tampilkanInfo() {
        System.out.printf("%-10s | %-25s | Rp %-12.2f | %-10s\n", 
            kodeMenu, nama, harga, kategori);
    }
    
    // Method untuk menampilkan menu dalam format sederhana
    public void tampilkanSederhana() {
        System.out.printf("%-30s Rp %-12.2f\n", nama, harga);
    }
}