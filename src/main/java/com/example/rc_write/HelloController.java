package com.example.rc_write;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    public ImageView ImageBox;
    @FXML
    public TextField TextColour1;
    public TextField TextColour2;
    public TextField TextColour3;
    public TextField TextColour4;
    public TextField TextDistance1;
    public TextField TextDistance2;
    public TextField TextDistance3;
    public TextField TextDistance4;
    public CheckBox ChoiceBox1;
    public CheckBox ChoiceBox2;
    public CheckBox ChoiceBox3;
    public CheckBox ChoiceBox4;

    public File imageFile;




    public static void Starting(Stage stage)
    {
        FileChooser fileChooser = new FileChooser();

        Button button = new Button("Select File");
        button.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(stage);
        });

    }
    @FXML
    protected void onHelloButtonClick() throws FileNotFoundException {
        welcomeText.setText("Welcome to JavaFX Application!");

    }

    public void onFileSelect() {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        imageFile = fileChooser.showOpenDialog(stage);
        try{
            InputStream stream = new FileInputStream(imageFile);
            Image image = new Image(stream);
            ImageBox.setImage(image);
        }
        catch (Exception e)
        {
            System.out.println("FILE NOT ACCEPTED");
            System.out.println(e);
        }
    }
    public void change(ActionEvent event)
    {
        if(ChoiceBox1.isSelected())
        {
            ChoiceBox2.setDisable(true);
            ChoiceBox3.setDisable(true);
            ChoiceBox4.setDisable(true);
        } else if (ChoiceBox2.isSelected())
        {
            ChoiceBox1.setDisable(true);
            ChoiceBox3.setDisable(true);
            ChoiceBox4.setDisable(true);
        } else if (ChoiceBox3.isSelected())
        {
            ChoiceBox1.setDisable(true);
            ChoiceBox2.setDisable(true);
            ChoiceBox4.setDisable(true);
        } else if (ChoiceBox4.isSelected())
        {
            ChoiceBox1.setDisable(true);
            ChoiceBox2.setDisable(true);
            ChoiceBox3.setDisable(true);
        }else
        {
            ChoiceBox1.setDisable(false);
            ChoiceBox2.setDisable(false);
            ChoiceBox3.setDisable(false);
            ChoiceBox4.setDisable(false);
        }
    }

    public void onFinishButtonPress(ActionEvent event) {
        String filelocation = "C:/Users/Ben04/IdeaProjects/RC/src/main/resources/MapsFile.csv";
        File file = new File(filelocation);

        int id = 0;
        String imageLocation = String.valueOf(imageFile);
        int choice = 0;
        int correctChoice = 0;
        List<String> colours = new ArrayList<>();
        List<String> distances = new ArrayList<>();
        if(!(TextColour1.getText().equals("") || TextDistance1.equals("")) )
        {
            choice++;
            colours.add(TextColour1.getText());
            distances.add(TextDistance1.getText());
            if(ChoiceBox1.isSelected())
            {
                correctChoice = 1;
            }
        }
        if(!(TextColour2.getText().equals("") || TextDistance2.equals("")) )
        {
            choice++;
            colours.add(TextColour2.getText());
            distances.add(TextDistance2.getText());
            if(ChoiceBox2.isSelected())
            {
                correctChoice = 2;
            }
        }
        if(!(TextColour3.getText().equals("") || TextDistance3.equals("") ))
        {
            choice++;
            colours.add(TextColour3.getText());
            distances.add(TextDistance3.getText());
            if(ChoiceBox3.isSelected())
            {
                correctChoice = 3;
            }
        }
        if(!(TextColour4.getText().equals("") || TextDistance4.equals("")) )
        {
            choice++;
            colours.add(TextColour4.getText());
            distances.add(TextDistance4.getText());
            if(ChoiceBox4.isSelected())
            {
                correctChoice = 4;
            }
        }

        String[] coloursString = new String[choice];


        String[] distancesString = new String[choice];

        try{
            Scanner sc = new Scanner(new File("C:/Users/Ben04/IdeaProjects/RC/src/main/resources/Counter.csv"));
            sc.useDelimiter(",");
            id = Integer.parseInt(sc.next());
            System.out.println(id);
            id++;

            while (sc.hasNext())
            {
                List<String> tempList = new ArrayList<>();
                tempList.add(sc.next());
            }

            sc.close();
        }catch (Exception e)
        {
            System.out.println("ERROR READING FILE");
            System.out.println(e);
        }

        List<String[]> dataLines = new ArrayList<>();
        String[] fileinput = new String[4 + choice + choice];
        fileinput[0] = Integer.toString(id);
        fileinput[1] = imageLocation;
        fileinput[2] = Integer.toString(choice);
        fileinput[3] = Integer.toString(correctChoice);
        int counter = 0;
        for (String i:colours) {
            fileinput[4 + counter] = i;
            counter++;
        }
        counter = 0;
        for (String x:distances)
        {
            fileinput[4 + choice+ counter] = x;
            counter++;
        }

        try{
           FileWriter fileWriter1 = new FileWriter("C:/Users/Ben04/IdeaProjects/RC/src/main/resources/Counter.csv", false);
            System.out.println("ID:" + id);
           fileWriter1.write(String.valueOf(id));
           fileWriter1.write(",");
           fileWriter1.flush();
           fileWriter1.close();

            FileWriter fileWriter = new FileWriter(filelocation,true);
            for (String y:fileinput) {
                System.out.println(y);
                fileWriter.write(y);
                fileWriter.flush();
                fileWriter.write(",");
                fileWriter.flush();
            }
        }
        catch (Exception e)
        {
            System.out.println("FAILED TO WRITE TO FILE");
            System.out.println(e);
        }
    }
    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }
    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}