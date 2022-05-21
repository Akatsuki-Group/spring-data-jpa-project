package org.example.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
public class UserInfoEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
//	@Column(insertable = false,updatable = false,name = "user_id")
//	private Long userId;

	private Integer ages;
	private String telephone;
//	@MapsId
	@OneToOne(cascade = {CascadeType.PERSIST},orphanRemoval = true,fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),name = "my_user_id")
	private UserEntity user;
}