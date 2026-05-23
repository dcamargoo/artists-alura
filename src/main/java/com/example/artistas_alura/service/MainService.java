package com.example.artistas_alura.service;

import org.springframework.stereotype.Service;

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
            
            Seja bem vindo(a)!
            
            Escolha uma das opções abaixo:
            
            1) Registrar um artista
            2) Registrar a música de um artista
            3) Buscar por um artista
            4) Buscar as músicas de um artista
            5) Buscar as músicas mais bem avaliadas
            0) Sair
            
            Opção: """
            );

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1 -> {}
                case 2 -> {}
                case 3 -> {}
                case 4 -> {}
                case 5 -> {}
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }

        } while (option != 0);
    }

}
