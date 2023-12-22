import { Component ,OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IBooks } from '../Books.component';
import { BookService } from '../BookService/bookservice.component';
import { BookClass } from './BookClass';

@Component({
 
  templateUrl: './book-add.component.html',
  styleUrls: ['./book-add.component.css']
})
export class BookAddComponent implements OnInit {
title: string='Add Book Details';
book : BookClass = new BookClass();
submitted = false;
 i = 0;

constructor(private bookService: BookService , private router: Router , private aroute: ActivatedRoute)
{
  
}




  ngOnInit(): void {
   
  }

  newBook(): void {
   this.book = new BookClass();
   this.submitted=false;
  }

  addBook(){
    this.bookService.addBook(this.book).subscribe(
      book => {
        this.submitted=true;
        alert("Book added " + JSON.stringify(book));
        this.book= new BookClass();

      },
     error=> console.error(error)
      
      );
  }

  OnSave(): void {
    this.addBook();
    this.i++;

    
  }

}
