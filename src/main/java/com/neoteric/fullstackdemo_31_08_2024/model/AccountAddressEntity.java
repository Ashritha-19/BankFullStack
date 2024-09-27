package com.neoteric.fullstackdemo_31_08_2024.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "account_address",schema = "bank")
public class AccountAddressEntity {

    public AccountAddressEntity(){

    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "status")
    private Integer status ;

    @ManyToOne()
    @JoinColumn(name = "accountnumber", referencedColumnName = "accountNumber")
    private AccountEntity accountEntity;


}
