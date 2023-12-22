package com.demo.BookStoreManagement.BookStoreApp.Service;

import com.demo.BookStoreManagement.BookStoreApp.Entity.Books;
import com.demo.BookStoreManagement.BookStoreApp.Exception.NoBookFound;

import java.util.List;

public interface BookService {
    void addBook(Books book);
    List <Books> addListOfBooks (List<Books> booksList);
    List<Books> getAllBook();
    Books findBookByName(String bookName);
    Books getBookById (int id) throws NoBookFound ;
    List<Books> filterByPrice(String filter);
    void deleteById (int id) throws NoBookFound;


}