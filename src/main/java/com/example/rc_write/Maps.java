package com.example.rc_write;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Maps {
    private String Blanklocation;
    private String RouteLocation;
    private String DistanceLocation;

    private int numChoices;
    private List<String> colours;
    private int correctChoice;
    protected int id;
    private List<String> distances;

    public Maps(int id, String Blanklocation,String RouteLocation, String DistanceLocation, int numChoices, int correctChoice, List colours, List distances) {
        this.Blanklocation = Blanklocation;
        this.RouteLocation = RouteLocation;
        this.DistanceLocation = DistanceLocation;
        this.numChoices = numChoices;
        this.colours = colours;
        this.correctChoice = correctChoice;
        this.id = id;
        this.distances = distances;
    }
    public Maps() {
        this.Blanklocation = "";
        this.RouteLocation = "";
        this.DistanceLocation = "";
        this.numChoices = 0;
        this.colours = new ArrayList<>();
        this.correctChoice = 0;
        this.id = createID();
        this.distances = new ArrayList<>();
    }

    public Maps(int i) {
        this.Blanklocation = "";
        this.RouteLocation = "";
        this.DistanceLocation = "";
        this.numChoices = 0;
        this.colours = new ArrayList<>();
        this.correctChoice = 0;
        this.id = 0;
        this.distances = new ArrayList<>();

    }

    @Override
    public String toString() {
        return "Maps{" +
                "Blanklocation='" + Blanklocation + '\'' +
                ", RouteLocation='" + RouteLocation + '\'' +
                ", DistanceLocation='" + DistanceLocation + '\'' +
                ", numChoices=" + numChoices +
                ", colours=" + colours +
                ", correctChoice=" + correctChoice +
                ", id=" + id +
                ", distances=" + distances +
                '}';
    }

    public String getBlanklocation() {
        return Blanklocation;
    }

    public void setBlanklocation(String blanklocation) {
        Blanklocation = blanklocation;
    }

    public String getRouteLocation() {
        return RouteLocation;
    }

    public void setRouteLocation(String routeLocation) {
        RouteLocation = routeLocation;
    }

    public String getDistanceLocation() {
        return DistanceLocation;
    }

    public void setDistanceLocation(String distanceLocation) {
        DistanceLocation = distanceLocation;
    }

    public int getNumChoices() {
        return numChoices;
    }

    public void setNumChoices(int numChoices) {
        this.numChoices = numChoices;
    }

    public List<String> getColours() {
        return colours;
    }

    public void setColours(List<String> colours) {
        this.colours = colours;
    }

    public int getCorrectChoice() {
        return correctChoice;
    }

    public void setCorrectChoice(int correctChoice) {
        this.correctChoice = correctChoice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getDistances() {
        return distances;
    }

    public void setDistances(List<String> distances) {
        this.distances = distances;
    }
    int otherCounterVariable = 0;
    String counterFileLocation = "../Counter.csv";


    public int createID()
    {
        int id = 0;

        try {
            Scanner counterScanner = new Scanner(new File(counterFileLocation));
            counterScanner.useDelimiter(",");
            id = Integer.parseInt(counterScanner.next());
            otherCounterVariable = Integer.parseInt(counterScanner.next());
            System.out.println(id);
            id++;
            counterScanner.close();
        } catch (Exception e) {
            System.out.println("ERROR READING COUNTER FILE");
            System.out.println(e);
        }


        return id;
    }

    public void saveToFile()
    {

        try {
            FileWriter fileWriter1 = new FileWriter(counterFileLocation, false);
            System.out.println("ID:" + id);
            fileWriter1.write(String.valueOf(id));
            fileWriter1.write(",");
            fileWriter1.write(String.valueOf(otherCounterVariable));
            fileWriter1.write(",");
            fileWriter1.flush();
            fileWriter1.close();
        } catch (Exception e) {
            System.out.println("FAILED TO WRITE TO COUNTER FILE");
            System.out.println(e);

        }


        try{
            FileWriter fileWriter = new FileWriter("../UsersMapsNotDone.csv",true);
            fileWriter.write(String.valueOf(id));
            fileWriter.write(",");
            fileWriter.flush();
            fileWriter.close();
        }catch (Exception e)
        {
            System.out.println("FAILED TO WRITE TO USERSMAPSNOTDONEFILE");
            System.out.println(e);
        }

        String MapFilesLocation = "../MapsFile.csv";

        String[] fileinput = new String[6 + this.numChoices + this.numChoices];
        fileinput[0] = Integer.toString(this.getId());
        fileinput[1] = String.valueOf(this.getBlanklocation());
        fileinput[2] = String.valueOf(this.getRouteLocation());
        fileinput[3] = String.valueOf(this.getDistanceLocation());
        fileinput[4] = Integer.toString(this.getNumChoices());
        fileinput[5] = Integer.toString(this.getCorrectChoice());
        int counter = 0;
        for (String i : this.getColours()) {
            fileinput[6 + counter] = i;
            counter++;
        }
        counter = 0;
        for (String x : this.getDistances()) {
            fileinput[6 + this.numChoices + counter] = x;
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

    }
}
