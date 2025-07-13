package de.hsrm.mi.web.projekt.auth.services;

import com.nimbusds.jose.JOSEException;

public interface AuthTokenService {
    String generateToken(String username, String authority) throws JOSEException;
}
