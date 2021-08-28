import { Brand } from "../brand/brand";


export class Perfume {
    id: number;
    perfume_code: string
    perfume_name: string;
    amount: number;
    price: number;
    description: string;  
    ngayNhap: Date;
    brandId: number; 
    brand: Brand; 
    suplierId:number;
}
