package com.music_streaming.app.service.impl;

import com.music_streaming.app.entity.Song;
import com.music_streaming.app.repository.SongRepository;
import com.music_streaming.app.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SongServiceImpl implements SongService {

    private SongRepository songRepository;

    @Autowired
    public SongServiceImpl(SongRepository songRepository){
        this.songRepository = songRepository;
    }
    @Override
    public Page<Song> getAllSongs(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    @Transactional
    public Song addSong(Song song) {
        Song savedSong = songRepository.save(song);
        return savedSong;
    }

    @Override
    @Transactional
    public Song updateSong(Song song) {
        if(song.getId() == null){
            throw new RuntimeException("Cannot find song with id null");
        }
        Song updatedSong = songRepository.save(song);
        return updatedSong;
    }

    @Override
    @Transactional
    public void deleteSong(Long id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new RuntimeException());

        songRepository.delete(song);
    }

    @Override
    public Song getSongById(Long id) {
        return songRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }
}
