
export class Pet {
  id: number;
  name: string;
  status: 'available' | 'pending' | 'sold';
  description: string;

  constructor() {
    this.status = 'available';
  }
}

export const PetStatuses = ['available', 'pending', 'sold'];
