package com.neoteric.fullstackdemo_31_08_2024.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "account",schema = "bank")
@Data
public class AccountEntity {
    public AccountEntity(){

    }

    @Id
    @Column(name="name",nullable = false)
    private String name;

    @Column(name="accountnumber",nullable = false)
    private String accountNumber;

    @Column(name="pan",nullable = false)
    private String pan;

    @Column(name="mobile",nullable = false)
    private String mobileNumber;

    @Column(name="balance",nullable = false)
    private double balance;

    @OneToMany(mappedBy = "accountEntity",
              cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)



    /* cascade helps to identify the relation and it helps to store the data to DB from child to parent
     fetch helps to retrive the data
    We can fetch in 2 ways 1. Eager 2. Lazy
    1. Eager:- fetch the childs also without requesting.
    2. Lazy:- It doesn't fetch the child without requesting it will fetch if we request.
     */


    public List<AccountAddressEntity> accountAddressEntityList;


}
