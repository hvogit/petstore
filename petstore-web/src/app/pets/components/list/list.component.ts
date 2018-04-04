import { Component, OnInit } from '@angular/core';
import {PetService} from '../../services/pet.service';
import {Observable} from 'rxjs/Observable';
import {Pet} from '../../models/pet.model';
import {AuthService} from '../../../core/services/auth.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html'
})
export class ListComponent implements OnInit {

  pets$: Observable<Pet[]>;

  constructor(private petService: PetService, private authService: AuthService) {
  }

  ngOnInit() {
    this.pets$ = this.petService.pets$;
    this.petService.load();
  }

  public delete(pet: Pet) {
    this.petService.delete(pet);
  }
}
