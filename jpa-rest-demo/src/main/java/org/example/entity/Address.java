package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = "userInfo")
//@JsonIgnoreProperties("userInfo")
//@NamedEntityGraph(name = "getAllUserInfo",attributeNodes = @NamedAttributeNode(value = "userInfo"))
public class Address extends BaseEntity {
	@JsonProperty("myCity") //改变JSON响应的属性名字
	private String city;
	@JsonIgnore //JSON解析的时候忽略某个属性
	private String address;
////	@BatchSize(size = 30)
//	@Fetch(value = FetchMode.JOIN)
//	@Transient
//	@JsonBackReference
//	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
//	private UserInfo userInfo;
//	private Long userId;
}