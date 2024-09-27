package com.neoteric.fullstackdemo_31_08_2024.controller;

import com.neoteric.fullstackdemo_31_08_2024.exception.AccountCreationFailedException;
import com.neoteric.fullstackdemo_31_08_2024.model.Account;
import com.neoteric.fullstackdemo_31_08_2024.model.AccountAddressEntity;
import com.neoteric.fullstackdemo_31_08_2024.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class AccountController {
@Autowired
    AccountServiceFindByMethods accountServiceTest;

    @PostMapping(value = "/api/createAccount/jdbc",
            consumes = "application/json",
            produces = "application/json")
    public Account getAccountNumber(@RequestBody Account account)
        throws AccountCreationFailedException {

            AccountServiceJdbc accountServiceJdbc = new AccountServiceJdbc();
            String accountNumber = accountServiceJdbc.createAccount(account);
             account.setAccountNumber(accountNumber);
             return account;
    }

        @PostMapping(value = "/api/createAccount/hibernate",
                      consumes = "application/json",
                      produces = "application/json")

        public Account getAccountNumberUsingHibernate(@RequestBody Account account)
                throws AccountCreationFailedException, InterruptedException {

            AccountServiceHibernate accountServiceHibernate = new AccountServiceHibernate();
            String accountNumber = accountServiceHibernate.createAccountUsingHibernate(account);
            account.setAccountNumber(accountNumber);
            return account;

    }

    @PostMapping(value = "/api/createAccount/singleTable",
            consumes = "application/json",
            produces = "application/json")
       public Account getAccountNumberUsingHibernateSingleTable(@RequestBody Account account)
            throws AccountCreationFailedException, InterruptedException {
        AccountServiceHibernate accountServiceHibernate = new AccountServiceHibernate();
        String accountNumber = accountServiceHibernate.createAccountUsingHibernate(account);
        account.setAccountNumber(accountNumber);
        return account;

       }



    @PostMapping(value = "/api/createAccount",
            consumes = "application/json",
            produces = "application/json")
    public Account getAccountNumberUsingHibernateFromUI(@RequestBody Account account)
            throws AccountCreationFailedException {

        AccountService accountService = new AccountService();
        String accountNumber = accountService.oneToManyUsingHibernateFromUI(account);
        account.setAccountNumber(accountNumber);
        return account;
    }


    @GetMapping(value = "/api/searchAccount",
                       consumes = "application/json",
                       produces = "application/json")
    public Account searchAccount(@RequestHeader("accountnumber") String accountNumber){
      //  List<String> accountNumberList = httpHeaders.get("accountnumber");
        AccountService accountService = new AccountService();
        Account account = accountService.searchAccount(accountNumber);
        return account;
    }

/*    @GetMapping(value = "/api/searchAccount/nonManagedJpa",
                 consumes = "application/json",
                  produces = "application/json")

    public Account searchAccountJpa(@RequestBody("inputAccountNumber") String accountNumber){
        AccountServiceJpa accountServiceJpa = new AccountServiceJpa();
       return accountServiceJpa.searchAccountByJpa(accountNumber);

    }*/

    @GetMapping(value = "api/searchAccount/finderMethods",
                 consumes = "application/json",
                 produces = "application/json")
    public Account searchAccountManagedJpaFinderMethods(@RequestHeader("accountnumber") String accountNumber,
                                                        @RequestHeader("pan")String pan){
        return accountServiceTest.searchAccountByFindByMethods(accountNumber,pan);


    }
}

