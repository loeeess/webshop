import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthenticationService } from './login-logout/authentication.service';
import { HttpClientModule } from '@angular/common/http';
import { WebshopComponent } from './webshop/webshop.component';
import { AuthGuard } from './login-logout/auth.guard';
import { AdminGuard } from './login-logout/admin.guard';
import { WebshopListComponent } from './webshop/webshop-list/webshop-list.component';
import { ApiService } from './shared/api.service';
import { WebshopItemComponent } from './webshop/webshop-list/webshop-item/webshop-item.component';
import { ProductService } from './webshop/product.service';
import { ProductResolver } from './webshop/product.resolver';
import { BuyItemComponent } from './webshop/buy-item/buy-item.component';
import { ShopCartComponent } from './shop-cart/shop-cart.component';
import { ShopCartListComponent } from './shop-cart/shop-cart-list/shop-cart-list.component';
import { HeaderComponent } from './header/header.component';
import { AdminComponent } from './admin/admin.component';

@NgModule({
  declarations: [
    AppComponent,
    MainMenuComponent,
    LoginComponent,
    WebshopComponent,
    WebshopListComponent,
    WebshopItemComponent,
    BuyItemComponent,
    ShopCartComponent,
    ShopCartListComponent,
    HeaderComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [
    AuthenticationService,
    AdminGuard,
    AuthGuard,
    ApiService,
    ProductService,
    ProductResolver,
    ProductService,
    ProductResolver
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
