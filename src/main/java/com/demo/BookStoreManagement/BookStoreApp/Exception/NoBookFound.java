package com.demo.BookStoreManagement.BookStoreApp.Exception;

public class NoBookFound  extends  RuntimeException{

    public NoBookFound(String msg)
    {
        super(msg);
    }
}
