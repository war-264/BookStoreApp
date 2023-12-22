package com.demo.BookStoreManagement.BookStoreApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document("Books")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Books {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String bookName;
    private String authorName;
    private int edition;
    private double price;



    private String description;



    private String imageUrl;


    public Books() {
        super();
    }

    public Books(int id, String bookName, String authorName, int edition, double price, String imageUrl,String description) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.edition = edition;
        this.price = price;
        this.imageUrl=imageUrl;
        this.description=description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id+1;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {

        String temp = "/assets/"+imageUrl.substring(10);

        this.imageUrl = temp;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", edition=" + edition +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl.toString() + '\'' +
                '}';
    }
}
