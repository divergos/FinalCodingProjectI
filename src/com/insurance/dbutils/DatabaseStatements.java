package com.insurance.dbutils;

import com.insurance.model.Car;
import com.insurance.model.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseStatements {
    DatabaseConnector dc = new DatabaseConnector();
    private Connection mCon=dc.getmCon();
    private PreparedStatement statement = null;
    private ResultSet rs;

    //close resultset,statement functions
    public static void closeResultSet(ResultSet resultSet) {
        try
        {
            if (resultSet!= null)
            {
                resultSet.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void closeStatement(PreparedStatement statement) {
        try
        {
            if (statement!= null)
            {
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Owner selectPlateStatement(String plateForSearch) {

        Owner owner=null;

        try {
            String codeSQL = "SELECT owners.Name, owners.AFM, cars.Plate, cars.expiration_date " +
                    "FROM owners INNER JOIN cars on owners.ID = cars.ID_owners WHERE cars.Plate=?";
            dc.establishConnection();
            mCon = dc.getmCon();
            statement = mCon.prepareStatement(codeSQL);
            statement.setString(1,plateForSearch);
            // execute select SQL stetement
            rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("Name");
                String afm = rs.getString("AFM");
                String plate = rs.getString("Plate");
                String date = rs.getString("expiration_date");

                //SQL will return only one result, so we will create one object owner and return it
                owner = new Owner(name,afm);
                owner.addCar(new Car(plate,date));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            closeResultSet(rs);
            closeStatement(statement);
            dc.closeConnection();
        }
        return owner;
    }

    public HashMap<String,Owner> selectAboutToExpire(int days){

        HashMap<String, Owner> owners = new HashMap<>();

        try {
            String codeSQL = "SELECT Name, AFM, Plate, expiration_date from owners, cars " +
                    "where DATEDIFF(expiration_date,CURDATE()) between 1 and ? " +
                    "and (owners.ID = cars.ID_owners);";
            dc.establishConnection();
            mCon = dc.getmCon();
            statement = mCon.prepareStatement(codeSQL);
            statement.setInt(1,days);
            // execute select SQL stetement
            rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("Name");
                String afm = rs.getString("AFM");
                String plate = rs.getString("Plate");
                String date = rs.getString("expiration_date");

                if(owners.get(afm)==null){
                    Owner owner = new Owner(name,afm);
                    owner.addCar(new Car(plate,date));
                    owners.put(afm,owner);
                }else{
                    owners.get(afm).addCar(new Car(plate,date));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            closeResultSet(rs);
            closeStatement(statement);
            dc.closeConnection();
        }
        return owners;
    }

    public HashMap<String,Owner> selectExpired(){

        HashMap<String, Owner> owners = new HashMap<>();

        try {
            String codeSQL = "SELECT Name, AFM, Plate, expiration_date from owners, cars " +
                    "where DATEDIFF(expiration_date,CURDATE()) < 0 and (owners.ID = cars.ID_owners);";
            dc.establishConnection();
            mCon = dc.getmCon();
            statement = mCon.prepareStatement(codeSQL);
            // execute select SQL stetement
            rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("Name");
                String afm = rs.getString("AFM");
                String plate = rs.getString("Plate");
                String date = rs.getString("expiration_date");

                if(owners.get(afm)==null){
                    Owner owner = new Owner(name,afm);
                    owner.addCar(new Car(plate,date));
                    owners.put(afm,owner);
                }else{
                    owners.get(afm).addCar(new Car(plate,date));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally{
            closeResultSet(rs);
            closeStatement(statement);
            dc.closeConnection();
        }
        return owners;

    }
}
