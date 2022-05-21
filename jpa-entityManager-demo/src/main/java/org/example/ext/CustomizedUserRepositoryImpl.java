package org.example.ext;

import org.example.entity.User;

import javax.persistence.EntityManager;

public class CustomizedUserRepositoryImpl implements CustomizedUserRepository {
    private EntityManager entityManager;

    public CustomizedUserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User logicallyDelete(User user) {
        user.setDeleted(true);
        return entityManager.merge(user);
    }
}