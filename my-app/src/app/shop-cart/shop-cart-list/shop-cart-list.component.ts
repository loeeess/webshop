import { Component, OnInit } from '@angular/core';
import { ShopCartService } from '../shop-cart.service';
import { Product } from 'src/app/webshop/product.model';
import { BuyProduct } from 'src/app/webshop/buyproduct.model';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-shop-cart-list',
  templateUrl: './shop-cart-list.component.html',
  styleUrls: ['./shop-cart-list.component.css']
})
export class ShopCartListComponent implements OnInit {
  products: BuyProduct[] = [];
  totalPrice: number = 0;

  forms: FormGroup[] = [];
  constructor(private shopcart: ShopCartService, private fb: FormBuilder) { }

  ngOnInit() {
    this.products = this.shopcart.getProducts();
    console.log(this.products);
    this.updatePrice();

    for(const product of this.products) {
      this.forms.push(this.fb.group({
        'amount': product.amount
      }));
    }
  }

  onSubmit(index) {
    var value = Number(this.forms[index].value['amount']);
    if (value < 1) {
      alert("The amount must be greater than 0!. If you wish to delete this purchase, click the `delete` button.");
      this.forms[index].setValue({
        'amount': 1
      });
      //alert("The amount must be greater than 0!. If you wish to delete this purchase, click the `delete` button.");
      return;
    }
    this.products[index].amount = value;
    this.shopcart.products = this.products;
    this.update();
  }

  deleteProduct(index: number) {
    this.shopcart.deleteProduct(index);
    this.update();
  }

  update() {
    this.products = this.shopcart.getProducts();
    this.updatePrice();
  }

  updatePrice() {
    this.totalPrice = 0;
    for (const product of this.products) {
      this.totalPrice += product.product.price * product.amount;
    }
  }
}
