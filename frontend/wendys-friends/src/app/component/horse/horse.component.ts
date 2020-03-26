import {Component, OnInit} from '@angular/core';

import {Horse} from '../../dto/horse';
import {HorseService} from '../../service/horse.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-horse',
  templateUrl: './horse.component.html',
  styleUrls: ['./horse.component.scss']
})

export class HorseComponent implements OnInit {

  error = false;
  errorMessage = '';
  horse: Horse;
  horses: Horse[];
  searchHorse: Horse = new Horse(null, null, null, null, null);
  selectedHorse: Horse;
  validBreeds: string[] = ['arabian', 'morgan', 'paint', 'appaloosa'];
  exampleHorse: Horse = new Horse('Horsename',1,new Date(),'paint','image');
  exampleHorses: Horse[] = [this.exampleHorse,this.exampleHorse,this.exampleHorse,this.exampleHorse,this.exampleHorse];

  constructor(private horseService: HorseService) {
  }

  ngOnInit() {
    this.loadHorse(1);
    this.loadAllHorsesFiltered(null);
  }

  onClickSearchFiltered(horse: Horse):void{
    this.loadAllHorsesFiltered(horse);
  }

  onSelect(horse: Horse): void {
    this.selectedHorse = horse;
  }

  onClickPostHorse(horse: Horse): void {
    this.horses.push(horse);
    this.postNewHorse(horse);
  }

  onClickDeleteHorse(id:number):void{
    this.deleteHorse(id);
  }

  /**
   * Error flag will be deactivated, which clears the error message
   */
  vanishError() {
    this.error = false;
  }

  // US-0
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

  // US-1
  private postNewHorse(horse: Horse): void {
    this.horseService.postHorse(horse).subscribe(
      data => console.log('Success posting!', data),
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  // US-4
  private deleteHorse(id:number):void{
    this.horseService
      // .deleteHorse(hero.id)
      .deleteHorse(id)
      .subscribe();
  }

  // // US-5
  // private loadAllHorsesFiltered(): void {
  //
  //   this.horseService.getAllHorsesFiltered().subscribe(
  //     horses => this.horses = horses);
  // }

  // US-5
  private loadAllHorsesFiltered(horse: Horse): void {
    if (horse == null) {
      this.horseService.getAllHorses().subscribe(
        horses => this.horses = horses);
    } else {
      this.horseService.getAllHorsesFiltered(horse).subscribe(
        horses => this.horses = horses);
    }
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

