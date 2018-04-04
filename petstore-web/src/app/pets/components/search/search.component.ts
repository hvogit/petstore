import { Component, OnInit } from '@angular/core';
import {Pet, PetStatuses} from '../../models/pet.model';
import {PetService} from '../../services/pet.service';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html'
})
export class SearchComponent implements OnInit {
  pet = new Pet();
  statuses = PetStatuses;

  pets$: Observable<Pet[]>;

  constructor(private petService: PetService) { }

  ngOnInit() {
  }

  search() {
    this.pets$ = this.petService.search(this.pet);
  }
}
