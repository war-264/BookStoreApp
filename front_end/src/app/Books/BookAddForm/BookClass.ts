import { IBooks } from "../Books.component";

export class BookClass implements IBooks{
    id:any;
    bookName:string='';
    authorName:string='';
    price:any;
    edition :Number=0;
    imageUrl:string='';
    description:string='';
}