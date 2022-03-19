package com.example.controllers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateTrackDto {
    String name;
    String genreTitle;
    String albumTitle;
    String authorName;
}
