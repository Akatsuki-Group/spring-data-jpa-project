package org.example.entity;

//@Entity
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString(exclude = "addresses")
//@EntityListeners(AuditingEntityListener.class)
//public class User implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String name;
//    private String email;
//    @Enumerated(EnumType.STRING)
//    private SexEnum sex;
//    private Integer age;
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<UserAddress> addresses;
//    private Boolean deleted;
//    @CreatedBy
//    private Integer createUserId;
//    @CreatedDate
//    private Date createTime;
//    @LastModifiedBy
//    private Integer lastModifiedUserId;
//    @LastModifiedDate
//    private Date lastModifiedTime;
//}

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//import org.springframework.data.domain.Auditable;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//import java.time.Instant;
//import java.org.example.util.List;
//import java.org.example.util.Optional;
//
//@Entity
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString(exclude = "addresses")
//@EntityListeners(AuditingEntityListener.class)
//public class User implements Auditable<Integer, Long, Instant> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String name;
//    private String email;
//    @Enumerated(EnumType.STRING)
//    private SexEnum sex;
//    private Integer age;
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<UserAddress> addresses;
//    private Boolean deleted;
//    private Integer createUserId;
//    private Instant createTime;
//    private Integer lastModifiedUserId;
//    private Instant lastModifiedTime;
//
//    @Override
//    public Optional<Integer> getCreatedBy() {
//        return Optional.ofNullable(this.createUserId);
//    }
//
//    @Override
//    public void setCreatedBy(Integer createdBy) {
//        this.createUserId = createdBy;
//    }
//
//    @Override
//    public Optional<Instant> getCreatedDate() {
//        return Optional.ofNullable(this.createTime);
//    }
//
//    @Override
//    public void setCreatedDate(Instant creationDate) {
//        this.createTime = creationDate;
//    }
//
//    @Override
//    public Optional<Integer> getLastModifiedBy() {
//        return Optional.ofNullable(this.lastModifiedUserId);
//    }
//
//    @Override
//    public void setLastModifiedBy(Integer lastModifiedBy) {
//        this.lastModifiedUserId = lastModifiedBy;
//    }
//
//    @Override
//    public void setLastModifiedDate(Instant lastModifiedDate) {
//        this.lastModifiedTime = lastModifiedDate;
//    }
//
//    @Override
//    public Optional<Instant> getLastModifiedDate() {
//        return Optional.ofNullable(this.lastModifiedTime);
//    }
//
//    @Override
//    public boolean isNew() {
//        return id == null;
//    }
//}

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "addresses")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private SexEnum sex;
    private Integer age;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserAddress> addresses;
    private Boolean deleted;
}
