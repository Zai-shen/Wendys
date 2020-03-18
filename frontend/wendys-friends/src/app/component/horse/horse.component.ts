import { Component, OnInit } from '@angular/core';

import {Horse} from '../../dto/horse';
import {HorseService} from '../../service/horse.service';
import {HORSES} from './exampleHorses';

@Component({
  selector: 'app-horse',
  templateUrl: './horse.component.html',
  styleUrls: ['./horse.component.scss']
})

export class HorseComponent implements OnInit {

  error = false;
  errorMessage = '';
  horse: Horse;
  horses = HORSES;
  testHorse: Horse;
  selectedHorse: Horse;

  constructor(private horseService: HorseService) {
  }

  ngOnInit() {
    this.loadHorse(103);
  }

  onSelect(horse: Horse): void {
    this.selectedHorse = horse;
  }

  /**
   * Error flag will be deactivated, which clears the error message
   */
  vanishError() {
    this.error = false;
  }

  /**
   * Loads the owner for the specified id
   * @param id the id of the owner
   */
  private loadHorse(id: number) {
    this.horseService.getHorseById(id).subscribe(
      (horse: Horse) => {
        this.horse = horse;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  private defaultServiceErrorHandling(error: any) {
    console.log(error);
    this.error = true;
    if (error.status === 0) {
      // If status is 0, the backend is probably down
      this.errorMessage = 'The backend seems not to be reachable';
    } else if (error.error.message === 'No message available') {
      // If no detailed error message is provided, fall back to the simple error name
      this.errorMessage = error.error.error;
    } else {
      this.errorMessage = error.error.message;
    }
  }

}

