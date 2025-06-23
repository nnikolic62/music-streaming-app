package com.music_streaming.app.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "genre")
    private String genre;
    @ManyToMany
    @JoinTable(
            name = "artist_song",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songsPerformed = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "artis_album",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    private List<Album> albums = new ArrayList<>();

    public Artist() {
    }

    public Artist(String name, String genre, List<Song> songsPerformed, List<Album> albums) {
        this.name = name;
        this.genre = genre;
        this.songsPerformed = songsPerformed;
        this.albums = albums;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Song> getSongsPerformed() {
        return songsPerformed;
    }

    public void setSongsPerformed(List<Song> songsPerformed) {
        this.songsPerformed = songsPerformed;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }
}
