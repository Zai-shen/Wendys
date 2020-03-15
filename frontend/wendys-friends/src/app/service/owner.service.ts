import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Globals} from '../global/globals';
import {Observable} from 'rxjs';
import {Owner} from '../dto/owner';

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  private messageBaseUri: string = this.globals.backendUri + '/owners';

  constructor(private httpClient: HttpClient, private globals: Globals) {
  }

  /**
   * Loads specific owner from the backend
   * @param id of owner to load
   */
  getOwnerById(id: number): Observable<Owner> {
    console.log('Load owner details for ' + id);
    return this.httpClient.get<Owner>(this.messageBaseUri + '/' + id);
  }

}
