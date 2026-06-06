package com.example.artistas_alura.dto;

import com.example.artistas_alura.entity.Sex;

import java.util.List;

public record ArtistDTO(Long id, String name, Sex sex, List<SongDTO> songs) {

    public ArtistDTO(String name, Sex sex) {
        this(null, name, sex, List.of());
    }
}
