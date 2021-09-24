/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.LinkShortener.bussines.concretes;

import com.demo.LinkShortener.bussines.abstracts.ShortUrlService;
import com.demo.LinkShortener.core.entities.User;
import com.demo.LinkShortener.dataAccess.abstracts.ShortUrlRepository;
import com.demo.LinkShortener.entities.concretes.ShortUrl;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author kidrish
 */
@Service
@AllArgsConstructor
public class ShortUrlManager implements ShortUrlService {

    @Autowired
    private final ShortUrlRepository shortUrlRepository;
    private static String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private String createShortUrl(String url) {
        String shortenedUrl = "";
        url = url.replace("http://", "").replace("https://", "");
        Integer num = 0;
        for (char letter : url.toCharArray()) {
            num += letter;
        }
        while (num > 0) {
            shortenedUrl += ALPHABET.charAt(num % ALPHABET.length());
            num /= ALPHABET.length();
        }
        return shortenedUrl;
    }

    @Override
    public String addShortUrl(ShortUrl shortUrl) {
        if (shortUrl.getShortenedUrl() != null) {
            if (shortUrlRepository.getByShortenedUrl(shortUrl.getShortenedUrl()) != null) {
                throw new IllegalStateException("The url you sent is already registered.");
            } else {
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                shortUrl.setUser(user);
                shortUrl.setCreatedAt(LocalDateTime.now());
                shortUrlRepository.save(shortUrl);
            }
        } else {
            String longUrl = shortUrl.getLongUrl();
            if (shortUrlRepository.getByShortenedUrl(createShortUrl(longUrl)) != null) {
                throw new IllegalStateException("The url you sent is already registered.");
            } else {
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                shortUrl.setUser(user);

                // Same shortened url blocked 
                while (shortUrlRepository.getByShortenedUrl(createShortUrl(longUrl)) != null) {
                    longUrl += "a";
                }
                shortUrl.setCreatedAt(LocalDateTime.now());
                shortUrl.setShortenedUrl(createShortUrl(shortUrl.getLongUrl()));
                shortUrlRepository.save(shortUrl);
            }
        }
        return shortUrl.getShortenedUrl();
    }

    @Override
    public ShortUrl getByShortenedUrl(String url) {
        return shortUrlRepository.getByShortenedUrl(url);
    }

}
