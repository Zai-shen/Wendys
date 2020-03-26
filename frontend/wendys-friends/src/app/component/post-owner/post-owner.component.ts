import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Owner} from '../../dto/owner';

@Component({
  selector: 'app-post-owner',
  templateUrl: './post-owner.component.html',
  styleUrls: ['./post-owner.component.scss']
})
export class PostOwnerComponent implements OnInit {

  @Input() validBreeds: string[];
  toPostOwner: Owner = new Owner('');
  @Output() childEvent = new EventEmitter<Owner>();

  constructor() { }

  ngOnInit(): void {
  }

  onClickPostOwner(owner: Owner){
    this.childEvent.emit(owner);
  }

}
