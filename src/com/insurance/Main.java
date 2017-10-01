package com.insurance;

import static com.insurance.Menu.*;

public class Main {

    public static void main(String[] args) {
        Functions f = new Functions();
        menu();
        int ch = makeChoice(3);
        switch(ch){
            case 1:
                f.searchPlate();
                break;
            case 2:
                int days = readInt("Please give the number of days: ");
                f.aboutToExpire(days);
                break;
            case 3:
                int find = readInt("Please give fine's cost: ");
                f.findsReport(find);
                break;
            default:
                System.out.println("Exiting...");
                break;
        }
    }
}
