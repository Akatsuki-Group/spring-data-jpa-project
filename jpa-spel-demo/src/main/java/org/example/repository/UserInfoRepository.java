package org.example.repository;

import org.example.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    // JPA约定的变量entityName取得当前实体的实体名字
    @Query("from #{#entityName}")
    List<UserInfo> findAllByEntityName();

    //一个查询中既可以支持SpEL也可以支持普通的:ParamName的方式
    @Modifying
    @Query("update #{#entityName} u set u.name = :name where u.id =:id")
    void updateUserActiveState(@Param("name") String name, @Param("id") Long id);

    //演示SpEL根据数组下标取参数，和根据普通的Parma的名字:name取参数
    @Query("select u from UserInfo u where u.lastName like %:#{[0]} and u.name like %:name%")
    List<UserInfo> findContainingEscaped(@Param("name") String name);

    //SpEL取Parma的名字customer里面的属性
    @Query("select u from UserInfo u where u.name = :#{#customer.name}")
    List<UserInfo> findUsersByCustomersFirstname(@Param("customer") UserInfo customer);

    //利用SpEL根据一个写死的'jack'字符串作为参数
    @Query("select u from UserInfo u where u.name = ?#{'jack'}")
    List<UserInfo> findOliverBySpELExpressionWithoutArgumentsWithQuestionmark();

    //同时SpEL支持特殊函数escape和escapeCharacter
    @Query("select u from UserInfo u where u.lastName like %?#{escape([0])}% escape ?#{escapeCharacter()}")
    List<UserInfo> findByNameWithSpelExpression(String name);

    // #entityName和#[]同时使用
    @Query("select u from #{#entityName} u where u.name = ?#{[0]} and u.lastName = ?#{[1]}")
    List<UserInfo> findUsersByFirstnameForSpELExpressionWithParameterIndexOnlyWithEntityExpression(String name, String lastName);

    //对于 native SQL同样适用，并且同样支持取pageable分页里面的属性值
    @Query(value = "select * from (" //
            + "select u.*, rownum() as RN from (" //
            + "select * from user_info ORDER BY ucase(firstname)" //
            + ") u" //
            + ") where RN between ?#{ #pageable.offset +1 } and ?#{#pageable.offset + #pageable.pageSize}", //
            countQuery = "select count(u.id) from user_info u", //
            nativeQuery = true)
    Page<UserInfo> findUsersInNativeQueryWithPagination(Pageable pageable);
}