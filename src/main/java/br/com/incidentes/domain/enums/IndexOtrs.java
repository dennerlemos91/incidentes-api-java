package br.com.incidentes.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IndexOtrs {
    NUMERO(1),
    IDADE(2),
    CRIADODT(3),
    CRIADOHR(4),
    FECHADO(5),
    PRIMEIRO_BLOQUEIO(6),
    PRIMEIRA_RESPOSTA(7),
    ESTADO(8),
    PRIORIDADE(9),
    FILA(10),
    BLOQUEAR(11),
    PROPRIETARIO(12),
    PRIMEIRO_NOME(13),
    ULTIMO_NOME(14),
    ID_DO_CLIENTE(15),
    NOME_DO_CLIENTE(16),
    DE(17),
    ASSUNTO(18),
    TEMPO_CONTABILIZADO(19),
    ARVORE_DO_ARTIGO(20),
    TS_MINUTOS(21),
    PR_MINUTOS(22),
    DTPR_MINUTOS(23);

    private final Integer codigo;
}
