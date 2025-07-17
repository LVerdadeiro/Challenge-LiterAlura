package br.com.literAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("title") String titulo,
                         @JsonAlias("authors") Autor autor,
                         @JsonAlias("summaries") String sinopse,
                         @JsonAlias("languages") String idiomas,
                         @JsonAlias("download_count") Integer numeroDownloads) {
}
