package com.music_streaming.app.service.impl;

import com.music_streaming.app.entity.Artist;
import com.music_streaming.app.repository.ArtistRepository;
import com.music_streaming.app.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> getAllArtist() {
        return artistRepository.findAll();
    }

    @Override
    public Page<Artist> getAllArtist(Pageable pageable) {
        return artistRepository.findAll(pageable);
    }

    @Override
    public Artist findArtistById(Long id) {
        return artistRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public Artist addArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public Artist updateArtist(Artist artist) {
        if(artist.getId() == null){
            throw new IllegalArgumentException();
        }
        return artistRepository.save(artist);
    }

    @Override
    public void deleteArtist(Long id) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new RuntimeException());

        artistRepository.delete(artist);
    }
}
