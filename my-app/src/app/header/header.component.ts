import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../login-logout/authentication.service';
import { Router } from '@angular/router';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { AdminGuard } from '../login-logout/admin.guard';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private authService: AuthenticationService, private router: Router, private adminGuard: AdminGuard) { }

  ngOnInit() {
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  isAdmin() {
    return this.adminGuard.isAdmin();
  }

  isLoggedIn() {
    return this.adminGuard.isLoggedIn();
  }
}
