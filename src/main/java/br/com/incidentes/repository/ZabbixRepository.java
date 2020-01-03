package br.com.incidentes.repository;

import br.com.incidentes.domain.Zabbix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZabbixRepository extends JpaRepository<Zabbix, Integer> {
}
