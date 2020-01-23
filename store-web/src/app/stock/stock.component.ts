import { Component, OnInit } from '@angular/core';
import { Stock } from '../model/stock.model';
import { StockService } from '../services/stock/stock.service';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit {

  stocks: Stock[];
  error = '';

  constructor(private stockService: StockService) { }

  ngOnInit() {
    this.stockService.getAllStock().subscribe(
      res => {
        this.stocks = res;
      },
      error=> {
        this.error = error;
      }
    );
  }

}
