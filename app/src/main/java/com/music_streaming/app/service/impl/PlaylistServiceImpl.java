package com.music_streaming.app.service.impl;

import com.music_streaming.app.entity.Playlist;
import com.music_streaming.app.repository.PlaylistRepository;
import com.music_streaming.app.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository){
        this.playlistRepository = playlistRepository;
    }
    @Override
    @Transactional
    public Playlist addPlaylist(Playlist playlist) {
        Playlist savedPlaylist = playlistRepository.save(playlist);
        return savedPlaylist;
    }

    @Override
    public Playlist findById(Long id) {
        Playlist playlist = playlistRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find!"));

        return playlist;
    }

    @Override
    @Transactional
    public Playlist updatePlaylist(Playlist playlist) {
        if(playlist.getId() == null){
            throw new IllegalArgumentException("ID must bre present!");
        }
        Playlist savedPlaylist = playlistRepository.save(playlist);
        return savedPlaylist;
    }

    @Override
    @Transactional
    public void deletePlaylist(Long id) {
        Playlist playlist = playlistRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist with ID " + id + " not found!"));

        playlistRepository.delete(playlist);

    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    @Override
    public Page<Playlist> getPlaylistsPagination(Pageable pageable) {
        return playlistRepository.findAll(pageable);
    }


}
