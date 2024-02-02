import {Component, Inject} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
    selector: 'dialog',
    templateUrl: 'dlalog.html',
  })
  export class ItemNameDialog {
  
    constructor(
      public dialogRef: MatDialogRef<ItemNameDialog>,
      @Inject(MAT_DIALOG_DATA) public itemName: String) {}
  
    onNoClick(): void {
      this.dialogRef.close();
    }
  
  }