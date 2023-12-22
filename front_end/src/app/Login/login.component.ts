import { Component, OnInit } from '@angular/core';
import { Router, ɵafterNextNavigation } from '@angular/router';
import { Iusers } from '../User/Iuser.component';
import { Users } from '../User/user.component';
import { UserService } from '../User/UserService/userservice.components';
import {
  MatSnackBar,
  MatSnackBarAction,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
  MatSnackBarRef,
} from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  user: Users = new Users();
  UserByName: Iusers | undefined;
  loginForm: boolean = true;
  userName: any = '';
  password: string = '';
  usertype: any = '';
  isUserLogdin: boolean = false;
  hideLoginButton: any = '';
  msg: string = '';
  errorMessage: string = '';
  UserArr: Iusers[] = [];

  vertical: MatSnackBarVerticalPosition = 'bottom';
  horizontal: MatSnackBarHorizontalPosition = 'center';

  constructor(
    private router: Router,
    private UserService: UserService,
    private snackbar: MatSnackBar
  ) {}

  ngOnInit(): void {
    console.log(sessionStorage.getItem('isUserLogdin'));
    this.hideLoginButton =
      sessionStorage.getItem('isUserLogdin') == 'true' ? true : false;
  }

  onClick(): void {
    if (this.loginForm) {
      this.loginForm = false;
    } else {
      this.loginForm = true;
    }
  }
  //login

  login() {
    this.UserService.login(this.user).subscribe(
      (result) => {
        this.getByName(this.user.userName);
        this.msg = result;

        this.isUserLogdin = true;
        sessionStorage.setItem(
          'isUserLogdin',
          this.isUserLogdin ? 'true' : 'false'
        );

        window.location.replace('/home');
        // this.router.navigate(['home']);
      },
      (error) => {
      this.msg = (this.msg.length>20)? 'wrong user name or password' : error.message;
      console.log (this.msg);
        this.snackbar.open(this.msg, 'try agian', {
          verticalPosition: this.vertical,
          horizontalPosition: this.horizontal,
          panelClass:['mat-error']

        });

        this.isUserLogdin = false;
        sessionStorage.setItem(
          'isUserLogdin',
          this.isUserLogdin ? 'true' : 'false'
        );
        sessionStorage.removeItem('userName');
        // window.location.reload();
      }
    );
  }

  // login end
  getByName(userName: string): void {
    this.UserService.getUserByName(userName).subscribe({
      next: (UserByName) => (this.UserByName = UserByName),
      error: (errorMessage) => (this.errorMessage = errorMessage),
      complete: () => {
        console.log(this.UserByName),
          (this.usertype = this.UserByName?.userType);
        sessionStorage.setItem('userType', this.usertype),
          (this.userName = this.UserByName?.userName);
        sessionStorage.setItem('userName', this.userName);
      },
    });
  }

  LogOut(): void {

    if(confirm('Are you sure you want to log out')  ==true)
    {
    sessionStorage.removeItem('isUserLogdin');
    sessionStorage.removeItem('userName');
    sessionStorage.removeItem('userType');
    this.isUserLogdin = false;
    this.snackbar
      .open('LogOut', '', {
        verticalPosition: this.vertical,
        horizontalPosition: this.horizontal,
        duration:2000
      })
      .afterDismissed()
      .subscribe((data) => {
        console.log(data);
        window.location.replace('/home');
      });
    }else{}

    // this.router.navigate(['home']);
  }
}
