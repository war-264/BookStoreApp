package com.demo.BookStoreManagement.BookStoreApp.Service;

import com.demo.BookStoreManagement.BookStoreApp.Entity.User;
import com.demo.BookStoreManagement.BookStoreApp.Exception.InvalidUserNamePasswordException;
import com.demo.BookStoreManagement.BookStoreApp.Exception.LoginFailedException;
import com.demo.BookStoreManagement.BookStoreApp.Exception.UserNotFoundException;
import com.demo.BookStoreManagement.BookStoreApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository urepo;

    @Override
    public String signUp(User user) {

        List<User>ul = urepo.findAll();
        if(ul.stream().noneMatch(a->a.getUserName().equals(user.getUserName()))) {
            if (ul.size() == 0) {
                user.setId(0);
            } else {
                user.setId(ul.size());
            }
            urepo.save(user);
            System.out.println(user);
            return "User signup successfully";
        }
        return "user name already available";
    }

    @Override
    public User getUserById(int id) {

        Optional<User> u= urepo.findById(id);
        return u.get();
    }

    @Override
    public List<User> getAllUsers() {
        List<User>ul = urepo.findAll();
        ul.stream().forEach(a-> System.out.println(a));
        return ul;
    }

    @Override
    public String deleteById(int id) throws UserNotFoundException {
        if(urepo.existsById(id)){
            urepo.deleteById(id);
            return "User deleted for ID : " +id;
        }
        throw new UserNotFoundException("User not available for id : " +id);

    }


    //login method................

    @Override
    public boolean login(User user) throws UserNotFoundException, LoginFailedException, InvalidUserNamePasswordException {

        if(!user.getUserName().isEmpty() || !user.getPassword().isEmpty()) {
            User userCheck = urepo.getUserByName(user.getUserName());
            if (null == userCheck) {
                throw new UserNotFoundException("user not available,please signup");

            } else if (user.getPassword().equals("") || !user.getPassword().equals(userCheck.getPassword())) {
                throw new LoginFailedException("login failed incorrect password");

            } else {
                System.out.println(" Hi " + user.getUserName() + "(" + user.getUserType() + ")" + " you are  login to our BookStore app");
                return true;
            }
        }else{
            throw new InvalidUserNamePasswordException("invalid password or user name, please try again");
        }



    }

    @Override
    public User getByName(String userName) {
        User user= urepo.getUserByName(userName);
        if(user==null)
        {
            System.out.println("user not found from getbyname method");
        }else {
            System.out.println(user.toString());
            return user;
        }
        return null;
    }

}
