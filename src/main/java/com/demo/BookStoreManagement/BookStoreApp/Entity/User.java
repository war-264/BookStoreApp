package com.demo.BookStoreManagement.BookStoreApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GeneratorType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Document("appuser")
//@Table(name = "AppUser")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {


  @Id
   private int id;
  @NotBlank(message = "should not blank")
  private  String userName;
  private  String userType;
  private  String password;

  public User() {
  }



  public void setUserName(String userName) {
    this.userName = userName;
  }
  public String getUserName() {
    return userName;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id+1;
  }

  public User(int id, String userName, String userType, String password) {
    this.id = id;
    this.userName = userName;
    this.userType = userType;
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", userType='" + userType + '\'' +
            '}';
  }
}
