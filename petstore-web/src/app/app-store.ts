import {Pet} from './pets/models/pet.model';

export interface AppStore {
  pets: Pet[];
}
