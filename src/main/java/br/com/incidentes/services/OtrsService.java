package br.com.incidentes.services;

import br.com.incidentes.domain.Otrs;
import br.com.incidentes.domain.enums.IndexOtrs;
import br.com.incidentes.repository.OtrsRepository;
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
public class OtrsService {

    private OtrsRepository otrsRepository;

    @Transactional
    public void importar(MultipartFile arquivo) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(arquivo.getInputStream()));
        List<Otrs> itensOtrs = br.lines().skip(1)
                .map(this::mapToItem)
                .collect(Collectors.toList());
        otrsRepository.saveAll(itensOtrs);
    }

    private Otrs mapToItem(String line) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<String> itenslinha = Arrays.stream(
                line.replace("\"", "")
                        .split(";"))
                .collect(Collectors.toList());
        return Otrs.builder()
                .numero(itenslinha.get(IndexOtrs.NUMERO.getCodigo()))
                .idade(itenslinha.get(IndexOtrs.IDADE.getCodigo()))
                .criadoDT(obterData(itenslinha.get(IndexOtrs.DATA_CRIACAO.getCodigo()), formatter))
                .criadoHR(obterHora(itenslinha.get(IndexOtrs.DATA_CRIACAO.getCodigo()), formatter))
                .fechadoDT(obterData(itenslinha.get(IndexOtrs.FECHADO.getCodigo()), formatter))
                .fechadoHR(obterHora(itenslinha.get(IndexOtrs.FECHADO.getCodigo()), formatter))
                .primeiroBloqueio(itenslinha.get(IndexOtrs.PRIMEIRO_BLOQUEIO.getCodigo()))
                .primeiraResposta(itenslinha.get(IndexOtrs.PRIMEIRA_RESPOSTA.getCodigo()))
                .estado(itenslinha.get(IndexOtrs.ESTADO.getCodigo()))
                .prioridade(itenslinha.get(IndexOtrs.PRIORIDADE.getCodigo()))
                .fila(itenslinha.get(IndexOtrs.FILA.getCodigo()))
                .bloquear(itenslinha.get(IndexOtrs.BLOQUEAR.getCodigo()))
                .proprietario(itenslinha.get(IndexOtrs.PROPRIETARIO.getCodigo()))
                .primeiroNome(itenslinha.get(IndexOtrs.PRIMEIRO_NOME.getCodigo()))
                .ultimoNome(itenslinha.get(IndexOtrs.ULTIMO_NOME.getCodigo()))
                .nomeDoCliente(itenslinha.get(IndexOtrs.NOME_DO_CLIENTE.getCodigo()))
                .de(itenslinha.get(IndexOtrs.DE.getCodigo()))
                .assunto(itenslinha.get(IndexOtrs.ASSUNTO.getCodigo()))
                .tempoContabilizado(itenslinha.get(IndexOtrs.TEMPO_CONTABILIZADO.getCodigo()))
                .arvoreDoArtigo(itenslinha.get(IndexOtrs.ARVORE_DO_ARTIGO.getCodigo()))
                .TSminutos(itenslinha.get(IndexOtrs.TS_MINUTOS.getCodigo()))
                .PRminutos(itenslinha.get(IndexOtrs.PR_MINUTOS.getCodigo()))
                .DTPRminutos(itenslinha.get(IndexOtrs.DTPR_MINUTOS.getCodigo()))
                .build();
    }

    private LocalDate obterData(String data, DateTimeFormatter formatter) {
        return LocalDateTime.parse(data, formatter).toLocalDate();
    }

    private LocalTime obterHora(String data, DateTimeFormatter formatter) {
        return LocalDateTime.parse(data, formatter).toLocalTime();
    }


    public List<Otrs> obterRegistrosOtrsPorDatas(LocalDate dataInicio, LocalDate dataFim) {
        return otrsRepository.getAllBetweenDates(dataInicio, dataFim);
    }
}