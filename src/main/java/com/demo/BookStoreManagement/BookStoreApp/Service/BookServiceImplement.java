package com.demo.BookStoreManagement.BookStoreApp.Service;

import com.demo.BookStoreManagement.BookStoreApp.Entity.Books;
import com.demo.BookStoreManagement.BookStoreApp.Exception.InvalidInputException;
import com.demo.BookStoreManagement.BookStoreApp.Exception.NoBookFound;
import com.demo.BookStoreManagement.BookStoreApp.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class BookServiceImplement implements BookService {

     @Autowired
     private BookRepository brepo;

     // constructor
    public BookServiceImplement(BookRepository brepo) {
        this.brepo = brepo;
    }


      // add book
    @Override
    public void addBook(Books book) {
        book.setImageUrl(book.getImageUrl());
        List<Books> b = brepo.findAll();
        if(b.size()==0)
        {
            book.setId(0);
        }else{
          book.setId(b.size());
        }
        brepo.save(book);
        System.out.println(book.toString());

    }

    @Override
    public List<Books> addListOfBooks(List<Books> booksList) {

       brepo.saveAll(booksList);

        return brepo.findAll();
    }

    // List of all the books
    @Override
    public List<Books> getAllBook() {

        return brepo.findAll();

    }

    // search book by name
    @Override
    public Books findBookByName(String bookName)  {

        Books b = brepo.getByName(bookName);
        if (bookName.matches("[0-9]{0,}")) {

            System.out.println("Invalid input name must in Alphabets");

        } else {
            if (b == null) {
                System.out.println("no book available");
            } else {
                System.out.println(b.toString());
                return b;
            }
        }
            return null;
        }


    // Delete book from record by id
    @Override
    public void deleteById(int id) throws NoBookFound {


            if(brepo.existsById(id))
            {
                System.out.println(" Book deleted successfully , id = " + id);

                brepo.deleteById(id);
            }else {
                System.out.println(new NoBookFound("not available"));
                throw new NoBookFound("Book not available with given id : " +id);
            }

    }

    // search a book by id
    @Override
    public Books getBookById(int id) throws NoBookFound{


    if (brepo.existsById(id)) {
        Optional<Books> b = brepo.findById(id);
        System.out.println("book :" + b);
        return b.get();
    } else {
        System.out.println(new NoBookFound("not available"));
        throw new NoBookFound("Book not available with give id : " + id);
    }

        }

    //Filter book list by price
    @Override
    public List<Books> filterByPrice(String filter) {
//        if (filter.equalsIgnoreCase("LowToHigh")) {
//            List<Books> sortedBookList = brepo.findAll(Sort.by(Sort.Direction.ASC, "price"));
//            System.out.println("sortedBookList = " + sortedBookList.toString().replace("],","\n"));
//            return sortedBookList;
//        } else if (filter.equalsIgnoreCase("HighToLow")) {
//            List<Books> sortedBookList = brepo.findAll(Sort.by(Sort.Direction.DESC, "price"));
//
//            System.out.println("sortedBookList = " + sortedBookList.toString().replace("],","\n"));
//            return sortedBookList;
//        }
        return null;
    }


}

