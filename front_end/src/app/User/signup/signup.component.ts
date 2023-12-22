import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Users } from '../user.component';
import { UserService } from '../UserService/userservice.components';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

   user: Users = new Users();
   userType: string ='user';
   UserAvailability: string ='';
  ngOnInit(): void {

  }
constructor( private userService: UserService , private router: Router )
{

}
  newUsers(): void
  {
    this.user = new Users();

  }

  addUser(){

    this.userService.signup(this.user).subscribe(
      user=>{
          alert(JSON.stringify(user));
          if('User signup successfully' === user)
          {
          this.user=new Users();
          this.router.navigate(['/home']);
          }
          this.UserAvailability = user;
      },
      error=>console.log(error)

    );
  }

  Onsignup(): void{
    this.addUser();
  }

  myFunction() {

        };

}
