package com.example.artistas_alura.repository;

import com.example.artistas_alura.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
