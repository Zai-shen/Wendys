export class Horse {
  constructor(
    public id: number,
    public name: string,
    public description: string, // public description?: string, <- optional param
    public birthDay: Date,
    public breed: string,
    public imageURI: string,
    public createdAt: Date,
    public updatedAt: Date) {
  }
}
