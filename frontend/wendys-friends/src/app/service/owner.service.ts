import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
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

  // US-0
  /**
   * Loads specific owner from the backend
   * @param id of owner to load
   */
  getOwnerById(id: number): Observable<Owner> {
    console.log('Load owner details for ' + id);
    return this.httpClient.get<Owner>(this.messageBaseUri + '/' + id);
  }

  // US-6
  /**
   * Loads specific owner from the backend
   * @param id of owner to load
   */
  postOwner(owner: Owner): Observable<Owner> {
    console.log('Post ' + owner.name);
    return this.httpClient.post<Owner>(this.messageBaseUri, owner);
  }

  // US-7
  /**
   * Save specific owner to the backend
   * @param id of owner to post
   * @package owner to be posted
   */
  putOwner(id: number, owner: Owner): Observable<Owner> {
    console.log('Put ' + 'owner');
    return this.httpClient.put<Owner>(this.messageBaseUri + '/' + id, owner)
  }

  // US-8
  /**
   * Delete the owner from the server
   * @param id of owner to be deleted
   */
  deleteOwner(id: number): Observable<{}> {
    console.log('Delete ' + 'owner/' + id);
    const url = `${this.messageBaseUri}/${id}`;
    return this.httpClient.delete(url);
  }

  // US-9, 10
  /**
   * Loads all owners from the backend
   */
  getAllOwners(): Observable<Owner[]> {
    console.log('Load all owners ');
    return this.httpClient.get<Owner[]>(this.messageBaseUri);
  }

  // US-9, 10
  /**
   * Loads all filtered owner from the backend
   * @param owner params to filter by
   */
  getAllOwnersFiltered(owner: Owner): Observable<Owner[]> {
    console.log('Load all owners filtered by ' + owner.name);

    if (owner.name === undefined) {
      owner.name = null;
    }

    const options =
      {
        params: new HttpParams().set('name', owner.name)
      };
    return this.httpClient.get<Owner[]>(this.messageBaseUri, options);
  }
}
