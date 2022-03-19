package com.example.service;

import com.example.controllers.dto.CreateTrackDto;
import com.example.model.Album;
import com.example.model.Author;
import com.example.model.Track;
import com.example.utils.JsonUtil;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Slf4j
public class TrackService {

    private final List<Track> tracks = new ArrayList<>();

    public TrackService() {
        init();
    }

    private void init() {
        Thread thread = new Thread(() -> {
            try {
                tracks.addAll(JsonUtil.parseJson("/data/tracks.json", new TypeToken<Collection<Track>>() {}));
                log.info(String.format("Got %d tracks", tracks.size()));
            } catch (IOException e) {
                log.error("Couldn't parse json!", e);
            }
        });
        thread.start();
    }

    public Collection<Track> list() {
        return tracks;
    }

    public void add(CreateTrackDto dto) {
        var track = Track.builder()
                         .id(UUID.randomUUID())
                         .name(dto.getName())
                         .album(Album.builder()
                                     .name(dto.getAlbumTitle())
                                     .author(Author.builder().build())
                                     .build())
                         .build();
        tracks.add(track);
    }

    public void save() {
        Thread thread = new Thread(() -> {
            try {
                JsonUtil.writeJson("/data/tracks.json", tracks);
                log.info(String.format("%d tracks saved", tracks.size()));
            } catch (IOException e) {
                log.error("Couldn't save json!", e);
            }
        });
        thread.start();
    }

}
