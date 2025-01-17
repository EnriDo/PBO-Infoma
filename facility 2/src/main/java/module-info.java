module com.facility {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.facility to javafx.fxml, javafx.base, javafx.controls;
    exports com.facility;
}