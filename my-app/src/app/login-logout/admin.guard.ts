import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { routerNgProbeToken } from '@angular/router/src/router_module';

@Injectable()
export class AdminGuard implements CanActivate {

  constructor(private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if(this.isAdmin()) {
        return true;
    } else {
        this.router.navigate(['/login']);
        return false;
    }
  }

  isAdmin() {
    if (sessionStorage.getItem('currentUser')) {
      var user = JSON.parse(sessionStorage.getItem('currentUser'));
      return user.role == "ADMIN";
    }
    return false;
  }

  isLoggedIn() {
      return sessionStorage.getItem('currentUser') != null;
  }
}