import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ProductService } from '../webshop/product.service';
import { Product } from '../webshop/product.model';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  newProductForm: FormGroup;
  products: Product[] = [];
  forms: FormGroup[] = [];
  constructor(private productService: ProductService, private fb: FormBuilder) { }

  ngOnInit() {
    this.updateSelf();
  }

  updateSelf() {
    this.newProductForm = this.fb.group({
      'name': '',
      'price': '',
      'description': '',
      'image': ''
    });

    this.productService.fetchProducts().subscribe(
      (products) => {
        this.products = products;
        for(var product of this.products) {
          console.log(product)
          this.forms.push(this.fb.group({
            'name': product.name,
            'price': product.price,
          }));
        }
      });


  }
  createProduct() {
    let product = this.formToProduct();

    this.productService.createProduct(product).subscribe(
      (c: any) => {
        alert("Added " + product.name + " to the shop.");
      },
      (err) => {
        console.log(err)
      }
    );
    
    this.products.push(product);
    this.forms.push(this.fb.group({
      'name': product.name,
      'price': product.price,
    }))
  }
  
  updateProduct(index: number) {
    this.products[index].name = this.forms[index].value['name'];
    this.products[index].price = this.forms[index].value['price'];

    let product = this.products[index];

    this.productService.updateProduct(product.id, product).subscribe(
      (c: any) => {
        alert!("Updated " + product.name + ".");
      }
    );

    this.updateSelf();
  }

  deleteProduct(index: number) {
    let product = this.products[index];
    this.productService.deleteProduct(product.id).subscribe(
      (c: any) => {
        alert!("Deleted " + product.name + ".");
      }
    );
    
    this.products.splice(index, 1);
  }

  formToProduct() {
    return new Product(0,
      this.newProductForm.value['name'],
      this.newProductForm.value['price'],
      this.newProductForm.value['description'],
      this.newProductForm.value['image'])
  }
  
}
