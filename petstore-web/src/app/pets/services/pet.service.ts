import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {environment} from '../../../environments/environment';
import {Pet} from '../models/pet.model';
import {Store} from '@ngrx/store';
import {AppStore} from '../../app-store';
import 'rxjs/add/operator/map';
import {ADD_PET, DELETE_PET, LOAD_PETS} from '../reducers/pet.reducer';
import {Router, ActivatedRoute} from '@angular/router';
import {HttpParams} from '@angular/common/http';

@Injectable()
export class PetService {
  pets$: Observable<Pet[]> = this.store.select('pets');
  readonly apiUrl = environment.API_ENDPOINT + '/pets';

  constructor(private http: HttpClient, private store: Store<AppStore>,
              private route: ActivatedRoute, private router: Router) {
  }

  public load() {
    return this.http.get<Pet[]>(this.apiUrl)
      .map(payload => ({type: LOAD_PETS, payload}))
      .subscribe(action => this.store.dispatch(action));
  }

  public search(pet: Pet) {
    let params = new HttpParams();
    if (pet.name) {
      params = params.set('name', pet.name);
    } else {
      params = params.set('status', pet.status);
    }
    return this.http.get<Pet[]>(this.apiUrl, {params: params});
  }

  public add(pet: Pet) {
    return this.http.post(this.apiUrl, pet)
      .map(payload => ({type: ADD_PET, payload}))
      .subscribe(action => {
        this.store.dispatch(action);
        this.router.navigateByUrl('/pets/list');
      });
  }

  public delete(pet: Pet) {
    return this.http.delete(`${this.apiUrl}/${pet.id}`)
      .map(noContent => ({type: DELETE_PET, payload: pet}))
      .subscribe(action => this.store.dispatch(action));
  }

}

