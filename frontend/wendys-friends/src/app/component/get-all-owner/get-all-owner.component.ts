import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Owner} from '../../dto/owner';
import {Horse} from '../../dto/horse';

@Component({
  selector: 'app-get-all-owner',
  templateUrl: './get-all-owner.component.html',
  styleUrls: ['./get-all-owner.component.scss']
})
export class GetAllOwnerComponent implements OnInit {

  @Input() owners: Owner[];
  @Output() childEvent = new EventEmitter<number>();
  status: boolean;
  selectedOwner: Owner;
  selectedIndex: number;

  constructor() { }

  ngOnInit(): void {
  }

  onClickDeleteOwner(owner: Owner){
    this.owners.splice(this.owners.indexOf(owner),1);
    this.childEvent.emit(owner.id);
  }

  onClickPutOwner(sIndex: number, owner: Owner){
    this.selectedOwner = owner;
    this.selectedIndex = sIndex;
  }

  getFieldsString(horse: Horse): string {
    return 'Horse{ ' + 'id=' + (horse.id?horse.id:'null') + ', createdAt=' + (horse.createdAt?horse.createdAt:'null')
      + ', updatedAt=' + (horse.updatedAt?horse.updatedAt:null)
      + ', name=' + horse.name + ', description=' + (horse.description?horse.description:'null') + ', rating=' + horse.rating
      + ', birthday=' + horse.birthDay + ', breed=' + horse.breed
      + ', imageURI=' + (horse.imageURI ? 'true' : 'false') + ', ownerId=' + (horse.ownerId?horse.ownerId:'null') + ' }';
  }

}
