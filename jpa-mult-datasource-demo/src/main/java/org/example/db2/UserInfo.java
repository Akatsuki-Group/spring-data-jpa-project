package org.example.db2;

import lombok.*;
import org.example.common.BaseEntity;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Table(name = "userInfo")
public class UserInfo extends BaseEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private Integer ages;
	private String lastName;
	@Column(name = "myAddress")
	private String emailAddress;
	private String telephone;
}

