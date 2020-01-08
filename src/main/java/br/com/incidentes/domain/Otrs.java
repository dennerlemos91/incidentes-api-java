package br.com.incidentes.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@Table(name = "OTRS")
public class Otrs implements Serializable {

    private static final long serialVersionUID = -2621129987063697044L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero")
    private String numero;

    @Column(name = "idade")
    private String idade;

    @Column(name = "criadoDT")
    private String criadoDT;

    @Column(name = "criadoHR")
    private String criadoHR;

    @Column(name = "fechado")
    private String fechado;

    @Column(name = "primeiroBloqueio")
    private String primeiroBloqueio;

    @Column(name = "primeiraResposta")
    private String primeiraResposta;

    @Column(name = "estado")
    private String estado;

    @Column(name = "prioridade")
    private String prioridade;

    @Column(name = "fila")
    private String fila;

    @Column(name = "bloquear")
    private String bloquear;

    @Column(name = "proprietario")
    private String proprietario;

    @Column(name = "primeiroNome")
    private String primeiroNome;

    @Column(name = "ultimoNome")
    private String ultimoNome;

    @Column(name = "idDoCliente")
    private String idDoCliente;

    @Column(name = "nomeDoCliente")
    private String nomeDoCliente;

    @Column(name = "de")
    private String de;

    @Column(name = "assunto")
    private String assunto;

    @Column(name = "tempoContabilizado")
    private String tempoContabilizado;

    @Column(name = "arvoreDoArtigo")
    private String arvoreDoArtigo;

    @Column(name = "TSminutos")
    private String TSminutos;

    @Column(name = "DTSminutos")
    private String DTSminutos;

    @Column(name = "PRminutos")
    private String PRminutos;

    @Column(name = "DTPRminutos")
    private String DTPRminutos;
}
