import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { Users } from "../user.component";
import { catchError, map, Observable, tap, throwError } from "rxjs";
import { Injectable } from "@angular/core";
import { Iusers } from "../Iuser.component";


@Injectable({
    providedIn: 'root',
})
export class UserService {

    constructor(private Http : HttpClient){}


    private url = 'http://localhost:1234/user/';

    signup(user : Users) : Observable<any> {
         
      return  this.Http.post(this.url+'save/',user , {responseType: 'text'}).pipe(
            tap(()=> console.log('user added successfully '+ JSON.stringify(user))),
            catchError(this.HandleError)
        )
    }
    
    getUserByName(userName : string) : Observable<Iusers | undefined> {
        return this.Http.get<Iusers>(this.url+'name/'+userName).pipe(
            tap (data=> console.log(JSON.stringify(data))),
            catchError(this.HandleError)
            )
        
    }

    login(user: Users) : Observable<any>  {
      return  this.Http.post(this.url+'login',user,{responseType: 'text'}).pipe(
        tap (data=> console.log(" user " + JSON.stringify(data))),
        catchError(this.HandleError)
      )
    
    }


    private HandleError(error : HttpErrorResponse)
    {
        let errorMessage = '';
        if (error.status===0)
        {//client error
            errorMessage = 'error occured: ' + error.message ;
            console.error(errorMessage);
            alert('network error, please try again later');
        }else{//server error
            errorMessage = 'server site error ' + error.status + ' error message ' + error.error ; 
            console.error(errorMessage);
          
        }

       
        return throwError(
            error.error);
        
    }
}