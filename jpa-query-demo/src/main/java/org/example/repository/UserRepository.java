package org.example.repository;

import org.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author MSI-NB
 */
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.address = ?1")
    User findByEmailAddress(String address);

    @Query("select u from User u where u.name like %?1")
    List<User> findByFirstnameEndsWith(String firstname);

    @Query(value = "SELECT * FROM USER WHERE ADDRESS = ?1", nativeQuery = true)
    User findByAddress(String emailAddress);

    @Query("select u from User u where u.name like ?1%")
    List<User> findByAndSort(String lastname, Sort sort);

    @Query("select u.id, LENGTH(u.name) as fn_len from User u where u.name like ?1%")
    List<Object[]> findByAsArrayAndSort(String lastname, Sort sort);

    @Query(value = "select u from User u where u.name = ?1")
    Page<User> findByLastname(String lastname, Pageable pageable);

    @Query("select u from User u where u.firstname = :firstname or u.lastname = :lastname")
    User findByLastnameOrFirstname(@Param("lastname") String lastname,
                                   @Param("firstname") String firstname);

    @Query("select u from User u where u.firstname = :firstname or u.lastname = :lastname")
    User findTop10ByLastnameOrFirstname(@Param("lastname") String lastname,
                                        @Param("firstname") String firstname);
}