package org.example.entity;

import lombok.Data;

import javax.persistence.*;
@Data
@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	@Version
	private Integer version;
}