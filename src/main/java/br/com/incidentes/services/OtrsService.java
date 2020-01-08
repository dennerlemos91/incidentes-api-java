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
import java.time.LocalDateTime;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        List<String> itenslinha = Arrays.stream(
                line.replace("\"", "")
                        .split(";"))
                .collect(Collectors.toList());

        Otrs.OtrsBuilder builder = Otrs.builder();
        for (int index = 0; index < itenslinha.size(); index++) {
            String valor = itenslinha.get(index);
            if (index == IndexOtrs.NUMERO.getCodigo()) {
                builder.numero(valor);
            } else if( index == IndexOtrs.IDADE.getCodigo()) {
                builder.idade(valor);
            }else if( index == IndexOtrs.CRIADODT.getCodigo()) {
                builder.criadoDT(valor.substring(0, 10));
            }else if( index == IndexOtrs.CRIADOHR.getCodigo()) {
                builder.criadoHR(valor.substring(11, 15));
            }else if( index == IndexOtrs.FECHADO.getCodigo()) {
                builder.fechado(valor);
            }else if( index == IndexOtrs.PRIMEIRO_BLOQUEIO.getCodigo()) {
                builder.primeiroBloqueio(valor);
            }else if( index == IndexOtrs.PRIMEIRA_RESPOSTA.getCodigo()) {
                builder.primeiraResposta(valor);
            }else if( index == IndexOtrs.ESTADO.getCodigo()) {
                builder.estado(valor);
            }else if( index == IndexOtrs.PRIORIDADE.getCodigo()) {
                builder.prioridade(valor);
            }else if( index == IndexOtrs.FILA.getCodigo()) {
                builder.fila(valor);
            }else if( index == IndexOtrs.BLOQUEAR.getCodigo()) {
                builder.bloquear(valor);
            }else if( index == IndexOtrs.PROPRIETARIO.getCodigo()) {
                builder.proprietario(valor);
            }else if( index == IndexOtrs.PRIMEIRO_NOME.getCodigo()) {
                builder.primeiroNome(valor);
            }else if( index == IndexOtrs.ULTIMO_NOME.getCodigo()) {
                builder.ultimoNome(valor);
            }else if( index == IndexOtrs.ID_DO_CLIENTE.getCodigo()) {
                builder.idDoCliente(valor);
            }else if( index == IndexOtrs.NOME_DO_CLIENTE.getCodigo()) {
                builder.nomeDoCliente(valor);
            }else if( index == IndexOtrs.DE.getCodigo()) {
                builder.de(valor);
            }else if( index == IndexOtrs.ASSUNTO.getCodigo()) {
                builder.assunto(valor);
            }else if( index == IndexOtrs.TEMPO_CONTABILIZADO.getCodigo()) {
                builder.tempoContabilizado(valor);
            }else if( index == IndexOtrs.ARVORE_DO_ARTIGO.getCodigo()) {
                builder.arvoreDoArtigo(valor);
            }else if( index == IndexOtrs.TS_MINUTOS.getCodigo()) {
                builder.TSminutos(valor);
            }else if( index == IndexOtrs.PR_MINUTOS.getCodigo()) {
                builder.PRminutos(valor);
            }else {
                builder.DTPRminutos(valor);
            }
        }
        return builder.build();
    }
}