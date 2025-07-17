package br.com.literAlura.principal;

import br.com.literAlura.model.Autor;
import br.com.literAlura.model.Livro;
import br.com.literAlura.repository.LivrosRepository;
import br.com.literAlura.service.ConsumoAPI;
import br.com.literAlura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books?";

    private List<Livro> livros = new ArrayList<>();

    private Optional<Livro> livroBusca;

    private List<Autor> autores = new ArrayList<>();

 ;   //public Principal(LivrosRepository repositorio){this.repositorio = repositorio;}

    public void exibeMenu(){
        var opcao = -1;

        while(opcao != 0){
            var menu = """
                    |==[LITERALURA]==|
                    
                    Selecione uma opção abaixo:
                    1 - Buscar um livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    listarLivrosBuscados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void buscarLivro() {
    }

    private void listarLivrosBuscados() {
    }

    private void listarAutoresRegistrados() {
    }

    private void listarAutoresVivosPorAno() {
    }

    private void listarLivrosPorIdioma() {
    }

}
