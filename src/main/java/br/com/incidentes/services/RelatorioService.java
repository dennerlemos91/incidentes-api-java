package br.com.incidentes.services;

import br.com.incidentes.domain.Zabbix;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import br.com.incidentes.domain.Otrs;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class RelatorioService {

    private OtrsService otrsService;
    private ZabbixService zabbixService;

    public void obterRelatorioPorDatas(LocalDate dataInicio, LocalDate dataFim) {
        List<Otrs> registrosOtrs = otrsService.obterRegistrosOtrsPorDatas(dataInicio, dataFim);
        List<Zabbix> registrosZabbix = zabbixService.obterRegistrosZabbixPorDatas(dataInicio, dataFim);

        AtomicReference<Integer> quantidateSucesso = new AtomicReference<>(0);
        AtomicReference<Integer> quantidate1minuto = new AtomicReference<>(0);
        AtomicReference<Integer> quantidate2minuto = new AtomicReference<>(0);
        AtomicReference<Integer> quantidate3minuto = new AtomicReference<>(0);
        AtomicReference<Integer> quantidate4minuto = new AtomicReference<>(0);
        AtomicReference<Integer> quantidate5minuto = new AtomicReference<>(0);
        AtomicReference<Integer> quantidateImprecisa= new AtomicReference<>(0);
        registrosZabbix.forEach(zabbix -> {
            for (Otrs otrs : registrosOtrs)
                if (otrs.getCriadoDT().equals(zabbix.getDtCriacao()) && otrs.getCriadoHR().getHour() == zabbix.getHrCriacao().getHour()) {
                    //Minutos Iguais
                    if (otrs.getCriadoHR().getMinute() == zabbix.getHrCriacao().getMinute()) {
                        quantidateSucesso.getAndSet(quantidateSucesso.get() + 1);
                        break;
                    } else if(zabbix.getHrCriacao().getMinute() - otrs.getCriadoHR().getMinute() == 1) {
                        quantidate1minuto.getAndSet(quantidate1minuto.get() + 1);
                        break;
                    } else if(zabbix.getHrCriacao().getMinute() - otrs.getCriadoHR().getMinute() == 2) {
                        quantidate2minuto.getAndSet(quantidate2minuto.get() + 1);
                        break;
                    }
                    else if(zabbix.getHrCriacao().getMinute() - otrs.getCriadoHR().getMinute() == 3) {
                        quantidate3minuto.getAndSet(quantidate3minuto.get() + 1);
                        break;
                    }
                    else if(zabbix.getHrCriacao().getMinute() - otrs.getCriadoHR().getMinute() == 4) {
                        quantidate4minuto.getAndSet(quantidate4minuto.get() + 1);
                        break;
                    }
                    else if(zabbix.getHrCriacao().getMinute() - otrs.getCriadoHR().getMinute() == 5) {
                        quantidate5minuto.getAndSet(quantidate5minuto.get() + 1);
                        break;
                    } else {
                        quantidateImprecisa.getAndSet(quantidateImprecisa.get() + 1);
                        break;
                    }
                }
        });

        System.out.println("Quantidade Sucesso: " + quantidateSucesso);
        System.out.println("Quantidade 1 minuto: "+ quantidate1minuto);
        System.out.println("Quantidade 2 minutos: "+ quantidate2minuto);
        System.out.println("Quantidade 3 minutos: "+ quantidate3minuto);
        System.out.println("Quantidade 4 minutos: "+ quantidate4minuto);
        System.out.println("Quantidade 5 minutos: "+ quantidate5minuto);
        System.out.println("Quantidade Imprecisa: "+ quantidateImprecisa);
        System.out.println("\n");
        System.out.println("Quantidade de chamdos OTRS: " + registrosOtrs.size());



    }
}
