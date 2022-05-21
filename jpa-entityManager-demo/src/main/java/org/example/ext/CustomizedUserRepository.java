package org.example.ext;


import org.example.entity.User;

public interface CustomizedUserRepository {
    User logicallyDelete(User user);
}
