package com.example.utslabpbo.Soal1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/utslabpbo/karyawan.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 782, 500);
        stage.setTitle("Manajemen Karyawan");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}