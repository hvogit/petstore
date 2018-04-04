import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ListComponent} from './components/list/list.component';
import {SearchComponent} from './components/search/search.component';
import {AddComponent} from './components/add/add.component';
import {PetsComponent} from './pets.component';
import {AuthGuardService} from '../core/services/auth-guard.service';
import {RoleGuardService} from '../core/services/role-guard.service';

const routes: Routes = [
  { path: 'pets', component: PetsComponent, canActivate: [AuthGuardService],
    children: [
      {path: 'list', component: ListComponent},
      {path: 'add', component: AddComponent, canActivate: [RoleGuardService], data: {role: 'ROLE_ADMIN'}},
      {path: 'search', component: SearchComponent},
      {path: '', redirectTo: 'list', pathMatch: 'full'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PetsRoutingModule { }
