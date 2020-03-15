import {EventEmitter, Injectable} from '@angular/core';
import {Product} from './product.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {NgForm} from '@angular/forms';
import { ApiService } from '../shared/api.service';
import { Observable } from 'rxjs';

@Injectable()
export class ProductService {

    constructor(private apiService: ApiService) {}

    fetchProduct(idx: number): Observable<Product> {
        return this.apiService.getById<Product>('/product', idx);
    }

    fetchProducts(): Observable<Product[]> {
        return this.apiService.get<Product[]>('/product');
    }

    createProduct(product: Product): Observable<Product> {
        return this.apiService.post<Product>('/product', product);
    }

    updateProduct(id: number, product: Product): Observable<Product> {
        return this.apiService.put<Product>('/product', id, product);
    }

    deleteProduct(id: number) {
        return this.apiService.delete<Product>('/product', id);
    }
}