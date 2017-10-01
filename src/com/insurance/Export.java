package com.insurance;

import com.insurance.model.Car;
import com.insurance.model.Owner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import static com.insurance.Menu.exportSubMenu;
import static com.insurance.Menu.makeChoice;

public class Export {

    public static int exportControl() {
        //export submenu and return info about the appropriate function
        exportSubMenu();
        return makeChoice(2);
    }

    public static void printPlateStatus(Owner owner){
        String plate=null;
        Boolean status=false;
        for(Car c:owner.getCars()){
            plate=c.getPlate();
            status = c.getStatus();
        }
        Columns col = new Columns();
        col.addLine("Name", "AFM", "Plate", "Status");
        if(status) col.addLine(owner.getName(), owner.getAfm(), plate, "Insured");
        else col.addLine(owner.getName(), owner.getAfm(), plate, "Not Insured");
        col.print();
    }

    public static void printAboutSorted(ArrayList<Car> cars){
        Columns col = new Columns();
        col.addLine("Plate","Expiration Date", "   Name", "AFM");
        for(int i=0;i<cars.size();i++){
            col.addLine(cars.get(i).getPlate(), cars.get(i).getDate(), cars.get(i).getOwner().getName(), cars.get(i).getOwner().getAfm());
        }
        col.print();
    }

    public static void printAboutUnsorted(HashMap<String, Owner> re) {
        int counter = 0;

        Columns col = new Columns();
        col.addLine("Name", "AFM", "Plate", "Expiration Date");

        for (String afm : re.keySet()) {
            String name = re.get(afm).getName();
            ArrayList<Car> cars = re.get(afm).getCars();

            for (Car elem : cars) {
                if (counter > 0) {
                    String plate = elem.getPlate();
                    String date = elem.getDate();
                    col.addLine("//", "//", plate, date);
                } else {
                    String plate = elem.getPlate();
                    String date = elem.getDate();
                    col.addLine(name, afm, plate, date);
                }
                counter++;
            }
            counter = 0;
        }
        col.print();
    }

    public static void printFinds(HashMap<String,Owner> re){
        Columns col = new Columns();
        col.addLine("Name", "AFM", "Fine");
        for(String afm : re.keySet()){
            col.addLine(re.get(afm).getName(), re.get(afm).getAfm(), Integer.toString(re.get(afm).getFine()));
        }
        col.print();
    }

