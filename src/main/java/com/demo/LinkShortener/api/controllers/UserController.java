/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.LinkShortener.api.controllers;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kidrish
 */
@RestController
public class UserController {

    @GetMapping(path = "/getUsername")
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }
}
