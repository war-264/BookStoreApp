import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { IBooks } from "../Books.component";
import { catchError, map, Observable, tap, throwError } from "rxjs";
import { BookClass } from "../BookAddForm/BookClass";


@Injectable(
    {
        providedIn: 'root',
    }
)

export class BookService {

    constructor(private Http: HttpClient)
    {}

    private url = 'http://localhost:1234/Book/';

    getAllBooks(): Observable <IBooks[]> {

        return this.Http.get<IBooks[]>(this.url+'list').pipe(
                tap (data=> console.log("all books " + JSON.stringify(data))),
                catchError(this.HandleError)
          
        )

    }

    getBookByName(bookName: string):Observable<IBooks | undefined> {

        console.log(bookName.replaceAll('"',''));

        return this.Http.get<IBooks>(this.url+`name/${bookName.replaceAll('"','')}`).pipe(
           
            tap (data=> console.log(" books " + JSON.stringify(data))),
            catchError(this.HandleError)


        )
    }

    deleteById(id: number): Observable<any> {
       return this.Http.delete(this.url+'delete/'+id , {responseType: 'text'}).pipe(
            tap ( ()=>console.log('Book deleted with id ' + id)),
            catchError(this.HandleError)
        );
    }

    addBook(book:BookClass):Observable<any> {
        return this.Http.post(this.url+'add/',book).pipe(
            tap ( (data)=>console.log('Book added ' + JSON.stringify(data)),
            catchError (this.HandleError)
        )
        );
    }


    private HandleError(error : HttpErrorResponse)
    {

        let errorMessage = '';
        if (error. error instanceof ErrorEvent)
        {
            errorMessage = 'error occured: ' + error. error.message ;
        }else{
            errorMessage = 'server site error' + error.status + ' error message ' + error.message ; 
        }

        console.error(errorMessage);
         return throwError(()=> errorMessage)
    }

}