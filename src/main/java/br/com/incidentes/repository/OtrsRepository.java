package br.com.incidentes.repository;

import br.com.incidentes.domain.Otrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OtrsRepository extends JpaRepository<Otrs, Integer> {
    @Query(value = "from Otrs o where o.criadoDT BETWEEN :dataInicio AND :dataFim")
    List<Otrs> getAllBetweenDates(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}
