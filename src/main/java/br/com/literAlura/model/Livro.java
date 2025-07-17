package br.com.literAlura.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Integer numeroDownloads;
    private String sinopse;

    @ManyToMany(mappedBy = "livros")
    private List<Autor> autores = new ArrayList<>();

    public Livro(){}

    public Livro(DadosLivro dadosLivro){
        this.titulo = dadosLivro.titulo();
        this.idioma = dadosLivro.idiomas();
        this.numeroDownloads = dadosLivro.numeroDownloads();
        this.sinopse = dadosLivro.sinopse();
    }
}
