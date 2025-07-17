package br.com.literAlura.principal;

import br.com.literAlura.model.*;
import br.com.literAlura.model.dto.LivroDTO;
import br.com.literAlura.repository.LivrosRepository;
import br.com.literAlura.repository.AutoresRepository;
import br.com.literAlura.service.ConsumoAPI;
import br.com.literAlura.service.ConverteDados;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?";

    private List<Livro> livros = new ArrayList<>();

    private Optional<Livro> livroBusca;

    private List<Autor> autores = new ArrayList<>();

    private final LivrosRepository repositorioLivro;
    private final AutoresRepository repositorioAutores;

    public Principal(LivrosRepository repositorioLivro, AutoresRepository repositorioAutores) {
        this.repositorioLivro = repositorioLivro;
        this.repositorioAutores = repositorioAutores;
    }

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
        System.out.print("Digite o título do livro: ");
        String titulo = leitura.nextLine();

        Optional<Livro> livroExistente = repositorioLivro.findByTitulo(titulo);
        if (livroExistente.isPresent()) {
            System.out.println("Livro já registrado:");
            System.out.println(livroExistente.get());
            return;
        }

        String url = ENDERECO + "search=" + titulo.replace(" ", "+");
        String json = consumo.obterDados(url);
        DadosResultado resultado = conversor.obterDados(json, DadosResultado.class);

        if (resultado.results().isEmpty()) {
            System.out.println("Livro não encontrado na API.");
            return;
        }

        DadosLivro dados = resultado.results().get(0);
        Livro livro = new Livro(dados);

        for (DadosAutor dadosAutor : dados.autores()) {
            Optional<Autor> autorExistente = repositorioAutores
                    .findByNomeContainingIgnoreCase(dadosAutor.nome())
                    .stream()
                    .findFirst();

            Autor autor = autorExistente.orElseGet(() -> {
                Autor novoAutor = new Autor();
                novoAutor.setNome(dadosAutor.nome());
                novoAutor.setAnoNascimento(dadosAutor.anoNascimento());
                novoAutor.setAnoFalecimento(dadosAutor.anoFalecimento());
                return repositorioAutores.save(novoAutor);
            });
            livro.getAutores().add(autor);
        }

        repositorioLivro.save(livro);
        System.out.println("Livro salvo com sucesso:");
        System.out.println(livro);
    }


    private void listarLivrosBuscados() {
        var livros = repositorioLivro.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado ainda.");
            return;
        }
        livros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        var autores = repositorioAutores.findAll();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado ainda.");
            return;
        }
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivosPorAno() {
        System.out.print("Digite o ano para pesquisar autores vivos: ");
        int ano = leitura.nextInt();
        leitura.nextLine(); // limpar buffer do Scanner

        var autoresVivos = repositorioAutores
                .findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanOrAnoFalecimentoIsNull(ano, ano);

        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor encontrado vivo neste ano.");
            return;
        }

        autoresVivos.forEach(autor -> {
            System.out.println("\nNome: " + autor.getNome());
            System.out.println("Ano de nascimento: " + autor.getAnoNascimento());
            System.out.println("Ano de falecimento: " + (autor.getAnoFalecimento() == null ? "Ainda vivo" : autor.getAnoFalecimento()));
        });
    }

    private void listarLivrosPorIdioma() {
        System.out.print("Digite o idioma (ex: en, pt, fr): ");
        var idioma = leitura.nextLine();

        var livrosPorIdioma = repositorioLivro.findByIdioma(idioma);

        if (livrosPorIdioma.isEmpty()) {
            System.out.println("Nenhum livro encontrado para esse idioma.");
            return;
        }

        var livrosDTO = livrosPorIdioma.stream()
                .map(livro -> new LivroDTO(
                        livro.getTitulo(),
                        livro.getIdioma(),
                        livro.getNumeroDownloads(),
                        livro.getSinopse(),
                        livro.getAutores().stream()
                                .map(Autor::getNome)
                                .toList()
                ))
                .toList();

        livrosDTO.forEach(System.out::println);
    }


}
