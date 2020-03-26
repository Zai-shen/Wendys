import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Owner} from '../../dto/owner';

@Component({
  selector: 'app-search-owner',
  templateUrl: './search-owner.component.html',
  styleUrls: ['./search-owner.component.scss']
})
export class SearchOwnerComponent implements OnInit {

  searchOwner: Owner = new Owner(null)
  @Output() childEvent = new EventEmitter<Owner>();

  constructor() { }

  ngOnInit(): void {
  }

  onClickSearchFiltered(owner: Owner){
    this.childEvent.emit(owner);
  }

}
