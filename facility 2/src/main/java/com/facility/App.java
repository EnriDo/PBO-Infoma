package com.facility; 
// Mendeklarasikan package bernama `com.facility` yang menjadi namespace untuk mengorganisasi kelas dalam proyek.

// Mengimpor library JavaFX yang diperlukan untuk membuat aplikasi berbasis GUI.
import javafx.application.Application; // Untuk membuat aplikasi JavaFX.
import javafx.fxml.FXMLLoader; // Untuk memuat file FXML yang mendefinisikan layout GUI.
import javafx.scene.Parent; // Representasi root node dari scene graph.
import javafx.scene.Scene; // Scene adalah wadah utama untuk semua elemen UI.
import javafx.stage.Stage; // Stage adalah window utama aplikasi JavaFX.

import java.io.IOException; 
// Mengimpor exception `IOException` untuk menangani kesalahan yang mungkin terjadi saat memuat file FXML.

/**
 * JavaFX App
 * 
 * Kelas utama aplikasi berbasis JavaFX. 
 * Kelas ini mengatur lifecycle aplikasi seperti inisialisasi dan menampilkan GUI.
 */
public class App extends Application {

    // Variabel statis `scene` digunakan untuk menyimpan referensi scene utama aplikasi.
    private static Scene scene;

    // Override metode `start` dari superclass Application untuk mengatur tampilan awal aplikasi.
    @Override
    public void start(Stage stage) throws IOException {
        // Membuat scene baru dengan memuat layout dari file FXML bernama "primary.fxml".
        scene = new Scene(loadFXML("primary"), 640, 480);
        // Menetapkan scene ke stage utama aplikasi.
        stage.setScene(scene);
        // Menampilkan stage sehingga aplikasi menjadi terlihat.
        stage.show();
    }

    // Metode statis untuk mengganti root dari scene dengan layout baru yang dimuat dari file FXML.
    static void setRoot(String fxml) throws IOException {
        // Mengganti root node dari scene dengan yang baru.
        scene.setRoot(loadFXML(fxml));
    }

    // Metode statis untuk memuat file FXML berdasarkan nama file yang diberikan.
    private static Parent loadFXML(String fxml) throws IOException {
        // Membuat instance FXMLLoader untuk memuat file FXML dari resource dengan nama yang diberikan.
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        // Memuat file FXML dan mengembalikan root node sebagai objek Parent.
        return fxmlLoader.load();
    }

    // Metode utama `main` untuk menjalankan aplikasi.
    public static void main(String[] args) {
        // Meluncurkan aplikasi JavaFX. Metode ini akan memanggil `start` secara otomatis.
        launch();
    }
}
