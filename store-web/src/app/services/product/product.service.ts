import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from 'src/app/model/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  url = environment.baseUrl;
  
  constructor(private http: HttpClient) { }
  
  public getAllProduct(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.url}/products`);
  }
  
  public getProducto(productId: string): Observable<Product> {
    return this.http.get<Product>(`${this.url}/products/${productId}`);
  }
}
