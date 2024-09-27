package com.neoteric.fullstackdemo_31_08_2024.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Aadhar",schema = "bank")
@Data
public class AadharEntity {

    public AadharEntity() {

    }
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncrement
@Column
    public Integer aadharNumber;
@Column
    public String name;


}