    public static void exportAboutSorted(ArrayList<Car> cars){
        String COMMA_DELIMITER = ";";
        String NEW_LINE_SEPARATOR = "\n";
        Path path = Paths.get("export.csv");
        File file = new File(String.valueOf(path));
        if (file.exists()) {
            System.out.println("CSV file already exists");
        } else {
            try {
                file.createNewFile();
                System.out.println("CSV file created!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.append("Plate");
            writer.append(COMMA_DELIMITER);
            writer.append("Expiration Date");
            writer.append(COMMA_DELIMITER);
            writer.append("Name");
            writer.append(COMMA_DELIMITER);
            writer.append("AFM");
            writer.append(NEW_LINE_SEPARATOR);
            for (int i=0;i<cars.size();i++){
                writer.append(cars.get(i).getPlate());
                writer.append(COMMA_DELIMITER);
                writer.append(cars.get(i).getDate());
                writer.append(COMMA_DELIMITER);
                writer.append(cars.get(i).getOwner().getName());
                writer.append(COMMA_DELIMITER);
                writer.append(cars.get(i).getOwner().getAfm());
                writer.append(NEW_LINE_SEPARATOR);
            }
            writer.append(COMMA_DELIMITER);
            writer.append(COMMA_DELIMITER);
            writer.close();
            System.out.println("CSV file written successfully!");
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }
    }

    public static void exportPlateStatus(Owner owner) {
        String plate=null;
        Boolean status=false;
        for(Car c:owner.getCars()){
            plate=c.getPlate();
            status = c.getStatus();
        }
        String COMMA_DELIMITER = ";";
        String NEW_LINE_SEPARATOR = "\n";
        Path path = Paths.get("export.csv");
        File file = new File(String.valueOf(path));
        if (file.exists()) {
            System.out.println("CSV file already exists");
        } else {
            try {
                file.createNewFile();
                System.out.println("CSV file created!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.append("Name");
            writer.append(COMMA_DELIMITER);
            writer.append("Afm");
            writer.append(COMMA_DELIMITER);
            writer.append("Plate");
            writer.append(COMMA_DELIMITER);
            writer.append("Status");
            writer.append(NEW_LINE_SEPARATOR);

            writer.append(owner.getName());
            writer.append(COMMA_DELIMITER);
            writer.append(owner.getAfm());
            writer.append(COMMA_DELIMITER);
            writer.append(plate);
            writer.append(COMMA_DELIMITER);
            if(status) writer.append("Insured");
            else writer.append("Not Insured");
            writer.append(NEW_LINE_SEPARATOR);

            writer.append(COMMA_DELIMITER);
            writer.append(COMMA_DELIMITER);
            writer.close();
            System.out.println("CSV file written successfully!");
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }
    }

    public static void exportAboutUnsorted(HashMap<String,Owner> re) {
        String COMMA_DELIMITER = ";";
        String NEW_LINE_SEPARATOR = "\n";
        Path path = Paths.get("export.csv");
        File file = new File(String.valueOf(path));
        if (file.exists()) {
            System.out.println("CSV file already exists");
        } else {
            try {
                file.createNewFile();
                System.out.println("CSV file created!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.append("Name");
            writer.append(COMMA_DELIMITER);
            writer.append("Afm");
            writer.append(COMMA_DELIMITER);
            writer.append("Plate");
            writer.append(COMMA_DELIMITER);
            writer.append("Expiration date");
            writer.append(NEW_LINE_SEPARATOR);
            for (String afm : re.keySet()) {
                String name = re.get(afm).getName();
                ArrayList<Car> cars = re.get(afm).getCars();

                for (Car elem : cars) {
                    String plate = elem.getPlate();
                    String date = elem.getDate();
                    writer.append(name);
                    writer.append(COMMA_DELIMITER);
                    writer.append(afm);
                    writer.append(COMMA_DELIMITER);
                    writer.append(plate);
                    writer.append(COMMA_DELIMITER);
                    writer.append(date);
                    writer.append(NEW_LINE_SEPARATOR);
                }
            }
            writer.append(COMMA_DELIMITER);
            writer.append(COMMA_DELIMITER);
            writer.close();
            System.out.println("CSV file written successfully!");
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }
    }

    public static void exportFinds(HashMap<String,Owner> re){
        String COMMA_DELIMITER = ";";
        String NEW_LINE_SEPARATOR = "\n";
        Path path = Paths.get("export.csv");
        File file = new File(String.valueOf(path));
        if (file.exists()) {
            System.out.println("CSV file already exists");
        } else {
            try {
                file.createNewFile();
                System.out.println("CSV file created!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.append("Name");
            writer.append(COMMA_DELIMITER);
            writer.append("Afm");
            writer.append(COMMA_DELIMITER);
            writer.append("Fine");
            writer.append(NEW_LINE_SEPARATOR);

            for(String afm : re.keySet()){
                writer.append(re.get(afm).getName());
                writer.append(COMMA_DELIMITER);
                writer.append(re.get(afm).getAfm());
                writer.append(COMMA_DELIMITER);
                writer.append(Integer.toString(re.get(afm).getFine()));
                writer.append(NEW_LINE_SEPARATOR);
            }

            writer.append(COMMA_DELIMITER);
            writer.append(COMMA_DELIMITER);
            writer.close();
            System.out.println("CSV file written successfully!");
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }
    }
}
