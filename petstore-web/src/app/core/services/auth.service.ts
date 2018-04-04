import {Injectable} from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {environment} from '../../../environments/environment';


@Injectable()
export class AuthService {

  constructor(private jwtHelper: JwtHelperService, private http: HttpClient, private router: Router) {
  }

  public login(username: string, password: string) {
    const body = new URLSearchParams();
    body.set('grant_type', 'password');
    body.set('username', username);
    body.set('password', password);

    const options = {
      headers: new HttpHeaders ({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic ' + btoa('rbc:rbc')
      })
    };
    return this.http.post(environment.AUTH_ENDPOINT, body.toString(), options)
      .subscribe(
        (data: any) => {
          console.log(data);
          localStorage.setItem(environment.ACCESS_TOKEN, data.access_token);
          localStorage.setItem(environment.REFRESH_TOKEN, data.refresh_token);
          return this.router.navigateByUrl('/pets');
        }
      );
  }

  public isAuthenticated(): boolean {
    const tokenStr = localStorage.getItem(environment.ACCESS_TOKEN);
    return !this.jwtHelper.isTokenExpired(tokenStr);
  }

  public hasRoleAdmin(): boolean {
    return this.hasRole('ROLE_ADMIN');
  }

  public hasRoleUser(): boolean {
    return this.hasRole('ROLE_USER');
  }

  public hasRole(role: string): boolean {
    const tokenStr = localStorage.getItem(environment.ACCESS_TOKEN);
    const token = tokenStr && this.jwtHelper.decodeToken(tokenStr);
    return token && token.authorities && token.authorities.indexOf(role.toUpperCase()) > -1;
  }

  public logout(): void {
    localStorage.removeItem(environment.ACCESS_TOKEN);
    localStorage.removeItem(environment.REFRESH_TOKEN);
    this.router.navigateByUrl('/login');
  }
}
