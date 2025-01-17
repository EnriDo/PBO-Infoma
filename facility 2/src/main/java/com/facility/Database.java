package com.facility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String DATABASE_URL = "jdbc:mariadb://127.0.0.1:3307/facility_db";
    private static final String DATABASE_USER = "root"; 
    private static final String DATABASE_PASSWORD = "tubescrud"; 

    private static Connection connection;

    // Initialize database connection
    static {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Connected to database successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
            System.exit(1); // Exit if connection fails
        }
    }

    // Method to get all facilities
    public static List<Fasilitas> getAllFasilitas() throws SQLException {
        List<Fasilitas> fasilitasList = new ArrayList<>();
        String sql = "SELECT * FROM Fasilitas"; // Adjust this based on your actual table structure
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String kategori = rs.getString("kategori");
            String namaTempat = rs.getString("nama_tempat");
            String deskripsi = rs.getString("deskripsi");
            String alamat = rs.getString("alamat");
            double harga = rs.getDouble("harga");
            String fasilitas = rs.getString("fasilitas");
            String jamOperasional = rs.getString("jam_operasional");
            int kontak = rs.getInt("kontak");

            Fasilitas fasilitass = new Fasilitas(id, kategori, namaTempat, deskripsi, alamat, harga, fasilitas, jamOperasional, kontak);
            fasilitasList.add(fasilitass);
        }

        return fasilitasList;
    }

    // Method to add a new facility
    public static void addFasilitas(String kategori, String namaTempat, String deskripsi, String alamat, 
                                     double harga, String fasilitas, String jamOperasional, int kontak) throws SQLException {
        String sql = "INSERT INTO Fasilitas (kategori, nama_tempat, deskripsi, alamat, harga, fasilitas, jam_operasional, kontak) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, kategori);
        stmt.setString(2, namaTempat);
        stmt.setString(3, deskripsi);
        stmt.setString(4, alamat);
        stmt.setDouble(5, harga);
        stmt.setString(6, fasilitas);
        stmt.setString(7, jamOperasional);
        stmt.setInt(8, kontak);
        stmt.executeUpdate();
    }

    // Method to update an existing facility
    public static void updateFasilitas(int id, String kategori, String namaTempat, String deskripsi, String alamat, 
                                        double harga, String fasilitas, String jamOperasional, int kontak) throws SQLException {
        String sql = "UPDATE Fasilitas SET kategori = ?, nama_tempat = ?, deskripsi = ?, alamat = ?, harga = ?, fasilitas = ?, jam_operasional = ?, kontak = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, kategori);
        stmt.setString(2, namaTempat);
        stmt.setString(3, deskripsi);
        stmt.setString(4, alamat);
        stmt.setDouble(5, harga);
        stmt.setString(6, fasilitas);
        stmt.setString(7, jamOperasional);
        stmt.setInt(8, kontak);
        stmt.setInt(9, id);
        stmt.executeUpdate();
    }

    // Method to delete a facility by ID
    public static void deleteFasilitas(int id) throws SQLException {
        String sql = "DELETE FROM Fasilitas WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    // Close database connection (optional)
    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}