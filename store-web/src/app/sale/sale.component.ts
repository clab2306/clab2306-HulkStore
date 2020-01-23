import { Component, OnInit } from '@angular/core';
import { Sale } from '../model/sale.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import * as moment from 'moment';
import { ProductSale } from '../model/product-sale.model';
import { Product } from '../model/product.model';
import { Stock } from '../model/stock.model';
import { StockService } from '../services/stock/stock.service';
import { ProductService } from '../services/product/product.service';
import { SaleService } from '../services/sale/sale.service';

@Component({
  selector: 'app-sale',
  templateUrl: './sale.component.html',
  styleUrls: ['./sale.component.css']
})
export class SaleComponent implements OnInit {

  crearForm: FormGroup;
  guardando = false;
  errorProducto = false;
  sale: Sale;
  productSale: ProductSale;
  products: Product[];
  stock: Stock;
  error: string = '';
  mensajeExito: string;

  constructor(private formBuilder: FormBuilder, private saleService: SaleService,
    private productService: ProductService, private stockService: StockService) { }

  ngOnInit() {
    this.crearForm = this.formBuilder.group({
      id: '',
      productId: ['', [Validators.required]],
      amount: ['', [Validators.required]]
    });
    this.obtenerProductos();    
  }  

  obtenerProductos() {
    this.productService.getAllProduct().subscribe(
      res => {
        this.products = res;
      },
      error => {
        this.error = error.message;
      }
    );
  }

  visualizarProducto() {
    if(this.crearForm.value.productId) {
      this.stockService.getStockByProductoId(this.crearForm.value.productId).subscribe(res=>{
        this.stock = res;
        this.errorProducto = this.stock.amount === 0;
      })
    }    
  }
  
  get f() { return this.crearForm.controls; }

  previousState() {
    window.history.back();
  }

  crear() {
    this.guardando = true;
    let estadoError = false;
    if(this.errorProducto) {
      estadoError = true;
      this.error = 'Este producto no está disponible';
    }
    if (this.crearForm.invalid) {
      estadoError = true;
      this.error = 'Se deben diligenciar todos los campos que son obligatorios(*)';
    }
    if(this.crearForm.value.amount > this.stock.amount) {
      estadoError = true;
      this.error = `La cantidad supera los elementos disponibles: ${this.stock.amount}`;
    }
    if (estadoError) {
      this.mensajeExito = '';
      window.scroll(0, 0);
      return;
    }
    this.productSale = this.crearForm.value;
    this.sale = new Sale();
    this.sale.saleDate = moment();
    this.sale.userId = 151;
    this.sale.total = this.stock.product.price * this.productSale.amount;
    this.sale.productSales = [this.productSale];
    this.saleService.createSale(this.sale)
    .subscribe(res => {
      this.sale = res;
      this.mensajeExito = 'Se creó con éxito';
      window.scroll(0, 0);
      this.guardando = false;
      this.error = '';
    }, error => {
      this.mensajeExito = '';
      this.error = error.message;
      window.scroll(0, 0);
      this.guardando = false;
    });
  }
}
