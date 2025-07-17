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
    @ElementCollection
    private List<String> idioma;
    private Integer numeroDownloads;
    @Column(columnDefinition = "TEXT")
    private String sinopse;

    @ManyToMany(mappedBy = "livros")
    private List<Autor> autores = new ArrayList<>();

    public Livro(){}

    public Livro(DadosLivro dadosLivro){
        this.titulo = dadosLivro.titulo();
        this.idioma = dadosLivro.idiomas();
        this.numeroDownloads = dadosLivro.numeroDownloads();
        this.sinopse = String.join("\n", dadosLivro.sinopse());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idioma='" + idioma + '\'' +
                ", numeroDownloads=" + numeroDownloads +
                ", sinopse='" + sinopse + '\'' +
                ", autores=" + autores +
                '}';
    }
}
