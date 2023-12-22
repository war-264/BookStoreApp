import { Component, OnInit } from '@angular/core';
import { LoginComponent } from '../Login/login.component';
import { UserService } from '../User/UserService/userservice.components';

@Component({
  
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']

})
export class HomepageComponent implements OnInit  {


   userName : any='';
  ngOnInit(): void {
    
   this.userName = sessionStorage.getItem('userType')=='admin'?'Admin':sessionStorage.getItem('userName') ;
   console.log(this.userName);
     
  }


  

}
