import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../global/globals';
import {Observable} from 'rxjs';
import {Horse} from '../dto/horse';

@Injectable({
  providedIn: 'root'
})
export class HorseService {

  private messageBaseUri: string = this.globals.backendUri + '/horses';

  constructor(private httpClient: HttpClient, private globals: Globals) {
  }

  // US-0
  /**
   * Loads specific horse from the backend
   * @param id of horse to load
   */
  getHorseById(id: number): Observable<Horse> {
    console.log('Load horse details for ' + id);
    return this.httpClient.get<Horse>(this.messageBaseUri + '/' + id);
  }

  // US-1
  postHorse(horse: Horse): Observable<Horse> {
    console.log('Post ' + horse.getFieldsString());
    return this.httpClient.post<Horse>(this.messageBaseUri, horse);
  }

  // US-3
  putHorse (id:number, horse: Horse): Observable<Horse> {
    console.log('Put ' + horse.getFieldsString());
    return this.httpClient.put<Horse>(this.messageBaseUri + '/' + id, horse)
  }

  // US-5
  getAllHorsesFiltered(): Observable<Horse[]>{
    console.log('Load all horses filtered by ');
    return this.httpClient.get<Horse[]>(this.messageBaseUri);
  }

}
