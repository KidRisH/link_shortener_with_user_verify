/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.LinkShortener.api.controllers;

import com.demo.LinkShortener.bussines.abstracts.ShortUrlService;
import com.demo.LinkShortener.entities.concretes.ShortUrl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kidrish
 */
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ShortUrlController {

    @Autowired
    private final ShortUrlService shortUrlService;

    @GetMapping("/url-shortener")
    public String urlShortener(@RequestParam String longUrl, String shortenedUrl) {
        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setLongUrl(longUrl);
        shortUrl.setShortenedUrl(shortenedUrl);
        return shortUrlService.addShortUrl(shortUrl);
    }
}
