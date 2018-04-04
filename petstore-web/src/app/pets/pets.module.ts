import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PetsRoutingModule } from './pets-routing.module';
import { ListComponent } from './components/list/list.component';
import { AddComponent } from './components/add/add.component';
import { SearchComponent } from './components/search/search.component';
import { MenuComponent } from './components/menu/menu.component';
import { PetsComponent } from './pets.component';
import {PetService} from './services/pet.service';
import { PetComponent } from './components/pet/pet.component';
import {FormsModule} from '@angular/forms';
import {pets} from './reducers/pet.reducer';
import {StoreModule} from '@ngrx/store';
import { LayoutComponent } from './components/layout/layout.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    PetsRoutingModule,
    StoreModule.provideStore({pets})
  ],
  declarations: [
    ListComponent, AddComponent, SearchComponent,
    MenuComponent, PetsComponent, PetComponent, LayoutComponent
  ],
  providers: [ PetService ]
})
export class PetsModule { }
