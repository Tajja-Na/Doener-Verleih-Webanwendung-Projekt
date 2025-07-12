package de.hsrm.mi.web.projekt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean 
    PasswordEncoder passwordEncoder() { // @Bean -> Encoder woanders per @Autowired abrufbar
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChainAPI(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authz -> authz
            .requestMatchers(toH2Console()).permitAll()
            .requestMatchers("/benutzer/**").hasRole("ADMIN")
            .requestMatchers("/api/**", "/stompbroker/**", "/images/**").permitAll()
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
}
