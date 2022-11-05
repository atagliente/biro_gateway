package it.biro.biro_gateway.controllers;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
public class Controller extends org.springframework.web.cors.CorsConfiguration {
    @CrossOrigin
    @GetMapping("/user")
    public String u_index(Principal principal) {
        return principal.getName();
    }

    @CrossOrigin
    @GetMapping("/admin")
    public String a_index(Principal principal) {
        return principal.getName();
    }

    @CrossOrigin
    @GetMapping("/test")
    public String s_index(Principal principal) {
        JwtAuthenticationToken jwtAT = (JwtAuthenticationToken) principal;
        return jwtAT.getTokenAttributes().toString();
    }

}