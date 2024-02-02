import { AppConfigService } from "../app-config.service";

export function setupAppConfigServiceFactory(
    service: AppConfigService
  ): Function {
    return () => service.load();
  }