package top.blogapi.client.zing_mp3;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class Mp3Service {

    ZingMp3Client zingMp3Client;

    public void setConfigFromDb(Map<String, Map<String, Object>> configMap) {
        zingMp3Client.setConfig(configMap);
    }


    public boolean isConfigured() {
        return zingMp3Client.hasConfig();
    }

    public Map<String, String> getSongInfo(String songId) {
        JsonNode info = zingMp3Client.getSongInfo(songId);
        return Map.of(
                "title", info.at("/data/title").asText(),
                "name", info.at("/data/artists/0/name").asText(),
                "thumbnail", info.at("/data/thumbnail").asText()
        );
    }

    public String getSongStreaming(String songId) {
        return zingMp3Client.getSongStreaming(songId).at("/data/128").asText();
    }

    public List<String> getLyric(String songId) throws Exception {
        JsonNode lyric = zingMp3Client.getLyric(songId);
        return readLRC(lyric.at("/data/file").asText());
    }

    private List<String> readLRC(String lrcUrl) throws Exception {
        List<String> lrcList = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(lrcUrl))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

        if (response.statusCode() == 200) {
            String content = response.body();
            String[] lines = content.split("\\r?\\n");
            lrcList.addAll(Arrays.asList(lines));
        }

        return lrcList;
    }
}