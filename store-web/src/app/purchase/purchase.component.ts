import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PurchaseService } from '../services/purchase/purchase.service';
import { ProductService } from '../services/product/product.service';
import { Product } from '../model/product.model';
import { Purchase } from '../model/purchase.model';
import { ProductPurchase } from '../model/product-purchase.model';
import * as moment from 'moment';
import { StockService } from '../services/stock/stock.service';
import { Stock } from '../model/stock.model';

@Component({
  selector: 'app-purchase',
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit {

  crearForm: FormGroup;
  guardando = false;
  purchase: Purchase;
  productPurchase: ProductPurchase;
  products: Product[];
  stock: Stock;
  error: string = '';
  mensajeExito: string;

  constructor(private formBuilder: FormBuilder, private purchaseService: PurchaseService,
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
    if (this.crearForm.invalid) {
      estadoError = true;
      this.error = 'Se deben diligenciar todos los campos que son obligatorios(*)';
    }
    if (estadoError) {
      this.mensajeExito = '';
      window.scroll(0, 0);
      return;
    }
    this.productPurchase = this.crearForm.value;
    this.purchase = new Purchase();
    this.purchase.purchaseDate = moment();
    this.purchase.userId = 151;
    this.purchase.total = this.stock.product.price * this.productPurchase.amount;
    this.purchase.productPurchases = [this.productPurchase];
    this.purchaseService.createPurchase(this.purchase)
    .subscribe(res => {
      this.purchase = res;
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
