package com.logonedigital.Nnam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Bonjour {
    @GetMapping ("/Bonjour")
    public String direBonjour () {
        return "Bonjour";
    }
}
