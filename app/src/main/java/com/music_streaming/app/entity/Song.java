package com.music_streaming.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "song")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Song {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "duration")
    private int duration;
    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonBackReference(value = "album-song")
    private Album album;
    @ManyToMany(mappedBy = "songsPerformed")
    private List<Artist> artists = new ArrayList<>();
}
