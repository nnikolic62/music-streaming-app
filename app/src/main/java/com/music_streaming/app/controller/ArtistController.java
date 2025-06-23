package com.music_streaming.app.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.music_streaming.app.entity.Artist;
import com.music_streaming.app.service.ArtistService;
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
@RequestMapping("/api/artist")
public class ArtistController {

    private ArtistService artistService;
    private ObjectMapper objectMapper;

    @Autowired
    public ArtistController(ArtistService artistService, ObjectMapper objectMapper){
        this.artistService = artistService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAllArtists(@RequestParam(required = false) Integer pageNubmer,
                                           @RequestParam(required = false) Integer pageSize,
                                           @RequestParam(required = false) String sortBy,
                                           @RequestParam(required = false) String sortDir){
        if(pageNubmer != null && pageSize != null){
            Pageable pageable;
            if(sortBy != null && sortDir != null){
                Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
                pageable = PageRequest.of(pageNubmer, pageSize, sort);
            }else{
                pageable = PageRequest.of(pageNubmer, pageSize);
            }
            Page<Artist> artists = artistService.getAllArtist(pageable);
            return ResponseEntity.ok(artists);
        }else{
            List<Artist> artists = artistService.getAllArtist();
            return ResponseEntity.ok(artists);
        }
    }

    @PostMapping
    public ResponseEntity<Artist> addArtist(@RequestBody Artist artist){
        Artist savedArtist = artistService.addArtist(artist);

        return new ResponseEntity<>(savedArtist, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Artist> updateArtistPut(@RequestBody Artist artist){
        Artist updatedArtist = artistService.updateArtist(artist);

        return ResponseEntity.ok(updatedArtist);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> findById(@PathVariable Long id){
        Artist artist = artistService.findArtistById(id);

        return ResponseEntity.ok(artist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArtist(@PathVariable Long id){
        artistService.deleteArtist(id);

        return ResponseEntity.ok("Successfully deleted!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Artist> updateArticlePatch(@PathVariable Long id, @RequestBody Map<String, Object> patchPayload){
        Artist artist = artistService.findArtistById(id);

        if(artist == null){
            throw new RuntimeException();
        }

        if(patchPayload.containsKey("id")){
            throw new RuntimeException();
        }

        Artist patchedArtist = null;

        try {
            patchedArtist = objectMapper.updateValue(artist, patchPayload);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(artistService.updateArtist(patchedArtist));
    }
}
