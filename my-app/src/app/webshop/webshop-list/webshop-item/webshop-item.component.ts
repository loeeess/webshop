import { Component, OnInit, Input } from '@angular/core';
import { Product } from '../../product.model';

@Component({
  selector: 'app-webshop-item',
  templateUrl: './webshop-item.component.html',
  styleUrls: ['./webshop-item.component.css']
})
export class WebshopItemComponent implements OnInit {
  @Input() item: Product;
  @Input() index: number;

  constructor() { }

  ngOnInit() {
    console.log("ITEM:" + this.index);
  }
}
