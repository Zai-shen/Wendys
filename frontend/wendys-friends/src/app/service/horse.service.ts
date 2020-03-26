import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
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
  /**
   * Loads specific horse from the backend
   * @param id of horse to load
   */
  postHorse(horse: Horse): Observable<Horse> {
    console.log('Post ' + horse.getFieldsString());
    return this.httpClient.post<Horse>(this.messageBaseUri, horse);
  }

  // US-3
  /**
   * Save specific horse to the backend
   * @param id of horse to post
   * @package horse to be posted
   */
  putHorse(id: number, horse: Horse): Observable<Horse> {
    console.log('Put ' + 'horse'); // horse.getFieldsString());
    return this.httpClient.put<Horse>(this.messageBaseUri + '/' + id, horse)
  }

  // US-4
  /**
   * Delete the horse from the server
   * @param id of horse to be deleted
   */
  deleteHorse(id: number): Observable<{}> {
    console.log('Delete ' + 'horse/' + id);
    const url = `${this.messageBaseUri}/${id}`;
    return this.httpClient.delete(url);
  }

  // US-5
  /**
   * Loads all horses from the backend
   */
  getAllHorses(): Observable<Horse[]> {
    console.log('Load all horses ');
    return this.httpClient.get<Horse[]>(this.messageBaseUri);
  }

  // US-5
  /**
   * Loads all filtered horse from the backend
   * @param horse params to filter by
   */
  getAllHorsesFiltered(horse: Horse): Observable<Horse[]> {
    console.log('Load all horses filtered by ' + horse.getFieldsString());
    let ratingString: string = null;
    let birthDayString: string = null;

    if (horse.name === undefined) {
      horse.name = null;
    }

    if (horse.description === undefined || horse.description.length === 0) {
      horse.description = null;
    }

    if (horse.rating === undefined || horse.rating === null) {
      ratingString = null;
    } else {
      ratingString = horse.rating.toString();
    }

    if (horse.breed === undefined) {
      horse.breed = null;
    }
    if (horse.birthDay === undefined || horse.birthDay === null) {
      birthDayString = null;
    } else {
      birthDayString = horse.birthDay.toString();
    }

    const options =
      {
        params: new HttpParams().set('name', horse.name)
          .set('description', horse.description)
          .set('rating', ratingString)
          .set('breed', horse.breed)
          .set('birthDay', birthDayString)
      };
    return this.httpClient.get<Horse[]>(this.messageBaseUri, options);
  }

}
