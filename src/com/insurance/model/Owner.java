package com.insurance.model;

import java.util.ArrayList;


public class Owner{
    private String name;
    private String afm;
    private ArrayList<Car> cars;
    private int fine;

    public Owner(String name, String afm){
        this.name = name;
        this.afm = afm;
        this.cars = new ArrayList<>();
    }

    public Owner(String afm){
        this.afm = afm;
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public int numCars(){
        return cars.size();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public String getAfm() {
        return afm;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }
}
