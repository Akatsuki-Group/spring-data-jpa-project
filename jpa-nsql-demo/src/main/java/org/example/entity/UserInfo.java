package org.example.entity;

//@Entity
//@Data
//@SuperBuilder
//@AllArgsConstructor
//@NoArgsConstructor
//@Table
//@ToString(exclude = "addressList")
////@JsonIgnoreProperties("addressList")
//public class UserInfo extends BaseEntity {
//	private String name;
//	private Integer ages;
//	private String lastName;
//	private String emailAddress;
//	private String telephone;
////	@OneToMany(mappedBy = "userInfo",fetch = FetchType.EAGER)
//////	@BatchSize(size = 20)
////	@Fetch(value = FetchMode.SUBSELECT)
////	private List<Address> addressList;
//
//	@Transient
//	private List<Address> addressList;
//}

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table
@ToString(exclude = "addressList")
//UserInfo对应的关联关系，我们利用@NamedEntityGraphs配置了两个，一个是针对Address的关联关系，一个是name叫rooms的实体图包含了rooms属性；我们在UserInfo里面增加了两个关联关系；
@NamedEntityGraphs(value = {@NamedEntityGraph(name = "addressGraph", attributeNodes = @NamedAttributeNode(value = "addressList")),
        @NamedEntityGraph(name = "rooms", attributeNodes = @NamedAttributeNode(value = "rooms"))})
public class UserInfo extends BaseEntity {
    private String name;
    private String telephone;
    private Integer ages;
    //默认LAZY模式
    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Address> addressList;
    //默认EAGER模式
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Room> rooms;
}

