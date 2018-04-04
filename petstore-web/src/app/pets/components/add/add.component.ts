import { Component, OnInit } from '@angular/core';
import {PetService} from '../../services/pet.service';
import {Pet, PetStatuses} from '../../models/pet.model';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html'
})
export class AddComponent implements OnInit {
  pet = new Pet();
  statuses = PetStatuses;

  constructor(private petService: PetService ) { }

  ngOnInit() {
  }

  add() {
    this.petService.add(this.pet);
  }

}
