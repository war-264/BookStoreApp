import { Component, OnInit } from '@angular/core';
import { LoginComponent } from '../Login/login.component';
import { UserService } from '../User/UserService/userservice.components';
import { Router, ɵafterNextNavigation } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  type: string='';
  title: string='';
  login:boolean=false;


  constructor(private router: Router ,private userService: UserService,private snackbar : MatSnackBar){}


  ngOnInit(): string {
    console.log(sessionStorage.getItem('isUserLogdin'));
    this.login=sessionStorage.getItem('isUserLogdin')=='true'?true:false;
    this.type = sessionStorage.getItem('userType')=='user'?'user':'admin';

    return this.type;



  }
  LogoutClick(): void {
   let Logout = new LoginComponent( this.router , this.userService, this.snackbar )
  Logout.LogOut();
    console.log('Logout clicked');
    console.log(Logout.isUserLogdin)

  }








}
