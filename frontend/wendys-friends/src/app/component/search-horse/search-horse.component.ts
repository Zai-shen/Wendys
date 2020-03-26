import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Horse} from '../../dto/horse';

@Component({
  selector: 'app-search-horse',
  templateUrl: './search-horse.component.html',
  styleUrls: ['./search-horse.component.scss']
})
export class SearchHorseComponent implements OnInit {

  searchHorse: Horse = new Horse(null, null, null, null, null);
  @Input() validBreeds: string[];
  @Output() childEvent = new EventEmitter<Horse>();

  constructor() { }

  ngOnInit(): void {
  }

  onClickSearchFiltered(horse: Horse){
    this.childEvent.emit(horse);
  }

}
