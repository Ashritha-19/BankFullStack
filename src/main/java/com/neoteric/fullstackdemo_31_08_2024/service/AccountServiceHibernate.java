package com.neoteric.fullstackdemo_31_08_2024.service;

import com.neoteric.fullstackdemo_31_08_2024.hibernate.HibernateUtils;
import com.neoteric.fullstackdemo_31_08_2024.model.AadharEntity;
import com.neoteric.fullstackdemo_31_08_2024.model.Account;
import com.neoteric.fullstackdemo_31_08_2024.model.AccountEntity;
import com.neoteric.fullstackdemo_31_08_2024.model.AddressEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountServiceHibernate {

    public String createAccountUsingHibernate(Account account) throws InterruptedException {

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(UUID.randomUUID().toString());
        accountEntity.setName(account.getName());
        accountEntity.setPan(account.getPan());
        accountEntity.setMobileNumber(account.getMobileNumber());
        accountEntity.setBalance(account.getBalance());
        session.persist(accountEntity);

        Thread.sleep(1000);
        transaction.commit();

        return accountEntity.getAccountNumber();

    }

 /*   public String oneToManyUsingHibernate(Account account){

        SessionFactory sessionFactory =HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        AadharEntity aadharEntity = new AadharEntity();
        aadharEntity.setName(account.getName());

        List<AddressEntity> addressEntityList = new ArrayList<>();
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setState(account.getMobileNumber());
        addressEntity.setMyMappedByTestEntity(aadharEntity);
    } */

}
