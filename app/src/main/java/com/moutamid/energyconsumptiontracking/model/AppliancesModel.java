package com.moutamid.energyconsumptiontracking.model;

import java.util.ArrayList;

public class AppliancesModel {
    private String id, name;
    private int number;
    private ArrayList<Integer> hours; // Daily operating time
    private double power; // (W)
    private double numberOfUnits; // Number of appliance units

    public AppliancesModel(String id, String name, int number, ArrayList<Integer> hours, double power, double numberOfUnits) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.hours = hours;
        this.power = power;
        this.numberOfUnits = numberOfUnits;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Integer> getHours() {
        return hours;
    }

    public void setHours(ArrayList<Integer> hours) {
        this.hours = hours;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(double numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public double calculateEnergyConsumption(int period, double hours) {
        if (period != -1) {
            return ((hours * period) * power * numberOfUnits) / 1000;
        }
        return (hours * power * numberOfUnits) / 1000;
    }
}
