package com.example.artistas_alura.repository;

import com.example.artistas_alura.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByArtistId(Long artistId);
    List<Song> findTop5ByOrderByDurationDesc();
}
