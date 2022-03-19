package com.example.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Track {
    UUID id;
    String name;
    Genre genre;
    Album album;
}
