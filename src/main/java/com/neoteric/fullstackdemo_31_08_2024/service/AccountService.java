package com.neoteric.fullstackdemo_31_08_2024.service;

import com.neoteric.fullstackdemo_31_08_2024.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo_31_08_2024.hibernate.HibernateUtils;
import com.neoteric.fullstackdemo_31_08_2024.model.Account;
import com.neoteric.fullstackdemo_31_08_2024.model.AccountAddressEntity;
import com.neoteric.fullstackdemo_31_08_2024.model.AccountEntity;
import com.neoteric.fullstackdemo_31_08_2024.model.Address;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountService {

      public Account searchAccount(String accountNumber) {
          SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
          Session session = sessionFactory.openSession();
         /* Query<AccountEntity> query = session.createQuery("From AccountEntity a where a.accountNumber=: inputAccountNumber");
          here inputAccountNumber is named parameter
          query.setParameter("inputAccountNumber", accountNumber);
          AccountEntity accountEntity = query.list().get(0); */

          // Criteria Query code starts from here
          CriteriaBuilder builder = session.getCriteriaBuilder();
          CriteriaQuery<AccountEntity> criteriaQuery = builder.createQuery(AccountEntity.class);
          Root<AccountEntity> accountEntityRoot = criteriaQuery.from(AccountEntity.class);

          // Set the criteria where clause
          criteriaQuery.select(accountEntityRoot).where(builder.equal(accountEntityRoot.get("accountNumber"), accountNumber));

          // Execute the query
          List<AccountEntity> accountEntities = session.createQuery(criteriaQuery).getResultList();

          // Assuming only one account entity for the given account number
          if (accountEntities.isEmpty()) {
              return null;
          }
          System.out.println("Parent Query(Account) is loaded");
          AccountEntity accountEntity = accountEntities.get(0);
          Account account = Account.builder()
                  .name(accountEntity.getName())
                  .pan(accountEntity.getPan())
                  .mobileNumber(accountEntity.getMobileNumber())
                  .balance(accountEntity.getBalance())
                  .accountNumber(accountEntity.getAccountNumber())
                 .address(Address.builder()
                          .add1(accountEntity.getAccountAddressEntityList().get(0).getAddress1())
                          .add2(accountEntity.getAccountAddressEntityList().get(0).getAddress2())
                          .state(accountEntity.getAccountAddressEntityList().get(0).getState())
                          .city(accountEntity.getAccountAddressEntityList().get(0).getCity())
                          .pincode(accountEntity.getAccountAddressEntityList().get(0).getPincode())
                          .build()
                 ).build();

          System.out.println("Child Query(Account_Address) is loaded");

          return account;
      }



    public String oneToManyUsingHibernateFromUI(Account account){

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction =session.beginTransaction();

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccountNumber(UUID.randomUUID().toString());
        accountEntity.setName(account.getName());
        accountEntity.setPan(account.getPan());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setMobileNumber(account.getMobileNumber());

        List<AccountAddressEntity> accountAddressEntityList = new ArrayList<>();
        AccountAddressEntity accountAddressEntity = new AccountAddressEntity();
        accountAddressEntity.setAddress1(account.getAddress().getAdd1());
        accountAddressEntity.setAddress2(account.getAddress().getAdd2());
        accountAddressEntity.setState(account.getAddress().getState());
        accountAddressEntity.setCity(account.getAddress().getCity());
        accountAddressEntity.setPincode(account.getAddress().getPincode());
        accountAddressEntity.setStatus(1);
        accountAddressEntity.setAccountEntity(accountEntity);
        accountAddressEntityList.add(accountAddressEntity);

        accountEntity.setAccountAddressEntityList(accountAddressEntityList);
        session.persist(accountEntity);

       // Thread.sleep(300000,);
        transaction.commit();

        return accountEntity.getAccountNumber();
    }

}
