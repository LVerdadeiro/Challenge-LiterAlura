package br.com.literAlura.model.dto;

import java.util.List;

public record LivroDTO(String titulo, List<String> idiomas, Integer numeroDownloads, String sinopse, List<String> autores) {

    @Override
    public String toString() {
        return "\nTítulo: " + titulo +
                "\nIdiomas: " + idiomas +
                "\nDownloads: " + numeroDownloads +
                "\nSinopse: " + sinopse +
                "\nAutores: " + autores + "\n";
    }
}
