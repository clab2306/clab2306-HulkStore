import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Sale } from 'src/app/model/sale.model';

@Injectable({
  providedIn: 'root'
})
export class SaleService {

  url = environment.baseUrl;
  
  constructor(private http: HttpClient) { }
  
  createSale(sale: Sale) {
    return this.http.post<Sale>(`${this.url}/sales`, sale);
  }
}
