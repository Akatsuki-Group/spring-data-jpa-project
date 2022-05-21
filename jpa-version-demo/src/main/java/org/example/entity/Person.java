package org.example.entity;

import org.example.convert.GenderConverter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Person implements Serializable {
    private static final long serialVersionUID = 8849870114127659929L;
 
    @Id
    @GeneratedValue
    private Long id;
 
    @Column(nullable = false)
    private String name;
 
    @Column
//    @Enumerated(EnumType.STRING)
    @Convert(converter = GenderConverter.class)
    private Gender gender;
 
    public Person(String name, Gender gender){
        this.name = name;
        this.gender = gender;
    }
 
    // getter、setter
}