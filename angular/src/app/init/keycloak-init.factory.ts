import { KeycloakService } from "keycloak-angular";

export function initializeKeycloak(
  keycloak: KeycloakService
  ) {
    return () =>
      keycloak.init({
        config: {
          url: 'https://keycloak.lewes.family',
          realm: 'LewesFamily',
          clientId: 'shopping-list-app',
        },
        initOptions: {
            onLoad: 'check-sso',
            silentCheckSsoRedirectUri:
              window.location.origin + '/assets/silent-check-sso.html'
          }
      });
}