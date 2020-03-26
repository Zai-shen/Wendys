import { Component, OnInit, Input } from '@angular/core';

import {Horse} from '../../dto/horse';

@Component({
  selector: 'app-horse-detail',
  templateUrl: './horse-detail.component.html',
  styleUrls: ['./horse-detail.component.scss']
})
export class HorseDetailComponent implements OnInit {

  @Input() detailHorse: Horse;
  validBreeds: string[] = ['arabian', 'morgan', 'paint', 'appaloosa'];

  constructor() { }

  ngOnInit(): void {
  }

}
