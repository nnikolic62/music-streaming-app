package com.music_streaming.app.service;

import com.music_streaming.app.entity.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlaylistService {

    Playlist addPlaylist(Playlist playlist);
    Playlist findById(Long id);
    Playlist updatePlaylist(Playlist playlist);
    void deletePlaylist(Long id);
    List<Playlist> getAllPlaylists();
    Page<Playlist> getPlaylistsPagination(Pageable pageable);

}
