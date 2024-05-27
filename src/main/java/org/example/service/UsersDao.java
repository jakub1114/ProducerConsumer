package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UsersDao {

    /**
     * Insert User into db
     * insertOrUpdate would be better probably use getById check if record exists
     * then ignore or update record
     */
    void add(User user);

    /**
     * List all users
     * @return user list
     */
    List<User> printAll();

    /**
     * Delete all users
     */
    void deleteAll();
}
