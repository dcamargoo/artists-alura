package com.example.artistas_alura.service;

import com.example.artistas_alura.dto.ArtistDTO;
import com.example.artistas_alura.dto.SongDTO;
import com.example.artistas_alura.entity.Sex;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class MainService {

    private final ArtistService artistService;
    private final SongService songService;

    public MainService(ArtistService artistService, SongService songService){
        this.artistService = artistService;
        this.songService = songService;
    }

    // lógica da execução do programa
    public void execute(){

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("""
            
            Escolha uma das opções abaixo:
            
            1) Registrar um artista
            2) Registrar a música de um artista
            3) Buscar por um artista
            4) Buscar as músicas de um artista
            5) Buscar as músicas com maior duração
            0) Sair
            
            Opção: """
            );

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1 -> {
                    System.out.println("Insira o nome do artista: ");
                    String artistName = scanner.nextLine();
                    Sex artistSex = null;
                    System.out.println("""
                        Qual o sexo do artista: 
                        1 - Masculino
                        2 - Feminino
                        
                        Opção: """);
                    int optionSex = scanner.nextInt();
                    scanner.nextLine();
                    if(optionSex == 1) artistSex = Sex.MALE;
                    else artistSex = Sex.FEMALE;
                    ArtistDTO artistDTO = new ArtistDTO(artistName, artistSex);
                    artistService.saveArtist(artistDTO);
                    System.out.println("Artista salvo com sucesso!");
                }
                case 2 -> {
                    System.out.println("Insira o nome do artista dono da música: ");
                    String artistName = scanner.nextLine();
                    Optional<ArtistDTO> artistOptional = artistService.getArtistByName(artistName);
                    if (artistOptional.isPresent()) {
                        ArtistDTO artistDTO = artistOptional.get();
                        System.out.println("Insira o título da música: ");
                        String songTitle = scanner.nextLine();
                        System.out.println("Insira a duração da música (em segundos): ");
                        int songDuration = scanner.nextInt();
                        scanner.nextLine();
                        SongDTO songDTO = new SongDTO(songTitle, songDuration);
                        songService.saveSong(songDTO, artistDTO.id());
                        System.out.println("Música salva com sucesso!");
                    } else {
                        System.out.println("Artista não encontrado! Cadastre o artista primeiro.");
                    }
                }
                case 3 -> {
                    System.out.print("Insira o nome do artista: ");
                    String artistName = scanner.nextLine();
                    Optional<ArtistDTO> optionalArtistDTO = artistService.getArtistByName(artistName);
                    if(optionalArtistDTO.isPresent()){
                        ArtistDTO artistDTO = optionalArtistDTO.get();
                        System.out.println("""                              
                                Dados do Artista:
                                Nome: %s
                                Sexo: %s
                                Músicas: %s                               
                                """.formatted(artistDTO.name(), artistDTO.sex(), artistDTO.songs()));
                    }
                    else{
                        System.out.println("Artista '" + artistName + "' não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("Insira o nome do artista para ver as músicas: ");
                    String artistName = scanner.nextLine();
                    Optional<ArtistDTO> artistOptional = artistService.getArtistByName(artistName);
                    if (artistOptional.isPresent()) {
                        ArtistDTO artistDTO = artistOptional.get();
                        List<SongDTO> songs = songService.getSongsByArtistId(artistDTO.id());
                        if (songs.isEmpty()) {
                            System.out.println("Este artista ainda não tem músicas cadastradas.");
                        } else {
                            System.out.println("Músicas de " + artistDTO.name() + ":");
                            songs.forEach(s -> System.out.println("- %s (%d segundos)".formatted(s.title(), s.duration())));
                        }
                    } else {
                        System.out.println("Artista não encontrado.");
                    }
                }
                case 5 -> {
                    System.out.println("AS 5 MÚSICAS DE MAIOR DURAÇÃO");
                    List<SongDTO> topSongs = songService.getTop5LongestsSongs();
                    if (topSongs.isEmpty()) {
                        System.out.println("Nenhuma música cadastrada no sistema ainda.");
                    } else {
                        topSongs.stream()
                                .forEach(s -> System.out.println("Música: " + s.title() + " | Duração: " + s.duration()));
                    }
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }

        } while (option != 0);
    }

}
