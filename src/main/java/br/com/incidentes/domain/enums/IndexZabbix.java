package br.com.incidentes.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IndexZabbix {
    SEVERIDADE(0),
    HORA(1),
    TEMPO_RECUPERACAO(2),
    STATUS(3),
    HOST(4),
    INCIDENTE(5),
    DURACAO(6),
    RECONHECIDO(7),
    ACAO(8),
    ETIQUETAS(9);
//
    private final Integer codigo;
}
