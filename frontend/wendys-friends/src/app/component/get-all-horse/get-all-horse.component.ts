import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

import {Horse} from '../../dto/horse';

@Component({
  selector: 'app-get-all-horse',
  templateUrl: './get-all-horse.component.html',
  styleUrls: ['./get-all-horse.component.scss']
})
export class GetAllHorseComponent implements OnInit {

  @Input() horses: Horse[];
  @Output() childEvent = new EventEmitter<number>();
  status: boolean;
  selectedHorse: Horse;
  selectedIndex: number;
  @Input() validBreeds: string[];

  constructor() { }

  ngOnInit(): void {
  }

  onClickDeleteHorse(horse: Horse){
    this.horses.splice(this.horses.indexOf(horse),1);
    this.childEvent.emit(horse.id);
  }

  onClickPutHorse(sIndex: number, horse: Horse){
    this.selectedHorse = horse;
    this.selectedIndex = sIndex;
  }

}
