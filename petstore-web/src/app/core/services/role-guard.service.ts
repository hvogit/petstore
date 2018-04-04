import {Injectable} from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {AuthService} from './auth.service';

@Injectable()
export class RoleGuardService implements CanActivate {

  constructor(public authService: AuthService, public router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

    const role = route.data.role;

    if (!this.authService.isAuthenticated() || !this.authService.hasRole(role)) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
