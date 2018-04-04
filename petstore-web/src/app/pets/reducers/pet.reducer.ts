import {Pet} from '../models/pet.model';
import {Action} from '@ngrx/store';

export const LOAD_PETS = 'LOAD_PETS';
export const ADD_PET = 'ADD_PET';
export const UPDATE_PET = 'UPDATE_PET';
export const DELETE_PET = 'DELETE_PET';

export function pets (state: Pet[] = [], action: Action) {
  switch (action.type) {

    case LOAD_PETS:
      return action.payload;

    case ADD_PET:
      return [...state, action.payload];

    case UPDATE_PET:
      return state.map(pet => {
        return pet.id === action.payload.id ? Object.assign({}, pet, action.payload) : pet;
      });

    case DELETE_PET:
      return state.filter(pet => {
        return pet.id !== action.payload.id;
      });

    default:
      return state;
  }
}
