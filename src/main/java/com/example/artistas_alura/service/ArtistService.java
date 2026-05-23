package com.example.artistas_alura.service;

import com.example.artistas_alura.dto.ArtistDTO;
import com.example.artistas_alura.entity.Artist;
import com.example.artistas_alura.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository){
        this.artistRepository = artistRepository;
    }

    public void saveArtist(ArtistDTO artistDTO){
        Artist artist = new Artist();
        artist.setName(artistDTO.name());
        artist.setSex(artistDTO.sex());
        artistRepository.save(artist);
    }

    public List<ArtistDTO> getAllArtists(){
        return artistRepository.findAll()
                .stream()
                .map(a -> new ArtistDTO(a.getName(), a.getSex()))
                .toList();
    }

    public ArtistDTO getArtistById(Long id){
        return artistRepository.findById(id)
                .map(a -> new ArtistDTO(a.getName(), a.getSex()))
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));
    }
}
