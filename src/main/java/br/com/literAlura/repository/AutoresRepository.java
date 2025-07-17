package br.com.literAlura.repository;

import br.com.literAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutoresRepository extends JpaRepository <Autor, Long> {
    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanOrAnoFalecimentoIsNull(Integer ano1, Integer ano2);

    List<Autor> findByNomeContainingIgnoreCase(String nome);
}
