package org.example.entity;

//红皮书
//@Entity
//@DiscriminatorValue("red")
//@Data
//@EqualsAndHashCode(callSuper=false)
//public class RedBook extends Book {
//   private String redMark;
//}


//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import javax.persistence.Entity;
//import javax.persistence.PrimaryKeyJoinColumn;
//@Entity
//@PrimaryKeyJoinColumn(name = "book_id", referencedColumnName = "id")
//@Data
//@EqualsAndHashCode(callSuper=false)
//public class RedBook extends Book {
//   private String redMark;
//}

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class RedBook extends Book {
   private String redMark;
}