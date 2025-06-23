package com.music_streaming.app.service;

import com.music_streaming.app.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SongService {
    Page<Song> getAllSongs(Pageable pageable);
    List<Song> getAllSongs();
    Song addSong(Song song);
    Song updateSong(Song song);
    void deleteSong(Long id);
    Song getSongById(Long id);

}
