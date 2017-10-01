package com.insurance;

import com.insurance.model.Car;
import com.insurance.model.Owner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileOp {

    public static Owner fileReader(String path,String plateIn) {
        Scanner scanner = null;
        Owner owner=null;
        try {
            scanner = new Scanner(new File(path));
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            while (scanner.hasNextLine()) {
                String line = scanner.next();
                if (line.equals(";;")){
                    break;
                }
                String[] lineV=line.split(";");
                String plate = lineV[0];
                String afm = lineV[1];
                String date = lineV[2];
                if(plateIn.equals(plate)){
                    owner = new Owner(afm);
                    owner.addCar(new Car(plate, date));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            scanner.close();
        }
        return owner;
    }
}
