import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { User } from '../../model/model.user';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component( {
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class LoginComponent implements OnInit {
  user: User = new User();
  errorMessage: string;
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  login() {
    this.authService.authenticate(this.user, (e) => {
      let resp: any;
      resp = e.principal;
      if( resp !== undefined ){
        this.router.navigateByUrl('/profile');
        if ( resp  ) {
          localStorage.setItem('currentUser', JSON.stringify(resp));
        } 
      }
      else {
        this.errorMessage = 'Error, make sure your email & password are right';
      }
    });
  }
}
