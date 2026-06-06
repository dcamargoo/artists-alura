package com.example.artistas_alura.service;

import com.example.artistas_alura.dto.SongDTO;
import com.example.artistas_alura.entity.Artist;
import com.example.artistas_alura.entity.Song;
import com.example.artistas_alura.repository.ArtistRepository;
import com.example.artistas_alura.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;

    public SongService(SongRepository songRepository, ArtistRepository artistRepository){
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }

    public void saveSong(SongDTO songDTO, Long artistId){

        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artista não encontrado"));

        Song song = new Song();
        song.setTitle(songDTO.title());
        song.setDuration(songDTO.duration());
        song.setArtist(artist);

        songRepository.save(song);
    }

    public List<SongDTO> getSongs(){
        return songRepository.findAll().stream()
                .map(s -> new SongDTO(s.getTitle(), s.getDuration()))
                .toList();
    }

    public SongDTO getSongById(Long id){
        return songRepository.findById(id)
                .map(s -> new SongDTO(s.getTitle(), s.getDuration()))
                .orElseThrow(() -> new RuntimeException("Música não encontrada"));
    }

    public List<SongDTO> getSongsByArtistId(Long artistId) {
        return songRepository.findByArtistId(artistId).stream()
                .map(s -> new SongDTO(s.getTitle(), s.getDuration()))
                .toList();
    }

    public List<SongDTO> getTop5LongestsSongs(){
        return songRepository.findTop5ByOrderByDurationDesc().stream()
                .map(s -> new SongDTO(s.getTitle(), s.getDuration()))
                .toList();
    }

}
