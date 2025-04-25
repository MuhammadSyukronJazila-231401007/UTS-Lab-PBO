module com.example.utslabpbo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.utslabpbo to javafx.fxml;
//    exports com.example.utslabpbo;
    exports com.example.utslabpbo.Soal1;
    opens com.example.utslabpbo.Soal1 to javafx.fxml;
}