package com.shreyas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class GUIMain extends Application {
    private static final Logger log = LogManager.getLogger(GUIMain.class);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewController.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 840, 840);
        stage.setTitle("Automated Garden");
        stage.setScene(scene);
        stage.show();

    }

}