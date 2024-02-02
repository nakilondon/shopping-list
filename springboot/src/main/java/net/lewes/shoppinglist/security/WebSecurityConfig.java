package net.lewes.shoppinglist.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/addItem").hasRole(VIEW)
                .antMatchers(HttpMethod.POST, "/updateName/*").hasRole(VIEW)
                .antMatchers(HttpMethod.POST, "/decrease/*").hasRole(VIEW)
                .antMatchers(HttpMethod.POST, "/increase/*").hasRole(VIEW)
                .antMatchers(HttpMethod.DELETE, "/listItem").hasRole(VIEW)
                .antMatchers(HttpMethod.GET, "/list").hasRole(VIEW)
                .antMatchers(HttpMethod.GET, "/products").hasRole(VIEW)
                .antMatchers(HttpMethod.POST, "/barcode/*").hasRole(BARCODE_SCANNER)
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()
                .anyRequest().authenticated();
        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().and().csrf().disable();
        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder(OAuth2ResourceServerProperties oAuth2ResourceServerProperties) {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(oAuth2ResourceServerProperties.getJwt().getJwkSetUri()).build();
        jwtDecoder.setJwtValidator(JwtValidators.createDefaultWithIssuer(oAuth2ResourceServerProperties.getJwt().getIssuerUri()));
        return jwtDecoder;
    }

    public static final String BARCODE_SCANNER =  "barcode-scanner";
    public static final String VIEW =  "view";
}
