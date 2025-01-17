package com.facility;
// Mendeklarasikan package bernama `com.facility`.

public class Fasilitas {
    // Deklarasi atribut/field untuk menyimpan informasi fasilitas.
    private int id; // ID unik untuk fasilitas.
    private String kategori; // Kategori fasilitas.
    private String namaTempat; // Nama tempat fasilitas.
    private String deskripsi; // Deskripsi fasilitas.
    private String alamat; // Alamat fasilitas.
    private double harga; // Harga fasilitas.
    private String fasilitas; // Informasi tambahan tentang fasilitas.
    private String jamOperasional; // Jam operasional fasilitas.
    private int kontak; // Nomor kontak fasilitas.

    // Constructor: digunakan untuk menginisialisasi objek `Fasilitas`.
    public Fasilitas(int id, String kategori, String namaTempat, String deskripsi, String alamat, 
                     double harga, String fasilitas, String jamOperasional, int kontak) {
        this.id = id; // Mengatur ID fasilitas.
        this.kategori = kategori; // Mengatur kategori fasilitas.
        this.namaTempat = namaTempat; // Mengatur nama tempat.
        this.deskripsi = deskripsi; // Mengatur deskripsi fasilitas.
        this.alamat = alamat; // Mengatur alamat fasilitas.
        this.harga = harga; // Mengatur harga fasilitas.
        this.fasilitas = fasilitas; // Mengatur informasi tambahan fasilitas.
        this.jamOperasional = jamOperasional; // Mengatur jam operasional.
        this.kontak = kontak; // Mengatur nomor kontak fasilitas.
    }

    // Getter dan Setter: menyediakan akses untuk membaca/mengubah nilai atribut.

    public int getId() { 
        return id; // Mengembalikan nilai ID.
    }

    public String getKategori() { 
        return kategori; // Mengembalikan kategori fasilitas.
    }

    public void setKategori(String kategori) { 
        this.kategori = kategori; // Mengatur kategori fasilitas.
    }

    public String getNamaTempat() { 
        return namaTempat; // Mengembalikan nama tempat.
    }

    public void setNamaTempat(String namaTempat) { 
        this.namaTempat = namaTempat; // Mengatur nama tempat.
    }

    public String getDeskripsi() { 
        return deskripsi; // Mengembalikan deskripsi fasilitas.
    }

    public void setDeskripsi(String deskripsi) { 
        this.deskripsi = deskripsi; // Mengatur deskripsi fasilitas.
    }

    public String getAlamat() { 
        return alamat; // Mengembalikan alamat fasilitas.
    }

    public void setAlamat(String alamat) { 
        this.alamat = alamat; // Mengatur alamat fasilitas.
    }

    public double getHarga() { 
        return harga; // Mengembalikan harga fasilitas.
    }

    public void setHarga(double harga) { 
        this.harga = harga; // Mengatur harga fasilitas.
    }

    public String getFasilitas() { 
        return fasilitas; // Mengembalikan informasi tambahan fasilitas.
    }

    public void setFasilitas(String fasilitas) { 
        this.fasilitas = fasilitas; // Mengatur informasi tambahan fasilitas.
    }

    public String getJamOperasional() { 
        return jamOperasional; // Mengembalikan jam operasional fasilitas.
    }

    public void setJamOperasional(String jamOperasional) { 
        this.jamOperasional = jamOperasional; // Mengatur jam operasional fasilitas.
    }

    public int getKontak() { 
        return kontak; // Mengembalikan nomor kontak fasilitas.
    }

    public void setKontak(int kontak) { 
        this.kontak = kontak; // Mengatur nomor kontak fasilitas.
    }

    // Method `toString`: memberikan representasi string dari objek `Fasilitas`.
    @Override
    public String toString() {
        return "Fasilitas{" +
                "id=" + id +
                ", kategori='" + kategori + '\'' +
                ", namaTempat='" + namaTempat + '\'' +
                ", deskripsi='" + deskripsi + '\'' +
                ", alamat='" + alamat + '\'' +
                ", harga=" + harga +
                ", fasilitas='" + fasilitas + '\'' +
                ", jamOperasional='" + jamOperasional + '\'' +
                ", kontak=" + kontak +
                '}';
    }
}
