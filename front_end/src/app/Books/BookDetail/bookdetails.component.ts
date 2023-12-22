import { Component, OnInit } from '@angular/core';
import { IBooks } from '../Books.component';
import { BookService } from '../BookService/bookservice.component';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  
  templateUrl: './bookdetails.component.html',
  styleUrls: ['./bookdetails.component.css']
})
export class BookdetailsComponent implements OnInit  {

  Book : IBooks | undefined;
  errorMessage : string ='';

  constructor(private bookService: BookService,
    private aroute : ActivatedRoute,
    private router: Router){

    }
  title = 'Book Details';
  ngOnInit(): void {
   let  name  = JSON.stringify(this.aroute.snapshot.paramMap.get('name')) ;
    console.log(name);
    this.getBookbyName( name );
    
  }

  getBookbyName(name : string) : void {
    this.bookService.getBookByName(name).subscribe({
     next: Book=> this.Book = Book,
     error: errorMessage => this.errorMessage = errorMessage
    })
 }

 OnBack(): void {
  this.router.navigate(['booklist']);

 }


}
