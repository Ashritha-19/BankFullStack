package com.neoteric.fullstackdemo_31_08_2024.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class AddressEntity {

    public AddressEntity(){

    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @Column(name = "state")
    public String state;


    @ManyToOne
    @JoinColumn(name = "aadharnumber", referencedColumnName = "aadharnumber")
    //name is  child column and reference is parent column
    // It is used to give foreign key like in sql
    // only child knows about relationship


    public AadharEntity aadharEntity;


}
