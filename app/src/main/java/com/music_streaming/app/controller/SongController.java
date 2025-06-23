package com.music_streaming.app.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.music_streaming.app.entity.Song;
import com.music_streaming.app.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private SongService songService;
    private ObjectMapper objectMapper;

    @Autowired
    public SongController(SongService songService, ObjectMapper objectMapper){
        this.songService = songService;
        this.objectMapper = objectMapper;
    }

//    @GetMapping
//    public ResponseEntity<List<Song>> getAllSongs(){
//        return ResponseEntity.ok(songService.getAllSongs());
//    }

    @GetMapping
    public ResponseEntity<?> getAllSongs(@RequestParam(required = false) Integer pageNumber,
                                         @RequestParam(required = false) Integer pageSize,
                                         @RequestParam(required = false) String sortBy,
                                         @RequestParam(required = false) String sortDir){
        if(pageNumber != null && pageSize != null){
            Pageable pageable;
            if(sortBy != null && sortDir != null){
                Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
                pageable = PageRequest.of(pageNumber, pageSize, sort);
            }else{
                pageable = PageRequest.of(pageNumber, pageSize);
            }
            Page<Song> songs = songService.getAllSongs(pageable);
            return ResponseEntity.ok(songs);
        }else{
            List<Song> songs = songService.getAllSongs();
            return ResponseEntity.ok(songs);
        }
    }

    @PostMapping
    public ResponseEntity<Song> addSong(@RequestBody Song song){
        return new ResponseEntity<>(songService.addSong(song), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Song> updateSongPut(Song song){
        return ResponseEntity.ok(songService.updateSong(song));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Song> updateSongPatch(@PathVariable Long id, Map<String, Object> patchValues){
        Song song = songService.getSongById(id);

        if(song == null){
            throw new RuntimeException();
        }
        if(patchValues.containsKey("id")){
            throw new RuntimeException("Cannot change id!");
        }

        Song patchedSong = null;

        try {
            patchedSong = objectMapper.updateValue(song, patchValues);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(songService.updateSong(patchedSong));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id){
        songService.deleteSong(id);

        return ResponseEntity.ok("Successfully deleted");
    }
}
