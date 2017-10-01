package com.insurance;

import com.insurance.dbutils.DatabaseStatements;
import com.insurance.model.Car;
import com.insurance.model.Owner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.insurance.Export.*;
import static com.insurance.FileOp.fileReader;
import static com.insurance.Menu.makeChoice;
import static com.insurance.Menu.readFileSubMenu;
import static com.insurance.Menu.sorted;

public class Functions {

    DatabaseStatements ds = new DatabaseStatements();

    ////////////////////////////////////////////////////
    //          F1: Vehicle​​ Insurance​​ Status          //
    ////////////////////////////////////////////////////

    //Input and check the pattern of the plate
    public static String inputPlate() {
        Scanner sc = new Scanner(System.in);
        //Pattern for three English-Greek mutual chars and four numbers
        Pattern p = Pattern.compile("[A-BEH-IKM-PTX-Z]{3}[-][0-9]{4}");
        String plate;
        boolean b;
        do {
            System.out.println("Please give a plate: ");
            plate = sc.next();
            Matcher m = p.matcher(plate);
            b = m.matches();
            if (!b) {
                System.out.println("Wrong input, you have to use the following pattern: \"ABC-1234\" with latin chars! ");
            }
        } while (!b);
        return plate;
    }

    //Call select plate function, check if plate exists, give the report for print
    public void searchPlate() {
        Owner result;
        boolean found = false;
        readFileSubMenu();
        int from = makeChoice(2);

        String plate = inputPlate();
        if (from == 2) {
            String path = "VehiclesData.csv";
            result = fileReader(path, plate);
            if (result == null) {
                System.out.println("I didn't find a file entry for " + plate);
            } else {
                found = true;
                result.setName("N/A");
                setInsStatus(result, false);
            }
        } else {
            result = ds.selectPlateStatement(plate);
            if (result == null) {
                System.out.println("I didn't find a database entry for " + plate);
            } else {
                found = true;
                setInsStatus(result, true);
            }
        }
        if (found) {
            int where = exportControl();
            if (where == 1) printPlateStatus(result);
            else exportPlateStatus(result);
        }
    }

    private static void setInsStatus(Owner owner, Boolean fromDb) {
        for (Car c : owner.getCars()) {
            if (compareDates(c.getDate(), fromDb)) c.setStatus(true);
            else c.setStatus(false);
        }
    }

    private static boolean compareDates(String date, Boolean fromDb) {
        Date dateD = null;
        Date today = Calendar.getInstance().getTime();
        try {
            if (fromDb) dateD = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            else dateD = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            //System.out.print(dateD.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dateD.after(today)) return true;
        else return false;
    }

    ////////////////////////////////////////////////////
    //F2: Vehicles Insurance​​ that are​ about​ to​ expire //
    ////////////////////////////////////////////////////

    public void aboutToExpire(int days) {
        HashMap<String, Owner> result;
        result = ds.selectAboutToExpire(days);
        if (!result.isEmpty()) {

            if (sorted()) {
                //Pass Cars to List
                ArrayList<Car> cars = new ArrayList<>();
                for (String afm : result.keySet()) {
                    Owner owner = result.get(afm);
                    for (Car c : owner.getCars()) {
                        c.setOwner(owner);
                        cars.add(c);
                    }
                }
                quickCarSort(cars, 0, cars.size() - 1);
                int where = exportControl();
                if (where == 1) printAboutSorted(cars);
                else exportAboutSorted(cars);
            } else {
                int where = exportControl();
                if (where == 1) printAboutUnsorted(result);
                else exportAboutUnsorted(result);
            }
        } else {
            System.err.println("No records were found that match the specified search criteria");
        }
    }

    ////////////////////////////////////////////////////
    //        F3: Sorting​ of​ the​ plates-number        //
    ////////////////////////////////////////////////////

    public static void quickCarSort(ArrayList<Car> cars, int low, int high) {
        if (cars == null || cars.size() == 0)
            return;
        if (low >= high)
            return;
        // pick the pivot
        int middle = low + (high - low) / 2;
        Car pivot = cars.get(middle);

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            while (cars.get(i).compareTo(pivot) == -1) {
                i++;
            }
            while (cars.get(j).compareTo(pivot) == 1) {
                j--;
            }
            if (i <= j) {
                Car temp = cars.get(i);
                cars.set(i, cars.get(j));
                cars.set(j, temp);
                i++;
                j--;
            }
        }
        // recursively sort two sub parts
        if (low < j)
            quickCarSort(cars, low, j);

        if (high > i)
            quickCarSort(cars, i, high);
    }

    ////////////////////////////////////////////////////
    //         F4: Fine​ calculation​ per​ owner         //
    ////////////////////////////////////////////////////

    public void findsReport(int fine) {
        HashMap<String, Owner> result;
        result = ds.selectExpired();
        for (String afm : result.keySet()) {
            result.get(afm).setFine(result.get(afm).numCars() * fine);
        }
        int where = exportControl();
        if (where == 1) {
            printFinds(result);
        } else exportFinds(result);
    }

}
