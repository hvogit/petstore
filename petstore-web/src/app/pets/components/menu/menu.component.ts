import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../core/services/auth.service';
import {Router, ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html'
})
export class MenuComponent implements OnInit {

  constructor(private route: ActivatedRoute, public authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  public isAdmin(): boolean {
    return this.authService.hasRoleAdmin();
  }

  public logout(): void {
    this.authService.logout();
    this.router.navigate(['../login'], {relativeTo: this.route});
  }

}
