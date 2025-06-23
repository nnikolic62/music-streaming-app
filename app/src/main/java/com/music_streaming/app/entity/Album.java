package com.music_streaming.app.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "releaseYear")
    private int releaseYear;
    @OneToMany(mappedBy = "album")
    @JsonManagedReference(value = "album-song")
    private List<Song> songs = new ArrayList<>();
    @ManyToMany(mappedBy = "albums")
    private List<Artist> artists = new ArrayList<>();

    public Album() {
    }

    public Album(String title, int releaseYear, List<Song> songs, List<Artist> artists) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.songs = songs;
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

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
