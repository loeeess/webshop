import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainMenuComponent } from './main-menu/main-menu.component';
import { LoginComponent } from './login/login.component';
import { WebshopComponent } from './webshop/webshop.component';
import { AuthGuard } from './login-logout/auth.guard';
import { WebshopListComponent } from './webshop/webshop-list/webshop-list.component';
import { WebshopItemComponent } from './webshop/webshop-list/webshop-item/webshop-item.component';
import { ProductResolver } from './webshop/product.resolver';
import { BuyItemComponent } from './webshop/buy-item/buy-item.component';
import { ShopCartComponent } from './shop-cart/shop-cart.component';
import { ShopCartListComponent } from './shop-cart/shop-cart-list/shop-cart-list.component';
import { AdminGuard } from './login-logout/admin.guard';
import { AdminComponent } from './admin/admin.component';

const routes: Routes = [
  {path: '', component: MainMenuComponent },
  {path: 'login', component: LoginComponent},
  {path: 'shop', component: WebshopComponent, canActivate: [AuthGuard], children: [
    {path: '', component: WebshopListComponent},
    {path: ':id', component: BuyItemComponent, resolve: {item: ProductResolver}}
  ]},
  {path: 'cart', component: ShopCartComponent, children: [
    {path: '', component: ShopCartListComponent}
  ]},
  {path: 'admin', component: AdminComponent, canActivate: [AdminGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
