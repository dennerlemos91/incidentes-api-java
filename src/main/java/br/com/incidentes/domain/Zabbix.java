package br.com.incidentes.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "ZABBIX")
public class Zabbix implements Serializable {

    private static final long serialVersionUID = -2087761609769869902L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "severidade")
    private String severidade;

    @Column(name = "hora_data")
    private LocalDate horaDT;

    @Column(name = "hora_hora")
    private LocalTime horaHR;

    @Column(name = "tempoRecuperacao_data")
    private LocalDate tempoRecuperacaoDT;

    @Column(name = "tempoRecuperacao_hora")
    private LocalTime tempoRecuperacaoHR;

    @Column(name = "status")
    private String status;

    @Column(name = "host")
    private String host;

    @Column(name = "incidente")
    private String incidente;

    @Column(name = "duracao")
    private String duracao;

    @Column(name = "reconhecimento")
    private String reconhecimento;

    @Column(name = "acoes")
    private String acoes;

    @Column(name = "etiquetas")
    private String etiquetas;

}
