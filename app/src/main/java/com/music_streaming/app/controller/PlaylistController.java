package com.music_streaming.app.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.music_streaming.app.entity.Playlist;
import com.music_streaming.app.service.PlaylistService;
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
@RequestMapping("/api/playlists")
public class PlaylistController {

    private PlaylistService playlistService;
    private ObjectMapper objectMapper;

    @Autowired
    public PlaylistController(PlaylistService playlistService, ObjectMapper objectMapper){
        this.playlistService = playlistService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<Playlist> addPlayist(@RequestBody Playlist playlist){
        System.out.println(playlist);
        Playlist savedPlaylist = playlistService.addPlaylist(playlist);

        return new ResponseEntity<>(savedPlaylist, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Playlist> updatePlaylistPatch(@PathVariable Long id, @RequestBody Map<String, Object> patchPayload){
        Playlist currentPlaylist = playlistService.findById(id);

        if(currentPlaylist == null){
            throw new RuntimeException("Doesnt exist!");
        }

        if(patchPayload.containsKey("id")){
            throw new RuntimeException("Cannot change id!");
        }

        Playlist patchedPlaylist = null;
        try {
            patchedPlaylist = objectMapper.updateValue(currentPlaylist, patchPayload);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        }
        Playlist playlist = playlistService.addPlaylist(patchedPlaylist);

        return ResponseEntity.ok(playlist);
    }

    @PutMapping()
    public ResponseEntity<Playlist> updatePlaylistPut(@RequestBody Playlist playlist){
        Playlist savedPlaylist = playlistService.updatePlaylist(playlist);

        return ResponseEntity.ok(savedPlaylist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlaylist(@PathVariable Long id){
        playlistService.deletePlaylist(id);

        return ResponseEntity.ok("Successfully deleted!");
    }

    @GetMapping
    public ResponseEntity<?> getPlaylists(@RequestParam (required = false) Integer pageNumber,
                                          @RequestParam (required = false) Integer pageSize,
                                          @RequestParam (required = false) String sortBy,
                                          @RequestParam (required = false) String sortDir){
        if(pageNumber != null && pageSize != null){
            Pageable pageable;
            if(sortBy != null && sortDir != null){
                Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
                pageable = PageRequest.of(pageNumber, pageSize, sort);
            }else{
                pageable = PageRequest.of(pageNumber, pageSize);
            }
            Page<Playlist> playlists = playlistService.getPlaylistsPagination(pageable);
            return ResponseEntity.ok(playlists);
        }else{
            List<Playlist> playlists = playlistService.getAllPlaylists();
            return ResponseEntity.ok(playlists);
        }
    }
}
