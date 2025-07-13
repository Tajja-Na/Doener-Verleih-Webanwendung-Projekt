package de.hsrm.mi.web.projekt.auth.services;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service
public class AuthTokenServiceImpl implements AuthTokenService {

    // Wert von SECRETKEY aus application.properties
    @Value("${SECRETKEY}")
    private String signierschluessel;

    @Override
    public String generateToken(String username, String authority) throws JOSEException {
         var jetzt = LocalDateTime.now();
            var ablauf = jetzt.plusHours(1);
            // "Claims" (Token-Nutzdaten) zusammenstellen
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(username)
                    .issuer("http://www.joghurta-software.xy")
                    .issueTime(Date.from(jetzt.toInstant(ZoneOffset.UTC)))
                    .expirationTime(Date.from(ablauf.toInstant(ZoneOffset.UTC)))
                    .claim("authorities", new String[] { authority })
                    .build();
            // JWT bauen und mit mit HMAC256 sichern
            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            JWSSigner signer = new MACSigner(signierschluessel);
            signedJWT.sign(signer);
            // Token in Kompaktform als String zurückgeben
            return signedJWT.serialize();
    }

}
