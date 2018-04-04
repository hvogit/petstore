import {Component, OnInit, Input} from '@angular/core';
import {Pet} from '../../models/pet.model';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html'
})
export class LayoutComponent implements OnInit {
  @Input() showDelete = true;
  @Input() pets$: Observable<Pet[]>;

  constructor() {
  }

  ngOnInit() {
  }

}
