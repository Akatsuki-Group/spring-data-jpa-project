package org.example.help;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

/**
 * 利用spring进行管理
 */
@Component
public class TransactionHelper {
    /**
     * 利用spring 的机制和jdk8的function机制实现事务
     */
    @Transactional(rollbackFor = Exception.class) //可以根据实际业务情况，指定明确的回滚异常
    public <T, R> R transactional(Function<T, R> function, T t) {
        return function.apply(t);
    }
}