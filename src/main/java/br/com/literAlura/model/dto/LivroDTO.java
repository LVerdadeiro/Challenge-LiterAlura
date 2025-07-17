package br.com.literAlura.model.dto;

import br.com.literAlura.model.DadosAutor;

import java.util.List;

public record LivroDTO(String titulo, String idioma, Integer numeroDownloads, List<DadosAutor> autores) {
}
