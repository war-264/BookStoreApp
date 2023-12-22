import { NgModule } from  '@angular/core'
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { BookListComponent } from './Books/BookList/book-list.component';
import { RouterModule } from '@angular/router';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { BookdetailsComponent } from './Books/BookDetail/bookdetails.component';
import { BookAddComponent } from './Books/BookAddForm/book-add.component';
import { HomepageComponent } from './HomePage/homepage.component';
import { NavbarComponent } from './NavBar/navbar.component';
import { LoginComponent } from './Login/login.component';
import { SignupComponent } from './User/signup/signup.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { ReqresinterceptInterceptor } from './Intercept/reqresintercept.interceptor';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    BookdetailsComponent,
    BookAddComponent,
    HomepageComponent,
    NavbarComponent,
    LoginComponent,
    SignupComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    CommonModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: '', component: HomepageComponent ,pathMatch:'full'},
      { path: 'home', component: HomepageComponent},
      { path: 'booklist', component: BookListComponent},
      { path: 'Book/name/:name', component: BookdetailsComponent},
      { path: 'Book/add', component: BookAddComponent},
      { path: 'user/add', component: SignupComponent}
   
     
    ]),
    BrowserAnimationsModule,
    MatSnackBarModule
  ],
  providers: [NavbarComponent,LoginComponent,

    { 
      provide: HTTP_INTERCEPTORS, useClass: ReqresinterceptInterceptor, multi:true
    }
  
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
