// src/app/app-config.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from './app-config';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class AppConfigService {

  data: AppConfig = {};
  configFile = 'assets/app-config.json';

  constructor(private http: HttpClient) {
    if (environment.production)  {
      this.configFile = 'assets/app-config-production.json';
    }
  }

  load(defaults?: AppConfig): Promise<AppConfig> {
    return new Promise<AppConfig>(resolve => {
      this.http.get(this.configFile).subscribe(
        response => {
          console.log('using server-side configuration');
          this.data = Object.assign({}, defaults || {}, response || {});
          resolve(this.data);
        },
        () => {
          console.log('using default configuration');
          this.data = Object.assign({}, defaults || {});
          resolve(this.data);
        }
      );
    });
  }
}
