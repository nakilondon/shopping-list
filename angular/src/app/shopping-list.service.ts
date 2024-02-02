import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ShoppingItem } from './shopping-item';
import { config, map, Observable } from 'rxjs';
import { AppConfigService } from './app-config.service';

@Injectable({
  providedIn: 'root'
})

export class ShoppingListService {
  items: ShoppingItem[] = [];
  shoppingListServer? = 'http://bad:8095';
  
  constructor(private http: HttpClient, appConfig: AppConfigService) { 
    this.shoppingListServer = appConfig.data.shoppingListServer
  }

  getItems(): Observable<ShoppingItem[]> {
    return this.http.get<ShoppingItem[]>(this.shoppingListServer + '/list');
  }

  sendList() {
    return this.http.post<{message:string}>(this.shoppingListServer + '/sendMail',"");
  }


  clearList() {
    return this.http.post(this.shoppingListServer + '/clearList',"");
  }

  deleteItem(barcode: String) {
    return this.http.delete( this.shoppingListServer + '/listItem/' + barcode);
  } 

  increaseItem(barcode: String) {
    return this.http.post( this.shoppingListServer + '/increase/' + barcode, "");
  }

  decreaseItem(barcode: String) {
    return this.http.post( this.shoppingListServer + '/decrease/' + barcode, "");
  }

  updateItem(item: ShoppingItem): Observable<ShoppingItem> {
    const headers= new HttpHeaders()
      .set('content-type', 'application/json');

    return this.http.post<ShoppingItem>( this.shoppingListServer + '/updateItem/' + item.barcode, 
      JSON.stringify(item), {'headers': headers});  
  }

  addItem(item: ShoppingItem): Observable<ShoppingItem> {
    const headers= new HttpHeaders()
      .set('content-type', 'application/json');

    return this.http.post<ShoppingItem>( this.shoppingListServer + '/addItem', 
      JSON.stringify(item), {'headers': headers});  
  }
}
