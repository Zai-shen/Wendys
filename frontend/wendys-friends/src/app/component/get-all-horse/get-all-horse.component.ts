import {Component, Input, OnInit} from '@angular/core';

import {Horse} from '../../dto/horse';

@Component({
  selector: 'app-get-all-horse',
  templateUrl: './get-all-horse.component.html',
  styleUrls: ['./get-all-horse.component.scss']
})
export class GetAllHorseComponent implements OnInit {

  @Input() horses: Horse[];
  status: boolean;

  constructor() { }

  ngOnInit(): void {
  }

}
