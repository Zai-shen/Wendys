import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

import {Horse} from '../../dto/horse';

@Component({
  selector: 'app-get-all-horse',
  templateUrl: './get-all-horse.component.html',
  styleUrls: ['./get-all-horse.component.scss']
})
export class GetAllHorseComponent implements OnInit {

  @Input() horses: Horse[];
  status: boolean;
  @Output() childEvent = new EventEmitter<number>();

  constructor() { }

  ngOnInit(): void {
  }

  onClickDeleteHorse(id: number){
    this.childEvent.emit(id);
  }

}
