import { Moment } from 'moment';
import { Product } from './product.model';

export class Stock {
    constructor(
        public id?: number,
        public amount?: number,
        public updateDate?: Moment,
        public productId?: number,
        public updateUserId?: number,
        public product?: Product,
        public updateUserDescription?: string
    ) {}
}
