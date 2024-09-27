package com.neoteric.fullstackdemo_31_08_2024.service;

import com.neoteric.fullstackdemo_31_08_2024.exception.InvalidCredentialsException;
import com.neoteric.fullstackdemo_31_08_2024.exception.AtmCreationFailedException;
import com.neoteric.fullstackdemo_31_08_2024.model.Atm;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class AtmService {

        public String createAtm(Atm atm) throws Exception{
            String cardnumber=null;
            try{
                Connection connection=DbConnection.getConnection();
                Statement stmt=connection.createStatement();

                cardnumber=UUID.randomUUID().toString();

                // Check if cardExpiry is null before formatting
                String formattedExpireDate;
                if (atm.getCardExpiry() != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // MySQL datetime format
                    formattedExpireDate = sdf.format(atm.getCardExpiry());
                } else {
                    // Handle the case where cardExpiry is null (could set a default value or throw an exception)
                    throw new IllegalArgumentException("Card expiry date must not be null.");
                }
              /*  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // MySQL datetime format
                String formattedExpireDate = sdf.format(atm.getCardExpiry()); */

                String query="insert into bank.atm values("
                        + "'" + cardnumber + "'"+","
                        + "'" + atm.getCvv() + "'"+","
                        + "'" + atm.getPin() + "'"+","
                        + "'" +formattedExpireDate + "'"+","
                        + "'" + atm.getAccountNumber() + "'"
                        + ")";
                int status = stmt.executeUpdate(query);

                if (status==1) {
                    System.out.println("Account is created" + cardnumber);

                } else {
                    //it will throw new exception
                    throw new AtmCreationFailedException("Account Creation is failed " + atm.getPin());

                }}
            catch(Exception e) {
                System.out.println("Exception Occured "+e);
                throw e;//rethrowing the existing exception using throws.
                // throw-> it will throw the exception and it will create new exception and it will rethrow the exception even checked or unchecked or custom exceptions
                //throws-> to handle the re-throw the Exception.
            }
            return cardnumber;

        }


        public boolean validateAtmLogin(String cardNumber, String pin) throws Exception {
            String query = "SELECT * FROM bank.atm WHERE cardNumber = ? AND pin = ?";

            try (Connection connection = DbConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, cardNumber);
                preparedStatement.setString(2, pin);

                try (ResultSet rs = preparedStatement.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("loggedin successfully ");
                        return true;
                    } else {
                        throw new InvalidCredentialsException("Invalid card number or PIN.");
                    }
                }
            } catch (Exception ex) {
                // Consider using a logging framework instead of System.out.println
                System.out.println("Exception occurred: " + ex);
                throw ex;
            }
        }




    }
