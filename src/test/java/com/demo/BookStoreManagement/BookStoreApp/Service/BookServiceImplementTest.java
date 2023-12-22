package com.demo.BookStoreManagement.BookStoreApp.Service;

import com.demo.BookStoreManagement.BookStoreApp.Entity.Books;
import com.demo.BookStoreManagement.BookStoreApp.Exception.NoBookFound;
import com.demo.BookStoreManagement.BookStoreApp.Repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)

class BookServiceImplementTest {



    @Mock
    private BookRepository bookrepo;



    private BookServiceImplement bsi;

    @BeforeEach
    void setUp()
    {
        this.bsi = new BookServiceImplement(this.bookrepo);
    }



    @Test
    void getBookById() throws NoBookFound {
        Mockito.when(bookrepo.findById(2)).thenReturn(Optional.of(new Books(2, "Dance of Dragon", "G.R.R. Martin", 3, 220, "danceofdragon.jpg", "NA")));
       Mockito.when(bookrepo.existsById(2)).thenReturn(true);

        Books actualBook = bsi.getBookById(2);

        System.out.println("actualBook = " + actualBook);
        System.out.println("expectedBook = " + bookrepo.findById(2));
        assertEquals(bookrepo.findById(2), actualBook);
    }


}