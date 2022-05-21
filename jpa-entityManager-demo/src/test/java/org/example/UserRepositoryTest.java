package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    //利用该方式获得entityManager
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    /**
     * 测试entityManager用法
     *
     * @throws JsonProcessingException
     */
    @Test
    @Rollback(false)
    public void testEntityManager() throws JsonProcessingException {
        //测试找到一个User对象
        User user = entityManager.find(User.class, 2L);
        //Assertions.assertEquals(user.getAddresses(),"shanghai");

        //我们改变一下user的删除状态
        user.setDeleted(true);
        //merger方法
        entityManager.merge(user);
        //更新到数据库里面
        entityManager.flush();

        //再通过createQuery创建一个JPQL，进行查询
        List<User> users = entityManager.createQuery("select u From User u where u.name=?1")
                .setParameter(1, "jack")
                .getResultList();
        Assertions.assertTrue(users.get(0).getDeleted());
    }

    @Test
    public void testCustomizedUserRepository() {
        //查出来一个User对象
        User user = userRepository.findById(2L).get();
        //调用我们的逻辑删除方法进行删除
        userRepository.logicallyDelete(user);
        //我们再重新查出来，看看值变了没有
        List<User> users = userRepository.findAll();
        Assertions.assertEquals(users.get(0).getDeleted(), Boolean.TRUE);
    }

    @Test
    public void testCustomizedBaseRepository() {
        User user = userRepository.findById(2L).get();
        userRepository.logicallyDelete(user);
        userRepository.delete(user);
        List<User> users = userRepository.findAll();
        Assertions.assertEquals(users.get(0).getDeleted(), Boolean.TRUE);
    }
}