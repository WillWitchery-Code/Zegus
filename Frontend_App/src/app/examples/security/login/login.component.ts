import { Component, OnInit } from '@angular/core';
import { SecurityService } from '../../../services/security.service';
import { Router } from '@angular/router';
import { User } from 'app/models/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {

    data : Date = new Date();
    e_mail:string="";
    password:string="";
    focus;
    focus1;
    
    constructor(private securityService: SecurityService,
        private router: Router) { }

        login():void{  
          let newUser:User={
            e_mail:this.e_mail,
            password:this.password
            
          }
          this.securityService.login(newUser).subscribe(
            data=>{
              this.router.navigate(['pages/dashboard']);
              this.securityService.saveDataSession(data);
            }
      );
  }
    
    ngOnInit(): void {
      var body = document.getElementsByTagName('body')[0];
        body.classList.add('login-page');

        var navbar = document.getElementsByTagName('nav')[0];
        navbar.classList.add('navbar-transparent');
    }
                
    ngOnDestroy(){
        var body = document.getElementsByTagName('body')[0];
        body.classList.remove('login-page');

        var navbar = document.getElementsByTagName('nav')[0];
        navbar.classList.remove('navbar-transparent');
    }

}
