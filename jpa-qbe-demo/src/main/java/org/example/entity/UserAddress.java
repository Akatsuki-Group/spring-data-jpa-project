package org.example.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
public class UserAddress {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String address;
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
}