package com.demo.BookStoreManagement.BookStoreApp.Controller;

import com.demo.BookStoreManagement.BookStoreApp.Entity.Books;
import com.demo.BookStoreManagement.BookStoreApp.Exception.InvalidInputException;
import com.demo.BookStoreManagement.BookStoreApp.Exception.NoBookFound;
import com.demo.BookStoreManagement.BookStoreApp.Service.BookServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/Book")
public class BookController {
    @Autowired
    private BookServiceImplement bsi;

    // add books to bookstore.
    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public Books addBook(@RequestBody Books book) {
        bsi.addBook(book);
        return book;
    }

    // add list
    @RequestMapping(method = RequestMethod.POST, path = "/addlist")
    public ResponseEntity<Object> addListOfBook(@RequestBody List<Books> listOfBooks) {

        bsi.addListOfBooks(listOfBooks);
        return new ResponseEntity<>(listOfBooks, HttpStatus.CREATED);

    }

    // Get all books from bookstore.
    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public List<Books> getAll() throws NoBookFound {
        List<Books> allBook = bsi.getAllBook();
        if (!allBook.isEmpty()) {


            System.out.println(allBook.toString());
            return allBook;
        } else {
            System.out.println(new NoBookFound("not in store"));
            throw new NoBookFound("not in store");
        }

    }
    // get a book by name
    @RequestMapping(method = RequestMethod.GET, path = "/name/{bookName}")
    public ResponseEntity<Object> findBookByName(@PathVariable String bookName) {
        String bookNameCheck = bookName;
        Books b = bsi.findBookByName(bookName);
        if (bookNameCheck.matches("[a-zA-Z \\s]{0,}")) {

            if (b == null) {
                return new ResponseEntity<>("no Book found", HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(b, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("given name is not valid", HttpStatus.NOT_ACCEPTABLE);
        }

    }

    // search by id
    @RequestMapping(method = RequestMethod.GET, path = "/id/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable("id") int id) {

        try {


            Books b = bsi.getBookById(id);
            if (b == null) {

                throw new NoBookFound("No book Available");

            } else {
                return new ResponseEntity<>(b, HttpStatus.OK);
            }
        } catch (NoBookFound nf) {
            return new ResponseEntity<>(nf.toString(), HttpStatus.NOT_FOUND);
        }
    }
    // filter by price
 @RequestMapping(method = RequestMethod.GET , path = "/price/{filter}")
    public  ResponseEntity<Object> filteListOfBooksByPrice (@PathVariable ("filter") String filter)
    {
        List<Books> filterList = bsi.filterByPrice(filter);
        return  new ResponseEntity<>(filterList , HttpStatus.OK);
    }

    // delete by id

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable int id) throws NoBookFound {
        try {
            if (bsi.getBookById(id) == null) {
                return new ResponseEntity<>("Book record not available", HttpStatus.NOT_FOUND);
            } else {
                bsi.deleteById(id);
                return new ResponseEntity<>("Book record deleted", HttpStatus.OK);
            }
        } catch (NoBookFound nf) {
            return new ResponseEntity<>(nf.toString(), HttpStatus.NOT_FOUND);
        }

    }


}
