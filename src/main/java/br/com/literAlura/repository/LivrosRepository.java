package br.com.literAlura.repository;

import br.com.literAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivrosRepository extends JpaRepository <Livro, Long> {
    Optional<Livro> findByTitulo(String titulo);

    @Query("SELECT l FROM Livro l JOIN l.idioma i WHERE i = :idioma")
    List<Livro> findByIdioma(@Param("idioma") String idioma);
}
