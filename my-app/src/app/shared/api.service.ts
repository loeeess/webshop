import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class ApiService {

    constructor(private httpClient: HttpClient) {
    }

    createURI(path: string) {
        return environment.apiUrl + path;
    }

    createHeaders(): HttpHeaders {
      return new HttpHeaders(
          {
            'Authorization': 'basic ' + btoa(sessionStorage.getItem('username') + ':' + sessionStorage.getItem('password'))
          });
    }

    getById<T>(path: string, id: number): Observable<T> {
        const uri = this.createURI(path) + '/' + id;
        const headers = this.createHeaders();
        return this.httpClient.get<T>(uri, { headers: headers});
    }

    get<T>(path: string): Observable<T> {
        const uri = this.createURI(path);
        const headers = this.createHeaders();
        return this.httpClient.get<T>(uri, { headers: headers });
    }

    post<T>(path: string, model: T):  Observable<T> {
        const uri = this.createURI(path);
        const headers = this.createHeaders();
        return this.httpClient.post<T>(uri, model, { headers: headers });
    }

    put<T>(path: string, id: number, model: T):  Observable<T> {
        const uri = this.createURI(path) + '/' + id;
        const headers = this.createHeaders();
        return this.httpClient.put<T>(uri, model, { headers: headers });
    }

    delete<T>(path: string, id: number): Observable<T> {
      const uri = this.createURI(path) + '/' + id;
      const headers = this.createHeaders();
      return this.httpClient.delete<T>(uri, { headers });
    }
}