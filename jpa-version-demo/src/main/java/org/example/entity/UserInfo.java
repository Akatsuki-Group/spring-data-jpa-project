package org.example.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class UserInfo extends BaseEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private Integer ages;
	private String telephone;
}