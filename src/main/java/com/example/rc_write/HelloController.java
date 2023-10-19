package com.example.rc_write;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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

    public void onFileSelect() {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        imageFile = fileChooser.showOpenDialog(stage);
        try {
            InputStream stream = new FileInputStream(imageFile);
            Image image = new Image(stream);
            ImageBox.setImage(image);
        } catch (Exception e) {
            System.out.println("FILE NOT ACCEPTED");
            System.out.println(e);
        }
    }

    public void change(ActionEvent event) {
        //Making sure only 1 choice can be "Best"
        if (ChoiceBox1.isSelected()) {
            ChoiceBox2.setDisable(true);
            ChoiceBox3.setDisable(true);
            ChoiceBox4.setDisable(true);
        } else if (ChoiceBox2.isSelected()) {
            ChoiceBox1.setDisable(true);
            ChoiceBox3.setDisable(true);
            ChoiceBox4.setDisable(true);
        } else if (ChoiceBox3.isSelected()) {
            ChoiceBox1.setDisable(true);
            ChoiceBox2.setDisable(true);
            ChoiceBox4.setDisable(true);
        } else if (ChoiceBox4.isSelected()) {
            ChoiceBox1.setDisable(true);
            ChoiceBox2.setDisable(true);
            ChoiceBox3.setDisable(true);
        } else {
            ChoiceBox1.setDisable(false);
            ChoiceBox2.setDisable(false);
            ChoiceBox3.setDisable(false);
            ChoiceBox4.setDisable(false);
        }
    }

    public void onFinishButtonPress(ActionEvent event) {
        String MapFilesLocation = "../MapsFile.csv";
        String counterFileLocation = "../Counter.csv";
        int id = 0;
        String imageLocation = String.valueOf(imageFile);
        int choice = 0;
        int correctChoice = 0;
        List<String> colours = new ArrayList<>();
        List<String> distances = new ArrayList<>();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        boolean validInput = true;
        if (!(TextColour1.getText().equals("") || TextDistance1.getText().equals(""))) {
            if (isNumber(TextColour1.getText()) || !isNumber(TextDistance1.getText())) {
                validInput = false;
                alert.setTitle("Invalid input");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("You have entered an invalid input for the first colour or distance box");
                alert.showAndWait();
            } else {
                choice++;
                colours.add(TextColour1.getText());
                distances.add(TextDistance1.getText());
                if (ChoiceBox1.isSelected()) {
                    correctChoice = 1;
                }
            }
        }
        if (!(TextColour2.getText().equals("") || TextDistance2.getText().equals(""))) {
            if (isNumber(TextColour2.getText()) || !isNumber(TextDistance2.getText())) {
                validInput = false;
                alert.setTitle("Invalid input");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("You have entered an invalid input for the second colour or distance box");
                alert.showAndWait();
            } else {
                choice++;
                colours.add(TextColour2.getText());
                distances.add(TextDistance2.getText());
                if (ChoiceBox2.isSelected()) {
                    correctChoice = 2;
                }
            }
        }
        if (!(TextColour3.getText().equals("") || TextDistance3.getText().equals(""))) {
            if (isNumber(TextColour3.getText()) || !isNumber(TextDistance3.getText())) {
                validInput = false;
                alert.setTitle("Invalid input");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("You have entered an invalid input for the third colour or distance box");
                alert.showAndWait();
            } else {
                choice++;
                colours.add(TextColour3.getText());
                distances.add(TextDistance3.getText());
                if (ChoiceBox3.isSelected()) {
                    correctChoice = 3;
                }
            }
        }
        if (!(TextColour4.getText().equals("") || TextDistance4.getText().equals(""))) {
            if (isNumber(TextColour4.getText()) || !isNumber(TextDistance4.getText())) {
                validInput = false;
                alert.setTitle("Invalid input");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("You have entered an invalid input for the forth colour or distance box");
                alert.showAndWait();
            } else {
                choice++;
                colours.add(TextColour4.getText());
                distances.add(TextDistance4.getText());
                if (ChoiceBox4.isSelected()) {
                    correctChoice = 4;
                }
            }
        }

        if ((choice < 2) && validInput) {
            validInput = false;
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("There needs to be more than 1 route choice !");
            alert.showAndWait();
        }

        if (imageLocation.equals("") && validInput) {
            validInput = false;
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Image File needed");
            alert.showAndWait();
        }
        if (correctChoice == 0 && validInput) {
            validInput = false;
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Which one is the Best choice?");
            alert.showAndWait();
        }

        if (validInput) {
            String[] coloursString = new String[choice];
            String[] distancesString = new String[choice];
            try {
                Scanner counterScanner = new Scanner(new File(counterFileLocation));
                counterScanner.useDelimiter(",");
                id = Integer.parseInt(counterScanner.next());

                System.out.println(id);
                id++;
                while (counterScanner.hasNext()) {
                    List<String> tempList = new ArrayList<>();
                    tempList.add(counterScanner.next());
                }

                counterScanner.close();
            } catch (Exception e) {
                System.out.println("ERROR READING COUNTER FILE");
                System.out.println(e);
            }

            List<String[]> dataLines = new ArrayList<>();
            String[] fileinput = new String[4 + choice + choice];
            fileinput[0] = Integer.toString(id);
            fileinput[1] = imageLocation;
            fileinput[2] = Integer.toString(choice);
            fileinput[3] = Integer.toString(correctChoice);
            int counter = 0;
            for (String i : colours) {
                fileinput[4 + counter] = i;
                counter++;
            }
            counter = 0;
            for (String x : distances) {
                fileinput[4 + choice + counter] = x;
                counter++;
            }

            try {
                FileWriter fileWriter = new FileWriter(MapFilesLocation, true);
                for (String y : fileinput) {
                    System.out.println(y);
                    fileWriter.write(y);
                    fileWriter.flush();
                    fileWriter.write(",");
                    fileWriter.flush();
                }

            } catch (Exception e) {
                System.out.println("FAILED TO WRITE TO MAPFILESLOCATION FILE");
                System.out.println(e);
            }

            try {
                FileWriter fileWriter1 = new FileWriter(counterFileLocation, false);
                System.out.println("ID:" + id);
                fileWriter1.write(String.valueOf(id));
                fileWriter1.write(",");
                fileWriter1.flush();
                fileWriter1.close();
            } catch (Exception e) {
                System.out.println("FAILED TO WRITE TO COUNTER FILE");
                System.out.println(e);

            }

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

    static boolean isNumber(String s) {
        for (int i = 0; i < s.length(); i++)
            if (Character.isDigit(s.charAt(i)) == false)
                return false;

        return true;
    }
}