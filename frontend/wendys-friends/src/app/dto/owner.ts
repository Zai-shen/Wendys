import {Horse} from './horse';

export class Owner {
  constructor(
    public name: string,
    public id?: number,
    public createdAt?: Date,
    public updatedAt?: Date,
    public ownedHorses?: Horse[]
  ) {
  }
}
