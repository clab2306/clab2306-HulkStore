import { Moment } from 'moment';

export class Product{
    constructor(
        public id?: number,
        public description?: string,
        public price?: number,
        public imageContentType?: string,
        public image?: any,
        public dateUpdate?: Moment,
        public userId?: number,
        public productTypeId?: number
    ) {}
}
