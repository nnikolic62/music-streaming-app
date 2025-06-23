package com.music_streaming.app.service.impl;

import com.music_streaming.app.entity.Song;
import com.music_streaming.app.repository.SongRepository;
import com.music_streaming.app.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SongServiceImpl implements SongService {

    private SongRepository songRepository;

    @Autowired
    public SongServiceImpl(SongRepository songRepository){
        this.songRepository = songRepository;
    }
    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }
}
