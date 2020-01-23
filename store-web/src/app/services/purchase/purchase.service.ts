import { Injectable } from '@angular/core';
import { Purchase } from 'src/app/model/purchase.model';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  url = environment.baseUrl;
  
  constructor(private http: HttpClient) { }
  
  createPurchase(purchase: Purchase) {
    return this.http.post<Purchase>(`${this.url}/purchases`, purchase);
  }

}
