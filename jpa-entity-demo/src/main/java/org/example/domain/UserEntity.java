package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.UserAddressEntity;
import org.example.entity.UserInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String name;
	private String email;
	private String sex;
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	private List<UserAddressEntity> address;
	@OneToOne(mappedBy = "user",fetch = FetchType.LAZY,optional = true)
	private UserInfo userInfo;
	@OneToMany(mappedBy = "user")
	private List<UserRoomRelation> userRoomRelations;
//	@ManyToMany(mappedBy = "users")
//	private List<Room> rooms;
}