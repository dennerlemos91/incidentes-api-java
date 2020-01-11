package br.com.incidentes.services;

import br.com.incidentes.domain.Zabbix;
import br.com.incidentes.domain.enums.IndexZabbix;
import br.com.incidentes.repository.ZabbixRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ZabbixService {

    private ZabbixRepository zabbixRepository;

    @Transactional
    public void importar(MultipartFile arquivo) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(arquivo.getInputStream()));
        List<Zabbix> itensZabbixes = br.lines().skip(1)
                .map(this::mapToItem)
                .collect(Collectors.toList());
        zabbixRepository.saveAll(itensZabbixes);
    }



    private Zabbix mapToItem(String line) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        List<String> itenslinha = Arrays.stream(
                line.replace("\"", "")
                        .split(","))
                .collect(Collectors.toList());

        Zabbix.ZabbixBuilder builder = Zabbix.builder();
        for (int index = 0; index < itenslinha.size(); index++) {
            String valor = itenslinha.get(index);
            if (index == IndexZabbix.SEVERIDADE.getCodigo()) {
                builder.severidade(valor);
            } else if( index == IndexZabbix.HORA.getCodigo()) {
                LocalDateTime dateTime = LocalDateTime.parse(valor, formatter);
                builder.dtCriacao(dateTime.toLocalDate());
                builder.hrCriacao(dateTime.toLocalTime());
            } else if(index == IndexZabbix.TEMPO_RECUPERACAO.getCodigo()) {
                LocalDateTime dateTime = LocalDateTime.parse(valor, formatter);
                builder.tempoRecuperacaoDT(dateTime.toLocalDate());
                builder.tempoRecuperacaoHR(dateTime.toLocalTime());
            } else if(index == IndexZabbix.STATUS.getCodigo()) {
                builder.status(valor);
            } else if(index == IndexZabbix.HOST.getCodigo()) {
                builder.host(valor);
            } else if(index == IndexZabbix.INCIDENTE.getCodigo()) {
                builder.incidente(valor);
            } else if(index == IndexZabbix.DURACAO.getCodigo()) {
                builder.duracao(valor);
            } else if(index == IndexZabbix.RECONHECIDO.getCodigo()) {
                builder.reconhecimento(valor);
            } else if(index == IndexZabbix.ACAO.getCodigo()) {
                builder.acoes(valor);
            } else {
                builder.etiquetas(valor);
            }
        }
        return builder.build();
    }

    public List<Zabbix> obterRegistrosZabbixPorDatas(LocalDate dataInicio, LocalDate dataFim) {
        return zabbixRepository.getAllBetweenDates(dataInicio, dataFim);
    }
}