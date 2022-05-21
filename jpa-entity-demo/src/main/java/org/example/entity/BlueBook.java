package org.example.entity;

//@Entity
//@Data
//@EqualsAndHashCode(callSuper = false)
//@DiscriminatorValue("blue")
//public class BlueBook extends Book {
//    private String blueMark;
//}

//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import javax.persistence.Entity;
//import javax.persistence.PrimaryKeyJoinColumn;
//@Entity
//@Data
//@EqualsAndHashCode(callSuper=false)
//@PrimaryKeyJoinColumn(name = "book_id", referencedColumnName = "id")
//public class BlueBook extends Book{
//    private String blueMark;
//}

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class BlueBook extends Book{
    private String blueMark;
}