import { Component, OnInit } from '@angular/core';
import { Product } from '../product.model';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-webshop-list',
  templateUrl: './webshop-list.component.html',
  styleUrls: ['./webshop-list.component.css']
})

export class WebshopListComponent implements OnInit {
  products: Product[];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.fetchProducts().subscribe(
      (products: Product[]) => {
        this.products = products;
    });
  }
}
