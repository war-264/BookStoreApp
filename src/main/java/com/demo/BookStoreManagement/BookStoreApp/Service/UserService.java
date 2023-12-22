package com.demo.BookStoreManagement.BookStoreApp.Service;

import com.demo.BookStoreManagement.BookStoreApp.Entity.User;
import com.demo.BookStoreManagement.BookStoreApp.Exception.InvalidUserNamePasswordException;
import com.demo.BookStoreManagement.BookStoreApp.Exception.LoginFailedException;
import com.demo.BookStoreManagement.BookStoreApp.Exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    String signUp  (User user);
   User getUserById (int id);

   List<User> getAllUsers();

   String deleteById(int id) throws UserNotFoundException;

   boolean login(User user) throws UserNotFoundException, LoginFailedException, InvalidUserNamePasswordException;

   User getByName(String userName);


}
