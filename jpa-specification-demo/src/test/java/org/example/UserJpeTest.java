package org.example;

import org.assertj.core.util.Lists;
import org.example.entity.SexEnum;
import org.example.entity.User;
import org.example.entity.UserAddress;
import org.example.repository.UserAddressRepository;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserJpeTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;
    private Date now = new Date();

    /**
     * 提前创建一些数据
     */
    @BeforeAll
    @Rollback(false)
    @Transactional
    void init() {
        User user = User.builder()
                .name("jack")
                .email("123456@126.com")
                .sex(SexEnum.BOY)
                .age(20)
                .createDate(Instant.now())
                .updateDate(now)
                .build();
        userAddressRepository.saveAll(Lists.newArrayList(UserAddress.builder().user(user).address("shanghai").build(),
                UserAddress.builder().user(user).address("beijing").build()));
    }

    @Test
    public void testSPE() {
        //模拟请求参数
        User userQuery = User.builder()
                .name("jack")
                .email("123456@126.com")
                .sex(SexEnum.BOY)
                .age(20)
                .addresses(Lists.newArrayList(UserAddress.builder().address("shanghai").build()))
                .build();
        //假设的时间范围参数
        Instant beginCreateDate = Instant.now().plus(-2, ChronoUnit.HOURS);
        Instant endCreateDate = Instant.now().plus(1, ChronoUnit.HOURS);
        //利用Specification进行查询
        Page<User> users = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> ps = new ArrayList<Predicate>();
                if (StringUtils.hasText(userQuery.getName())) {
                    //我们模仿一下like查询，根据name模糊查询
                    ps.add(cb.like(root.get("name"), "%" + userQuery.getName() + "%"));
                }
                if (userQuery.getSex() != null) {
                    //equal查询条件，这里需要注意，直接传递的是枚举
                    ps.add(cb.equal(root.get("sex"), userQuery.getSex()));
                }
                if (userQuery.getAge() != null) {
                    //greaterThan大于等于查询条件
                    ps.add(cb.greaterThan(root.get("age"), userQuery.getAge()));
                }
                if (beginCreateDate != null && endCreateDate != null) {
                    //根据时间区间去查询创建
                    ps.add(cb.between(root.get("createDate"), beginCreateDate, endCreateDate));
                }
                if (!ObjectUtils.isEmpty(userQuery.getAddresses())) {
                    //联表查询，利用root的join方法，根据关联关系表里面的字段进行查询。
                    ps.add(cb.in(root.join("addresses").get("address")).value(userQuery.getAddresses().stream().map(a -> a.getAddress()).collect(Collectors.toList())));
                }
                return query.where(ps.toArray(new Predicate[ps.size()])).getRestriction();
            }
        }, PageRequest.of(0, 2));
        System.out.println(users);
    }
}