package com.example.rc_write;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

//        stage.setTitle("JavaFX App");
//
//
//        BorderPane border = new BorderPane();
//
//
//        Label label = new Label("Add a Map");
//        label.prefWidth(1000);
//
//        border.setTop(label);
////        border.setLeft(addVBox());
//
//        border.setCenter(addGridPane(stage));
//
////        border.setRight(addFlowPane());
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        double width = screenSize.getWidth() *0.8;
//        double height = screenSize.getHeight() *0.8;
//        stage.setWidth(width);
//        stage.setHeight(height);
//        stage.setX((screenSize.getWidth() - width) / 2);
//        stage.setY((screenSize.getHeight() - height) / 2);

        Scene scene = new Scene(fxmlLoader.load(), 500 , 500);

        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

    public GridPane addGridPane(Stage stage) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));


        FileChooser fileChooser = new FileChooser();

        Button button = new Button("Select File");
        button.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(stage);
        });
        button.setPrefSize(100,100);
        grid.add(button,1,1);



        return grid;
    }

}