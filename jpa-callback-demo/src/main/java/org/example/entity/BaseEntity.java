package org.example.entity;

import lombok.Data;
import org.example.listener.EntityLoggingListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class, EntityLoggingListener.class})
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // @CreatedBy 这个可能会被 AuditingEntityListener覆盖，为了方便测试，我们先注释掉
    private Integer createUserId;
    @CreatedDate
    private Instant createTime;
    @LastModifiedBy
    private Integer lastModifiedUserId;
    @LastModifiedDate
    private Instant lastModifiedTime;
    //  @Version 由于本身有乐观锁机制，这个我们测试的时候先注释掉，改用手动设置的值；
    private Integer version;

    @PreUpdate
    public void preUpdate() {
        System.out.println("preUpdate::" + this.toString());
        this.setCreateUserId(200);
    }

    @PostUpdate
    public void postUpdate() {
        System.out.println("postUpdate::" + this.toString());
    }

    @PreRemove
    public void preRemove() {
        System.out.println("preRemove::" + this.toString());
    }

    @PostRemove
    public void postRemove() {
        System.out.println("postRemove::" + this.toString());
    }

    @PostLoad
    public void postLoad() {
        System.out.println("postLoad::" + this.toString());
    }
}