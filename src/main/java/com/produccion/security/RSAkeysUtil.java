/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.security;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;
/**
 *
 * @author Diego
 */
public class RSAkeysUtil {
    //http://localhost:5000/.well-known/openid-configuration/jwks
private String jwkUrl = "https://demo.identityserver.io/.well-known/openid-configuration/jwks";    

    public RSAkeysUtil() {
    }

    public RSAPublicKey getPublicKey(String kid) throws Exception {
        JwkProvider provider = new UrlJwkProvider(new URL(jwkUrl));
        Jwk jwk = provider.get(kid);
        return (RSAPublicKey) jwk.getPublicKey();
    }


}
