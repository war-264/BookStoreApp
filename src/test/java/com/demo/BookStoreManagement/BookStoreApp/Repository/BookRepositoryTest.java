package com.demo.BookStoreManagement.BookStoreApp.Repository;

import com.demo.BookStoreManagement.BookStoreApp.Entity.Books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookRepositoryTest {




    @Autowired
    private  BookRepository brepo ;


    @Test
    void test_getByName_method_of_repository_giving_book_of_same_name() {

      Books expectedBook =new Books(2,"Dance of Dragon","G.R.R. Martin",3,220,"danceofdragon.jpg","NA");


     Books actualBook= brepo.getByName("Dance of Dragon");
//        System.out.println(b);
        System.out.println(actualBook);
        System.out.println(expectedBook.getBookName() + " = " + actualBook.getBookName());
          assertEquals(actualBook.getBookName(),expectedBook.getBookName());

    }



    @Test
    void test_filterByPrice_for_price_lowTohigh ()
    {

        List<Books> ListBook = brepo.findAll((Sort.by(Sort.Direction.ASC , "price")));

        int price1,price2 = 0;
        int i = 0;
        System.out.println("ListBook = " + ListBook);
        for(Books l : ListBook )
        {
          price1= (int) ListBook.get(i).getPrice();

          assertTrue(price1>=price2 , "failed at index "+ i);
            System.out.println("test pass at index " +i);
            i++;
           price2=price1;

        }
    }

    @Test
    void test_filterByPrice_for_price_hightTolow ()
    {

        List<Books> ListBook = brepo.findAll((Sort.by(Sort.Direction.DESC , "price")));

        int price1 = 0,price2 = 0;
        int i = 0;
        System.out.println("ListBook = " + ListBook);
        for(Books l : ListBook )
        {
            price2= (int) ListBook.get(i).getPrice();

            assertTrue(price1<=price2 , "failed at index "+ i);
            System.out.println("test pass at index " +i);
            i++;
            price2=price1;

        }
    }

//    @Test
//    void test_getTheBooksFilterByPriceHigherOrEqaualTo_method_of_repository_is_working()
//    {
//        List<Books> filterList , listOfAll;
//
//        listOfAll =brepo.findAll();
//        System.out.println(" list of all \n " + listOfAll);
//
//        filterList= brepo.getTheBooksFilterByPriceHigherOrEqaualTo(100);
//        System.out.println(" filter list \n " + filterList);
//
//        int expected = 100;
//
//
//        if (!(listOfAll.isEmpty()) && listOfAll.stream().anyMatch(a->(int)a.getPrice()>=100))
//        {
//            int actual = (int) filterList.get(0).getPrice();
//            assertTrue(actual>=expected);
//            System.out.println(actual>=expected);
//
//        }
//        else
//        {
//            assertTrue(false,"either no book is available in database or all the books are of price less than 100 so cannot test the method");
//        }
//
//
//    }
    
}