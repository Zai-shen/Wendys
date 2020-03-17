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

  /**
   * Loads specific horse from the backend
   * @param id of horse to load
   */
  getHorseById(id: number): Observable<Horse> {
    console.log('Load horse details for ' + id);
    return this.httpClient.get<Horse>(this.messageBaseUri + '/' + id);
  }

}
