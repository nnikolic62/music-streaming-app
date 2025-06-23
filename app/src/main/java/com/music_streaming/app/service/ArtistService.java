package com.music_streaming.app.service;

import com.music_streaming.app.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArtistService {

    List<Artist> getAllArtist();
    Page<Artist> getAllArtist(Pageable pageable);
    Artist findArtistById(Long id);
    Artist addArtist(Artist artist);
    Artist updateArtist(Artist artist);
    void deleteArtist(Long id);
}
