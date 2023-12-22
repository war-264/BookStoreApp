package com.demo.BookStoreManagement.BookStoreApp.Repository;

import com.demo.BookStoreManagement.BookStoreApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User,Integer> {

   @Query("{'userName':?0}")
    public User getUserByName(String userName);
}
