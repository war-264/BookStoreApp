package com.demo.BookStoreManagement.BookStoreApp.Controller;

import com.demo.BookStoreManagement.BookStoreApp.Entity.User;
import com.demo.BookStoreManagement.BookStoreApp.Exception.InvalidUserNamePasswordException;
import com.demo.BookStoreManagement.BookStoreApp.Exception.LoginFailedException;
import com.demo.BookStoreManagement.BookStoreApp.Exception.UserNotFoundException;
import com.demo.BookStoreManagement.BookStoreApp.Service.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImplement userv;

    @RequestMapping(method = RequestMethod.POST, path = "/save")
    public ResponseEntity<Object> signUp(@Valid @RequestBody User user)
    {
      String msg =  userv.signUp(user);
        return new ResponseEntity<>( msg,  HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Object> signUp(@PathVariable int id)
    {
       User user= userv.getUserById(id);
        return new ResponseEntity<>("user details " + user.toString(), HttpStatus.OK);
    }

    @RequestMapping (method = RequestMethod.GET,path = "/viewAll")
    public ResponseEntity<Object> getAllUser()
    {
       List<User> ul = userv.getAllUsers();
       return  new ResponseEntity<>(ul, HttpStatus.OK);
    }

    @RequestMapping (method = RequestMethod.DELETE,path = "/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id)
    {
        try{
         String msg = userv.deleteById(id);
            return  new ResponseEntity<>(msg, HttpStatus.OK);

        } catch (UserNotFoundException e) {
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }
    // login

    @RequestMapping(method = RequestMethod.POST,path = "/login")
    public ResponseEntity<Object>  Login (@RequestBody User user) throws UserNotFoundException, LoginFailedException {

        try {
            boolean b = userv.login(user);
            if(b) {
                return new ResponseEntity<>("Login successfull", HttpStatus.OK);
            }
        }catch (UserNotFoundException ue)
        {
            return new ResponseEntity<>(ue.getMessage(), HttpStatus.NOT_FOUND);
        }catch (LoginFailedException le)
        {
            return new ResponseEntity<>(le.getMessage(), HttpStatus.UNAUTHORIZED);
        }catch (InvalidUserNamePasswordException ie)
        {
            return new ResponseEntity<>(ie.getMessage(), HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>("login failed!!!" , HttpStatus.NOT_FOUND );
    }

    @RequestMapping(method = RequestMethod.GET, path = "/name/{userName}")
    public ResponseEntity<Object> getByName(@PathVariable String userName)
    {
       User user= userv.getByName(userName);
       if(user!=null) {
           return new ResponseEntity<>( user, HttpStatus.OK);
       }
        return new ResponseEntity<>("user not found with given username " , HttpStatus.NOT_FOUND);
    }




}
