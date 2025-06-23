package com.music_streaming.app.service;

import com.music_streaming.app.entity.Album;

import java.util.List;

public interface AlbumService {

    Album addAlbum(Album album);
    List<Album> getAllAlbums();
    void deleteAlbum(Long id);
    Album findById(Long id);
    Album updateAlbumPut(Album album);
}
