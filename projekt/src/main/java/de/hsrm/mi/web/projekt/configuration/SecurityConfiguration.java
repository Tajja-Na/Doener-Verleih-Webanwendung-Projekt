package de.hsrm.mi.web.projekt.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Value("${SECRETKEY}")
    private String signierschluessel;

    @Bean 
    PasswordEncoder passwordEncoder() { // @Bean -> Encoder woanders per @Autowired abrufbar
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Order(1)
    SecurityFilterChain filterChainAPI(HttpSecurity http) throws Exception {
        return http
            .securityMatcher("/api/**", "/stompbroker/**")
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.POST, "api/token").permitAll()
                .requestMatchers("/stompbroker/**").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().denyAll()
            )
        // kein CSRF-Check und kein Sessionmanagement für /api-Endpunkte
        .csrf(csrf -> csrf.disable())
        .sessionManagement((session) ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .oauth2ResourceServer(o -> o.jwt(j -> j.decoder(jwtDecoder())))
        .build();
    }

    @Bean
    @Order(2)
    SecurityFilterChain filterChainWeb(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authz -> authz
            .requestMatchers(toH2Console()).permitAll()
            .requestMatchers("/benutzer/**").hasRole("ADMIN")
            .requestMatchers("/images/**").permitAll()
            .requestMatchers("/doener/**").authenticated()
            .anyRequest().authenticated()
            )
        .formLogin(folo -> folo.defaultSuccessUrl("/doener"))
        .logout(out -> out.logoutSuccessUrl("/login"))
        .csrf(csrf -> csrf
            .ignoringRequestMatchers(toH2Console())
            .ignoringRequestMatchers("/benutzer/*/hx/feld/*")
            .ignoringRequestMatchers("/api/**"))
        .headers(hdrs -> hdrs.frameOptions(fo -> fo.sameOrigin()))
        .build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception {
        return config.getAuthenticationManager();
    }
    
    @Bean
    JwtDecoder jwtDecoder() {
        byte[] keyBytes = signierschluessel.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec).build();
    }

}
