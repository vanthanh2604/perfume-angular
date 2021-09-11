import { Brand } from "../brand/brand";
import { Origin } from "../origin/origin";


export class Perfume {
    id: string;
    perfume_name: string;
    amount: number;
    price: number;
    description: string;  
    ngayNhap: Date;
    brandId: number; 
    originId: number; 
    brand: Brand;
    origin: Origin
    suplierId:number;
}
