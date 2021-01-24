import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import {sha256} from 'js-sha256';
import { environment } from 'src/environments/environment';

@Injectable()
export class AuthenticationService {
  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    const passwordHashed = sha256(password);

    return this.http.post<any>(environment.apiUrl + '/login', { username: username, password: passwordHashed })
      .pipe(map(user => {
        // login successful
        if (user !== null) {
          // store user details in local storage to keep user logged in between page refreshes
          sessionStorage.setItem('currentUser', JSON.stringify(user));
          sessionStorage.setItem('password', passwordHashed);
          sessionStorage.setItem('username', username);
        }
        return user;
      }));
    }

  logout() {
    // remove user from local storage to log user out
    sessionStorage.removeItem('currentUser');
    sessionStorage.removeItem('password');
  }
}
