package com.neoteric.fullstackdemo_31_08_2024.service;

import com.neoteric.fullstackdemo_31_08_2024.model.Account;
import com.neoteric.fullstackdemo_31_08_2024.model.AccountAddressEntity;
import com.neoteric.fullstackdemo_31_08_2024.model.AccountEntity;
import com.neoteric.fullstackdemo_31_08_2024.model.Address;
import com.neoteric.fullstackdemo_31_08_2024.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceFindByMethods {

  @Autowired
  public AccountRepository accountRepository;

  @Transactional

    public Account searchAccountByFindByMethods(String accountNumber, String pan){
       Account account = null;
        AccountEntity accountEntity = accountRepository.findByAccountNumberAndPan(accountNumber,pan);

       if (accountEntity != null) {

           account = Account.builder()
                   .accountNumber(accountEntity.getAccountNumber())
                   .name(accountEntity.getName())
                   .mobileNumber(accountEntity.getMobileNumber())
                   .balance(accountEntity.getBalance())
                   .pan(accountEntity.getPan()).build();
           List<AccountAddressEntity> accountAddressEntityList = accountEntity.getAccountAddressEntityList();
           if (Objects.nonNull(accountAddressEntityList) && accountAddressEntityList.size() > 0) {
               AccountAddressEntity accountAddressEntity = accountAddressEntityList.get(0);
               System.out.println("AccountAddressEntity is loaded");
               Address address = new Address();
               address.setAdd1(accountAddressEntity.getAddress1());
               address.setAdd2(accountAddressEntity.getAddress2());
               address.setCity(accountAddressEntity.getCity());
               address.setState(accountAddressEntity.getState());
               address.setPincode(accountAddressEntity.getPincode());
               account.setAddress(address);
           } else {
               System.out.println("No address found for this account");
           }
       }
        return account;
    }
}
