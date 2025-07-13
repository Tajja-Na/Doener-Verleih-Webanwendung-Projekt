package de.hsrm.mi.web.projekt.auth.api;

import org.springframework.web.bind.annotation.RestController;
import com.nimbusds.jose.JOSEException;
import de.hsrm.mi.web.projekt.auth.LoginDTO;
import de.hsrm.mi.web.projekt.auth.services.AuthTokenService;
import java.nio.file.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private AuthTokenService ats;

    public AuthController(AuthenticationManager authenticationManager, AuthTokenService ats) {
        this.authenticationManager = authenticationManager;
        this.ats = ats;
    }

    @PostMapping("/token")
    public String postAuth(@RequestBody LoginDTO loginrec) throws AccessDeniedException {
        String jwtToken, au;
        try {
            // Username/Passwort-Kombi mit AuthenticationManager checken
            var tok = new UsernamePasswordAuthenticationToken(
                    loginrec.username(), loginrec.losung());
            var authentication = authenticationManager.authenticate(tok);
            if (!authentication.isAuthenticated()) {
                throw new AccessDeniedException("Logindaten falsch");
            }
            var authorities = authentication.getAuthorities().toArray();
            au = authorities[0].toString();
            // Token erzeugen lassen
            jwtToken = ats.generateToken(loginrec.username(), au);
        } catch (JOSEException | AuthenticationException exc) {
            throw new AccessDeniedException(exc.getMessage());
        }
        return jwtToken;
    }

}
