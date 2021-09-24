/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.LinkShortener.bussines.abstracts;

import com.demo.LinkShortener.entities.concretes.ShortUrl;

/**
 *
 * @author kidrish
 */
public interface ShortUrlService {

    String addShortUrl(ShortUrl shortUrl);

    ShortUrl getByShortenedUrl(String longUrl);


}
