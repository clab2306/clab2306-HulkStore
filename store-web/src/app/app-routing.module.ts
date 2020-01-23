import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StockComponent } from './stock/stock.component';
import { PurchaseComponent } from './purchase/purchase.component';
import { SaleComponent } from './sale/sale.component';


const routes: Routes = [
  {path: '', redirectTo:'stock', pathMatch: 'full'},
  {path: 'stock', component: StockComponent},
  {path: 'purchase', component: PurchaseComponent},
  {path: 'sale', component: SaleComponent},
  {path: '**', redirectTo:'stock', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
