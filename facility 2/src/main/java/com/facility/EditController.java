package com.facility;
// Mendeklarasikan package bernama `com.facility`.

import javafx.fxml.FXML; // Mengimpor anotasi FXML untuk menghubungkan komponen FXML dengan kode controller.
import javafx.scene.control.Alert; // Mengimpor class Alert untuk menampilkan pesan dialog.
import javafx.scene.control.ComboBox; // Mengimpor ComboBox untuk dropdown kategori.
import javafx.scene.control.TextField; // Mengimpor TextField untuk input teks.

import java.io.IOException; // Untuk menangani kesalahan input/output.
import java.sql.SQLException; // Untuk menangani kesalahan SQL.

public class EditController {
    @FXML
    private ComboBox<String> kategoriComboBox; // ComboBox untuk memilih kategori.
    @FXML
    private TextField namaTempatField; // Field untuk nama tempat.
    @FXML
    private TextField deskripsiField; // Field untuk deskripsi tempat.
    @FXML
    private TextField alamatField; // Field untuk alamat.
    @FXML
    private TextField hargaField; // Field untuk harga.
    @FXML
    private TextField fasilitasField; // Field untuk fasilitas yang tersedia.
    @FXML
    private TextField jamOperasionalField; // Field untuk jam operasional.
    @FXML
    private TextField kontakField; // Field untuk nomor kontak.

    private static Fasilitas selectedFasilitas; // Objek `Fasilitas` yang sedang diedit (jika mode edit).

    // Setter untuk menentukan fasilitas yang dipilih.
    public static void setSelectedFasilitas(Fasilitas fasilitas) {
        selectedFasilitas = fasilitas;
    }

    // Metode inisialisasi otomatis saat controller di-load.
    @FXML
    private void initialize() {
        // Menambahkan item ke ComboBox kategori.
        kategoriComboBox.getItems().addAll("Kategori 1", "Kategori 2", "Kategori 3"); // Tambahkan kategori sesuai kebutuhan.

        // Jika ada fasilitas yang dipilih, isi semua field dengan data fasilitas.
        if (selectedFasilitas != null) {
            kategoriComboBox.setValue(selectedFasilitas.getKategori()); // Mengatur nilai ComboBox berdasarkan kategori.
            namaTempatField.setText(selectedFasilitas.getNamaTempat());
            deskripsiField.setText(selectedFasilitas.getDeskripsi());
            alamatField.setText(selectedFasilitas.getAlamat());
            hargaField.setText(String.valueOf(selectedFasilitas.getHarga()));
            fasilitasField.setText(selectedFasilitas.getFasilitas());
            jamOperasionalField.setText(selectedFasilitas.getJamOperasional());
            kontakField.setText(String.valueOf(selectedFasilitas.getKontak()));
        }
    }

    // Metode untuk menangani tombol "Simpan".
    @FXML
    private void handleSave() {
        // Mengambil input dari semua field.
        String kategori = kategoriComboBox.getValue(); // Nilai kategori dari ComboBox.
        String namaTempat = namaTempatField.getText().trim(); // Menghapus spasi berlebih di input.
        String deskripsi = deskripsiField.getText().trim();
        String alamat = alamatField.getText().trim();
        String hargaText = hargaField.getText().trim();
        String fasilitas = fasilitasField.getText().trim();
        String jamOperasional = jamOperasionalField.getText().trim();
        String kontakText = kontakField.getText().trim();

        // Validasi untuk memastikan semua field terisi.
        if (kategori == null || kategori.isEmpty() || namaTempat.isEmpty() || deskripsi.isEmpty() || alamat.isEmpty() ||
            hargaText.isEmpty() || fasilitas.isEmpty() || jamOperasional.isEmpty() || kontakText.isEmpty()) {
            showAlert("Semua field harus diisi.", Alert.AlertType.WARNING);
            return;
        }

        try {
            // Parsing input harga dan kontak menjadi angka.
            double harga = Double.parseDouble(hargaText);
            int kontak = Integer.parseInt(kontakText);

            // Mode tambah atau edit berdasarkan ada atau tidaknya `selectedFasilitas`.
            if (selectedFasilitas == null) {
                // Mode tambah fasilitas baru.
                Database.addFasilitas(kategori, namaTempat, deskripsi, alamat, harga, fasilitas, jamOperasional, kontak);
            } else {
                // Mode edit fasilitas yang sudah ada.
                Database.updateFasilitas(selectedFasilitas.getId(), kategori, namaTempat, deskripsi, alamat, harga, fasilitas, jamOperasional, kontak);
            }

            // Kembali ke halaman utama (primary).
            App.setRoot("primary");
        } catch (NumberFormatException e) {
            // Validasi untuk memastikan harga dan kontak adalah angka.
            showAlert("Harga dan Kontak harus berupa angka.", Alert.AlertType.ERROR);
        } catch (SQLException | IOException e) {
            // Menangani kesalahan SQL atau IO.
            e.printStackTrace();
        }
    }

    // Metode untuk kembali ke halaman utama tanpa menyimpan perubahan.
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    // Metode untuk menampilkan pesan dialog kepada pengguna.
    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType); // Membuat alert berdasarkan tipe (WARNING, ERROR, dll.).
        alert.setContentText(message); // Menentukan pesan yang ditampilkan.
        alert.showAndWait(); // Menampilkan alert hingga pengguna menutupnya.
    }
}
