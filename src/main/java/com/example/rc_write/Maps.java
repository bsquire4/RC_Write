package com.example.rc_write;

import java.util.Arrays;

public class Maps {
    private String Blanklocation;
    private String RouteLocation;
    private String DistanceLocation;

    private int numChoices;
    private String[] colours;
    private int correctChoice;
    protected int id;
    private int [] distances;

    public Maps(int id, String Blanklocation,String RouteLocation, String DistanceLocation, int numChoices, int correctChoice, String[] colours, int[] distances) {
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
        this.colours = new String[0];
        this.correctChoice = 0;
        this.id = 0;
        this.distances = new int[0];
    }


    @Override
    public String toString() {
        return "Maps{" +
                "Blanklocation='" + Blanklocation + '\'' +
                ", RouteLocation='" + RouteLocation + '\'' +
                ", DistanceLocation='" + DistanceLocation + '\'' +
                ", numChoices=" + numChoices +
                ", colours=" + Arrays.toString(colours) +
                ", correctChoice=" + correctChoice +
                ", id=" + id +
                ", distances=" + Arrays.toString(distances) +
                '}';
    }

    public void setId(int id) {
        this.id = id;
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

    public String[] getColours() {
        return colours;
    }

    public void setColours(String[] colours) {
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

    public int[] getDistances() {
        return distances;
    }

    public void setDistances(int[] distances) {
        this.distances = distances;
    }

    public void getDistancesString()
    {
        for (int i:distances) {
            System.out.println(i);
        }
    }

}
