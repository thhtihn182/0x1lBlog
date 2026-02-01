package top.blogapi.controller;



import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.blogapi.client.zing_mp3.Mp3Orchestrator;


import java.util.Map;

@RestController
@RequestMapping("/mp3")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Mp3Controller {
    Mp3Orchestrator songOrchestrator;

    public Mp3Controller(Mp3Orchestrator songOrchestrator) {
        this.songOrchestrator = songOrchestrator;
    }
    @GetMapping("/{songId}")
    public ResponseEntity<Map<String, Object>> getSong(@PathVariable String songId) {
        try {
            Map<String, Object> songData = songOrchestrator.getCompleteSongData(songId);
            return ResponseEntity.ok(songData);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}