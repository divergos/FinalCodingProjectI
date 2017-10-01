package com.insurance.model;

public class Car implements Comparable<Car>{
    private String plate;
    private String date;
    private Boolean status;
    private Owner owner;

    public Car(String plate,String date){
        this.plate = plate;
        this.date = date;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public int compareTo(Car o) {
        String st1 = this.getPlate();
        String st2 = o.getPlate();
        char a;
        char b;
        int code1;
        int code2;
        for(int i = 0;i < st1.length();i++){
            a = st1.charAt(i);
            b = st2.charAt(i);
            code1 = (int) a;
            code2 = (int) b;
            if(code1<code2) return -1;
            else if (code1>code2) return 1;
        }
        return 0;
    }
}
