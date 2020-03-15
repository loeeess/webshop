import { Injectable } from '@angular/core';
import { Product } from '../webshop/product.model';
import { BuyProduct } from '../webshop/buyproduct.model';

@Injectable({
  providedIn: 'root'
})
export class ShopCartService {
  products: BuyProduct[] = [];

  constructor() { }

  getProducts() {
    //q 3console.log(this.products)
    return this.products.slice()
  }

  deleteProduct(index: number) {
    this.products.splice(index, 1);
  }

  push(push_product: BuyProduct) {

    for(let product of this.products) {
      if(product.product.id == push_product.product.id) {
        var new_amount = Number(product.amount) + Number(push_product.amount);
        product.amount = new_amount;
        return;
      }
    }

    this.products.push(push_product);

    console.log(this.products);
  }
}
