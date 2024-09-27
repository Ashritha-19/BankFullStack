package com.neoteric.fullstackdemo_31_08_2024.model;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class Account {

    private String name;

    private String accountNumber;

    private String pan;

    private String mobileNumber;

    private double balance;

    private Address address;

}
