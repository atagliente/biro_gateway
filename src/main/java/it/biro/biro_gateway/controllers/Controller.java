package it.biro.biro_gateway.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Controller extends org.springframework.web.cors.CorsConfiguration {

    @CrossOrigin
    @GetMapping("/user")
    public String index(Principal principal) {
        return principal.getName();
    }

}