/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.LinkShortener.api.controllers;

import com.demo.LinkShortener.bussines.abstracts.ShortUrlService;
import java.net.URISyntaxException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author kidrish
 */
@RestController
@AllArgsConstructor
public class RedirectController {

    @Autowired
    private final ShortUrlService shortUrlService;

    @GetMapping("/{shortUrl}")
    public RedirectView redirectToLongUrl(@PathVariable String shortUrl) throws URISyntaxException {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(shortUrlService.getByShortenedUrl(shortUrl).getLongUrl());
        return redirectView;
    }

    @GetMapping("/get-long-url")
    public String getLongUrlByShortenedUrl(@RequestParam String shortUrl) {
        return shortUrlService.getByShortenedUrl(shortUrl).getLongUrl();
    }
}
