package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

/**
 * 值集实体
 *
 * @author Administrator
 */
@Accessors(chain = true)
@EqualsAndHashCode(of = {"key", "value", "enable"})
@ToString(of = {"key", "value", "enable"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity(name = "ScMesValueSet")
@Table(name = "p_m_value_set")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
//@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class ScMesValueSet{
    private static final long serialVersionUID = 732332244245825L;
    //    @JsonIgnoreProperties(ignoreUnknown = true, value = {"parent"})
    //@JsonSerialize(using = ScMesValueSetSerializer.class)
//    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ScMesValueSet parent;

    @Column(name = "key_id", nullable = false, unique = true)
    private String key;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "enable", columnDefinition = "bit(1) default 1")
    private boolean enable = true;

    @Column(name = "description")
    private String description;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parent")
    private List<ScMesValueSet> children;

}
