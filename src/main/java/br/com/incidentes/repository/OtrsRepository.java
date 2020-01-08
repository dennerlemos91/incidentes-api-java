package br.com.incidentes.repository;

import br.com.incidentes.domain.Otrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtrsRepository extends JpaRepository<Otrs, Integer> {
}
