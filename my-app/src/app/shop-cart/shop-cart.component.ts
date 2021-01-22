import { Component, OnInit } from '@angular/core';
import { ShopCartService } from './shop-cart.service';
import { BuyProduct } from 'src/app/webshop/buyproduct.model';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-shop-cart',
  templateUrl: './shop-cart.component.html',
  styleUrls: ['./shop-cart.component.css']
})
export class ShopCartComponent implements OnInit {
  products: BuyProduct[] = [];
  totalPrice: number = 0;

  forms: FormGroup[] = [];
  constructor(private shopcart: ShopCartService, private fb: FormBuilder) { }

  ngOnInit() {
    this.products = this.shopcart.getProducts();
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
      alert("The amount must be greater than 0. If you want to remove the item from your cart, click the 'delete' button.");
      this.forms[index].setValue({
        'amount': 1
      });
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
