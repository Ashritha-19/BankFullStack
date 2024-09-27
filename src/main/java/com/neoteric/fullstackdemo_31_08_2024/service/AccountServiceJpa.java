package com.neoteric.fullstackdemo_31_08_2024.service;

import com.neoteric.fullstackdemo_31_08_2024.model.Account;
import com.neoteric.fullstackdemo_31_08_2024.model.AccountAddressEntity;
import com.neoteric.fullstackdemo_31_08_2024.model.AccountEntity;
import com.neoteric.fullstackdemo_31_08_2024.model.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Objects;

public class AccountServiceJpa {

    public Account searchAccountByJpa(String accountNumber){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaDemo");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        jakarta.persistence.Query query = entityManager.createQuery("select a from AccountEntity a where a.accountNumber=:inputAccountNumber");
        query.setParameter("inputAccountNumber", accountNumber);
        List<AccountEntity> accountEntityList = query.getResultList();
        AccountEntity accountEntity = accountEntityList.get(0);
        Account account = Account.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .name(accountEntity.getName())
                .mobileNumber(accountEntity.getMobileNumber())
                .balance(accountEntity.getBalance())
                .pan(accountEntity.getPan()).build();
        List<AccountAddressEntity> accountAddressEntityList = accountEntity.getAccountAddressEntityList();
        if(Objects.nonNull(accountAddressEntityList) && accountAddressEntityList.size() >0){
            AccountAddressEntity accountAddressEntity = accountAddressEntityList.get(0);
            System.out.println("AccountAddressEntity is loaded");
            Address address = new Address();
            address.setAdd1(accountAddressEntity.getAddress1());
            address.setAdd2(accountAddressEntity.getAddress2());
            address.setCity(accountAddressEntity.getCity());
            address.setState(accountAddressEntity.getState());
            address.setPincode(accountAddressEntity.getPincode());
            account.setAddress(address);
        }
        entityManager.getTransaction().commit();
        return account;

    }
}
