package com.music_streaming.app.service;

import com.music_streaming.app.entity.Song;

import java.util.List;

public interface SongService {
    List<Song> getAllSongs();
}
