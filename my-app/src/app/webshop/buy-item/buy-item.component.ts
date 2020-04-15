import { Component, OnInit } from '@angular/core';
import { Product } from '../product.model';
import { ActivatedRoute } from '@angular/router';
import { ShopCartService } from 'src/app/shop-cart/shop-cart.service';
import { BuyProduct } from '../buyproduct.model';

@Component({
  selector: 'app-buy-item',
  templateUrl: './buy-item.component.html',
  styleUrls: ['./buy-item.component.css']
})
export class BuyItemComponent implements OnInit {
  item: Product;
  amount: number = 1;
  constructor(private route: ActivatedRoute, private shopcart: ShopCartService) { }

  ngOnInit() {
    this.route.params.subscribe(
      () => {
        this.item = this.route.snapshot.data.item;
      }
    )
  }

  addToShopCart() {
    if (this.amount < 1) {
      alert("The amount must greater than 0!");
      return;
    }
    this.shopcart.push(new BuyProduct(this.item, Number(this.amount)));
    var s = "Added " + this.amount + " " + this.item.name + "(s) to the shopping cart";
    alert(s);
  }

  setAmount(amount) {
    if (amount.value < 1) {
      alert("Buying nothing? Not cool.");
      return;
    }
    console.log("SET amount");
    this.amount = amount.value;
  }
}
