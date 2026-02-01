package top.blogapi.client.zing_mp3;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class Mp3Orchestrator {
    private final Mp3Service mp3Service;
    private final ExecutorService executorService;

    public Mp3Orchestrator(Mp3Service mp3Service) {
        this.mp3Service = mp3Service;
        this.executorService = Executors.newFixedThreadPool(4);
    }

    public Map<String, Object> getCompleteSongData(String songId) {
        try {
            CompletableFuture<Map<String,String>> infoFuture = CompletableFuture
                    .supplyAsync(() -> mp3Service.getSongInfo(songId), executorService);

            CompletableFuture<String> streamingFuture = CompletableFuture
                    .supplyAsync(() -> mp3Service.getSongStreaming(songId), executorService);

            CompletableFuture<List<String>> lyricFuture = CompletableFuture
                    .supplyAsync(() -> {
                        try {
                            return mp3Service.getLyric(songId);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }, executorService);

            CompletableFuture.allOf(infoFuture, streamingFuture, lyricFuture).join();

            return Map.of(
                    "info", infoFuture.get(),
                    "streaming", streamingFuture.get(),
                    "lyric", lyricFuture.get()
            );
        } catch (Exception e) {
            throw new RuntimeException("Lấy dữ liệu bài hát thất bại", e);
        }
    }
}