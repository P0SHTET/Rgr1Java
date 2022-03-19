package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }

    public static class App extends Application {

        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1920, 1024);
            stage.setTitle("Javashit");
            stage.setScene(scene);
            stage.show();
        }

    }

}
