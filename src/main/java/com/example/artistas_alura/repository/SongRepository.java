package com.example.artistas_alura.repository;

import com.example.artistas_alura.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
