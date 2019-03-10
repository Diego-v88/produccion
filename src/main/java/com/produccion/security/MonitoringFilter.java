/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produccion.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;


public class MonitoringFilter extends GenericFilterBean{
        
@Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRq = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String token = httpRq.getHeader("Authorization");
        //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJJZGVudGl0eVNlcnZlcjQifQ.JpqRBHKdJw3ziN4geIoeEK3AEzZzzWiSpkQJlIKd334
        
        if (token == null || !token.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            //resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            try {
                RSAkeysUtil pr = new RSAkeysUtil();
                //RSAPublicKey publicKey = pr.getPublicKey("d37bb56242dd1e5aabdd649e0c8dcb5f");
                token = token.substring(7);
                //RSAPublicKey publicKey = null;
                //RSAPrivateKey privateKey = null;
                //Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
                
                Algorithm algorithm = Algorithm.HMAC256("secret");
                JWTVerifier verifier = JWT.require(algorithm)
                    .build(); 
                DecodedJWT jwt = verifier.verify(token);
                System.out.println(jwt.getPayload());
            } catch (JWTVerificationException exception){
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            } catch (Exception ex) {
                Logger.getLogger(MonitoringFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //chain.doFilter(request, response);
    }
}
