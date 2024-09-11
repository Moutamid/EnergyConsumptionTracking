package com.moutamid.energyconsumptiontracking.model;

public class AppliancesModel {
    private String id, name;
    private double hours; // Daily operating time
    private double power; // (W)
    private double numberOfUnits; // Number of appliance units

    public AppliancesModel(String id, String name, double hours, double power, double numberOfUnits) {
        this.id = id;
        this.name = name;
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

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
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

    public double calculateEnergyConsumption(int period) {
        if (period != -1) {
            return ((hours * period) * power * numberOfUnits) / 1000; // kWh
        }
        return (hours * power * numberOfUnits) / 1000; // kWh
    }
}
