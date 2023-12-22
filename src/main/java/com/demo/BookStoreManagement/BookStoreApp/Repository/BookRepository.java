package com.demo.BookStoreManagement.BookStoreApp.Repository;

import com.demo.BookStoreManagement.BookStoreApp.Entity.Books;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository  extends MongoRepository<Books,Integer> {

    @org.springframework.data.mongodb.repository.Query("{'bookName':?0}")
    public Books getByName (String bookName);

    List<Books> findAll();

//    @Query(value = "select * from books where book_name ILIKE ?1",nativeQuery = true)
//    public Books getByName (String bookName);

//    @Query  (value = "select * from books where price >= ?1",nativeQuery = true)
//    public List<Books> getTheBooksFilterByPriceHigherOrEqaualTo (int price);

//    @Query(value = "select * from books order by price= :filter DESC", nativeQuery = true)
//    public List<Books> getSortedByPrice (String highToLow);

//    @Query(value = "select * from books order by price ASC", nativeQuery = true)
//    public List<Books> getSortedByPrice (String lowToHigh);


}
