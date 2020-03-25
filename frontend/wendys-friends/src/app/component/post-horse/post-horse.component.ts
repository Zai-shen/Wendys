import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

import {Horse} from '../../dto/horse';

@Component({
  selector: 'app-post-horse',
  templateUrl: './post-horse.component.html',
  styleUrls: ['./post-horse.component.scss']
})
export class PostHorseComponent implements OnInit {

  @Input() validBreeds: string[];
  toPostHorse: Horse = new Horse('', 1, null, 'paint', 'noimage');
  @Output() childEvent = new EventEmitter<Horse>();

  constructor() { }

  ngOnInit(): void {
  }

  onClickPostHorse(horse: Horse){
    this.childEvent.emit(horse);
  }

}
