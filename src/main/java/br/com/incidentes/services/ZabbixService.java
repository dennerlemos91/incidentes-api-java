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
import java.time.LocalTime;
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

        return Zabbix.builder()
                .severidade(itenslinha.get(IndexZabbix.SEVERIDADE.getCodigo()))
                .dtCriacao(obterData(itenslinha.get(IndexZabbix.HORA.getCodigo()), formatter))
                .hrCriacao(obterHora(itenslinha.get(IndexZabbix.HORA.getCodigo()), formatter))
                .tempoRecuperacaoDT(obterData(itenslinha.get(IndexZabbix.TEMPO_RECUPERACAO.getCodigo()), formatter))
                .tempoRecuperacaoHR(obterHora(itenslinha.get(IndexZabbix.TEMPO_RECUPERACAO.getCodigo()), formatter))
                .status(itenslinha.get(IndexZabbix.STATUS.getCodigo()))
                .host(itenslinha.get(IndexZabbix.HOST.getCodigo()))
                .incidente(itenslinha.get(IndexZabbix.INCIDENTE.getCodigo()))
                .duracao(itenslinha.get(IndexZabbix.DURACAO.getCodigo()))
                .reconhecimento(itenslinha.get(IndexZabbix.RECONHECIDO.getCodigo()))
                .acoes(itenslinha.get(IndexZabbix.ACAO.getCodigo()))
                .etiquetas(itenslinha.get(IndexZabbix.ETIQUETAS.getCodigo()))
                .build();

    }

    private LocalDate obterData(String data, DateTimeFormatter formatter) {
        return LocalDateTime.parse(data, formatter).toLocalDate();
    }

    private LocalTime obterHora(String data, DateTimeFormatter formatter) {
        return LocalDateTime.parse(data, formatter).toLocalTime();
    }

    public List<Zabbix> obterRegistrosZabbixPorDatas(LocalDate dataInicio, LocalDate dataFim) {
        return zabbixRepository.getAllBetweenDates(dataInicio, dataFim);
    }
}