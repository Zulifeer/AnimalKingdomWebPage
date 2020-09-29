package com.Capston2.joel_y_seba.AnimalKingdomWebPage.Security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@Configurable
public class HandelerAuthentication implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth)
            throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities  = auth.getAuthorities();
        boolean log = authorities.stream().anyMatch(authority -> authority.getAuthority().equals("ADMIN") || authority.getAuthority().equals("USER"));
        Logger logger = LoggerFactory.getLogger(HandelerAuthentication.class);

        logger.info("auth collection:" + authorities.toString());
       
        if(log){
            try {
                res.sendRedirect("/");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            try {
                res.sendRedirect("/403");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    
}
