import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Stock } from 'src/app/model/stock.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StockService {

  url = environment.baseUrl;
  
  constructor(private http: HttpClient) { }
  
  public getAllStock(): Observable<Stock[]> {
    return this.http.get<Stock[]>(`${this.url}/stocks`);
  }

  public getStockByProductoId(productId: string): Observable<Stock> {
    return this.http.get<Stock>(`${this.url}/stocks/product/${productId}`);
  }
}
