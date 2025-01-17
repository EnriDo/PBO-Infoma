package com.facility;
// Mendeklarasikan package `com.facility`.

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;

public class PrimaryController {
    // Deklarasi elemen-elemen UI.
    @FXML
    private TableView<Fasilitas> tableView; // Tabel untuk menampilkan daftar fasilitas.
    @FXML
    private TableColumn<Fasilitas, String> kategoriColumn;
    @FXML
    private TableColumn<Fasilitas, String> namaTempatColumn;
    @FXML
    private TableColumn<Fasilitas, String> deskripsiColumn;
    @FXML
    private TableColumn<Fasilitas, String> alamatColumn;
    @FXML
    private TableColumn<Fasilitas, Double> hargaColumn;
    @FXML
    private TableColumn<Fasilitas, String> fasilitasColumn;
    @FXML
    private TableColumn<Fasilitas, String> jamOperasionalColumn;
    @FXML
    private TableColumn<Fasilitas, Integer> kontakColumn;

    private ObservableList<Fasilitas> fasilitasData = FXCollections.observableArrayList(); 
    // ObservableList untuk mengelola data fasilitas yang akan ditampilkan di tabel.

    @FXML
    private void initialize() {
        // Menghubungkan kolom tabel dengan atribut di kelas Fasilitas.
        kategoriColumn.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        namaTempatColumn.setCellValueFactory(new PropertyValueFactory<>("namaTempat"));
        deskripsiColumn.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
        alamatColumn.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        hargaColumn.setCellValueFactory(new PropertyValueFactory<>("harga"));
        fasilitasColumn.setCellValueFactory(new PropertyValueFactory<>("fasilitas"));
        jamOperasionalColumn.setCellValueFactory(new PropertyValueFactory<>("jamOperasional"));
        kontakColumn.setCellValueFactory(new PropertyValueFactory<>("kontak"));

        loadFasilitasData(); // Memuat data fasilitas ke tabel.
        addRowColoring(); // Menambahkan pewarnaan untuk baris tabel.
    }

    private void loadFasilitasData() {
        try {
            fasilitasData.setAll(Database.getAllFasilitas()); // Mengambil data fasilitas dari database.
            tableView.setItems(fasilitasData); // Mengatur data tabel dengan fasilitasData.
        } catch (SQLException e) {
            showError("Failed to load facilities from database.");
            e.printStackTrace();
        }
    }

    private void addRowColoring() {
        // Menambahkan pewarnaan pada baris tabel menggunakan pola zebra.
        tableView.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(Fasilitas item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setStyle(""); // Menghapus style untuk baris kosong.
                } else {
                    // Warna berbeda untuk baris genap dan ganjil.
                    if (getIndex() % 2 == 0) {
                        setStyle("-fx-background-color: #f2f2f2;"); // Warna abu-abu muda untuk baris genap.
                    } else {
                        setStyle("-fx-background-color: #ddd;"); // Warna putih untuk baris ganjil.
                    }
                }
            }
        });
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary"); // Berpindah ke form tambah fasilitas.
    }

    @FXML
    private void handleEdit() throws IOException {
        // Mengambil fasilitas yang dipilih dari tabel.
        Fasilitas selectedFasilitas = tableView.getSelectionModel().getSelectedItem();

        if (selectedFasilitas != null) {
            EditController.setSelectedFasilitas(selectedFasilitas); // Mengatur fasilitas terpilih untuk pengeditan.
            App.setRoot("edit"); // Berpindah ke form edit fasilitas.
        } else {
            showWarning("Please select a facility to edit.");
        }
    }

    @FXML
    private void handleDelete() {
        // Mengambil fasilitas yang dipilih dari tabel.
        Fasilitas selectedFasilitas = tableView.getSelectionModel().getSelectedItem();

        if (selectedFasilitas != null) {
            // Konfirmasi penghapusan.
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Confirmation");
            confirmationAlert.setHeaderText("Delete Facility");
            confirmationAlert.setContentText("Are you sure you want to delete this facility?");

            if (confirmationAlert.showAndWait().get() == ButtonType.OK) {
                try {
                    Database.deleteFasilitas(selectedFasilitas.getId()); // Menghapus fasilitas dari database.
                    tableView.getItems().remove(selectedFasilitas); // Menghapus fasilitas dari tabel.
                    showInfo("Facility deleted successfully.");
                } catch (SQLException e) {
                    e.printStackTrace();
                    showError("An error occurred while deleting the facility.");
                }
            }
        } else {
            showWarning("Please select a facility to delete.");
        }
    }

    // Utility method untuk menampilkan pesan informasi.
    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Utility method untuk menampilkan pesan peringatan.
    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Utility method untuk menampilkan pesan error.
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
