import {Component, OnInit, Input} from '@angular/core';

import {Horse} from '../../dto/horse';
import {HorseService} from '../../service/horse.service';

@Component({
  selector: 'app-horse-detail',
  templateUrl: './horse-detail.component.html',
  styleUrls: ['./horse-detail.component.scss']
})
export class HorseDetailComponent implements OnInit {

  @Input() detailHorse: Horse;
  @Input() validBreeds: string[];

  constructor(private horseService: HorseService) {
  }

  ngOnInit() {
  }

  onClickPutHorse(id:number, horse: Horse) :void{
    horse.id = null;
    horse.updatedAt = null;
    horse.createdAt = null;
    if (horse.ownerId === 0){
      horse.ownerId = null;
    }
    this.putHorse(id,horse);
    this.detailHorse = null;
  }

  // US-3
  private putHorse(id: number, horse: Horse){
    this.horseService.putHorse(id,horse).subscribe(
      data => console.log('Success putting!', data),
      error => {
        console.log(error);
      }
    );
  }


}
