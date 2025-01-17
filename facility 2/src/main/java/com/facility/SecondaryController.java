package com.facility;

// Import paket dan kelas yang diperlukan
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox; // Import untuk ComboBox
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class SecondaryController {
    // Deklarasi elemen-elemen UI
    @FXML
    private ComboBox<String> kategoriComboBox; // ComboBox untuk kategori
    @FXML
    private TextField namaTempatField;
    @FXML
    private TextField deskripsiField;
    @FXML
    private TextField alamatField;
    @FXML
    private TextField hargaField;
    @FXML
    private TextField fasilitasField;
    @FXML
    private TextField jamOperasionalField;
    @FXML
    private TextField kontakField;

    @FXML
    private void initialize() {
        // Memuat daftar kategori ke dalam ComboBox
        kategoriComboBox.getItems().addAll("Kategori 1", "Kategori 2", "Kategori 3"); // Tambahkan kategori sesuai kebutuhan
    }

    @FXML
    private void handleSave() {
        // Mengambil data dari input pengguna
        String kategori = kategoriComboBox.getValue(); // Mendapatkan kategori yang dipilih
        String namaTempat = namaTempatField.getText().trim();
        String deskripsi = deskripsiField.getText().trim();
        String alamat = alamatField.getText().trim();
        String hargaText = hargaField.getText().trim();
        String fasilitas = fasilitasField.getText().trim();
        String jamOperasional = jamOperasionalField.getText().trim();
        String kontakText = kontakField.getText().trim();

        // Validasi input: pastikan semua field diisi
        if (kategori == null || kategori.isEmpty() || namaTempat.isEmpty() || deskripsi.isEmpty() || alamat.isEmpty() ||
            hargaText.isEmpty() || fasilitas.isEmpty() || jamOperasional.isEmpty() || kontakText.isEmpty()) {
            showAlert("Semua field harus diisi.", Alert.AlertType.WARNING);
            return;
        }
    
        try {
            // Parsing data harga dan kontak ke tipe data yang sesuai
            double harga = Double.parseDouble(hargaText);
            int kontak = Integer.parseInt(kontakText);

            // Menambahkan fasilitas baru ke database
            Database.addFasilitas(kategori, namaTempat, deskripsi, alamat, harga, fasilitas, jamOperasional, kontak);

            // Kembali ke tampilan utama setelah menyimpan data
            App.setRoot("primary");
        } catch (NumberFormatException e) {
            // Menangani kesalahan parsing angka
            showAlert("Kontak dan Harga harus berupa angka!", Alert.AlertType.ERROR);
        } catch (SQLException | IOException e) {
            // Menangani kesalahan lain
            e.printStackTrace();
            showAlert("An error occurred while saving the facility.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void switchToPrimary() throws IOException {
        // Berpindah ke tampilan utama
        App.setRoot("primary");
    }

    // Fungsi utilitas untuk menampilkan pesan alert
    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
