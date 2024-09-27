package com.neoteric.fullstackdemo_31_08_2024.model;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Builder
@Data
public class Atm {

    private String cardNumber;

    private String pin;

    private String cvv;

    private String accountNumber;

    private Date cardExpiry;

}
