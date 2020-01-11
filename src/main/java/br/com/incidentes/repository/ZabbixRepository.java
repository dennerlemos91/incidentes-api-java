package br.com.incidentes.repository;

import br.com.incidentes.domain.Otrs;
import br.com.incidentes.domain.Zabbix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ZabbixRepository extends JpaRepository<Zabbix, Integer> {
    @Query(value = "from Zabbix z where z.dtCriacao BETWEEN :dataInicio AND :dataFim")
    List<Zabbix> getAllBetweenDates(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}
