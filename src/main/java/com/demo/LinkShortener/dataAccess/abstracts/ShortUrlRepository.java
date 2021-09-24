/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.LinkShortener.dataAccess.abstracts;

import com.demo.LinkShortener.entities.concretes.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kidrish
 */
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    @Transactional
    ShortUrl getByShortenedUrl(String shortUrl);
}
