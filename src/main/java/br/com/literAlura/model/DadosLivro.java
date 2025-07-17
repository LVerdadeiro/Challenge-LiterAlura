package br.com.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DadosAutor> autores,
        @JsonAlias("summaries") List<String> sinopse,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Integer numeroDownloads
) {}
