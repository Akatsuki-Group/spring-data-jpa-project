package org.example.entity;

//@Entity
//@Table
//@Data
//@SuperBuilder
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString(exclude = "userInfo")
//public class Address extends BaseEntity {
//	private String city;
//
////	@ManyToOne(fetch = FetchType.EAGER)
////	@JsonBackReference
////	@Fetch(value = FetchMode.JOIN)
////	private UserInfo userInfo;
//
//	private String userId;
//	@Transient //同样Address里面也可以不维护UserInfo的关联关系
//	private UserInfo userInfo;
//}


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "userInfo")
//这里我们直接使用@NamedEntityGraph，因为只需要配置一个@NamedEntityGraph，我们指定一个名字getAllUserInfo，指定被这个名字的实体试图关联的关联关系属性是userInfo
@NamedEntityGraph(name = "getAllUserInfo", attributeNodes = @NamedAttributeNode(value = "userInfo"))
public class Address extends BaseEntity {
    private String city;
    @JsonBackReference //防止JSON死循环
    @ManyToOne(fetch = FetchType.LAZY)//采用默认的lazy模式
    private UserInfo userInfo;
}