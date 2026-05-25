import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Menu> daftarMenu = new ArrayList<>();
    private static ArrayList<String> pesananNama = new ArrayList<>();
    private static ArrayList<Integer> pesananJumlah = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Inisialisasi data menu awal
        inisialisasiMenu();
        
        // Jalankan menu utama
        tampilkanMenuUtama();
    }
    
    // Method untuk menginisialisasi data menu awal
    private static void inisialisasiMenu() {
        // Menu Makanan (4 menu)
        daftarMenu.add(new Menu("Nasi Goreng Spesial", 25000, "Makanan"));
        daftarMenu.add(new Menu("Mie Ayam Komplit", 20000, "Makanan"));
        daftarMenu.add(new Menu("Ayam Bakar Pedas", 35000, "Makanan"));
        daftarMenu.add(new Menu("Sate Ayam 10 Tusuk", 30000, "Makanan"));
        
        // Menu Minuman (4 menu)
        daftarMenu.add(new Menu("Es Teh Manis", 5000, "Minuman"));
        daftarMenu.add(new Menu("Jus Jeruk Segar", 12000, "Minuman"));
        daftarMenu.add(new Menu("Kopi Hitam", 8000, "Minuman"));
        daftarMenu.add(new Menu("Susu Coklat Dingin", 10000, "Minuman"));
    }
    
    // Method untuk menampilkan menu utama
    private static void tampilkanMenuUtama() {
        while (true) {
            System.out.println("\n================================================");
            System.out.println("           APLIKASI RESTORAN SEDERHANA");
            System.out.println("================================================");
            System.out.println("1. Menu Pelanggan (Pemesanan)");
            System.out.println("2. Menu Pengelolaan (Pemilik Restoran)");
            System.out.println("3. Keluar Aplikasi");
            System.out.println("================================================");
            System.out.print("Pilih menu (1-3): ");
            
            int pilihan = 0;
            try {
                pilihan = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid! Masukkan angka 1-3.");
                continue;
            }
            
            if (pilihan == 1) {
                menuPelanggan();
            } else if (pilihan == 2) {
                menuPengelolaan();
            } else if (pilihan == 3) {
                System.out.println("\nTerima kasih telah menggunakan aplikasi ini!");
                break;
            } else {
                System.out.println("Pilihan tidak valid! Silakan pilih 1-3.");
            }
        }
    }
    
    // ==================== MENU PELANGGAN ====================
    private static void menuPelanggan() {
        pesananNama.clear();
        pesananJumlah.clear();
        
        System.out.println("\n================================================");
        System.out.println("           MODE PEMESANAN PELANGGAN");
        System.out.println("================================================");
        
        while (true) {
            tampilkanDaftarMenu();
            
            System.out.println("\nMasukkan nama menu yang ingin dipesan:");
            System.out.println("(Ketik 'selesai' untuk mengakhiri pemesanan)");
            System.out.print("> ");
            String inputMenu = scanner.nextLine().trim();
            
            if (inputMenu.equalsIgnoreCase("selesai")) {
                if (pesananNama.isEmpty()) {
                    System.out.println("\nBelum ada pesanan! Silakan pesan terlebih dahulu.");
                    continue;
                }
                break;
            }
            
            Menu menuDipilih = cariMenuByNama(inputMenu);
            
            if (menuDipilih == null) {
                System.out.println("\n!!! Menu tidak ditemukan !!!");
                System.out.println("Silakan pilih menu dari daftar yang tersedia.\n");
                continue;
            }
            
            System.out.print("Masukkan jumlah pesanan: ");
            int jumlah = 0;
            try {
                jumlah = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Jumlah tidak valid! Masukkan angka.\n");
                continue;
            }
            
            if (jumlah <= 0) {
                System.out.println("Jumlah pesanan harus lebih dari 0!\n");
                continue;
            }
            
            pesananNama.add(menuDipilih.getNama());
            pesananJumlah.add(jumlah);
            
            System.out.println("\n✓ " + jumlah + " " + menuDipilih.getNama() + " berhasil ditambahkan!\n");
        }
        
        cetakStrukPesanan();
    }
    
    // ==================== MENU PENGELOLAAN ====================
    private static void menuPengelolaan() {
        while (true) {
            System.out.println("\n================================================");
            System.out.println("         MODE PENGELOLAAN RESTORAN");
            System.out.println("================================================");
            System.out.println("1. Tambah Menu Baru");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Lihat Semua Menu");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.println("================================================");
            System.out.print("Pilih menu (1-5): ");
            
            int pilihan = 0;
            try {
                pilihan = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid! Masukkan angka 1-5.");
                continue;
            }
            
            if (pilihan == 1) {
                tambahMenu();
            } else if (pilihan == 2) {
                ubahHargaMenu();
            } else if (pilihan == 3) {
                hapusMenu();
            } else if (pilihan == 4) {
                tampilkanDaftarMenuLengkap();
            } else if (pilihan == 5) {
                break;
            } else {
                System.out.println("Pilihan tidak valid! Silakan pilih 1-5.");
            }
        }
    }
    
    // Method untuk menampilkan daftar menu (dikelompokkan berdasarkan kategori)
    private static void tampilkanDaftarMenu() {
        System.out.println("\n=================================================");
        System.out.println("              DAFTAR MENU RESTORAN");
        System.out.println("=================================================");
        
        System.out.println("\n【 MAKANAN 】");
        System.out.println("-------------------------------------------------");
        for (Menu menu : daftarMenu) {
            if (menu.getKategori().equals("Makanan")) {
                System.out.printf("%-25s Rp %-12.2f\n", menu.getNama(), menu.getHarga());
            }
        }
        
        System.out.println("\n【 MINUMAN 】");
        System.out.println("-------------------------------------------------");
        for (Menu menu : daftarMenu) {
            if (menu.getKategori().equals("Minuman")) {
                System.out.printf("%-25s Rp %-12.2f\n", menu.getNama(), menu.getHarga());
            }
        }
        System.out.println("=================================================");
    }
    
    // Method untuk menampilkan semua menu dengan nomor
    private static void tampilkanDaftarMenuLengkap() {
        System.out.println("\n=================================================");
        System.out.println("              DAFTAR SEMUA MENU");
        System.out.println("=================================================");
        
        for (int i = 0; i < daftarMenu.size(); i++) {
            Menu menu = daftarMenu.get(i);
            System.out.printf("%d. %-25s Rp %-12.2f (%s)\n", 
                (i + 1), menu.getNama(), menu.getHarga(), menu.getKategori());
        }
        System.out.println("=================================================");
    }
    
    // Method untuk mencari menu berdasarkan nama
    private static Menu cariMenuByNama(String nama) {
        for (Menu menu : daftarMenu) {
            if (menu.getNama().equalsIgnoreCase(nama)) {
                return menu;
            }
        }
        return null;
    }
    
    // Method untuk menambah menu baru
    private static void tambahMenu() {
        System.out.println("\n--- TAMBAH MENU BARU ---");
        
        System.out.print("Nama menu: ");
        String nama = scanner.nextLine().trim();
        
        if (nama.isEmpty()) {
            System.out.println("Nama menu tidak boleh kosong!");
            return;
        }
        
        System.out.print("Harga menu: Rp ");
        double harga = 0;
        try {
            harga = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Harga tidak valid!");
            return;
        }
        
        if (harga <= 0) {
            System.out.println("Harga harus lebih dari 0!");
            return;
        }
        
        System.out.print("Kategori (Makanan/Minuman): ");
        String kategori = scanner.nextLine().trim();
        
        if (!kategori.equalsIgnoreCase("Makanan") && !kategori.equalsIgnoreCase("Minuman")) {
            System.out.println("Kategori harus 'Makanan' atau 'Minuman'!");
            return;
        }
        
        // Konfirmasi
        System.out.println("\n--- KONFIRMASI ---");
        System.out.println("Nama     : " + nama);
        System.out.println("Harga    : Rp " + harga);
        System.out.println("Kategori : " + kategori);
        System.out.print("Apakah Anda yakin ingin menambahkan menu ini? (Ya/Tidak): ");
        
        String konfirmasi = scanner.nextLine().trim();
        if (konfirmasi.equalsIgnoreCase("Ya")) {
            daftarMenu.add(new Menu(nama, harga, kategori));
            System.out.println("✓ Menu berhasil ditambahkan!");
        } else {
            System.out.println("Penambahan menu dibatalkan.");
        }
    }
    
    // Method untuk mengubah harga menu
    private static void ubahHargaMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Tidak ada menu yang tersedia!");
            return;
        }
        
        tampilkanDaftarMenuLengkap();
        System.out.print("Pilih nomor menu yang akan diubah: ");
        
        int nomor = 0;
        try {
            nomor = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Nomor tidak valid!");
            return;
        }
        
        if (nomor < 1 || nomor > daftarMenu.size()) {
            System.out.println("Nomor menu tidak valid!");
            return;
        }
        
        Menu menuDipilih = daftarMenu.get(nomor - 1);
        System.out.println("\nMenu yang dipilih:");
        System.out.println("Nama  : " + menuDipilih.getNama());
        System.out.println("Harga : Rp " + menuDipilih.getHarga());
        
        System.out.print("\nMasukkan harga baru: Rp ");
        double hargaBaru = 0;
        try {
            hargaBaru = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Harga tidak valid!");
            return;
        }
        
        if (hargaBaru <= 0) {
            System.out.println("Harga harus lebih dari 0!");
            return;
        }
        
        // Konfirmasi
        System.out.println("\n--- KONFIRMASI ---");
        System.out.println("Harga lama : Rp " + menuDipilih.getHarga());
        System.out.println("Harga baru : Rp " + hargaBaru);
        System.out.print("Apakah Anda yakin ingin mengubah harga menu ini? (Ya/Tidak): ");
        
        String konfirmasi = scanner.nextLine().trim();
        if (konfirmasi.equalsIgnoreCase("Ya")) {
            menuDipilih.setHarga(hargaBaru);
            System.out.println("✓ Harga menu berhasil diubah!");
        } else {
            System.out.println("Perubahan harga dibatalkan.");
        }
    }
    
    // Method untuk menghapus menu
    private static void hapusMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Tidak ada menu yang tersedia!");
            return;
        }
        
        tampilkanDaftarMenuLengkap();
        System.out.print("Pilih nomor menu yang akan dihapus: ");
        
        int nomor = 0;
        try {
            nomor = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Nomor tidak valid!");
            return;
        }
        
        if (nomor < 1 || nomor > daftarMenu.size()) {
            System.out.println("Nomor menu tidak valid!");
            return;
        }
        
        Menu menuDipilih = daftarMenu.get(nomor - 1);
        System.out.println("\nMenu yang akan dihapus:");
        System.out.println("Nama  : " + menuDipilih.getNama());
        System.out.println("Harga : Rp " + menuDipilih.getHarga());
        System.out.println("Kategori: " + menuDipilih.getKategori());
        
        // Konfirmasi
        System.out.print("\nApakah Anda yakin ingin menghapus menu ini? (Ya/Tidak): ");
        String konfirmasi = scanner.nextLine().trim();
        
        if (konfirmasi.equalsIgnoreCase("Ya")) {
            daftarMenu.remove(nomor - 1);
            System.out.println("✓ Menu berhasil dihapus!");
        } else {
            System.out.println("Penghapusan menu dibatalkan.");
        }
    }
    
    // Method untuk mencetak struk pesanan
    private static void cetakStrukPesanan() {
        double totalAwal = 0;
        
        System.out.println("\n\n================================================");
        System.out.println("                STRUK PEMBAYARAN");
        System.out.println("             RESTORAN ENAK TENAN");
        System.out.println("================================================");
        System.out.printf("%-3s %-25s %-8s %-12s %-12s\n", "No", "Menu", "Qty", "Harga", "Subtotal");
        System.out.println("------------------------------------------------");
        
        for (int i = 0; i < pesananNama.size(); i++) {
            String namaMenu = pesananNama.get(i);
            int jumlah = pesananJumlah.get(i);
            Menu menu = cariMenuByNama(namaMenu);
            
            if (menu != null) {
                double subtotal = menu.getHarga() * jumlah;
                totalAwal += subtotal;
                System.out.printf("%-3d %-25s %-8d Rp %-9.2f Rp %-9.2f\n",
                    (i + 1), namaMenu, jumlah, menu.getHarga(), subtotal);
            }
        }
        
        System.out.println("------------------------------------------------");
        System.out.printf("%-52s Rp %10.2f\n", "Total Awal:", totalAwal);
        
        // Struktur keputusan: Diskon 10% jika total > 100000
        double diskon = 0;
        if (totalAwal > 100000) {
            diskon = totalAwal * 0.1;
            System.out.printf("%-52s Rp %10.2f\n", "Diskon 10% (Total > Rp 100.000):", diskon);
        }
        
        double totalSetelahDiskon = totalAwal - diskon;
        
        // Struktur keputusan: Promo beli 1 gratis 1 untuk minuman
        if (totalAwal > 50000) {
            int totalMinuman = 0;
            for (int i = 0; i < pesananNama.size(); i++) {
                String namaMenu = pesananNama.get(i);
                Menu menu = cariMenuByNama(namaMenu);
                if (menu != null && menu.getKategori().equals("Minuman")) {
                    totalMinuman += pesananJumlah.get(i);
                }
            }
            
            int gratis = totalMinuman / 2;
            if (gratis > 0) {
                System.out.printf("%-52s %11d menu\n", "Promo Beli 1 Gratis 1 (Minuman):", gratis);
                
                // Hitung diskon minuman
                double diskonMinuman = 0;
                for (int i = 0; i < pesananNama.size(); i++) {
                    String namaMenu = pesananNama.get(i);
                    Menu menu = cariMenuByNama(namaMenu);
                    if (menu != null && menu.getKategori().equals("Minuman")) {
                        int gratisItem = pesananJumlah.get(i) / 2;
                        diskonMinuman += gratisItem * menu.getHarga();
                    }
                }
                totalSetelahDiskon = totalSetelahDiskon - diskonMinuman;
                System.out.printf("%-52s Rp %10.2f\n", "Nilai Promo Minuman:", diskonMinuman);
            }
        }
        
        double pajak = totalSetelahDiskon * 0.1;
        double biayaPelayanan = 20000;
        double totalAkhir = totalSetelahDiskon + pajak + biayaPelayanan;
        
        System.out.printf("%-52s Rp %10.2f\n", "Total Setelah Diskon:", totalSetelahDiskon);
        System.out.printf("%-52s Rp %10.2f\n", "Pajak 10%:", pajak);
        System.out.printf("%-52s Rp %10.2f\n", "Biaya Pelayanan:", biayaPelayanan);
        System.out.println("------------------------------------------------");
        System.out.printf("%-52s Rp %10.2f\n", "TOTAL YANG HARUS DIBAYAR:", totalAkhir);
        System.out.println("================================================");
        System.out.println("      Terima kasih telah berkunjung!");
        System.out.println("      Selamat menikmati pesanan Anda!");
        System.out.println("================================================\n");
    }
}