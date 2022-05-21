package org.example.entity;

/*
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data
@Builder
//@IdClass(UserInfoID.class)
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Integer ages;
//   @Id
//   private String name;
//   @Id
//   private String telephone;

    @EmbeddedId
    private UserInfoID userInfoID;
    @Column(unique = true)
    private String uniqueNumber;

}*/

//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString(exclude = "user")
//public class UserInfo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private Integer ages;
//    private String telephone;
//    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) //维护user的外键关联关系，配置一对一
//    private User user;
//}


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
public class UserInfo implements Serializable {
    @Id
    private Long userId;
    private Integer ages;
    private String telephone;
    @MapsId
    @OneToOne(cascade = {CascadeType.PERSIST},orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),name = "my_user_id")
    private User user;
}
