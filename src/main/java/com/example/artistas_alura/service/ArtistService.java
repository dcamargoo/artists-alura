package com.example.artistas_alura.service;

import com.example.artistas_alura.dto.ArtistDTO;
import com.example.artistas_alura.dto.SongDTO;
import com.example.artistas_alura.entity.Artist;
import com.example.artistas_alura.repository.ArtistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public List<ArtistDTO> getAllArtists(){
        return artistRepository.findAll()
                .stream()
                .map(a -> {
                    List<SongDTO> songDTOs = a.getSongs().stream()
                            .map(s -> new SongDTO(s.getTitle(), s.getDuration()))
                            .toList();
                    return new ArtistDTO(a.getId(), a.getName(), a.getSex(), songDTOs);
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public ArtistDTO getArtistById(Long id){
        return artistRepository.findById(id)
                .map(a -> {
                    List<SongDTO> songDTOs = a.getSongs().stream()
                            .map(s -> new SongDTO(s.getTitle(), s.getDuration()))
                            .toList();
                    return new ArtistDTO(a.getId(), a.getName(), a.getSex(), songDTOs);
                })
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));
    }

    @Transactional(readOnly = true)
    public Optional<ArtistDTO> getArtistByName(String name){
        return artistRepository.findByNameIgnoreCase(name)
                .map(a -> {
                    List<SongDTO> songDTOs = a.getSongs().stream()
                            .map(s -> new SongDTO(s.getTitle(), s.getDuration()))
                            .toList();
                    return new ArtistDTO(a.getId(), a.getName(), a.getSex(), songDTOs);
                });
    }
}