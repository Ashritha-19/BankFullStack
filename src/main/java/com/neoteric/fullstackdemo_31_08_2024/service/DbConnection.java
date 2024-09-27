package com.neoteric.fullstackdemo_31_08_2024.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    public static Connection connection;

    public static Connection getConnection(){

        try{

            if (connection== null) {

                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/bank", "root", "root");
            }else {
                System.out.println("Returing Existing Connection");
            }
        }catch (Exception e){
            System.out.println("Exception Occured in getConnection"+ e);
        }
           return connection;
    }
}
