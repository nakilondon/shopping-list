import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';

import { HttpClientModule } from '@angular/common/http';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { initializeKeycloak } from './init/keycloak-init.factory';
import { AppConfigService } from './app-config.service';
import { setupAppConfigServiceFactory } from './init/AppConfigService.factory';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table'  
import { MatFormFieldModule } from '@angular/material/form-field';
import {FormsModule} from "@angular/forms";
import { MatInputModule} from '@angular/material/input'
import {BrowserAnimationsModule} from '@angular/platform-browser/animations'

@NgModule({
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    KeycloakAngularModule,
    MatIconModule,
    MatTableModule,
    MatFormFieldModule,
    FormsModule, 
    MatInputModule,
    BrowserAnimationsModule,
    RouterModule.forRoot([
      { path: '', component: ShoppingListComponent },
    ])
  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    ShoppingListComponent
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService],
    },
    {
      provide: APP_INITIALIZER,
      useFactory: setupAppConfigServiceFactory,
      deps: [
          AppConfigService
      ],
      multi: true
  }
  ],
  exports: [RouterModule],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at https://angular.io/license
*/