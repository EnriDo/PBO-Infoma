package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainFrame {

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button newButton;

    @FXML
    private TextField kategoriField;

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
    private TableView<?> tableView;

    @FXML
    public void initialize() {
        // Set action for Add button
        addButton.setOnAction(event -> {
            System.out.println("Add button clicked!");
            // Tambahkan logika untuk menyimpan data di sini
        });

        // Set action for Delete button
        deleteButton.setOnAction(event -> {
            System.out.println("Delete button clicked!");
            // Tambahkan logika untuk menghapus data di sini
        });

        // Set action for Update button
        updateButton.setOnAction(event -> {
            System.out.println("Update button clicked!");
            // Tambahkan logika untuk memperbarui data di sini
        });

        // Set action for New button
        newButton.setOnAction(event -> {
            System.out.println("New button clicked!");
            clearFields();
        });
    }

    private void clearFields() {
        kategoriField.clear();
        namaTempatField.clear();
        deskripsiField.clear();
        alamatField.clear();
        hargaField.clear();
        fasilitasField.clear();
        jamOperasionalField.clear();
        kontakField.clear();
    }
}
