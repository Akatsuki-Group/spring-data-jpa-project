package org.example.repository;

import org.example.dto.UserOnlyNameEmailDto;
import org.example.entity.User;
import org.example.entity.UserAddress;
import org.example.entity.UserOnlyName;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Streamable;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface UserRepository extends JpaRepository<User, Long> {
//    //根据名称进行查询用户列表
//    List<User> findByName(String name);

    List<User> findByEmail(String email);

    // 根据用户的邮箱和名称查询
    List<User> findByEmailAndName(String email, String name);


//    Page<User> findByLastname(String lastname, Pageable pageable);//根据分页参数查询User，返回一个带分页结果的Page(下一课时详解)对象（方法一）

    Slice<User> findByLastname(String lastname, Pageable pageable);//我们根据分页参数返回一个Slice的user结果（方法二）

    List<User> findByLastname(String lastname, Sort sort);//根据排序结果返回一个List（方法三）

//    List<User> findByLastname(String lastname, Pageable pageable);//根据分页参数返回一个List对象（方法四）


    User findFirstByOrderByLastnameAsc();

    User findTopByOrderByAgeDesc();

    List<User> findDistinctUserTop3ByLastname(String lastname, Pageable pageable);

    List<User> findFirst10ByLastname(String lastname, Sort sort);

    List<User> findTop10ByLastname(String lastname, Pageable pageable);

    User getByAddress(UserAddress userAddress);

    @Nullable
    User findByAddress(@Nullable UserAddress userAddress);//当我们添加@Nullable 注解之后，参数和返回结果这个时候就都会允许为 null 了；

    Optional<User> findOptionalByAddress(UserAddress userAddress); //返回结果允许为 null,参数不允许为 null 的情况

    Streamable<User> findByAge(int age);


    //自定义一个查询方法，返回Stream对象，并且有分页属性
    @Query("select u from User u")
    Stream<User> findAllByCustomQueryAndStream(Pageable pageable);

    //测试Slice的返回结果
    @Query("select u from User u")
    Slice<User> findAllByCustomQueryAndSlice(Pageable pageable);

    @Async
    Future<User> findByFirstname(String firstname);

    @Async
    CompletableFuture<User> findOneByFirstname(String firstname);

    @Async
    ListenableFuture<User> findOneByLastname(String lastname);

    UserOnlyNameEmailDto findByName(String email);

    /**
     * 接口的方式返回DTO
     * @param address
     * @return
     */
    UserOnlyName findByAddress(String address);
}