package com.insurance;

import java.util.Scanner;

public class Menu {

    public static void menu(){
        System.out.println("Select functionality to perform:\n");
        System.out.println("\t1) Vehicle insurance status");
        System.out.println("\t2) Forthcoming Expiries");
        System.out.println("\t3) Calculate fines per owner");
    }

    public static void exportSubMenu(){
        System.out.println("Select export type:\n");
        System.out.println("\t1) Console");
        System.out.println("\t2) File");
    }

    public static void readFileSubMenu(){
        System.out.println("Select input type:\n");
        System.out.println("\t1) Database");
        System.out.println("\t2) File");
    }

    public static boolean sorted(){
        Scanner sc = new Scanner(System.in);
        String ans;
        boolean flag = false;
        System.out.print("Do you want your results to be sorted? y/N ");
        do{
            ans = sc.nextLine();
            System.out.println(ans);
            if (ans.equals("y")||ans.equals("") || ans.equals("Y")||ans.equals("N")||ans.equals("n")){
                flag = true;
            }else System.out.println("Please answer...");
        }while(!flag);
        if (ans.equals("Y")||ans.equals("y")) return true;
        else return false;

    }

    public static int readInt(String prompt){
        Scanner sc = new Scanner(System.in);
        int inte;
        while (true) {
            System.out.print("\n"+prompt);
            try {
                inte = sc.nextInt();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please type an integer!!!");
                sc.next();
            }
        }
        return inte;
    }

    public static int makeChoice(int num){
        Scanner sc = new Scanner(System.in);
        int ch;
        boolean flag = false;

        do {
            while (true) {
                System.out.print("\nChoice: ");
                try {
                    ch = sc.nextInt();
                    break;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Please type an integer!!!");
                    sc.next();
                }
            }
            if((ch<=num&&ch>0)){
                flag = true;
            }else System.out.println("Please give a valid choice!!!");
        }while(!flag);
        return ch;
    }
}
