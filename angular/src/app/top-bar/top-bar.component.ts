import { Component } from '@angular/core';
import { ShoppingListService } from '../shopping-list.service';
import { KeycloakService } from 'keycloak-angular';
import { KeycloakProfile } from 'keycloak-js';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css']
})
export class TopBarComponent {
  public isLoggedIn = false;
  public userProfile: KeycloakProfile | null = null;

  constructor(private shoppingService: ShoppingListService, private readonly keycloak: KeycloakService) { }

  public async ngOnInit() {
    this.isLoggedIn = await this.keycloak.isLoggedIn();

    if (this.isLoggedIn) {
      this.userProfile = await this.keycloak.loadUserProfile();
    }
  }
  
  public login() {
    this.keycloak.login();
  }

  public logout() {
    this.keycloak.logout();
  }

  sendEmail() {
    this.shoppingService.sendList().subscribe(
      response => {
        window.alert(response.message);
        console.log(response.message);
      },
      err => {
        window.alert("Unable to send email");
        console.error(err);
      },
    );
  }
}


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/