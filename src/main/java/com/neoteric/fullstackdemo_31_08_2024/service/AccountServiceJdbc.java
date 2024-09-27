package com.neoteric.fullstackdemo_31_08_2024.service;

import com.neoteric.fullstackdemo_31_08_2024.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo_31_08_2024.model.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class AccountServiceJdbc {

    public String createAccount(Account account) throws AccountCreationFailedException {
        String accountNumber = null;
        try {


            Connection connection = DbConnection.getConnection();
            Statement stmt = connection.createStatement();
            accountNumber = UUID.randomUUID().toString();
            //  insert into bank.account values('123','Ashri','111','12345',123);
            String query = "insert into bank.account values(" + "'" + accountNumber + "'" + ","
                    + "'" + account.getName() + "'" + ","
                    + "'" + account.getPan() + "'" + ","
                    + "'" + account.getMobileNumber() + "'" + ","
                    + account.getBalance() + ")";

            int status = stmt.executeUpdate(query);

            if (status == 1) {
                System.out.println("Account is created" + accountNumber);
            } else {
                throw new AccountCreationFailedException("Account creation is failed" + account.getPan());
            }
        }catch (SQLException e) {
            System.out.println("Exception Occured in SQL Exception" + e);
        }catch (AccountCreationFailedException e){
//        }catch (Exception e){
            System.out.println("Exception Occured"+ e);
            throw  e;
        }
        return accountNumber;
    }

}
