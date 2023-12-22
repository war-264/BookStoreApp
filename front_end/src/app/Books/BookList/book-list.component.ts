import { Component, OnInit } from '@angular/core';
import { IBooks } from '../Books.component';
import { BookService } from '../BookService/bookservice.component';
import { ActivatedRoute, Router } from '@angular/router';
import { NavbarComponent } from 'src/app/NavBar/navbar.component';

@Component({
  // selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  
  title : string = 'Book List';

  deleteStatus : string='';

  BookList : IBooks[]=[];
 
  errorMessage : string ='';

  filtertype: string = '';

  pricefilter : string='>';

  activeUserType : string = '';

   private _filter: string = '';

  public get filter(): string {
    return this._filter;
  }
  public set filter(value: string) {
    this._filter = value;
    this.filterBookList=this.filterBook(value);
    console.log(this.filterBookList);
  }

  filterBookList : IBooks[] = [];

  constructor(private bookService: BookService,
    private aroute : ActivatedRoute ,
     private route :Router,
     private navbarcomponent: NavbarComponent){}

  ngOnInit(): void {
    this.activeUserType= this.navbarcomponent.ngOnInit();
    this.getAllBooks();
      
    console.log(this.activeUserType)
  }


  getAllBooks(): void {
    this.bookService.getAllBooks()
    .subscribe(
      {
        next : BookList => this.BookList=BookList,
        error : errorMessage => this.errorMessage=errorMessage,
        complete : () => this.filterBookList= this.BookList
        
        
      }
    )
  }

  deleteById(id: number) {
    if(this.BookList.length==1)
    {
      alert('cannot delete the last available book,  delete it from database');
      this.errorMessage='cannot delete the last available book,  delete it from database';
    }else{
    this.bookService.deleteById(id).subscribe( data =>
      {
       
       
        alert(data + 'Book id: ' +id)
        this.getAllBooks();
      },
      error => this.errorMessage=error
    )
    }
  }

 
  
  filterBook( filterBy : any): IBooks[] {
    var priceFilter = this.pricefilter;
    var type = this.filtertype;
   filterBy = filterBy.toLowerCase();
    console.log(filterBy)
    
 
    if(type=='price') {
      if(priceFilter=='>'){
       return this.BookList.filter(book => book.price >Number(filterBy))
      }else if(priceFilter=='<'){
       return this.BookList.filter(book => book.price <Number(filterBy))
      }else{
       return this.BookList.filter(book => book.price == Number(filterBy))
      }
      
  } else if(type=='authorName') {
    return this.BookList.filter((Book:IBooks)=> JSON.stringify(Book.authorName).toLowerCase().includes(filterBy));
  }else{
    return this.BookList.filter((Book:IBooks)=> JSON.stringify(Book.bookName).toLowerCase().includes(filterBy));
  }
  


  }


}
  


