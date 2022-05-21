package org.example.entity;

//@Entity(name = "book")
//@Data
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "color", discriminatorType = DiscriminatorType.STRING)
//public class Book {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String title;
//}

//import lombok.Data;
//
//import javax.persistence.*;
//
//@Entity(name = "book")
//@Data
//@Inheritance(strategy = InheritanceType.JOINED)
//public class Book {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String title;
//}

import lombok.Data;

import javax.persistence.*;

@Entity(name = "book")
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "title",columnDefinition = "ddsf")
    private String title;
}