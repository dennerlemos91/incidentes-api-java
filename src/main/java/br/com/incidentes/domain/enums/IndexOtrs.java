package br.com.incidentes.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IndexOtrs {
    NUMERO(0),
    IDADE(1),
    DATA_CRIACAO(2),
    FECHADO(3),
    PRIMEIRO_BLOQUEIO(4),
    PRIMEIRA_RESPOSTA(5),
    ESTADO(6),
    PRIORIDADE(7),
    FILA(8),
    BLOQUEAR(9),
    PROPRIETARIO(10),
    PRIMEIRO_NOME(11),
    ULTIMO_NOME(12),
    ID_DO_CLIENTE(13),
    NOME_DO_CLIENTE(14),
    DE(15),
    ASSUNTO(16),
    TEMPO_CONTABILIZADO(17),
    ARVORE_DO_ARTIGO(18),
    TS_MINUTOS(19),
    PR_MINUTOS(20),
    DTPR_MINUTOS(21);

    private final Integer codigo;
}
