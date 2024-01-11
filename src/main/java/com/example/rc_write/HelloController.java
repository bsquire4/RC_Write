package com.example.rc_write;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import javax.swing.*;
import java.io.*;
import java.util.*;
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

    public File[] imageFiles = new File[3];

    public void onBlankFileSelect() {

        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        imageFiles[0] = fileChooser.showOpenDialog(stage);
        try {
            InputStream stream = new FileInputStream(imageFiles[0]);
            Image image = new Image(stream);
            ImageBox.setImage(image);
        } catch (Exception e) {
            System.out.println("BLANK IMAGE FILE NOT ACCEPTED");
            System.out.println(e);
        }
    }

    public void onRoutesFileSelect() {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        imageFiles[1] = fileChooser.showOpenDialog(stage);
        try {
            InputStream stream = new FileInputStream(imageFiles[1]);
            Image image = new Image(stream);
            ImageBox.setImage(image);
        } catch (Exception e) {
            System.out.println("ROUTES IMAGE FILE NOT ACCEPTED");
            System.out.println(e);
        }
    }

    public void onDistancesFileSelect() {
        FileChooser fileChooser = new FileChooser();
        Stage stage = new Stage();
        imageFiles[2] = fileChooser.showOpenDialog(stage);
        try {
            InputStream stream = new FileInputStream(imageFiles[2]);
            Image image = new Image(stream);
            ImageBox.setImage(image);
        } catch (Exception e) {
            System.out.println("DISTANCES IMAGE FILE NOT ACCEPTED");
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
        Maps tmpMap = new Maps();

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

        if (String.valueOf(imageFiles[0]).equals("") || String.valueOf(imageFiles[1]).equals("") || String.valueOf(imageFiles[2]).equals("") && validInput) {
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
            tmpMap.setBlanklocation(String.valueOf(imageFiles[0]));
            tmpMap.setRouteLocation(String.valueOf(imageFiles[1]));
            tmpMap.setDistanceLocation(String.valueOf(imageFiles[2]));
            tmpMap.setNumChoices(choice);
            tmpMap.setCorrectChoice(correctChoice);
            tmpMap.setColours(colours);
            tmpMap.setDistances(distances);

            tmpMap.saveToFile();

            if(importedMapsList.size() > 0)
            {
                updateBoxes();
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

    int imageCounter = 0;

    public void changeImage(ActionEvent event) {
        System.out.println("TOGGLE PRESSED");
        if (imageCounter < 2) {
            imageCounter++;
        } else {
            imageCounter = 0;
        }
        try {
            InputStream stream;
            if (imageFiles[imageCounter] == null) {
                stream = new FileInputStream("C:/Users/Ben04/Downloads/EM104989.jpg");
            } else {
                stream = new FileInputStream(imageFiles[imageCounter]);

            }
            Image image = new Image(stream);
            ImageBox.setImage(image);
            System.out.println(("IMAGE SET"));
        } catch (Exception e) {
            System.out.println("ERROR GETTING FILE");
            System.out.println(e);
        }
    }


    private List<Maps> importedMapsList = new ArrayList<>();

    public void updateBoxes() {
        Maps currentMap = importedMapsList.get(0);

        TextColour1.setText("");
        TextColour2.setText("");
        TextColour3.setText("");
        TextColour4.setText("");

        TextDistance1.setText("");
        TextDistance2.setText("");
        TextDistance3.setText("");
        TextDistance4.setText("");

        ChoiceBox1.setSelected(false);
        ChoiceBox2.setSelected(false);
        ChoiceBox3.setSelected(false);
        ChoiceBox4.setSelected(false);
        change(new ActionEvent());

        imageFiles[0] = new File(currentMap.getBlanklocation());
        imageFiles[1] = new File(currentMap.getRouteLocation());
        imageFiles[2] = new File(currentMap.getDistanceLocation());

        if (currentMap.getNumChoices() > 1) {
            TextDistance1.setText(currentMap.getDistances().get(0));
            TextDistance2.setText(currentMap.getDistances().get(1));
        }
        if (currentMap.getNumChoices() > 2) {
            TextDistance3.setText(currentMap.getDistances().get(2));
        }
        if (currentMap.getNumChoices() > 3) {
            TextDistance4.setText(currentMap.getDistances().get(3));
        }



        switch (currentMap.getCorrectChoice()) {
            case 1:
                ChoiceBox1.setSelected(true);
                break;
            case 2:
                ChoiceBox2.setSelected(true);
                break;
            case 3:
                ChoiceBox3.setSelected(true);
                break;
            case 4:
                ChoiceBox4.setSelected(true);
                break;
        }
        change(new ActionEvent());
        imageCounter = 1;
        changeImage(new ActionEvent());

        importedMapsList.remove(currentMap);
    }

    public void onFolderBtnSelect(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = new Stage();
        File folderLocation = directoryChooser.showDialog(stage);

        try (BufferedReader br = new BufferedReader(new FileReader(folderLocation.getAbsolutePath() + "/config.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitData = line.split("\\|"); // Splitting by '|'

                //if 0 ignore
                if (splitData.length > 3) {
                    Maps tmpMap = new Maps(0);
                    String baseName = splitData[2].substring(0, splitData[2].lastIndexOf('.'));
                    String extension = splitData[2].substring(splitData[2].lastIndexOf('.'));

                    tmpMap.setBlanklocation(folderLocation.getAbsolutePath() + "/" + baseName + "_A" + extension);
                    tmpMap.setRouteLocation(folderLocation.getAbsolutePath() + "/" + baseName + "_B" + extension);
                    tmpMap.setDistanceLocation(folderLocation.getAbsolutePath() + "/" + baseName + "_C" + extension);
                    tmpMap.setNumChoices((int) (splitData[3].chars().filter(ch -> ch == ';').count() + 1));
                    tmpMap.setDistances(Arrays.stream(splitData[4].split(";")).collect(Collectors.toList()));
                    tmpMap.setCorrectChoice(tmpMap.getDistances().indexOf(Collections.min(tmpMap.getDistances())) + 1);
                    System.out.println(tmpMap.toString());
                    importedMapsList.add(tmpMap);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateBoxes();
    }
}