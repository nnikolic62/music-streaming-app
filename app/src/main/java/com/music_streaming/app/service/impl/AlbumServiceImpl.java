package com.music_streaming.app.service.impl;

import com.music_streaming.app.entity.Album;
import com.music_streaming.app.repository.AlbumRepository;
import com.music_streaming.app.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AlbumServiceImpl implements AlbumService {

    private AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository){
        this.albumRepository = albumRepository;
    }
    @Override
    @Transactional
    public Album addAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteAlbum(Long id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException());

        albumRepository.delete(album);
    }

    @Override
    public Album findById(Long id) {
        return albumRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    @Transactional
    public Album updateAlbumPut(Album album) {
        if(album.getId() == null){
            throw new RuntimeException("Cannot find item");
        }

        return albumRepository.save(album);
    }
}
