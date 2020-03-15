import { Injectable } from "@angular/core";
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { Product } from './product.model';
import { ProductService } from './product.service';

@Injectable()
export class ProductResolver implements Resolve<Observable<Product>> {
    constructor(private productService: ProductService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Observable<Product>> | Promise<Observable<Product>> | Observable<Product> {
        console.log("Resolver: ", route.params.id);
        return this.productService.fetchProduct(route.params.id);
    }
}