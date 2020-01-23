import { Moment } from 'moment';
import { ProductPurchase } from './product-purchase.model';

export class Purchase {
    constructor(
        public id?: number,
        public total?: number,
        public purchaseDate?: Moment,
        public productPurchases?: ProductPurchase[],
        public userId?: number
    ) {}
}
