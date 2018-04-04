import {Component, OnInit, Input} from '@angular/core';
import {AuthService} from '../../../core/services/auth.service';
import {PetService} from '../../services/pet.service';

@Component({
  selector: 'app-pet',
  templateUrl: './pet.component.html'
})
export class PetComponent implements OnInit {
  @Input() pet;
  @Input() showDelete = false;

  constructor(public authService: AuthService, private petService: PetService) {
  }

  ngOnInit() {
  }

  delete() {
    this.petService.delete(this.pet);
  }
}
