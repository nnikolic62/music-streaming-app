package com.music_streaming.app.repository;

import com.music_streaming.app.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
