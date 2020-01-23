import { ProductSale } from './product-sale.model';
import { Moment } from 'moment';

export class Sale {
    constructor(
        public id?: number,
        public total?: number,
        public saleDate?: Moment,
        public productSales?: ProductSale[],
        public userId?: number
    ) {}
}
