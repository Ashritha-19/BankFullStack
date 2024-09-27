package com.neoteric.fullstackdemo_31_08_2024.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String add1;

    private String add2;

    private String city;

    private String state;

    private String pincode;

}
