package com.music_streaming.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "song")
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
    @JsonBackReference(value = "playlist-song")
    private List<Playlist> playlists = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonManagedReference(value = "album-song")
    private Album album;
    @ManyToMany(mappedBy = "songsPerformed")
    private List<Artist> artists = new ArrayList<>();

    public Song() {
    }

    public Song(String title, int duration, List<Playlist> playlists, Album album, List<Artist> artists) {
        this.title = title;
        this.duration = duration;
        this.playlists = playlists;
        this.album = album;
        this.artists = artists;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
