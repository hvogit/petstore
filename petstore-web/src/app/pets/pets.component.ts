import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  template: `
    <app-menu></app-menu>
    <router-outlet></router-outlet>
  `,
})
export class PetsComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
