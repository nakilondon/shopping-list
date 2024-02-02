import { Component, OnInit } from '@angular/core';
import { ShoppingListService } from '../shopping-list.service';
import { ShoppingItem } from '../shopping-item';
import { Observable, tap } from 'rxjs';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.css']
})

export class ShoppingListComponent implements OnInit {
  shoppingItems$: Observable<ShoppingItem[]> | undefined;
  displayedColumns: string[] = ['itemName', 'count'];
  
  timeLeft: number = 0;
  interval: any;
  
  constructor( private shoppingService: ShoppingListService ) {}

  ngOnInit(): void {
    this.startRefreshTimer();
    
  }

  startRefreshTimer() {
    this.interval = setInterval(() => {
      if (this.timeLeft > 0 ) {
        this.timeLeft--;
      } else {
        this.refreshList();
        this.timeLeft = 5;
      }
    }, 1000 );
  }

  pauseRefreshTimer() {
    clearInterval(this.interval);
  }

  refreshList() {
    this.shoppingItems$ = this.shoppingService.getItems()
  }


  clearList() {
    this.shoppingService.clearList().subscribe(
      response => {
        console.log(response)
        this.refreshList();
      },
      err => {
        console.error(err)
        this.refreshList();
      },
    );
    
  }

  deleteFromList(barcode: String) {
    this.shoppingService.deleteItem(barcode).subscribe(
      response => {
        console.log("delete: " + barcode + " from list");
        this.refreshList();
      },
      err => {
        console.error("failed to delete: " + barcode + " from list");
        this.refreshList();
      }
    ); 
  }

  updateItemName(item: ShoppingItem) {
    console.log("Update barcode: " + item.barcode + " to name: " + item.itemName);
    this.shoppingService.updateItem(item).subscribe(
      response => {
        this.refreshList();
      },
      err => {
        console.error("failed to update " + item.barcode);
      }
    );

    this.startRefreshTimer();
  }

  dataChanged(event: Event) {
    console.log("event: " + event);

  }

  increaseCount(item: ShoppingItem) {
    console.log("increase " + item.barcode);
    item.count = item.count + 1;

    this.shoppingService.increaseItem(item.barcode).subscribe(
      response => {
        this.refreshList();
      },
      err => {
        console.error("failed to increase: " + item.barcode);
        this.refreshList();
      }
      
    );
  }

  decreaseCount(item: ShoppingItem) {
    console.log("decrease " + item.barcode);
    item.count = item.count - 1;

    this.shoppingService.decreaseItem(item.barcode).subscribe(
      response => {
        this.refreshList();
      },
      err => {
        console.error("failed to decrease: " + item.barcode);
        this.refreshList();
      }
      
    );
  }

  addToList() {
    const newItem: ShoppingItem = {  id: 0, barcode: "", itemName: "New Item", count: 1};     
    console.log("new item: " +  newItem.itemName);

    this.shoppingService.addItem(newItem).subscribe(
      response => {
        this.refreshList();
      },
      err => {
        console.error("failed to add item: " + newItem.itemName);
        this.refreshList();
      }     
    );
  }
}
