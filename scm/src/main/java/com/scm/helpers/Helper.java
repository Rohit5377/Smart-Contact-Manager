package com.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication) {
        // if user logged in with email and password then how we find their email
        if (authentication instanceof OAuth2AuthenticationToken) {
            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User) authentication.getPrincipal();
            String username = "";
            if (clientId.equalsIgnoreCase("google")) {
                System.out.println("Getting email from google");
                username = oauth2User.getAttribute("email").toString();
                return username;
            }

        } else {
            System.out.println("Getting data from local database");
            return authentication.getName();
        }
        return "";

    }

    public static String getLinkOfEmailVerification(String emailToken) {
        String link = "http://localhost:8091/auth/verify-email?token=" + emailToken;

        return link;
    }

}
