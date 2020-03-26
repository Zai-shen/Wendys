export class Horse {
  constructor(
    public name: string,
    public rating: number,
    public birthDay: Date,
    public breed: string,
    public imageURI: string,
    public id?: number,
    public createdAt?: Date,
    public updatedAt?: Date,
    public description?: string, // optional
    public ownerId?: number// optional
  ) {
  }

  getFieldsString?(): string {
    return 'Horse{ ' + 'id=' + (this.id?this.id:'null') + ', createdAt=' + (this.createdAt?this.createdAt:'null')
      + ', updatedAt=' + (this.updatedAt?this.updatedAt:'null')
      + ', name=' + this.name + ', description=' + (this.description?this.description:'null') + ', rating=' + this.rating
      + ', birthday=' + this.birthDay + ', breed=' + this.breed
      + ', imageURI=' + (this.imageURI ? 'true' : 'false') + ', ownerId=' + (this.ownerId?this.ownerId:'null') + ' }';
  }
}
