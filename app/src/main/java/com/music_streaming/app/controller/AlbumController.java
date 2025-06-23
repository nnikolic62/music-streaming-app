package com.music_streaming.app.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.music_streaming.app.entity.Album;
import com.music_streaming.app.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private AlbumService albumService;
    private ObjectMapper objectMapper;

    @Autowired
    public AlbumController(AlbumService albumService, ObjectMapper objectMapper){
        this.albumService = albumService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<Album> addAlbum(@RequestBody Album album){
        Album savedAlbum = albumService.addAlbum(album);

        return new ResponseEntity<>(savedAlbum, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums(){
        List<Album> albums = albumService.getAllAlbums();

        return ResponseEntity.ok(albums);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long id){
        albumService.deleteAlbum(id);

        return ResponseEntity.ok("Successfully deleted!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> findById(@PathVariable Long id){
        Album album = albumService.findById(id);

        return ResponseEntity.ok(album);
    }

    @PutMapping
    public ResponseEntity<Album> updateAlbumPut(@RequestBody Album album){
        Album savedAlbum = albumService.updateAlbumPut(album);

        return ResponseEntity.ok(savedAlbum);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Album> updateAlbumPatch(@PathVariable Long id, @RequestBody Map<String, Object> patchValues){
        Album album = albumService.findById(id);

        if(album == null){
            throw new RuntimeException();
        }

        if(patchValues.containsKey("id")){
            throw new RuntimeException("Cannot change id");
        }

        Album patchedAlbum = null;

        try {
            patchedAlbum = objectMapper.updateValue(album, patchValues);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }

        Album savedAlbum = albumService.addAlbum(patchedAlbum);

        return ResponseEntity.ok(savedAlbum);
    }
}
