package org.example.help;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;


/**
 * 利用spring进行管理
 */
@Component
public class TransactionHelper {
    /**
     * 利用spring 的机制和jdk8的Consumer机制实现只消费的事务
     */
    @Transactional(rollbackFor = Exception.class) //可以根据实际业务情况，指定明确的回滚异常
    @Retryable(value = ObjectOptimisticLockingFailureException.class, backoff = @Backoff(multiplier = 1.5, random = true))
    public void transactional(Consumer consumer, Object o) {
        consumer.accept(o);
    }
}