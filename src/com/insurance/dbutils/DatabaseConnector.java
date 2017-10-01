package com.insurance.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static String url1 = "jdbc:mysql://localhost:3306/cs_insurance";
    private static String user = "proj_insurance";
    private static String password = "admin123";
    private Connection mCon = null;

    public Connection getmCon(){
        return mCon;
    }

    public void establishConnection(){

        try {
            mCon = DriverManager.getConnection(url1, user, password);
            if (mCon != null) {
                System.out.println("Connected to the database cs_insurance");
            }
        }catch(SQLException ex){
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
    }

    public void closeConnection(){
        try{
            if (mCon!= null)
            {
                mCon.close();
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

}
